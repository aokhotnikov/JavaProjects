package com.shpp.cs.aokhotnikov.consoletest;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by FBI on 18.12.2015.
 */
public class Assignment5Part4 extends TextProgram {
    /** path to csv file */
    private static final String PATH = "assets/csv-file.csv";

    public void run(){
        println(extractColumn(PATH, 1));
    }
    /**
     * Opens the CSV file with the name "filename". Finds column with index "columnIndex",
     * and then returns the ArrayList which contains information from this column
     *
     * @param filename CSV file
     * @param columnIndex Column number, which will be displayed on the screen
     * @return A list of words that will be displayed on the screen
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> list = readFromFile(filename);
        if (list == null) {
            return null;
        }
        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            newList.add(fieldsIn(list.get(i)).get(columnIndex));
        }
        return newList;
    }
    /** Tokenizes the input CSV file line and returns all the fields in that line.
     *
     * @param line A line from a CSV file.
     * @return A list of all the tokens in that line, with any external quotation marks
     * removed.
     */
    private ArrayList<String> fieldsIn(String line){
        ArrayList<String> list = new ArrayList<>();
        int begin = 0;
        int i = 0;
        while (i <= line.length()) {
            if (i == line.length()) {
                list.add("\"" + line.substring(begin, i) + "\"");
                break;
            }
            if (line.charAt(i) == ',') {
                list.add("\"" + line.substring(begin, i) + "\"");
                begin = i + 1;
            }
            if (line.charAt(i) == '"') {
                int end = line.indexOf("\"", begin + 1) + 1;
                list.add(line.substring(begin, end));
                i = end;
                begin = end + 1;
            }
            i++;
        }
        return list;
    }
    /** Reads strings from file
     * @param path Path to file
     * @return List of strings from this file
     */
    private ArrayList<String> readFromFile(String path){
        ArrayList<String> lines = new ArrayList<>();
        try {
			/* Open the file for reading. */
            BufferedReader br = new BufferedReader(new FileReader(path));
            while (true) {
                String str = br.readLine();
                if (str == null) break;
                lines.add(str);
            }
            br.close();
        }
        catch (IOException e) {
            lines = null;
        }
        return lines;
    }
}
