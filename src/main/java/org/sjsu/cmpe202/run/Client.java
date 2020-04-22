package org.sjsu.cmpe202.run;

import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.parser.CsvFileParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Client {

    public static void main(String args[]) throws IOException {
      System.out.println(" Args Length" +args.length);
      if (args.length <=1) {
          throw new IllegalArgumentException("Expecting two argument as input and output file path");
      }
      String inputPath = args[0];
      String outputPath = args[1];
      System.out.println(" args[0]="+inputPath);
      System.out.println(" args[1]="+outputPath);

      File inputFile1 = new File(inputPath);
      if (!inputFile1.exists()) {
         throw new FileNotFoundException(inputPath+" is not valid file, and does not exist");
      }
      File outputFile = new File(outputPath);
      if (!outputFile.exists()) {
          //outputFile.mkdirs();
          outputFile.createNewFile();
      }
      if (inputFile1.getName().endsWith("csv")) {
          System.out.println("Input file 1 is csv");
      } else if (inputFile1.getName().endsWith("json")) {
          System.out.println("Input file 1 is json");
      } else if (inputFile1.getName().endsWith("xml")) {
            System.out.println("Input file 1 is xml");
      }

      CsvFileParser csvFileParser = new CsvFileParser();
      List<Record> result = csvFileParser.parse(inputFile1.getAbsolutePath());
        System.out.println();
        for(int i=0; i<result.size(); i++) {
            System.out.println(result.get(i));
        }
        csvFileParser.write(result, outputFile.getAbsolutePath());

    }

}
