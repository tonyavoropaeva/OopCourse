package ru.academits.voropaeva.csv_parser;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Аргумента должно быть 2 (имя исходного файла, и имя файла назначения), сейчас их: " + args.length);

            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
             PrintWriter printWriter = new PrintWriter(args[1])
        ) {
            printWriter.println("<!DOCTYPE HTML>");
            printWriter.println("<html>");
            printWriter.println("<head>");
            printWriter.println("\t<meta charset=\"utf-8\">");
            printWriter.println("\t<title>Таблица</title>");
            printWriter.println("</head>");
            printWriter.println("<body>");
            printWriter.println("\t<table>");

            boolean isQuoteOpen = false;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    if (isQuoteOpen) {
                        printWriter.print("<br/>");
                    }

                    continue;
                }

                if (!isQuoteOpen) {
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
                        printWriter.print("&amp;");
                        continue;
                    }

                    if (isQuoteOpen) {
                        if (currentChar == '\"') {
                            if (i == line.length() - 1) {
                                isQuoteOpen = false;
                            } else if (line.charAt(i + 1) == '\"') {
                                printWriter.print("\"");

                                ++i;
                            } else {
                                isQuoteOpen = false;
                            }
                        } else if (i == line.length() - 1) {
                            printWriter.print(currentChar);
                            printWriter.print("<br/>");
                        } else {
                            printWriter.print(currentChar);
                        }
                    } else {
                        if (currentChar == ',') {
                            printWriter.println("</td>");

                            if (i == line.length() - 1) {
                                printWriter.print("\t\t\t<td>");
                            } else {
                                printWriter.print("\t\t\t<td>");
                            }
                        } else if (currentChar == '"') {
                            isQuoteOpen = true;
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
            printWriter.println("</html>");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}