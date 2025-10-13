package tqs.prac.model;

public class Cell {
    private int value;
    private boolean reveal;
    public Cell()
    {
        value = 0;
        reveal = false;
    }
    public Cell(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
    public void setValue(int val)
    {
        value = val;
    }
    public boolean isRevelaed()
    {
        return reveal;
    }

    
}
