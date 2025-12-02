package modelTest;
import tqs.prac.Main;
import tqs.prac.model.Board;
import tqs.prac.model.Cell;
import tqs.prac.model.GenRandom;

public class MockBoardValues extends Board {


    public MockBoardValues(int nMines, int size, GenRandom ran) {
        super(nMines, size, ran);
    }
    public void setUpMockBoard(int type)
    {
        switch (type) {
        case 1:
            this.matrix= new Cell[][] {
                { new Cell(1),  new Cell(1),  new Cell(1),  new Cell(0), new Cell(0) },
                { new Cell(1),  new Cell(-1), new Cell(1),  new Cell(0),  new Cell(0) },
                { new Cell(1),  new Cell(1),  new Cell(1),  new Cell(0),  new Cell(0) },
                { new Cell(0),  new Cell(0),  new Cell(0), new Cell(0),  new Cell(0) },
                { new Cell(0), new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) }
            };
        break;
        case 2:
            this.matrix= new Cell[][] { 
                { new Cell(0),  new Cell(0),  new Cell(0)},
                { new Cell(1),  new Cell(1), new Cell(0)},
                { new Cell(-1), new Cell(1),  new Cell(0)}};
            
        break;
        case 3:
            this.matrix = new Cell[][]{
                { new Cell(-1), new Cell( 1), new Cell( 1), new Cell( 1), new Cell( 1), new Cell(0),  new Cell(0),  new Cell(0) },
                { new Cell( 1), new Cell( 1), new Cell( 1), new Cell(-1), new Cell( 1), new Cell(1), new Cell(1), new Cell(1) },
                { new Cell( 0), new Cell( 0), new Cell( 1), new Cell( 1), new Cell( 1), new Cell(1), new Cell(-1), new Cell(1) },
                { new Cell( 1), new Cell( 1), new Cell( 1), new Cell( 0), new Cell( 0), new Cell(1), new Cell(1), new Cell(1) },
                { new Cell( 1), new Cell(-1), new Cell( 1), new Cell( 0), new Cell( 1), new Cell(1), new Cell(1), new Cell(0) },
                { new Cell( 1), new Cell( 1), new Cell( 1), new Cell( 0), new Cell( 1), new Cell(-1), new Cell(1), new Cell(0) },
                { new Cell( 0), new Cell( 1), new Cell( 1), new Cell( 1), new Cell( 1), new Cell(1), new Cell(1), new Cell(0) },
                { new Cell( 0), new Cell( 1), new Cell(-1), new Cell( 1), new Cell( 0), new Cell(0), new Cell(0), new Cell(0) }};
    break;
    
        }
    }
}