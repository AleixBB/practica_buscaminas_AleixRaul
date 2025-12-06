package tqs.prac.model;

public class Cell {
    private int value;
    private boolean reveal;
    private boolean flagged;

    public Cell() {
        this.value = 0;
        this.reveal = false;
        this.flagged = false;
    }

    public Cell(int value) {
        this.value = value;
        this.reveal = false;
        this.flagged = false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int val) {
        this.value = val;
    } 

    public boolean isRevelaed()  {
        return reveal;
    }
    public void reveal() {
        this.reveal = true;
    } 

    public boolean isFlagged() {
        return flagged;
    }

    public void toggleFlag() {
        this.flagged = !this.flagged;
    }
}
