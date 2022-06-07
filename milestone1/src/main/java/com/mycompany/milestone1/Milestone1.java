/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.milestone1;

import java.cecs429.documents.*;
import java.cecs429.queries.*;
import java.cecs429.indexes.*;
import java.cecs429.text.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Milestone1 {

    public static void main(String[] args) {
       
        Scanner scan = new Scanner(System.in);
        String dir;
                
        System.out.print("Enter something to search: ");
        dir = scan.nextLine();
        
        DocumentCorpus corpus = DirectoryCorpus.loadJsonDirectory(Paths.get(dir), ".json");
        long sTime = System.nanoTime();
        
        
        
    }
}
