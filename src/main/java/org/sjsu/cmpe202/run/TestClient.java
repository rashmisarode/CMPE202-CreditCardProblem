package org.sjsu.cmpe202.run;

import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.parser.JsonFileParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TestClient {
  public static void main(String args[]) {
      JsonFileParser fileParser = new JsonFileParser();
      List<Record> result = fileParser.parse("src/main/resources/Sample.json");
     /* File outputFile = new FileWriter("src/main/resources/SampleOutput.json");
      if (!outputFile.exists()) {
          //outputFile.mkdirs();
          outputFile.createNewFile();
      }*/
      try {
          fileParser.write(result,"src/main/resources/SampleOutput.json");
      } catch (IOException e) {
          e.printStackTrace();
      }

  }
}
