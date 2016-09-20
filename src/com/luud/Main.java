package com.luud;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    private static ArrayList<Character> characters;
    private static final Operations operations = new Operations();

    public static void main(String[] args) throws IOException {
        characters = new ArrayList();
        int character = 1;
        FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);
        while((character = br.read()) != -1) {
            System.out.println(character);
            characters.add((char)character);
        }
        HashMap<Character, Integer> countedCharacters = operations.countCharacters(characters);
        System.out.println("HashMap:");
        for(Map.Entry<Character, Integer> e : countedCharacters.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
        PriorityQueue<Map.Entry<Character, Integer>> sortedCharacters = operations.sortCountedCharacters(countedCharacters);
        System.out.println("Sorted:");
        while(!sortedCharacters.isEmpty()) {
            Map.Entry<Character, Integer> entry = sortedCharacters.poll();
            System.out.println(Character.getNumericValue(entry.getKey()) + ": " + entry.getValue());
        }
    }
}
