package com.shpp.cs.aokhotnikov.consoletest;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by FBI on 17.12.2015.
 */
public class Assignment5Part3 extends TextProgram {

    /** path to file of words */
    private static final String PATH = "assets/en-dictionary.txt";

    public void run(){
        ArrayList<String> words = readWordsFromFile(PATH);
        while (true) {
            String letters = readLine("Enter 3 letters: ");
            println(allMatchesFor(letters.toLowerCase(), words));
            println();
        }
    }

    /**
     * Given as input a string of three letters and a list of strings, returns all
     * words in English that match that word according to the rules of the “license
     * plate game” (that is, all words that contain all of the letters in the string
     * 'letters' in the order in which they appear).
     *
     * @param letters A string of three letters.
     * @param allWords A list of all the strings to test.
     * @return A list of all the English words matching the given letter patter.
     */
    private ArrayList<String> allMatchesFor(String letters, ArrayList<String> allWords){
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < allWords.size(); i++) {
            String str = allWords.get(i);
            if(str.toLowerCase().matches("(.*)" + letters.charAt(0) + "(.*)" + letters.charAt(1) + "(.*)" + letters.charAt(2) + "(.*)")) {
                words.add(str);
            }
        }
        return words;
    }

    /** Reads list of words from file
     * @param path Path to file of words
     * @return List of words from this file
     */
    private ArrayList<String> readWordsFromFile(String path){
        ArrayList<String> words = new ArrayList<>();
        try {
			/* Open the file for reading. */
            BufferedReader br = new BufferedReader(new FileReader(path));
            while (true) {
                String str = br.readLine();
                if (str == null) break;
                words.add(str);
            }
            br.close();
        }
        catch (IOException e) {
            println("File not found");
        }
        return words;
    }
}
