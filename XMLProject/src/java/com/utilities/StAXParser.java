package com.utilities;

import com.entities.Block;
import com.entities.BlockOfMajor;
import com.entities.Major;
import com.entities.University;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StAXParser {

//    public static void parseXMLBlockData(String document, ListBlock list) throws XMLStreamException {
    public static void parseXMLBlockData(String document, List<Block> blocks) throws XMLStreamException {
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
                            Block block = new Block(0);
                            block.setBlockName(blockCode);
                            block.setDescription(description);
                            block.setActive(true);
                            blocks.add(block);
                            isCorrectRow = false;
                        }
                    } // end else !isCorrectRow
                } // end if event.isCharacter
            } // end reader event.hasNext()
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } finally {
            reader.close();
        }
    }

    public static void parseXMLSchoolInfoData(String document, University university) throws XMLStreamException {
        XMLInputFactory fact = XMLInputFactory.newInstance();
        fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

        XMLEventReader reader = null;

        try {
            InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
            reader = fact.createXMLEventReader(new InputStreamReader(is, "UTF-8"));

            boolean gotAddress = false;
            boolean gotWebsite = false;
            boolean gotEmail = false;

            String address = null;
            String wesite = null;
            String email = null;

            Pattern pWebsite = Pattern.compile(ConstantManager.REGEX_WEBSITE);
            Pattern pEmail = Pattern.compile(ConstantManager.REGEX_EMAIL);

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                if (event.isCharacters()) {
                    String text = event.asCharacters().getData();

                    // Get website
                    if (!gotWebsite) {
                        Matcher mWebsite = pWebsite.matcher(text);
                        if (mWebsite.find()) {
                            Matcher mEmail = pEmail.matcher(text);
                            if (!mEmail.find()) {
                                wesite = mWebsite.group();
                                university.setWebsite(wesite);
                                gotWebsite = true;
                            }
                        }
                    }

                    // Get website
                    if (!gotEmail) {
                        Matcher mEmail = pEmail.matcher(text);
                        if (mEmail.find()) {
                            email = mEmail.group();
                            university.setEmail(email);
                            gotEmail = true;
                        }
                    }

                    // Get address
                    if (!gotAddress) {
                        String lowerText = text.toLowerCase();
                        if (lowerText.contains("Địa chỉ".toLowerCase())
                                || lowerText.contains("Đ/C".toLowerCase())
                                || lowerText.contains("đường".toLowerCase())
                                || lowerText.contains("TP".toLowerCase())) {
                            if (lowerText.contains("Địa chỉ".toLowerCase())
                                    || lowerText.contains("Đ/C".toLowerCase())) {
                                String[] strSplit = text.split(":");
                                if (strSplit.length > 1) {
                                    address = strSplit[1];
                                }
                                university.setDescription(address);
                            } else {
                                if (!text.matches(ConstantManager.REGEX_EMAIL) && !text.matches(ConstantManager.REGEX_WEBSITE)) {
                                    address = text;
                                    university.setDescription(address);
                                }
                            }
                            gotAddress = true;
                        } // end if text contains "địa chỉ"
                    } // end if gotAddress
                } // end if event.isCharacters
            } // end while
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (XMLStreamException ex) {
            System.out.println("Error at school code " + university.getCode());
            System.out.println("doc is: " + document);
            ex.printStackTrace();
        } finally {
            reader.close();
        }
    }

    public static void parseXMLSchoolBaseScoreData(String document, University university, List<Block> blocks) throws XMLStreamException {
        XMLInputFactory fact = XMLInputFactory.newInstance();
        fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

        XMLEventReader reader = null;

        try {
            InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
            reader = fact.createXMLEventReader(new InputStreamReader(is, "UTF-8"));

            boolean inSelectedYear = false;
            boolean inMajor = false;
            boolean lastYear = false;
            boolean isEmptyBlockCode = false;

            List<Major> majors = university.getMajorList();
            if (majors == null) {
                majors = new ArrayList<Major>();
            }
            Major major = null;
            List<BlockOfMajor> listBlocksOfMajor = null;

            int column = 0;
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                if (event.isStartElement()) {
                    StartElement element = event.asStartElement();

                    // if tag is <option>
                    // <editor-fold>
                    if (element.getName().toString().equals("option")) {
                        Attribute attr = element.getAttributeByName(new QName("selected"));
                        if (attr != null) {
                            inSelectedYear = true;
                        }
                    } // end if element is <option>
                    // </editor-fold>

                    // if tag is <td>
                    // <editor-fold>
                    if (element.getName().toString().equals("td")) {
                        Attribute attr = element.getAttributeByName(new QName("class"));
                        if (attr != null) {
                            if (attr.getValue().contains("DSTT_row")) {
                                inMajor = true;
                                column++;
                            }
                        }
                    } // end if element is <td>
                    // </editor-fold>
                } // end if startElement

                // if year is last year
                // <editor-fold>
                if (inSelectedYear) {
                    if (event.isCharacters()) {
                        int year = Integer.parseInt(event.asCharacters().getData());
                        if (year == (currentYear - 1)) {
                            lastYear = true;
                        }
                        inSelectedYear = false;
                    }
                }
                // </editor-fold>

                if (inMajor) {
                    if (majors == null) {
                        majors = university.getMajorList();
                    }
                    if (event.isCharacters()) {
                        String text = event.asCharacters().getData();
                        switch (column) {
                            case 1:
                                break;
                            case 2:
                                major = new Major();
                                major.setId(0);
                                major.setUniversityId(university);              // set University ID for Major
                                major.setMajorCode(text.trim());                // set Major code
                                major.setActive(true);                          // set Major activation
                                listBlocksOfMajor = new ArrayList<BlockOfMajor>();
                                major.setBlockOfMajorList(listBlocksOfMajor);
                                break;
                            case 3:
                                major.setMajorName(text);
                                break;
                            case 4:
                                if (text.isEmpty() || text == null) {
                                    isEmptyBlockCode = true;
                                } else {
                                    String[] splitStrings = null;
                                    if (text.contains(",")) {
                                        splitStrings = text.split(",");
                                    } else if (text.contains(";")) {
                                        splitStrings = text.split(";");
                                    }

                                    if (splitStrings == null) { // if major has only 1 block
                                        if (text.trim().length() == 1) {
                                            text += "00";
                                        }
                                        BlockOfMajor blocksOfMajor = new BlockOfMajor(0);
                                        blocksOfMajor.setMajorId(major);
                                        blocksOfMajor.setActive(true);

                                        for (Block block : blocks) {    // find Block
                                            if (block.getBlockName().equalsIgnoreCase(text)) {
                                                blocksOfMajor.setBlockId(block);
                                                break;
                                            }
                                        } // end loop for blocks
                                        listBlocksOfMajor.add(blocksOfMajor);
                                    } else {    // if major has more than 1 block

                                        for (String eachString : splitStrings) {
                                            if (eachString.trim().length() == 1) {
                                                eachString += "00";
                                            }
                                            BlockOfMajor blocksOfMajor = new BlockOfMajor(0);
                                            blocksOfMajor.setMajorId(major);
                                            blocksOfMajor.setActive(true);

                                            for (Block block : blocks) {
                                                if (block.getBlockName().equalsIgnoreCase(eachString.trim())) {
                                                    blocksOfMajor.setBlockId(block);
                                                    break;
                                                }
                                            } // end loop for blocks
                                            listBlocksOfMajor.add(blocksOfMajor);
                                        }
                                    } // end if major has more than 1 block
                                }
                                break;
                            case 5:
                                if (!isEmptyBlockCode) {    // if major has 0 block code
                                    if (text.isEmpty() || text == null) {
                                    } else {
                                        Pattern p = Pattern.compile(ConstantManager.REGEX_DOUBLE);
                                        Matcher m = p.matcher(text);
                                        if (lastYear) { // if last year then add
                                            if (m.find()) {
                                                for (BlockOfMajor each : listBlocksOfMajor) {
                                                    each.setBaseScoreLastYear(Double.parseDouble(text));
                                                }
                                            }
                                        } // end if lastYear
                                    }
                                }
                                break;
                            default:
                                if (text.isEmpty() || text == null) {
                                    major.setDescription(text);
                                }
                                column = 0;
                                inMajor = false;

                                // save to DB
                                JAXBUtils.saveCrawlBlockAndMajorToDb(major, listBlocksOfMajor);

                                major = null;
                                listBlocksOfMajor = null;
                                break;
                        } // end switch
                    } // end if event.isCharacters()
                } // end inMajor
            } // end while

        } catch (XMLStreamException ex) {
            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            reader.close();
        }
    }
}
