package org.sjsu.cmpe202.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.sjsu.cmpe202.Record;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class XmlFileParserTest {
    @Test
    public void testXmlParser() throws IOException, ParserConfigurationException, SAXException {
        XmlFileParser xmlFileParser = new XmlFileParser();
        List<Record> result = xmlFileParser.parse("src/test/resources/Sample.xml");
        Assert.assertTrue(result.size() == 4);
        String value = result.get(1).getCcNumberStr();
        Assert.assertEquals(value,"4.12E+12");
        if (result.size() != 0){
            xmlFileParser.write(result,"src/test/resources/SampleOutput.xml");
        }
        File xmlFile = new File("src/test/resources/SampleOutput.xml");
        Assert.assertEquals(xmlFile.exists(), true);
        List<Record> resultOutput = xmlFileParser.parse("src/test/resources/Sample.xml");
        Assert.assertTrue(resultOutput.size() == 4);
        String value1 = resultOutput.get(1).getCcNumberStr();
        Assert.assertEquals(value1,"4.12E+12");
        xmlFile.delete();
    }

    @Test
    public void testInvalidXmlParameters(){
        XmlFileParser xmlFileParser = new XmlFileParser();
        List<Record> result = xmlFileParser.parse("src/test/resources/Sample1.xml");
        String value = result.get(4).getError();
        Assert.assertEquals(value,"InvalidCardNumber");
        result.forEach(record -> {
            if (record.getRecordID() <= 4){
                Assert.assertEquals(record.getError(),"None");
            }
        });
    }
    @Test
    public void testEmptyFile(){
        XmlFileParser xmlFileParser = new XmlFileParser();
        List<Record> result = xmlFileParser.parse("src/test/resources/Sample2.xml");
        Assert.assertTrue(result.size() == 0);
    }
}
