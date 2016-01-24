package com.shpp.cs.aokhotnikov.consoletest;

import com.shpp.cs.a.console.TextProgram;

/**
 * Created by FBI on 16.12.2015.
 */
public class Assignment5Part2 extends TextProgram {
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        String rez = "";
        int valTransit = 0;
        int numChar = 1;
        //compare strings and equalized in length
        if (n1.length() > n2.length())
            n2 = newLength(n2, n1.length() - n2.length());
        else
            n1 = newLength(n1, n2.length() - n1.length());

        while ((n1.length() - numChar) != -1) {
            int sum = sumChar(n1, n2, numChar) + valTransit;
            valTransit = getValTransit(sum);
            if (sum >= 10)
                rez = Integer.toString(sum).charAt(1) + rez;
            else
                rez = Integer.toString(sum) + rez;
            numChar++;
        }

        if (valTransit != 0) rez = Integer.toString(valTransit) + rez;
        return rez;
    }

    /** Adds zeros to the beginning of the string
     * @param str A string which will be lengthened
     * @param numZeros The number of zeros that need to increase the string
     * @return A string required length
     */
    private String newLength(String str, int numZeros) {
        for (int i = 0; i < numZeros; i++) {
            str = "0" + str;
        }
        return str;
    }

    /** Addition of two letters-numbers
     * @param str1 The first string-number
     * @param str2 The second string-number
     * @param numChar Sequence number of letters from the end of the string
     * @return The sum of two letters-numbers
     */
    private int sumChar(String str1, String str2, int numChar) {
        return str1.charAt(str1.length() - numChar) - '0' + str2.charAt(str2.length() - numChar) - '0';
    }

    /** Determines the number of tens
     * @param number
     * @return The number of tens
     */
    private int getValTransit(int number){
        if (number >= 10) {
            return Integer.toString(number).charAt(0) - '0';
        }
        return 0;
    }
}
