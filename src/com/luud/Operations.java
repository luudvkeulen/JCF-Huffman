package com.luud;

import com.luud.models.HuffNode;

import java.io.*;
import java.util.*;

public class Operations {
    private static int TreeDepth = 0;

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

            //System.out.println("Parent: " + (char)parentNode.value + ":" + parentNode.weight + " Left: " + (char)tempLeftNode.value + ":" + tempLeftNode.weight + " Right: " + (char)tempRightNode.value + ":" + tempRightNode.weight);
            nodes.offer(parentNode);
            TreeDepth++;
        }

        return nodes.peek();
    }

    public String lookup(HuffNode rootNode, Character c) {
        Map<Character, String> result = rootNode.lookup(c, "", new HashMap<>());
        String resultString = result.get(c);
        return resultString;
    }

    public String encode(ArrayList<Character> chars, HuffNode rootNode) {
        String returnString = "";
        for(Character c : chars) {
            returnString += lookup(rootNode, c);
        }
        return returnString;
    }

    public String encode(HuffNode rootNode) {
        return "";
    }

    public void exportToFile(HuffNode rootNode) {
        try {
            FileOutputStream fos = new FileOutputStream("boom.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(rootNode);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportToFile(String encoded) {
        try {
            FileOutputStream fos = new FileOutputStream("encoded.bin");
            fos.write( encoded.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HuffNode importFile() {
        try {
            FileInputStream fis = new FileInputStream("boom.bin");
            ObjectInput oos = new ObjectInputStream(fis);
            HuffNode result = (HuffNode)oos.readObject();
            System.out.println(result);
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
