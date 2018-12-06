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
public class Word {
    
    private final String word;
    private boolean typed;
    
    public Word(String pWord) {
        word = pWord;
        typed = false;
    }
    
    public boolean isTyped() {
        return typed;
    }
    
    public String getWord() {
        return word;
    }
    
    public void trig() {
        typed = true;
    }
    
}
