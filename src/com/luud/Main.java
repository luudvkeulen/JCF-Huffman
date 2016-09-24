package com.luud;

import com.luud.models.HuffNode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    private static final Operations operations = new Operations();

    public static void main(String[] args) throws IOException {
        ArrayList<Character> characters = FileToArray(args[0]);
        ArrayList<HuffNode> countedNodes = operations.countCharacters(characters);
        PriorityQueue<HuffNode> sortedNodes = operations.sortCountedCharacters(countedNodes);
        System.out.println("HashMap:");
        for(HuffNode node : countedNodes) {
            System.out.println(node);
        }
        System.out.println("Sorted:");
        while(sortedNodes.size() > 0) {
            System.out.println(sortedNodes.poll());
        }
    }

    private static ArrayList<Character> FileToArray(String file) {
        ArrayList<Character> characters = new ArrayList<>();
        int character = 1;
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
