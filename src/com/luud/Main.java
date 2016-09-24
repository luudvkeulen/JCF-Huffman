package com.luud;

import com.luud.models.HuffNode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    private static final Operations operations = new Operations();

    public static void main(String[] args) throws IOException {
        if(args.length < 1 || args.length > 1) return;
        String argument = args[0];
        //if(args[0])
        ArrayList<Character> characters = FileToArray(argument);
        ArrayList<HuffNode> countedNodes = operations.countCharacters(characters);
        PriorityQueue<HuffNode> sortedNodes = operations.sortCountedCharacters(countedNodes);
        HuffNode rootNode = operations.createTree(sortedNodes);
        operations.exportToFile(rootNode);
        operations.importFile();
        String encoded = operations.encode(characters, rootNode);
        System.out.println(encoded);
        operations.exportToFile(encoded);
    }

    private static ArrayList<Character> FileToArray(String file) {
        ArrayList<Character> characters = new ArrayList<>();
        int character;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while((character = br.read()) != -1) {
                characters.add((char)character);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return characters;
    }
}
