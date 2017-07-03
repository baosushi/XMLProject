/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilities;

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
import javax.xml.transform.TransformerFactory;
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
        } catch (JAXBException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
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
