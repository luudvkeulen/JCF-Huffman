package com.luud;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static org.junit.Assert.*;

public class OperationsTest {
    Operations ops = new Operations();

    @Test
    public void countCharacters() throws Exception {
        ArrayList<Character> testCharacters = new ArrayList();
        for(int i = 0; i < 5; i++) {
            testCharacters.add('a');
        }

        for(int i = 0; i < 7; i++) {
            testCharacters.add('b');
        }

        for(int i = 0; i < 3; i++) {
            testCharacters.add('c');
        }

        HashMap<Character, Integer> result = ops.countCharacters(testCharacters);
        for(Map.Entry<Character, Integer> e : result.entrySet()) {
            if(e.getKey().equals('a')){
                if(e.getValue() != 5) {
                    fail();
                }
            }
        }
    }

    @Test
    public void sortCharactersTest() {
        HashMap<Character, Integer> countedCharacters = new HashMap<>();
        countedCharacters.put('a', 19);
        countedCharacters.put('b', 7);
        countedCharacters.put('c', 34);
        countedCharacters.put('e', 45);
        countedCharacters.put('f', 3);
        countedCharacters.put('g', 3);
        PriorityQueue<Map.Entry<Character, Integer>> pq = ops.sortCountedCharacters(countedCharacters);
        Map.Entry<Character, Integer> entry = pq.poll();
        Assert.assertTrue(entry.getValue() == 3 && entry.getKey() == 'f');
        Assert.assertTrue(pq.poll().getValue() == 3);
        Assert.assertTrue(pq.poll().getValue() == 7);
        Assert.assertTrue(pq.poll().getValue() == 19);
        Assert.assertTrue(pq.poll().getValue() == 34);
        Assert.assertTrue(pq.poll().getValue() == 45);
    }

}