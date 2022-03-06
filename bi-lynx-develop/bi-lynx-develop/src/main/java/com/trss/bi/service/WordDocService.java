package com.trss.bi.service;

import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

@Service
public class WordDocService {
    public void convertXhtmlToDocx(String xhtml, OutputStream os) {
        try {
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
            XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
            wordMLPackage.getMainDocumentPart().getContent().addAll(XHTMLImporter.convert(xhtml, null));
            wordMLPackage.save(os);
        }
        catch(Exception e) {
            throw new RuntimeException("Error converting XHtml to Docx", e);
        }
    }
}
