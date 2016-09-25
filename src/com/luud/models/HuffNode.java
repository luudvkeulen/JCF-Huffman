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

    public StringBuilder lookup(Character c, String current, StringBuilder result) {
        if(c == this.value) {
            result.append(current);
        }

        if(leftNode != null) {
            leftNode.lookup(c, current + "0", result);
        }

        if(rightNode != null) {
            rightNode.lookup(c, current + "1", result);
        }
        return result;
    }

    public CharacterWithIndex findCharacter(String encoded, int index, CharacterWithIndex result) {
        if(index >= encoded.length()) {
            result.character = (char)this.value;
            result.index = index;
            return result;
        }
        String substring = Character.toString(encoded.charAt(index));
        if(substring.equals("0")) {
            if(leftNode != null) {
                leftNode.findCharacter(encoded, index + 1, result);
            } else {
                result.index = index;
                result.character = (char)this.value;
            }
        } else  if(substring.equals("1")) {
            if(rightNode != null) {
                rightNode.findCharacter(encoded, index + 1, result);
            } else {
                result.index = index;
                result.character = (char)this.value;
            }
        }
        return result;
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
