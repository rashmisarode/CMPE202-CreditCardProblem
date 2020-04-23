package org.sjsu.cmpe202.run;

import org.sjsu.cmpe202.CCType;
import org.sjsu.cmpe202.CreditCard;
import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.handler.MasterCardHandler;
import org.sjsu.cmpe202.parser.CsvFileParser;
import org.sjsu.cmpe202.FileParser;
import org.sjsu.cmpe202.parser.JsonFileParser;
import org.sjsu.cmpe202.parser.XmlFileParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Client {

    // Default Parser
    private FileParser fileParser = new CsvFileParser();

    public FileParser getFileParser() {
        return fileParser;
    }


    public void setParsingStrategy(String inputFile) {
        if(inputFile.endsWith("json")) {
            this.fileParser = new JsonFileParser();
        } else if(inputFile.endsWith("xml")) {
            this.fileParser = new XmlFileParser();
        }
    }

    public static void main(String args[]) throws IOException {
      System.out.println(" Args Length" +args.length);
      if (args.length <=1) {
          throw new IllegalArgumentException("Expecting two argument as input and output file path");
      }
      String inputPath = args[0];
      String outputPath = args[1];
      System.out.println(" args[0]="+inputPath);
      System.out.println(" args[1]="+outputPath);

      File inputFile = new File(inputPath);
      if (!inputFile.exists()) {
         throw new FileNotFoundException(inputPath+" is not valid file, and does not exist");
      }
      File outputFile = new File(outputPath);
      if (!outputFile.exists()) {
          //outputFile.mkdirs();
          outputFile.createNewFile();
      }

      Client client = new Client();
      client.setParsingStrategy(inputFile.getAbsolutePath());

      List<Record> result = client.getFileParser().parse(inputFile.getAbsolutePath());
      // invoke the code to find the card type
        CCType ccType = new MasterCardHandler();

        List<CreditCard> creditCards = new ArrayList<>();
        result.forEach(record -> {
            CreditCard cc = ccType.verifyCardAndProcess(record);
            creditCards.add(cc);
        });
      client.getFileParser().write(result, outputFile.getAbsolutePath());


    }

}
