package org.sjsu.cmpe202.parser;

import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.RecordBuilder;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlFileParser extends FileParser {
    @Override
    public List<Record> parse(String inputFile) {
        List<Record> result = new ArrayList<>();
        //String xmlFile = inputFile;
        File xmlFile = new File(inputFile);
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("row");
            System.out.println("----------------------------");

            int count = 1;
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String cNum = eElement.getElementsByTagName("CardNumber").item(0).getTextContent();
                    String cExp = eElement.getElementsByTagName("ExpirationDate").item(0).getTextContent();
                    String cHolder = eElement.getElementsByTagName("NameOfCardholder").item(0).getTextContent();
                    Record record1 = RecordBuilder.aRecord()
                            .withRecordID(count)
                            .withCcNumber(cNum)
                            .withExpDate(cExp)
                            .withCcHolderName(cHolder)
                            .build();
                    result.add(record1);
                    count++;
                }
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void write(List<Record> records, String outputFileName) throws IOException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            doc.setXmlStandalone(true);
            // root element
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);

            for (int i =0; i< records.size(); i++) {
                Element rowElement = doc.createElement("row");
                Element CardNumber  = doc.createElement("CardNumber");
                CardNumber.appendChild(doc.createTextNode(records.get(i).getCcNumberStr()));
                rowElement.appendChild(CardNumber);
                Element CardType  = doc.createElement("CardType");
                CardType.appendChild(doc.createTextNode(String.valueOf(records.get(i).getCcType())));
                rowElement.appendChild(CardType);
                Element Error  = doc.createElement("Error");
                Error.appendChild(doc.createTextNode(records.get(i).getError()));
                rowElement.appendChild(Error);
                rootElement.appendChild(rowElement);
            }
            System.out.println(rootElement);
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(outputFileName));
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
