package com.trss.bi.service.parser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class LynxResponseParser {
    private final Logger log = LoggerFactory.getLogger(LynxResponseParser.class);

    private final String XPATH_XHTML = "/newsMessage/itemSet/newsItem/contentSet/inlineXML/html/body";

    private LynxResponseParser() {}
    public static LynxResponseParser newInstance() { return new LynxResponseParser(); }

    public LynxParserOutput parseResponse(String xmlString) {
        try {
            log.info("xmlString: {}", xmlString);

            Document doc = parseToDom(xmlString);
            XPath xpath = XPathFactory.newInstance().newXPath();

            NodeList nodes = (NodeList) xpath.evaluate(XPATH_XHTML, doc, XPathConstants.NODESET);

            if (nodes.getLength() == 1) {
                String xhtml = convertNodeToString(nodes.item(0));
                return new LynxParserOutput(xhtml);
            }
            else {
                log.error("No Lynx Output XHTML found: {}", xmlString);
                return new LynxParserOutput(null, "No Lynx Output XHTML found");
            }
        } catch (Exception e) {
            log.error("Error parsing Lynx Insight response.", e);
            return new LynxParserOutput(null, "Error parsing Lynx Insight response: " + e.getMessage());
        }
    }

    private Document parseToDom(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)));
    }

    private static String convertNodeToString(Node node) throws TransformerException {
        StringWriter writer = new StringWriter();

        Transformer trans = TransformerFactory.newInstance().newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        trans.transform(new DOMSource(node), new StreamResult(writer));

        return writer.toString();
    }
}
