/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typingtest;

/**
 *
 * @author jonathanvo
 */
public class OutputManager {
    
    private final StringBuilder blank;

    public OutputManager(int lines) {
        
        blank = new StringBuilder();
        
        for(int i = 0; i < lines; i++)
            blank.append(System.getProperty("line.separator"));
    }
    
    public void newScreen() {
        System.out.print(blank);
    }
    
    public void optimize() {
        System.out.print("/\\");
        for(int i = 0; i < 18; i++) {
            System.out.print("\n|");
            if(i == 9) System.out.print("\t Align output window to fit arrows");
        }
        System.out.print("\n\\/");
    }
    
    
    
    
}
