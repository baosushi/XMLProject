package com.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BufferedReaderProvider {

//    public static BufferedReader getBufferedReader(String filePath, String uri) {
    public static BufferedReader getBufferedReader(String uri) throws IOException {
//        Writer writer = null;
        BufferedReader bReader = null;
        InputStream is = null;

        try {
            URL url = new URL(uri);
            URLConnection uc = url.openConnection();
            uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            is = uc.getInputStream();
            bReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//            String inputLine;
//            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
//            while ((inputLine = bReader.readLine()) != null) {
//                writer.write(inputLine + "\n");
//            }
//            bReader.close();
//            is.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(BufferedReaderProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BufferedReaderProvider.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return bReader;
        }
    }
}
