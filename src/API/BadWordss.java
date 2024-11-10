/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Grati Eya
 */
public class BadWordss {
    
    public static String[] loadProfanityWords() {
        String filePath = "/pi/utils/profanity_words.txt";
        try (InputStream inputStream = BadWordss.class.getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            StringBuilder wordList = new StringBuilder();

            while ((line = reader.readLine()) != null) 
                {
                wordList.append(line).append("\n");
            }

            String[] profanityWords = wordList.toString().split("\n");
            return profanityWords;
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0]; // Return an empty array in case of an error
        }
    }

    public static boolean containsProfanity(String text, String[] profanityWords) {
        // Convert the input text to lowercase for case-insensitive matching
        text = text.toLowerCase();

        // Check if the input text contains any profanity words
        for (String profanityWord : profanityWords) {
             if (text.contains(profanityWord)) {
                return true;
            }
        }

        return false;
    }
        
    
}
