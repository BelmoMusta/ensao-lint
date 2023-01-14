package com.ensao.gi5.lint.printer;

import com.ensao.gi5.lint.rules.violations.Violation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

public class HTMLPrinter implements Printer{
    @Override
    public void printViolations(Collection<Violation> violations) {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("violations.html"));
        ) {
            StringBuilder html = new StringBuilder();
            html.append("<html>")
                    .append("<head>")
                    .append("<title>Violations</title>")
                    .append("<style>")
                    .append("table {")
                    .append("border-collapse: collapse;")
                    .append("width: 100%;")
                    .append("}")
                    .append("th, td {")
                    .append("border: 1px solid #dddddd;")
                    .append("padding: 8px;")
                    .append("text-align: left;")
                    .append("}")
                    .append("th {")
                    .append("background-color: #dddddd;")
                    .append("}")
                    .append("</style>")
                    .append("</head>")
                    .append("<body>")
                    .append("<table>")
                    .append("<tr>")
                    .append("<th>LEVEL</th>")
                    .append("<th>RULE</th>")
                    .append("<th>DESCRIPTION</th>")
                    .append("</tr>");

            for (Violation violation : violations) {
                html.append("<tr>")
                        .append("<td>").append(violation.getLevel()).append("</td>")
                        .append("<td>").append(violation.getRuleId()).append("</td>")
                        .append("<td>").append(violation.getDescription()).append("</td>")
                        .append("</tr>");
            }

            html.append("</table>")
                    .append("</body>")
                    .append("</html>");
            writer.write(html.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}