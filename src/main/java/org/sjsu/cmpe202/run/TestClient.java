package org.sjsu.cmpe202.run;

import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.parser.JsonFileParser;
import org.sjsu.cmpe202.parser.XmlFileParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TestClient {
  public static void main(String args[]) {
     /* JsonFileParser fileParser = new JsonFileParser();
      List<Record> result = fileParser.parse("src/main/resources/Sample.json");
      try {
          fileParser.write(result,"src/main/resources/SampleOutput.json");
      } catch (IOException e) {
          e.printStackTrace();
      }*/

      XmlFileParser fileParser = new XmlFileParser();
      List<Record> result = fileParser.parse("src/main/resources/Sample.xml");
      System.out.println(result);
      try {
          fileParser.write(result,"src/main/resources/SampleOutput.xml");
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}
