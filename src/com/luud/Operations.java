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

    public HuffNode createTree(PriorityQueue<HuffNode> nodes) {
        while(nodes.size() > 1) {
            HuffNode tempLeftNode = nodes.poll();
            HuffNode tempRightNode = nodes.poll();

            HuffNode parentNode = new HuffNode(0, tempLeftNode.weight + tempRightNode.weight, tempLeftNode, tempRightNode, null);
            tempLeftNode.parent = parentNode;
            tempRightNode.parent = parentNode;

            System.out.println("Parent: " + (char)parentNode.value + ":" + parentNode.weight + " Left: " + (char)tempLeftNode.value + ":" + tempLeftNode.weight + " Right: " + (char)tempRightNode.value + ":" + tempRightNode.weight);
            nodes.offer(parentNode);
        }

        return nodes.peek();
    }
}
