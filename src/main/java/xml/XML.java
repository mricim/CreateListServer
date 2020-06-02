package main.java.xml;

import main.java.Rutas;
import org.w3c.dom.*;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

public class XML {

    public static Document getDocumentUrl(String hostDowloads, String fileList) throws ParserConfigurationException, IOException, SAXException {
        // DOM:
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document documento = db.parse(new URL(hostDowloads + fileList).openStream());
        documento.getDocumentElement().normalize();
        return documento;
    }

    public static Document getDocument(String path) throws ParserConfigurationException, IOException, SAXException {
        // DOM:
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document documento = dBuilder.parse(fXmlFile);
        documento.getDocumentElement().normalize();
        return documento;
    }

    public static NodeList getList(Document document) throws ParserConfigurationException, IOException, SAXException {
        return document.getElementsByTagName("file");
    }

    /*
        protected static Rutas getRuta(String url, String nameFile, String fileORlist, String ifSearch) throws IOException, SAXException, ParserConfigurationException {
            Document document2 = getDocument(url, nameFile);
            NodeList nList2 = document2.getElementsByTagName(fileORlist);
            return extractorXML(url, nList2, null, ifSearch);
        }
    */
    public static ArrayList<Rutas> extractorXML(NodeList nodeList) {
        HashSet<Rutas> rutas = new HashSet<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node2 = nodeList.item(i);
            if (node2.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node2;
                String seeker = null;
                String system = null;
                String type = null;
                String version = null;
                String href = null;
                String name = null;
                String path = null;
                String md5 = null;
                String isfile = null;
                try {
                    seeker = eElement.getElementsByTagName("seeker").item(0).getTextContent();
                } catch (NullPointerException e) {
                }
                try {
                    system = eElement.getElementsByTagName("system").item(0).getTextContent();
                } catch (NullPointerException e) {
                }
                try {
                    type = eElement.getElementsByTagName("type").item(0).getTextContent();
                } catch (NullPointerException e) {
                }
                try {
                    version = eElement.getElementsByTagName("version").item(0).getTextContent();
                } catch (NullPointerException e) {
                }
                try {
                    href = eElement.getElementsByTagName("href").item(0).getTextContent();
                } catch (NullPointerException e) {
                }
                try {
                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
                } catch (NullPointerException e) {
                }
                try {
                    path = eElement.getElementsByTagName("path").item(0).getTextContent();
                } catch (NullPointerException e) {
                }
                try {
                    md5 = eElement.getElementsByTagName("md5").item(0).getTextContent();
                } catch (NullPointerException e) {
                }
                try {
                    isfile = eElement.getElementsByTagName("nameFile").item(0).getTextContent();
                } catch (NullPointerException e) {
                }
//                System.out.println(system);
//                System.out.println(type);
//                System.out.println(version);
//                System.out.println(href);
//                System.out.println(name);
//                System.out.println(path);
//                System.out.println(md5 + "\n");
                rutas.add(new Rutas(system, type, version, href, name, path, md5, isfile));
            }
        }
        return new ArrayList<>(rutas);
    }

    public static void writeXML(String pathFile, String typeFILEorLIST, ArrayList<Rutas> rutas) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        // root element
        Element root = document.createElement(typeFILEorLIST);
        document.appendChild(root);

        for (Rutas ruta : rutas) {
            // employee element
            Element element = document.createElement(ruta.getFileORlist());
            root.appendChild(element);

            // set an attribute to staff element
            String version = ruta.getVersion();
            if (version != null) {
                Element attr = document.createElement("version");
                attr.appendChild(document.createTextNode(version));
                element.appendChild(attr);
            }
            String system = ruta.getSystem();
            if (system != null) {
                Element attr = document.createElement("system");
                attr.appendChild(document.createTextNode(system));
                element.appendChild(attr);
            }
            String type = ruta.getFileORlist();
            if (type != null) {
                Element attr = document.createElement("type");
                attr.appendChild(document.createTextNode(type));
                element.appendChild(attr);
            }
            String href = ruta.getHref();
            if (href != null) {
                Element attr = document.createElement("href");
                attr.appendChild(document.createTextNode(href));
                element.appendChild(attr);
            }
            String name = ruta.getName();
            if (name != null) {
                Element attr = document.createElement("name");
                attr.appendChild(document.createTextNode(name));
                element.appendChild(attr);
            }
            String path = ruta.getPath();
            if (path != null) {
                Element attr = document.createElement("path");
                attr.appendChild(document.createTextNode(path));
                element.appendChild(attr);
            }
            String md5 = ruta.getMd5();
            if (md5 != null) {
                Element attr = document.createElement("md5");
                attr.appendChild(document.createTextNode(md5));
                element.appendChild(attr);
            }
            String isfile = ruta.getFileSearch();
            if (isfile != null) {
                Element attr = document.createElement("nameFile");
                attr.appendChild(document.createTextNode(isfile));
                element.appendChild(attr);
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(pathFile));
        // If you use
        // StreamResult result = new StreamResult(System.out);
        // the output will be pushed to the standard output ...
        // You can use that for debugging
        transformer.transform(domSource, streamResult);
    }
}