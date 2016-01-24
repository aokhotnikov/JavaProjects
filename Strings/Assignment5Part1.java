package com.shpp.cs.aokhotnikov.consoletest;

import com.shpp.cs.a.console.TextProgram;

/**
 * Created by FBI on 15.12.2015.
 */
public class Assignment5Part1 extends TextProgram {
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */

        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        int count = 0;
        word = word.toLowerCase();

        // truncate the open syllable "e" on the end of one letter
        if ((word.charAt(word.length() - 1) == 'e') && (word.length() != 1)) {
            word = word.substring(0, word.length() - 1);
        }

        if (vowelChar(word.charAt(0))) count++;
        for (int i = 1; i < word.length(); i++) {
            if ((vowelChar(word.charAt(i))) && (!vowelChar(word.charAt(i - 1)))) {
                count++;
            }
        }

        if (count == 0) count++;
        return count;
    }

    /** Decides vowel or not
     * @param c Letter
     * @return vowel(true) or not(false)
     */
    private boolean vowelChar(char c) {
        char vowels[] = {'a', 'e', 'i', 'o', 'u', 'y'};
        for (int i: vowels) {
            if (c == i) return true;
        }
        return false;
    }
}
