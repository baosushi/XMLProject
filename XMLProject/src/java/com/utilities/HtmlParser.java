package com.utilities;

import java.io.BufferedReader;
import javax.xml.stream.XMLStreamException;
import jaxb.classes.ListBlock;

public class HtmlParser {

    private ListBlock listBlock;

    public HtmlParser() {
        this.listBlock = new ListBlock();
    }

    public void parseData(String url) {
//        String url = ConstantManager.khoidaihoc_path;
//        StAXParser.parseXMLBlockData(downnloadFilePath, this.listBlock);
        String document = parseBlockData(url);
        try {
            StAXParser.parseXMLBlockData(document, listBlock);
            
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
    }

    public String parseBlockData(String url) {
        String doc = "";
        try {
            BufferedReader bReader = BufferedReaderProvider.getBufferedReader(url);
            
            String line;
            boolean inCorrectLine = false;

            doc += "<root>";
            while ((line = bReader.readLine()) != null) {
                if (inCorrectLine) {
                    doc += line;
                }
                if (line.contains("</table>")) {
                    doc += "</root>";
                    break;
                }
                if (line.contains("<table")) {
                    doc += line.substring(line.indexOf("<table"));
                    inCorrectLine = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(doc);
        return doc;
    }
    
    public void saveBlockScrapDataToDB() {
        JAXBUtils.saveBlockJAXBToDatabase(this.listBlock);
    }
}
