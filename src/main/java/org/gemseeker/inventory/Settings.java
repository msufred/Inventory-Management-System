package org.gemseeker.inventory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public final class Settings {

    public enum Type {
        DATABASE, APPLICATION, SYSTEM
    }

    private static Settings instance;
    private Document doc;

    public static Settings getInstance() throws ParserConfigurationException, SAXException, IOException {
        if (instance == null) instance = new Settings();
        return instance;
    }

    private Settings() throws ParserConfigurationException, SAXException, IOException {
        File file = new File(Utils.SETTINGS_FILE);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(file);
        doc.getDocumentElement().normalize();
    }

    /**
     * Usage: Settings.get(Type.DATABASE, "username");
     */
    public String get(Type type, String value) {
        return switch (type) {
            case DATABASE -> get("database", value);
            case APPLICATION -> get("application", value);
            default -> get("system", value);
        };
    }

    /**
     * Usage: Settings.set(Type.DATABASE, "username", "john");
     */
    public void set(Type type, String setting, String value) {
        switch (type) {
            case DATABASE -> set("database", setting, value);
            case APPLICATION -> set("application", setting, value);
            default -> set("system", setting, value);
        }
    }

    private String get(String parentTag, String valueTag) {
        Node node = doc.getElementsByTagName(parentTag).item(0);
        if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
            return ((Element) node).getElementsByTagName(valueTag).item(0).getTextContent();
        }
        return null;
    }

    private void set(String parentTag, String valueTag, String value) {
        Node node = doc.getElementsByTagName(parentTag).item(0);
        if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
            ((Element) node).getElementsByTagName(valueTag).item(0).setTextContent(value);
        }
    }

    public void save() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(Utils.SETTINGS_FILE));
        transformer.transform(source, result);
    }
}
