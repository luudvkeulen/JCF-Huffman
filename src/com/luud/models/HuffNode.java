package com.luud.models;

import java.io.Serializable;
import java.util.Map;

public class HuffNode implements Comparable<HuffNode>, Serializable{
    public int value;
    public int weight;
    public HuffNode leftNode;
    public HuffNode rightNode;
    public HuffNode parent;

    public HuffNode(int value, int weight, HuffNode leftNode, HuffNode rightNode, HuffNode parent) {
        this.value = value;
        this.weight = weight;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.parent = parent;
    }

    public HuffNode(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public void addWeight() {
        weight += 1;
    }

    public Map<Character, String> lookup(Character c, String current, Map<Character, String> map) {
        if(c == this.value) {
            map.put(c, current);
        }

        if(leftNode != null) {
            leftNode.lookup(c, current + "0", map);
        }

        if(rightNode != null) {
            rightNode.lookup(c, current + "1", map);
        }
        return map;
    }

    @Override
    public int compareTo(HuffNode node) {
        return weight - node.weight;
    }

    @Override
    public boolean equals(Object other){
        HuffNode otherNode = (HuffNode)other;
        return otherNode.value == value;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", (char)value, weight);
    }
}
