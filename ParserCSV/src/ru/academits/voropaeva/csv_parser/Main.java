package ru.academits.voropaeva.csv_parser;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
             PrintWriter printWriter = new PrintWriter(args[1])
        ) {
            printWriter.println("<!DOCTYPE HTML>");
            printWriter.println("<head>");
            printWriter.println("\t<meta charset=\"utf-8\">");
            printWriter.println("\t<title>Таблица</title>");
            printWriter.println("</head>");
            printWriter.println("<body>");
            printWriter.println("\t<table>");

            boolean isQuoteOpen = false; //если кавычки открыты
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (!isQuoteOpen) { //если мы не в кавычках
                    printWriter.println("\t\t<tr>");
                    printWriter.print("\t\t\t<td>");
                }

                for (int i = 0; i < line.length(); ++i) {
                    char currentChar = line.charAt(i);

                    if (currentChar == '<') {
                        printWriter.print("&lt;");
                        continue;
                    }

                    if (currentChar == '>') {
                        printWriter.print("&gt;");
                        continue;
                    }

                    if (currentChar == '&') {
                        printWriter.print("&amp");
                        continue;
                    }

                    if (isQuoteOpen) { //если в кавычках
                        if (currentChar == '\"') { //если попалась кавычка
                            if (i == line.length() - 1) { //и мы в конце строки -> выходим из кавычек
                                isQuoteOpen = false;
                            } else if (line.charAt(i + 1) == '\"') { //если дальше кавычка
                                printWriter.print("\""); //печатаем одну кавычку

                                i += 1;
                            } else {
                                isQuoteOpen = false; // закрываем кавычку
                            }
                        } else if (i == line.length() - 1) { //если мы вконце строки и в кавычках, то есть перевод строки
                            printWriter.print(currentChar);
                            printWriter.print("<br/>");
                        } else {
                            printWriter.print(currentChar);
                        }
                    } else { //вне кавычек
                        if (currentChar == ',') {
                            printWriter.println("</td>");

                            if (i == line.length() - 1) {
                                printWriter.print("\t\t\t<td>");
                            } else {
                                printWriter.print("\t\t\t<td>");
                            }
                        } else if (currentChar == '"') { //если попалась кавычка
                            isQuoteOpen = true; // открываем кавычки
                        } else {
                            printWriter.print(currentChar);
                        }
                    }
                }

                if (!isQuoteOpen) {
                    printWriter.println("</td>");
                    printWriter.print("\t\t");
                    printWriter.println("</tr>");
                }
            }

            printWriter.println("\t</table>");
            printWriter.println("</body>");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}