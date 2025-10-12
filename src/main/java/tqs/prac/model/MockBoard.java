package tqs.prac.model;

import tqs.prac.main;

public class MockBoard extends board {

    private Cell[][] tauler;

    public MockBoard(int nMines, int size) {
        super(nMines, size);
    }
    public void setUpMockBoard(int type)
    {
        switch (type) {
        case 1:
            this.tauler= new Cell[][] {
                { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(-1), new Cell(0) },
                { new Cell(0),  new Cell(-1), new Cell(0),  new Cell(0),  new Cell(0) },
                { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) },
                { new Cell(0),  new Cell(0),  new Cell(-1), new Cell(0),  new Cell(0) },
                { new Cell(-1), new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) }
            };
        break;
}

    }
    
}
