package huffman;

public class HuffKnoop {
    private static Character character;
    private static Integer amount;
    private static HuffKnoop leftChild, rightChild;

    public static Character getCharacter() {
        return character;
    }

    public static Integer getAmount() {
        return amount;
    }

    public static HuffKnoop getLeftChild() {
        return leftChild;
    }

    public static void setLeftChild(HuffKnoop newLeftChild) {
        leftChild = newLeftChild;
    }

    public static HuffKnoop getRightChild() {
        return rightChild;
    }

    public static void setRightChild(HuffKnoop newRightChild) {
        rightChild = newRightChild;
    }

    public HuffKnoop(Character character, Integer amount){
        this.character = character;
        this.amount = amount;
    }

    public HuffKnoop(HuffKnoop leftChild) {
        this.leftChild = leftChild;
    }

    public HuffKnoop(){}

    public static HuffKnoop create(Character character ,Integer amount) {
        HuffKnoop newKnoop;
        if(getCharacter() == null) {
            newKnoop = new HuffKnoop(character, amount);
        } else {
            newKnoop = new HuffKnoop(getCharacter(), getAmount());
            if(getLeftChild() == null) {
                newKnoop.setLeftChild(new HuffKnoop(character, amount));
            } else if(getRightChild() == null) {
                newKnoop.setRightChild(new HuffKnoop(character, amount));
            }
        }
        return newKnoop;
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
