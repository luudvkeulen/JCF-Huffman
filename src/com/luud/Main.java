package com.luud;

import com.luud.models.HuffNode;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    private static final Operations operations = new Operations();

    public static void main(String[] args) throws IOException {
        if(args.length > 1) return;
        if(args.length > 0) {
            ArrayList<Character> characters = FileToArray(args[0]);
            ArrayList<HuffNode> countedNodes = operations.countCharacters(characters);
            PriorityQueue<HuffNode> sortedNodes = operations.sortCountedCharacters(countedNodes);
            HuffNode rootNode = operations.createTree(sortedNodes);
            operations.exportToFile(rootNode);
            String encoded = operations.encode(characters, countedNodes, rootNode);
            operations.exportToFile(encoded);
            System.out.println(encoded);
        } else {
            HuffNode root = operations.importTree();
            String encoded = operations.importEncoded();
            System.out.println(encoded);
            String decoded = operations.decode(encoded, root);
            operations.backToFile(decoded);
        }
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
