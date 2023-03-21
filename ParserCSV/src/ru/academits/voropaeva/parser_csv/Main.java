package ru.academits.voropaeva.parser_csv;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("ParserCSV/src/text.txt"));
             FileWriter fileWriter = new FileWriter("ParserCSV/src/result.html")
        ) {
            fileWriter.write("<table>" + System.lineSeparator());

            boolean tableRowFinished = true;

            while (bufferedReader.ready()) {
                if (tableRowFinished) {
                    fileWriter.write("\t" + "<tr>" + System.lineSeparator());
                }

                String currentLine = bufferedReader.readLine();

                String[] substrings = currentLine.split(",");

                for (String line : substrings) {
                    if (tableRowFinished) {
                        fileWriter.write("\t\t" + "<td>");
                    }

                    if (line.matches("\".*\"")) {
                        line = line.substring(1, line.length() - 1);
                        line = line.replaceAll("\"{2}", "\"");

                        fileWriter.write(line + "</td>" + System.lineSeparator());
                    } else if (line.matches("\".+")) {
                        tableRowFinished = false;
                        line = line.substring(1);

                        fileWriter.write(line + "<br/>");
                    } else if (line.matches(".+\"")) {
                        tableRowFinished = true;
                        line = line.substring(0, line.length() - 1);

                        fileWriter.write(line + "</td>" + System.lineSeparator());
                    } else {
                        fileWriter.write(line + "</td>" + System.lineSeparator());
                    }
                }

                if (tableRowFinished) {
                    fileWriter.write("\t" + "</tr>" + System.lineSeparator());
                }
            }

            fileWriter.write("</table>" + System.lineSeparator());
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
        }
    }
}