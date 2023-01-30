package com.ensao.gi5.lint.printer;

import com.ensao.gi5.lint.rules.violations.Violation;

import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONPrinter implements Printer{
    @Override
    public void printViolations(Collection<Violation> violations) {
        JSONArray jsonArray = new JSONArray();
        for (Violation violation : violations) {
            JSONObject json = new JSONObject();
            json.put("level", violation.getLevel());
            json.put("ruleId", violation.getRuleId());
            json.put("description", violation.getDescription());
            jsonArray.put(json);
        }
        writeToFile(jsonArray);
    }

    private void writeToFile(JSONArray json) {
        try {
            FileWriter fileWriter = new FileWriter(new File("violations.json"));
            fileWriter.write(json.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
