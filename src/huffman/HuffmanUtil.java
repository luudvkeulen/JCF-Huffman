package huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class HuffmanUtil {
    public static List<HuffKnoop> getFrequentieHuffKnoop(String data) {
        List<HuffKnoop> frequentieList = new ArrayList<>();
        for(Character c : data.toCharArray()) {
            HuffKnoop compareKnoop = new HuffKnoop(c, 1);
            if(!frequentieList.contains(compareKnoop)) {
                frequentieList.add(new HuffKnoop(c, 1));
            } else {
                HuffKnoop knoop = frequentieList.get(frequentieList.indexOf(compareKnoop));
                knoop.addOne();
            }
        }
        return frequentieList;
    }

    public static HashMap<Character,Integer> getFrequentie(String data) {
        HashMap<Character, Integer> countedCharacters = new HashMap();
        for(Character c : data.toCharArray()) {
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

    public static List<HuffKnoop> getKoppel(List<HuffKnoop> list, int index) {
        return null;
    }

    public static PriorityQueue<HuffKnoop> getHuffmanBoomRij(List<HuffKnoop> frequentieHuffKnoop) {
        return null;
    }
}
