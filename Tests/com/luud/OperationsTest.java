package com.luud;

import com.luud.models.HuffNode;
import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

public class OperationsTest {
    private final Operations operations = new Operations();
    private ArrayList<Character> characters = new ArrayList<>();

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
    public void countCharactersTest() throws Exception {
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
    public void sortCountedCharactersTest() throws Exception {
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
    public void createTreeTest() throws Exception {
        PriorityQueue<HuffNode> result = operations.sortCountedCharacters(operations.countCharacters(characters));
        HuffNode root = operations.createTree(result);
        //Create instances of first node
        HuffNode nNode = new HuffNode('n', 3, null, null);
        HuffNode fourNode = new HuffNode(0, 4, null, null);
        HuffNode expected = new HuffNode(0, 7, nNode, fourNode);
        //Second node
        nNode.leftNode = new HuffNode('a', 2, null, null);
        HuffNode twoNode = new HuffNode(0, 2, null, null);
        nNode.rightNode = twoNode;
        //Third node
        twoNode.rightNode = new HuffNode('e', 1, null, null);
        twoNode.leftNode = new HuffNode('b', 1, null, null);
        //Assert
        assertEquals(expected, root);
    }

    private String lookupCharacter(Character c) {
        PriorityQueue<HuffNode> result = operations.sortCountedCharacters(operations.countCharacters(characters));
        HuffNode root = operations.createTree(result);
        return operations.lookup(root, c);
    }

    @Test
    public void lookupCharactersTest() {
        String expectedBString = "110";
        String expectedAString = "10";
        String expectedNString = "0";
        String expectedEString = "111";
        assertEquals(expectedBString, lookupCharacter('b'));
        assertEquals(expectedAString, lookupCharacter('a'));
        assertEquals(expectedNString, lookupCharacter('n'));
        assertEquals(expectedEString, lookupCharacter('e'));
    }

    @Test
    public void encodeTest() {
        String expected = "1101001001110";
        ArrayList<HuffNode> uniqueCharacters = operations.countCharacters(characters);
        HuffNode root = operations.createTree(operations.sortCountedCharacters(uniqueCharacters));
        String result = operations.encode(characters, uniqueCharacters, root);
        assertEquals(expected, result);
    }
}