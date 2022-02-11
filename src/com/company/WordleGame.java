package com.company;

import java.io.*;
import java.util.*;

/**
 * Author - Stilian Kolev
 * 2/11/2022
 */

public class WordleGame {
    static Random random = new Random();
    static Dictionary dictionary = new Dictionary();
    static int tries;
    static String word1;
    static int green;
    static String user_word;

    public static void main(String[] args) {

        /**
         * Main implements the use of a dictionary class
         * takes in a random word from a pre-set .txt file
         */
        word1 = dictionary.getWord(random.nextInt(dictionary.getSize()));
        Scanner sc = new Scanner(System.in);
            for (int j = 0; j <= 6; j++) {
                user_word = sc.nextLine();
                if (dictionary.checker(user_word)) {
                    tries++;
                    counter(user_word, word1);
                    if (green == 5) {
                        System.out.println(user_word + " is a match!");
                    }
                    if (tries == 6) {
                        System.out.println("\n" + "GAME OVER, the word was - " + word1);
                        break;
                    }
                }  else if(!dictionary.checker(user_word)){
                    System.out.println("Not a valid word");
            }

        }
    }


    public static void counter(String input, String word1)
    {
        /**
         * Simple method to check for matching char's
         * or similarities
         */
        green=0;
            for (int i = 0; i < 5; i++) {
                if (input.charAt(i) == word1.charAt(i)) {
                    System.out.println(input.charAt(i) + " is GREEN");
                    green++;
                } else {
                    if (word1.contains(input.substring(i, i + 1))) {
                        System.out.println(input.charAt(i) + " is YELLOW");
                    } else {
                        System.out.println(input.charAt(i) + " is GRAY");
                    }
                }
            }
    }

   public static class Dictionary{
       /**
        * Dictionary class takes in a pre-set .txt file
        * this can be changed to anything
        */

       private String input[];

       public Dictionary(){
           input = load("C:\\Users\\words.txt"); //change the String file here to the file path you want to use
       }
       public boolean checker(String a) {
           return Arrays.toString(input).contains(a);
       }

       public int getSize(){
           return input.length;
       }

       public String getWord(int n){
           return input[n].trim();
       }

       private String[] load(String file) {
           File aFile = new File(file);
           StringBuffer contents = new StringBuffer();
           BufferedReader input = null;
           try {
               input = new BufferedReader( new FileReader(aFile) );
               String line = null;
               int i = 0;
               while (( line = input.readLine()) != null) {
                   contents.append(line);
                   i++;
                   contents.append(System.getProperty("line.separator"));
               }
           }catch (FileNotFoundException ex) {
               System.out.println("Can't find the file - are you sure the file is in this location: "+file);
               ex.printStackTrace();
           }catch (IOException ex) {
               System.out.println("Input output exception while processing file");
               ex.printStackTrace();
           }finally {
               try {
                   if (input!= null) {
                       input.close();
                   }
               }catch (IOException ex) {
                   System.out.println("Input output exception while processing file");
                   ex.printStackTrace();
               }
           }
           String[] array = contents.toString().split("\n");
           for(String s: array) {
               s.trim();
           }
           return array;
       }
   }
}
