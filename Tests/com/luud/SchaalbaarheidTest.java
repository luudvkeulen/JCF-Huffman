package com.luud;

import com.luud.models.HuffNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class SchaalbaarheidTest {
    private static final Integer aantaalWoorden = 10000;
    private static final String characterslist = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final Operations operations = new Operations();
    private ArrayList<Character> characters = new ArrayList<>();

    Random r = new Random();

    @Before
    public void setUp() {
        for(int i = 0; i < aantaalWoorden; i++) {
            characters.add(characterslist.charAt(r.nextInt(characterslist.length())));
        }
    }

    @After
    public void tearDown() {
        characters = null;
    }

    @Test
    public void schaalbaarheidsTest() throws Exception {
        //Encoden
        Long timeBefore = System.currentTimeMillis();
        ArrayList<HuffNode> countedNodes = operations.countCharacters(characters);
        PriorityQueue<HuffNode> sortedNodes = operations.sortCountedCharacters(countedNodes);
        HuffNode rootNode = operations.createTree(sortedNodes);
        operations.exportToFile(rootNode);
        String encoded = operations.encode(characters, countedNodes, rootNode);
        operations.exportToFile(encoded);
        //Decoden
        Long timeAfterEncoding = System.currentTimeMillis();
        HuffNode root = operations.importTree();
        String encoded2 = operations.importEncoded();
        System.out.println(encoded2);
        String decoded = operations.decode(encoded2, root);
        operations.backToFile(decoded);
        Long timeAfterEncodingDecoding = System.currentTimeMillis();
        Long encodingTime = (timeAfterEncoding - timeBefore);
        Long decodingTime = (timeAfterEncodingDecoding - timeAfterEncoding);
        System.out.println("Encoden: " + encodingTime + "ms");
        System.out.println("Decoding: " + decodingTime + "ms");
        System.out.println("Total: " + (timeAfterEncodingDecoding - timeBefore) + "ms");
    }
}