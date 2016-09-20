package huffman;

public class HuffKnoop {
    private Character character;
    private Integer amount;

    public Character getCharacter() {
        return character;
    }

    public Integer getAmount() {
        return amount;
    }

    public HuffKnoop(Character character, Integer amount){
        this.character = character;
        this.amount = amount;
    }

    public static HuffKnoop create(Character character ,Integer amount) {
        return new HuffKnoop(character, amount);
    }

    public void addOne() {
        amount += 1;
    }

    @Override
    public boolean equals(Object obj) {
        HuffKnoop knoop = (HuffKnoop)obj;
        if (obj == null) {
            return false;
        }
        if (this.character.equals(knoop.character)) {
            return true;
        } else {
            return false;
        }
    }
}
