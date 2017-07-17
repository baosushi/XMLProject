package com.utilities;

import com.entities.Block;
import com.entities.University;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import jaxb.classes.ListUniversity;

public class Crawler {

    private List<Block> blocks;
    private ListUniversity listUniversity;
    private List<University> universities;
    private Map<String, String> linkOfUniversity;

    public Crawler() {
        this.blocks = new ArrayList<Block>();
        this.universities = new ArrayList<University>();
        getListOfUniversity(); 
        this.linkOfUniversity = new HashMap<String, String>();
    }

    public void crawlBlock() {
        String document = getBlockDataFromHTML(ConstantManager.khoidaihoc_path);
        try {
            StAXParser.parseXMLBlockData(document, this.blocks);
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
    }

    public void crawlUniversityInformation() throws InterruptedException, IOException {
        getLinkOfUniversityInformation();
        int count = 0;
        for (Map.Entry<String, String> entry : this.linkOfUniversity.entrySet()) {
            try {
                University university = null;
                int index = -1;
                for (University each : this.universities) { // search for university in ArrayList
                    index++;
                    if (each.getCode().equals(entry.getKey())) {
                        university = each;
                        break;
                    }
                } // end for universities
                String doc = getUniversityInfoFromHTML(entry.getValue());
                StAXParser.parseXMLSchoolInfoData(doc, university);
                this.universities.set(index, university);
                index = -1;
                count++;
                if (count % 50 == 0) {
                    Thread.sleep(1000);
                }
            } // end for Map
            catch (IOException ex) {
                Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XMLStreamException ex) {
                Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // end for Map
    }

    public void crawlUniversityBaseScore() throws IOException {
        getLinkOfUniversityBaseScore();
        for (Map.Entry<String, String> entry : this.linkOfUniversity.entrySet()) {
            try {
                University university = null;
                for (University each : this.universities) { // search for university in ArrayList
                    if (each.getCode().equals(entry.getKey())) {
                        university = each;
                        break;
                    }
                } // end for universities
                String doc = getUniversityBaseScoreFromHTML(entry.getValue());
                StAXParser.parseXMLSchoolBaseScoreData(doc, university, this.blocks);
            } // end for Map
            catch (IOException ex) {
                Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XMLStreamException ex) {
                Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void crawlUniversityData() {
        try {
            JAXBUtils.clear();
            crawlBlock();
            JAXBUtils.saveCrawlBlockToDb(this.blocks);
            crawlUniversityInformation();
            JAXBUtils.saveCrawlUniversityToDb(this.universities);
            crawlUniversityBaseScore();
            System.out.println("Done crawling");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBlockDataFromHTML(String url) {
        String doc = "";
        try {
            BufferedReader bReader = BufferedReaderProvider.getBufferedReader(url);

            String line;
            boolean correctLine = false;

            doc += "<root>";
            while ((line = bReader.readLine()) != null) {
                if (correctLine) {
                    doc += line;
                }
                if (line.contains("</table>")) {
                    break;
                }
                if (line.contains("<table")) {
                    doc += line.substring(line.indexOf("<table"));
                    correctLine = true;
                }
            }
            doc += "</root>";
            doc = "<root>" + doc + "</root>";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    public void getListOfUniversity() {
        try {
            BufferedReader bReader = BufferedReaderProvider.getBufferedReader(ConstantManager.thongtintruong_tuyensinh247_path);

            boolean correctContent = false;
            boolean inOneRow = false;
            String line;
            String schoolCode = null;
            String schoolName = null;

            while ((line = bReader.readLine()) != null) {

                if (correctContent) {

                    if (line.contains("<tr")) {
                        inOneRow = true;
                    }

                    if (line.contains("</tr>")) {
                        inOneRow = false;
                    }

                    if (inOneRow) {
                        if (line.contains("href")) {
                            String text = Utils.getHtmlTextValue(line, "a");
                            if (text.length() <= 4) {
                                schoolCode = text;
                            } else {
                                schoolName = text;
                                University university = new University(0);
                                university.setCode(schoolCode);
                                university.setUniversityName(schoolName);
                                university.setPriority(1);
                                this.universities.add(university);
                                inOneRow = false;
                            }
                        }
                    } // end inOne Row

                    if (line.contains("</table>")) {
                        correctContent = false;
                        break;
                    }
                } // end correctContent

                if (line.contains("</strong></th>")) {
                    correctContent = true;
                }
            } // end while

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLinkOfUniversityInformation() throws IOException {
        BufferedReader bReader = null;
        try {
            bReader = BufferedReaderProvider.getBufferedReader(ConstantManager.thongtintruong_tuyensinh247_path);

            String line;
            boolean correctContent = false;
            boolean inOneRow = false;
            String link = null;
            String schoolCode = null;
            this.linkOfUniversity = new HashMap<String, String>();

            while ((line = bReader.readLine()) != null) {

                if (correctContent) {

                    if (line.contains("<tr")) {
                        inOneRow = true;
                    }

                    if (line.contains("</tr>")) {
                        inOneRow = false;
                    }

                    if (inOneRow) {
                        if (line.contains("href")) {
                            schoolCode = Utils.getHtmlTextValue(line, "a");
                            link = ConstantManager.mainPage_tuyensinh247_path + Utils.getHtmlAttributeValue(line, "href");
                            linkOfUniversity.put(schoolCode, link);
                            inOneRow = false;
                        }
                    } // end inOne Row

                    if (line.contains("</table>")) {
                        correctContent = false;
                        break;
                    }
                } // end correctContent

                if (line.contains("</strong></th>")) {
                    correctContent = true;
                }
            } // end while
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bReader.close();
        }
    }

    public void getLinkOfUniversityBaseScore() throws IOException {
        BufferedReader bReader = null;
        try {
            bReader = BufferedReaderProvider.getBufferedReader(ConstantManager.diemchuan_diemthi24h_path);
            String line;
            boolean correctContent = false;
            boolean inOneRow = false;
            String link = null;
            String schoolCode = null;
            this.linkOfUniversity = new HashMap<String, String>();

            while ((line = bReader.readLine()) != null) {
                if (correctContent) {
                    if (!inOneRow && line.contains("</div")) {
                        break;
                    }

                    if (!inOneRow && line.contains("<a")) {
                        inOneRow = true;
                        String title = Utils.getHtmlAttributeValue(line, "title");
                        schoolCode = title.split("-")[0].trim();
                        link = ConstantManager.mainPage_diemthi24h_path + Utils.getHtmlAttributeValue(line, "href");
                        this.linkOfUniversity.put(schoolCode, link);
                    }

                    if (inOneRow && line.contains("</a>")) {
                        inOneRow = false;
                    }
                }

                if (line.contains("Chọn trường ĐHCĐ")) {
                    correctContent = true;
                }

            } // end while
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bReader.close();
        }
    }

    public String getUniversityInfoFromHTML(String url) throws IOException {
        String doc = "";
        BufferedReader bReader = null;
        try {
            bReader = BufferedReaderProvider.getBufferedReader(url);
            String line;
            boolean correctContent = false;
            boolean hasContent = false;

            while ((line = bReader.readLine()) != null) {
                if (correctContent) {
                    if (line.contains("<div") || line.contains("</div")) {
                        break;
                    }

                    if (hasContent && line.contains("<table")) {
                        break;
                    }

                    if (line.contains("</table")) {
                        doc += line.trim();
                        break;
                    }
                }

                if (correctContent) {
                    doc += line.trim();
                    hasContent = true;
                }

                if (line.contains("cont-news")) {
                    correctContent = true;
                }
            } // end while
            doc = "<root>" + doc + "</root>";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bReader.close();
        }
        return doc;
    }

    public String getUniversityBaseScoreFromHTML(String url) throws IOException {
        String doc = "";
        BufferedReader bReader = null;
        try {
            bReader = BufferedReaderProvider.getBufferedReader(url);
            String line;
            boolean correctContent = false;
            boolean anotherDiv = false;

            while ((line = bReader.readLine()) != null) {

                if (line.contains("<div")) {
                    anotherDiv = true;
                }
                if (line.contains("<div class=\"greyBox-container")) {
                    correctContent = true;
                    anotherDiv = false;
                }
                if (correctContent) {
                    doc += Utils.normalizeHTMLString(line);

                    if (line.contains("</div")) {
                        if (anotherDiv) {
                            anotherDiv = false;
                        } else {
                            break;
                        }
                    } // end if line.contain("</div")
                } // end if correct
            } // end while

            doc = "<root>" + doc + "</div></root>";
            doc = Utils.removeTabCharacters(doc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bReader.close();
        }
        return doc;
    }
}
