package com.luud;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Operations {
    public HashMap<Character, Integer> countCharacters (ArrayList<Character> characters) {
        HashMap<Character, Integer> countedCharacters = new HashMap();
        for(Character c : characters) {
            if(!countedCharacters.containsKey(c)) {
                countedCharacters.put(c, 1);
            } else {
                Integer amount = countedCharacters.get(c);
                amount++;
                countedCharacters.put(c, amount);
            }
        }
        return countedCharacters;
    }

    public PriorityQueue<Map.Entry<Character, Integer>> sortCountedCharacters(HashMap<Character, Integer> countedCharacters) {
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<Map.Entry<Character, Integer>>(countedCharacters.size(), new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if(o2.getValue().equals(o1.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        pq.addAll(countedCharacters.entrySet());

        return pq;
    }
}
