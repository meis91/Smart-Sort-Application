package com.projectnetworld.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public final String ANSI_GREEN = "\u001B[32m";
    public final String ANSI_PURPLE = "\u001B[35m";
    public void writeInstructions(){
        System.out.println("""
                    
                    Welcome to Adrian's "Smart Sort Application".
                
                                    /\\_____/\\
                                   /  o   o  \\
                                  ( ==  ^  == )
                                   )         (
                                  (           )
                                 ( (  )   (  ) )
                                (__(__)___(__)__)
                
                    Please provide an input string to be sorted.
                
                        When finished, press "Ctrl + D".
                
                        PLEASE ENTER YOUR STRING BELOW:
                
                """);
    }

    public List<String> getUserInput(){
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();
        return lines;
    }

    public void writeSolution(List<String> lines){
        System.out.println("""
                
                
                HERE WE GO:
                        
                """);
        for (String line : lines) {
            System.out.println(ANSI_GREEN + line + ANSI_GREEN);
        }

    }

    public void writeContactInformation(){
        System.out.println(ANSI_PURPLE + """
                
                
                CONTACT INFORMATION:
                
                Adrian Perez Cabrera
                
                adrian.perez.cabrera@gmx.at
                
                004367761973160
                """ + ANSI_PURPLE);
    }
}
