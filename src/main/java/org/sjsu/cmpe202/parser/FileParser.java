package org.sjsu.cmpe202.parser;

import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.parser.format.FileFormat;

import java.io.IOException;
import java.util.List;

abstract class FileParser {
    protected FileFormat fileFormat = null;
    public abstract List<Record> parse(String inputFile);
    public abstract void write(List<Record> records, String outputFileName) throws IOException;
}