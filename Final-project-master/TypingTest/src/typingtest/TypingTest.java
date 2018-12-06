/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typingtest;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

/**
 *
 * @author jonathanvo
 */
public class TypingTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        //Create objects
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        OutputManager screen = new OutputManager(20);
        readingFile file = new readingFile();
        writingFile file1 = new writingFile();
        
        //Create game with 100 words in bank
        game.create(100);
        
        //Prompt user to adjust screen
        screen.optimize();
        System.out.print("\tPress enter to continue");
        scanner.nextLine();
        
        //Display Menu
        while(true) {
            screen.newScreen();
            System.out.println(
                " ____  _  _  ____  ____  _  _  ___    ____  ____  ___  ____ \n" +
                "(_  _)( \\/ )(  _ \\(_  _)( \\( )/ __)  (_  _)( ___)/ __)(_  _)\n" +
                "  )(   \\  /  )___/ _)(_  )  (( (_-.    )(   )__) \\__ \\  )(  \n" +
                " (__)  (__) (__)  (____)(_)\\_)\\___/   (__) (____)(___/ (__) "
            );
            System.out.println("\n[1] Play");
            System.out.println("[2] Show Last Game Stats");
            System.out.println("[3] Show Highscores");
            
            //Read and validate input
            int choice = 0;
            while (scanner.hasNext()){
                if (scanner.hasNextInt()){
                    choice = scanner.nextInt();
                    break;
                } else {
                    scanner.next();
                }
            }

            
            //Execute corresponding functions
            screen.newScreen();
            switch(choice) {
                case 1: game.playRound(1);
                        screen.newScreen();
                        System.out.println("STATISTICS\n");
                        System.out.println("WPM: " + game.getWPM());
                        ArrayList<Word> orig = game.getOrig();
                        ArrayList<String> input1 = game.getInput1();
                        float num = 0;
                        for(int i = 0; i < input1.size(); i++) {
                        num +=  game.checkScore(orig.get(i).getWord(), input1.get(i));
                        }
                        System.out.println("Accuracy: " + num/input1.size()* 100 + "%");
                        float accuracy = num / input1.size();
                        file1.WritingLaststats(game.getWPM(), accuracy);
                        file1.WritingLeaderbroads(game.getWPM(), accuracy);
                        TimeUnit.SECONDS.sleep(4);
                        game.reset();
                        break;
                case 2: 
                    file.ReadingLaststats();
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case 3:
                    file.ReadingLeaderbroads();
                     TimeUnit.SECONDS.sleep(4);
                    break;
                default: break;
            }
        }
    }
}

    
