package com.luud;

import com.luud.models.CharacterWithIndex;
import com.luud.models.HuffNode;

import java.io.*;
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

            HuffNode parentNode = new HuffNode(0, tempLeftNode.weight + tempRightNode.weight, tempLeftNode, tempRightNode);

            nodes.offer(parentNode);
        }

        return nodes.peek();
    }

    public String lookup(HuffNode rootNode, Character c) {
        return rootNode.lookup(c, "", new StringBuilder()).toString();
    }

    public String encode(ArrayList<Character> chars, HuffNode rootNode) {
        String returnString = "";
        for(Character c : chars) {
            returnString += lookup(rootNode, c);
        }
        return returnString;
    }

    public String decode(String encoded, HuffNode rootNode) {
        String message = "";
        int index = 0;
        while(index < encoded.length()) {
            CharacterWithIndex result = rootNode.findCharacter(encoded, index, new CharacterWithIndex());
            if(result == null || result.character == null) break;
            message += result.character;
            index = result.index;
        }
        return message;
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
        BitSet bitSet = createBitset(encoded);
        try {
            FileOutputStream fos = new FileOutputStream("encoded.bin");
            fos.write(bitSet.toByteArray());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backToFile(String decoded) {
        PrintWriter pw;
        try {
            pw = new PrintWriter("text.txt");
            pw.print(decoded);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HuffNode importTree() {
        try {
            FileInputStream fis = new FileInputStream("boom.bin");
            ObjectInputStream oos = new ObjectInputStream(fis);
            HuffNode result = (HuffNode)oos.readObject();
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

    public String importEncoded() {
        try {
            FileInputStream fis = new FileInputStream("encoded.bin");
            DataInputStream dis = new DataInputStream(fis);
            File file = new File("encoded.bin");
            byte[] result = new byte[(int)file.length()];
            dis.readFully(result);
            BitSet bs = BitSet.valueOf(result);
            dis.close();
            return bitSetToString(bs);
        } catch (FileNotFoundException e) {
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public BitSet createBitset(String s) {
        BitSet bitSet = new BitSet(s.length());
        int bitcounter = 0;
        for(Character c : s.toCharArray()) {
            if(c.equals('1')) {
                bitSet.set(bitcounter);
            }
            bitcounter++;
        }
        return bitSet;
    }

    public String bitSetToString(BitSet set){
        String result = "";
        for(int i = 0; i < set.length(); i++) {
            if(set.get(i)) {
                result += "1";
            } else {
                result += "0";
            }
        }
        return result;
    }
}
