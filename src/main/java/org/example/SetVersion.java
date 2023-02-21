package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.io.File;
import java.io.IOException;

import static javax.xml.xpath.XPathConstants.NODESET;

public class SetVersion {
    public static void main(String[] args) throws ParserConfigurationException, XPathExpressionException, TransformerException, IOException, SAXException {
        if (args.length < 1)
            System.exit(1);

        File child = new File("pom.xml");

        //System.out.println(child.getName());
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //dbf.setNamespaceAware(true);
        Document d = dbf.newDocumentBuilder().parse(child);
        XPathFactory pf = XPathFactory.newInstance();
        XPath p = pf.newXPath();
        NodeList l = (NodeList) p.compile(
                "//project/version | //project/parent/version"
        ).evaluate(d, NODESET);
        for (int i = 0; i < l.getLength(); i++) {
            Element e = (Element) l.item(i);
            e.getFirstChild().setNodeValue(repl(args[0], e.getFirstChild().getNodeValue()));
        }
        TransformerFactory.newInstance().newTransformer().transform(new DOMSource(d), new StreamResult(child));
    }

    private static String repl(String newVal, String oldVal) {
        return newVal;
    }
}
