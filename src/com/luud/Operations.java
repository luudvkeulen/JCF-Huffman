package com.luud;

import com.luud.models.HuffNode;

import java.util.*;

public class Operations {
    public ArrayList<HuffNode> countCharacters (ArrayList<Character> characters) {
        ArrayList<HuffNode> nodes = new ArrayList<>();
        for(Character c : characters) {
            HuffNode currentNode = new HuffNode(c, 1);
            if(!nodes.contains(currentNode)) {
                nodes.add(currentNode);
            } else {
                HuffNode node = nodes.get(nodes.indexOf(currentNode));
                node.addWeight();
            }
        }
        return nodes;
    }

    public PriorityQueue<HuffNode> sortCountedCharacters(ArrayList<HuffNode> nodes) {
        PriorityQueue<HuffNode> queue = new PriorityQueue<>();
        for(HuffNode n : nodes) {
            queue.offer(n);
        }

        return queue;
    }

    public void createTree(PriorityQueue<HuffNode> nodes) {

    }
}
