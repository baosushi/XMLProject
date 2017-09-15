/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Temporary
 */
public class Utils {

    public static void Transform(Source source, Source instruction, Result result) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Templates template = tf.newTemplates(instruction);
            Transformer trans = template.newTransformer();
            trans.transform(source, result);
            System.out.println("Transformation completed.");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void TransformToPDF(Source instruction, OutputStream pdfFile, String pathFile) {
        try {
            FopFactory ff = FopFactory.newInstance();
            FOUserAgent fua = ff.newFOUserAgent();
            Fop fop = ff.newFop(MimeConstants.MIME_PDF, fua, pdfFile);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setParameter("pathFile", pathFile);
            Result result = new SAXResult(fop.getDefaultHandler());
            transformer.transform(instruction, result);
        } catch (FOPException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Document parseDOMFromFile(String fileXMLPath)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(fileXMLPath);

        return doc;
    }

    public static <T> void marshallerToTransfer(T object, OutputStream os) {
        try {
            JAXBContext jaxb = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxb.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(object, os);
        } catch (JAXBException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static <T> String marshallerToString(T object) {
        try {
            JAXBContext jaxb = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxb.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            StringWriter sw = new StringWriter();
            marshaller.marshal(object, sw);

            return sw.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static <T> void marshallerToFile(T object, File file) {
        try {
            JAXBContext jaxb = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxb.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(object, file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String removeBrackets(String input) {
        while (input.contains("<")) {
            for (int i = input.length() - 1; i >= 0; i--) {
                if (input.charAt(i) == '>') {
                    int closeBracket = i + 1;
                    for (int j = i; j >= 0; j--) {
                        if (input.charAt(j) == '<') {
                            int openBracket = j;
                            String subString = input.substring(openBracket, closeBracket);
                            input = input.replace(subString, "");
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return input;
    }
}
