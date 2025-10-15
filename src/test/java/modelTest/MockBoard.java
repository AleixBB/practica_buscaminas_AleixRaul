package modelTest;

import tqs.prac.Main;
import tqs.prac.model.Board;
import tqs.prac.model.Cell;

public class MockBoard extends Board {


    public MockBoard(int nMines, int size) {
        super(nMines, size);
    }
    public void setUpMockBoard(int type)
    {
        switch (type) {
        case 1:
            this.matrix= new Cell[][] {
                { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(-1), new Cell(0) },
                { new Cell(0),  new Cell(-1), new Cell(0),  new Cell(0),  new Cell(0) },
                { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) },
                { new Cell(0),  new Cell(0),  new Cell(-1), new Cell(0),  new Cell(0) },
                { new Cell(-1), new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) }
            };
        break;
        case 2:
        this.matrix= new Cell[][] {
                { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(-1), new Cell(0), new Cell(-1), new Cell(0), new Cell(-1) },
                { new Cell(-1),  new Cell(0), new Cell(0),  new Cell(0),  new Cell(0), new Cell(0), new Cell(0), new Cell(-1)  },
                { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),new Cell(-1), new Cell(0), new Cell(0)  },
                { new Cell(0),  new Cell(0),  new Cell(0), new Cell(-1),  new Cell(-1), new Cell(0), new Cell(0), new Cell(0)  },
                { new Cell(-1), new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0), new Cell(0), new Cell(0), new Cell(-1)  },
                {new Cell(0), new Cell(-1),  new Cell(0),  new Cell(-1),  new Cell(0), new Cell(0), new Cell(0), new Cell(-1)},
                {new Cell(0), new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
                {new Cell(-1), new Cell(0),  new Cell(-1),  new Cell(0),  new Cell(-1), new Cell(0), new Cell(0), new Cell(0) }};   
        break;
 
            };
    }


}

    

