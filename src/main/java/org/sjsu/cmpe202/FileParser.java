package org.sjsu.cmpe202;

import org.sjsu.cmpe202.Record;

import java.io.IOException;
import java.util.List;

public interface FileParser {
     List<Record> parse(String inputFile);
     void write(List<Record> records, String outputFileName) throws IOException;
}