/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilities;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
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
    
    public static <T> void marshallerToFile(T object) {
        try {
            JAXBContext jaxb = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxb.createMarshaller();
            
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            
            marshaller.marshal(object, new File("university.xml"));
        } catch (JAXBException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static String getHtmlAttributeValue(String input, String attrName) {
        int openDitto = 0;
        int closeDitto = 0;
        boolean getDittoBefore = false;
        int beginIndex = input.indexOf(attrName);
        for (int i = beginIndex; i < input.length(); i++) {
            if (input.charAt(i) == '\"') {
                if (!getDittoBefore) {
                    openDitto = i;
                    getDittoBefore = true;
                } else {
                    closeDitto = i;
                    break;
                }
            }
        }
        return input.substring(openDitto + 1, closeDitto);
    }
    
    public static String getHtmlTextValue(String input, String tagName) {
        int begin = 0;
        int tmp = input.indexOf("<" + tagName);
        for (int i = tmp; i < input.length(); i++) {
            if (input.charAt(i) == '>') {
                begin = i + 1;
                break;
            }
        }
        int end = input.indexOf("</" + tagName);
        return input.substring(begin, end).trim();
    }
    
    public static String normalizeHTMLString(String input) {
        if (input.contains("&#40;")) {
            input = input.replaceAll("&#40;", "(");
        }
        if (input.contains("&#41;")) {
            input = input.replaceAll("&#41;", ")");
        }
        if (input.contains("&amp;")) {
            input = input.replaceAll("&amp;", "&");
        }
        return input;
    }
    
    public static String removeTabCharacters(String input) {
        return input.replaceAll("\\t", "");
    }
    
    public static <T> Integer getIndexOf(List<T> list, T object) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(object)) {
                return i;
            }
        }
        return null;
    }
}
