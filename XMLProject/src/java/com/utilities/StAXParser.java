package com.utilities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import jaxb.classes.BlockItem;
import jaxb.classes.ListBlock;

public class StAXParser {

    public static void parseXMLBlockData(String document, ListBlock list) throws XMLStreamException {
        XMLInputFactory fact = XMLInputFactory.newInstance();
        fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

        XMLEventReader reader = null;

        try {
            InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
            reader = fact.createXMLEventReader(new InputStreamReader(is, "UTF-8"));

            boolean isCorrectRow = false;
            String description = null;
            String blockCode = null;

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                System.out.println(event.toString());

                if (event.isCharacters()) {
                    String text = event.asCharacters().getData();
                    if (!isCorrectRow) {
                        Pattern p = Pattern.compile(ConstantManager.REGEX_NUMBERS);
                        Matcher m = p.matcher(text);
                        if (m.find()) {
                            isCorrectRow = true;
                            continue;
                        }
                    } else {
                        if (text.length() > 3) {
                            description = text;
                        }
                        if (text.length() == 3) {
                            blockCode = text;
                            BlockItem blockItem = new BlockItem();
                            blockItem.setDescription(description);
                            blockItem.setName(blockCode);
                            list.getBlock().add(blockItem);
                            isCorrectRow = false;
                        }
                    } // end else !isCorrectRow
                } // end if event.isCharacter
            } // end reader event.hasNext()
            for (BlockItem block : list.getBlock()) {
                System.out.println(block.getName());
                System.out.println(block.getDescription());
                System.out.println("====================");
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } finally {
            reader.close();
        }
    }
}
