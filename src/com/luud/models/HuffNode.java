package com.luud.models;

import java.io.Serializable;

public class HuffNode implements Comparable<HuffNode>, Serializable {
    public final int value;
    public int weight;
    public HuffNode leftNode;
    public HuffNode rightNode;

    public HuffNode(int value, int weight, HuffNode leftNode, HuffNode rightNode) {
        this.value = value;
        this.weight = weight;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public HuffNode(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public void addWeight() {
        weight += 1;
    }

    //region Encoding and decoding
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
    //endregion

    //region overrides
    @Override
    public int compareTo(HuffNode node) {
        return weight - node.weight;
    }

    @Override
    public boolean equals(Object other){
        if (!HuffNode.class.isAssignableFrom(other.getClass())) return false;
        HuffNode otherNode = (HuffNode)other;
        return otherNode.value == value;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", (char)value, weight);
    }
    //endregion
}
