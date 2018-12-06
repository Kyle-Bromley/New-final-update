/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typingtest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ncc
 */
public class writingFile {
    
    public void WritingLeaderbroads(int WPM, float accuracy){
     // The name of the file to open.
        String fileName = "Leaderbroad.txt";
        
        
        StringBuilder sb = new StringBuilder();
             

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter("Leaderbroad.txt",true);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            sb.append("WPM:" + WPM);
            sb.append("\n");
            sb.append("Accuracy: " + (accuracy * 100) + "%");
            sb.append("\n");
            bufferedWriter.write(sb.toString());


            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
    
    public void WritingLaststats(int WPM, float accuracy){
     // The name of the file to open.
        String fileName = "Laststats.txt";
        
        
        StringBuilder sb = new StringBuilder();
             

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            sb.append("WPM:" + WPM);
            sb.append("\n");
            sb.append("Accuracy: " + (accuracy * 100) + "%");
            sb.append("\n");
            bufferedWriter.write(sb.toString());


            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}

    

