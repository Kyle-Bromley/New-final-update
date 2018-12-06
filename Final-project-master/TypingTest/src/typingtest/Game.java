/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typingtest;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.HashMap;

/**
 *
 * @author jonathanvo
 */

public class Game {
    
    //Word holders
    private ArrayList<String> dictionary;
    private ArrayList<Word> words;
    private ArrayList<String> player;
    
    //Game variables
    int counter;
    int wpm;
    int gameSize;
    float accuracy;
    
    //Game Constructor - Creates array of words
    public Game() {
        //Read word file and append array
        dictionary = new ArrayList();
        try {
            File f = new File("dict.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine;
            while((readLine = b.readLine()) != null) {
                dictionary.add(readLine);
            } 
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Create word list
    public void create(int size) {
        gameSize = size;
        player = new ArrayList();
        words = new ArrayList();
        for(int i = 0; i < gameSize; i++) {
            //Create word object
            double random = Math.random() * 10000;
            Word word = new Word(dictionary.get((int)random));
            
            //Append to words list
            words.add(word);
        }
    }
    
    //Execute round
    public void playRound(int secs) throws Exception {
        
        OutputManager screen = new OutputManager(20);
        
        // Countdown prints first line and waits 3 seconds
        for(int sleep = 5; sleep > 0; sleep--) {
            System.out.println("STARTING IN " + sleep);
            System.out.println("Current WPM: " + wpm + "\n");
            
            //Output words and countdown
            printLine(0);
            TimeUnit.SECONDS.sleep(1);
            screen.newScreen();
        }
        System.out.println("GO!");
        
        // Creates scanner
        Scanner scanner = new Scanner(System.in);

        //Set timer
        long startTime = System.currentTimeMillis();
        
        // While within 30 seconds
        long time = System.currentTimeMillis() - startTime;
        while(time <= secs * 1000) {    
            // Get count to get current word user is on
            int count = player.size();
            System.out.println("Current WPM: " + wpm + "\n");

            //Output words
            printLine(count);
            
            // Take user input
            // If Scanner picks up input before .1 secs, assume invalid and reprompt
            long promptTime = System.currentTimeMillis();
            String input = scanner.nextLine();
            while(System.currentTimeMillis() <= promptTime+50) {
                input = scanner.nextLine();
            }
            takeInput(input);
            time = System.currentTimeMillis() - startTime;
            currentWPM(time);
            screen.newScreen();
        }
        //Display Stop Screen
        screen.newScreen();
        stop();
        TimeUnit.SECONDS.sleep(4);
        
        //Calculate final WPM
        int characters = 0;
        for(int i = 0; i < player.size(); i++) {
            characters += player.get(i).length();
        }
        wpm = (int)((characters/5) * 2);
    }
    
    //Get Accuracy
    public float getAcc() {
        return accuracy;
    }
    
    //Get WPM
    public int getWPM() {
        return wpm;
    }
    
    //Clears arrays, resets vars, and creates new word list
    public void reset() {
        wpm = 0;
        accuracy = 0;
        player.clear();
        words.clear();
        for(int i = 0; i < gameSize; i++) {
            //Create word object
            double random = Math.random() * 10000;
            Word word = new Word(dictionary.get((int)random));
            
            //Append to words list
            words.add(word);
        }
    }
    
    //Gets WPM from time 
    private void currentWPM(long time) {
        //Count characters typed
        int characters = 0;
        for(int i = 0; i < player.size(); i++) {
            characters += player.get(i).length();
        }
        wpm = (int)((characters/5) * (60000/time));
    }
    
    //Get word
    private String getWord(int index) {
        return words.get(index).getWord();
    }
    
    //Process word
    private void takeInput(String input) {
        player.add(input);
        words.get(counter).trig();
        counter++;
    }
    
    //Print stop
    private void stop() {
        System.out.println( 
" .----------------.  .----------------.  .----------------.  .----------------. \n" +
"| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
"| |    _______   | || |  _________   | || |     ____     | || |   ______     | |\n" +
"| |   /  ___  |  | || | |  _   _  |  | || |   .'    `.   | || |  |_   __ \\   | |\n" +
"| |  |  (__ \\_|  | || | |_/ | | \\_|  | || |  /  .--.  \\  | || |    | |__) |  | |\n" +
"| |   '.___`-.   | || |     | |      | || |  | |    | |  | || |    |  ___/   | |\n" +
"| |  |`\\____) |  | || |    _| |_     | || |  \\  `--'  /  | || |   _| |_      | |\n" +
"| |  |_______.'  | || |   |_____|    | || |   `.____.'   | || |  |_____|     | |\n" +
"| |              | || |              | || |              | || |              | |\n" +
"| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
" '----------------'  '----------------'  '----------------'  '----------------' ");
    }
    
    //Output line
    private void printLine(int count) {
        try {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
            out.write("-->\t");
            for(int i = count; i < count+5; i++) {
                out.write(String.format("%-20s", getWord(i)));
        }
        out.write("\n");
        out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public double checkScore(String orig, String input1) {
      //Testing arrays
        String words[] = {orig};
        String input[] = {input1};
        
        HashMap <Character, Integer> map = new HashMap<Character, Integer>();

        //Find differences in 2 words
        
        //Input string analysis
        //Iterate through each character of input string
        for(int i = 0; i < input[0].length(); i++) {
            char letter = input[0].charAt(i);

            //Put each letter into hash map
            //Check if letter is already inside map
            if(map.containsKey(letter)) {
                
                //Get value of letter
                int val = map.get(letter);
        
                //Increment by one and put back in
                map.put(letter, ++val);
                
            } else {
                //Creates new index at 1 for letter
                map.put(letter, 1);
            }
        }
        
        //Word analysis
        //Iterate through characters of word
        for(int i = 0; i < words[0].length(); i++) {
            char letter = words[0].charAt(i);
            
            //Put each letter into hash map
            //Check if letter is already inside map
            if(map.containsKey(letter)) {
                
                //Get value of letter
                int val = map.get(letter);
        
                //Decrement by one and put back in
                map.put(letter, --val);
                
            } else {
                //Creates new index at -1 for letter
                map.put(letter, -1);
            }
        }
        
        //Analyze analysis
        //Find total missing and extra letters
        int missing = 0;
        int extra = 0;
        //Iterate through every key's value
        for(Character key: map.keySet()) {
            //Find letters missing i.e. negative values
            int val = map.get(key);
            if(val < 0)
                missing += Math.abs(map.get(key));
            else
                extra += Math.abs(map.get(key));
        }
        
        
        
        //FIRST RATIO: letters in word correct / letters in word
        int cWLett = words[0].length() - missing;
        int wLett = words[0].length();
        double fRatio = (double)cWLett/(double)wLett;
        
        //SECOND RATIO: letters in input correct / letters input in total
        int cILett = input[0].length() - extra;
        int iLett = input[0].length();
        double sRatio = (double)cILett/(double)iLett;
        
        
        
        //THIRD RATIO: letters in correct order / total connections
        //Iterate through each character of word
        int index = 0;
        int lIndex = 0; //Index of last found correct pair
        int ordrErr = 0;    //Number of mismatched pairs
        for(int i = 0; i < words[0].length()-1; i++) {
            
            char letter = words[0].charAt(i);
            char letter2 = words[0].charAt(i+1);
            
            
            //Find first letter in input word
            //Keep searching until out of index of input
            while(index < input[0].length()-1) {
                
                //Check equality
                if(input[0].charAt(index) == letter) {
                    //Check if next character is correct
                    if(input[0].charAt(index+1) == letter2) {
                        index++;
                        lIndex = index; //Start next search from second character
                        break;
                    } else {
                        ordrErr++;
                        index = lIndex; //Start next search from last matched pair
                        break;
                    }
                }
                index++;
            }
        }
        //Calculate percentage
        int wInOrdr = words[0].length() - ordrErr;
        int totOrdr = words[0].length();
        double tRatio = (double)wInOrdr/(double)totOrdr;

        double accuracy = (fRatio+sRatio+tRatio)/3;
         
        return accuracy;
    }
    
    public ArrayList<Word> getOrig() {
        return words;
    }
    
    public ArrayList<String> getInput1() {
        return player;
    }
    
    
    
}
