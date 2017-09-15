/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlets;

import BLO.UniversityBLO;
import com.entities.University;
import com.utilities.ConstantManager;
import com.utilities.Utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.eclipse.persistence.internal.oxm.ByteArraySource;

/**
 *
 * @author Temporary
 */
@WebServlet(name = "PDFProcess", urlPatterns = {"/PDFProcess"})
public class PDFProcess extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
        String url = ConstantManager.errorPage;
        ByteArrayOutputStream baos = null;
        String uniCode = request.getParameter("uniCode");
        try {
            HttpSession sessionScope = request.getSession();
            University uni = (University) sessionScope.getAttribute("SelectedUniversity");
            
            if (!uni.getCode().equalsIgnoreCase(uniCode)) {
                UniversityBLO blo = new UniversityBLO();
                uni = blo.findInfoByCode(uniCode);
            }
            
            String path = getServletContext().getRealPath("/");
            String xslPath = path + "WEB-INF\\uniInfoFO.xsl";
            String xmlPath = path + "WEB-INF\\uniInfoFO.xml";
            String htmlPath = path + "WEB-INF\\uniInfoFO.html";
            File xmlFile = new File(xmlPath);
            Utils.marshallerToFile(uni, xmlFile);
            StreamSource instruction = new StreamSource(xslPath);
            StreamSource xmlSource = new StreamSource(xmlFile);
            
            File htmlFile = new File(htmlPath);

//            String foPath = path + "WEB-INF/uniInfoFO.fo";
//            File instruction = new File(foPath);
//            fis = new FileInputStream(instruction);
            methodTrAX(instruction, xmlSource, new FileOutputStream(htmlFile));
            
            baos = new ByteArrayOutputStream();
            
            Utils.TransformToPDF(new StreamSource(htmlFile), baos, path);
            
            byte[] content = baos.toByteArray();
            response.setContentType("application/pdf");
            response.getOutputStream().write(content);
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
//            out.close();
        }
    }
    
    private void methodTrAX(Source instruction, Source xml, OutputStream output) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer(instruction);
            
            StreamResult htmlFile = new StreamResult(output);
            transformer.transform(xml, htmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
