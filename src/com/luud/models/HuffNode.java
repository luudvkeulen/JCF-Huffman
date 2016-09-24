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
                //result = new CharacterWithIndex((char)this.value, index);
                //result.put((char)this.value, index);
            }
        } else  if(substring.equals("1")) {
            if(rightNode != null) {
                rightNode.findCharacter(encoded, index + 1, result);
            } else {
                result.index = index;
                result.character = (char)this.value;
                //result = new CharacterWithIndex((char)this.value, index);
               //result.put((char)this.value, index);
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
