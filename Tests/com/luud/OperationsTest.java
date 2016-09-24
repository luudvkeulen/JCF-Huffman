package com.luud;

import com.luud.models.HuffNode;
import org.junit.*;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.*;

public class OperationsTest {
    Operations operations = new Operations();
    ArrayList<Character> characters = new ArrayList<>();

    @Before
    public void setUp() {
        String testString = "bananen";
        for(Character c : testString.toCharArray()) {
            characters.add(c);
        }
    }

    @After
    public void tearDown() {
        characters = null;
    }

    @Test
    public void countCharacters() throws Exception {
        //Expected
        ArrayList<HuffNode> expected = new ArrayList<>();
        expected.add(new HuffNode('b', 1));
        expected.add(new HuffNode('a', 2));
        expected.add(new HuffNode('n', 3));
        expected.add(new HuffNode('e', 1));
        //Result code
        ArrayList<HuffNode> result = operations.countCharacters(characters);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void sortCountedCharacters() throws Exception {
        //Expected
        PriorityQueue<HuffNode> expected = new PriorityQueue<>();
        expected.add(new HuffNode('n', 3));
        expected.add(new HuffNode('a', 2));
        expected.add(new HuffNode('b', 1));
        expected.add(new HuffNode('e', 1));

        //Result
        PriorityQueue<HuffNode> result = operations.sortCountedCharacters(operations.countCharacters(characters));

        while(expected.size() > 0) {
            assertTrue(expected.poll().weight == result.poll().weight);
        }
    }

    @Test
    public void createTree() throws Exception {
        PriorityQueue<HuffNode> result = operations.sortCountedCharacters(operations.countCharacters(characters));
        HuffNode root = operations.createTree(result);
        System.out.println(root.value);
        System.out.println(root.weight);
    }
}