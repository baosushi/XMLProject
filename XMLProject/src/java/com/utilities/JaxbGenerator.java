package com.utilities;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

public class JaxbGenerator {
    public void generateSchemaToJaxb(String schemaFile){
        try {
            String output = "/";
            SchemaCompiler sc = XJC.createSchemaCompiler();
            sc.setErrorListener(new ErrorListener() {

                @Override
                public void error(SAXParseException saxpe) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void fatalError(SAXParseException saxpe) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void warning(SAXParseException saxpe) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void info(SAXParseException saxpe) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
            sc.forcePackageName("jaxb.classes");
            File schema = new File(schemaFile);
            
            InputSource inSource = new InputSource(schema.toURI().toString());
            sc.parseSchema(inSource);
            S2JJAXBModel model = sc.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(output));
            System.out.println(schemaFile + " generate finished");
        } catch (Exception e) {
        }
    }
}
