package com.luud.models;

public class HuffNode implements Comparable<HuffNode>{
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
