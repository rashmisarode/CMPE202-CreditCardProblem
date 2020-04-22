package org.sjsu.cmpe202.parser;

import com.cedarsoftware.util.io.JsonWriter;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.RecordBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonFileParser extends FileParser {
    @Override
    public List<Record> parse(String inputFile) {
        List<Record> result = new ArrayList<>();

        String csvFile = inputFile;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(csvFile));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
           // JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray cards = (JSONArray) obj;
            int count = 1;
            Iterator<JSONObject> iterator = cards.iterator();
            while (iterator.hasNext()) {
                JSONObject card = iterator.next();
                String cNum =  card.get("CardNumber").toString();
                String cExp = (String) card.get("ExpirationDate");
                String cHolder = (String) card.get("NameOfCardholder");
                System.out.println(card);

                Record record1 = RecordBuilder.aRecord()
                        .withRecordID(count)
                        .withCcNumber(cNum)
                        .withExpDate(cExp)
                        .withCcHolderName(cHolder)
                        .build();
                result.add(record1);
                count++;
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void write(List<Record> records, String outputFileName) throws IOException {
        JSONArray arr = new JSONArray();

        for (int i =0; i< records.size(); i++) {
            JSONObject card = new JSONObject();
            card.put("CardNumber", records.get(i).getCcNumberStr());
            card.put("CardType", String.valueOf(records.get(i).getCcType()));
            card.put("Error", records.get(i).getError());
            arr.add(card);
        }
        System.out.println(arr.toJSONString());

        File out = new File(outputFileName);
        FileWriter writer = new FileWriter(out);
        String niceFormattedJson = JsonWriter.formatJson(arr.toJSONString());
        writer.write(niceFormattedJson);

        writer.close();

    }
}
