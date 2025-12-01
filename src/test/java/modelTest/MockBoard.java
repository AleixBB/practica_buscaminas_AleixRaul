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
         case 3:
             this.matrix= new Cell[][] { //8x8 amb 10 mines
                { new Cell(-1),  new Cell(0),  new Cell(0),  new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0) },
                { new Cell(0),  new Cell(0), new Cell(0),  new Cell(0),  new Cell(0), new Cell(-1), new Cell(0), new Cell(0)  },
                { new Cell(0),  new Cell(-1),  new Cell(0),  new Cell(0),  new Cell(0),new Cell(0), new Cell(0), new Cell(0)  },
                { new Cell(0),  new Cell(0),  new Cell(0), new Cell(0),  new Cell(-1), new Cell(0), new Cell(0), new Cell(0)  },
                { new Cell(0), new Cell(-1),  new Cell(0),  new Cell(0),  new Cell(0), new Cell(0), new Cell(0), new Cell(0)  },
                {new Cell(0), new Cell(0),  new Cell(-1),  new Cell(0),  new Cell(0), new Cell(0), new Cell(0), new Cell(-1)},
                {new Cell(-1), new Cell(0),  new Cell(0),  new Cell(-1),  new Cell(0), new Cell(-1), new Cell(0), new Cell(0)},
                {new Cell(0), new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0), new Cell(0), new Cell(0), new Cell(0) }};
        break;
        case 4:
                this.matrix= new Cell[][] { //3x3 amb 1 mina
                { new Cell(0),  new Cell(0),  new Cell(0)},
                { new Cell(0),  new Cell(0), new Cell(0)},
                { new Cell(-1), new Cell(0),  new Cell(0)}
            
 
            };
        break;
        case 5:
            this.matrix= new Cell[][] { //5x5 amb 1 mina
            { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0), new Cell(0) },
            { new Cell(0),  new Cell(0), new Cell(0),  new Cell(0),  new Cell(0) },
            { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) },
            { new Cell(0),  new Cell(-1),  new Cell(0),  new Cell(0),  new Cell(0) },
            { new Cell(0), new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) }};
            break;
        
        case 6:
            this.matrix= new Cell[][] { //3x3 amb 0 mines
                { new Cell(0),  new Cell(0),  new Cell(0)},
                { new Cell(0),  new Cell(0), new Cell(0)},
                { new Cell(0), new Cell(0),  new Cell(0)}};

            break;
        
        case 7:
            this.matrix = new Cell[][]{  //8x8 amb 6 mines
                { new Cell(-1), new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) },
                { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(-1), new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) },
                { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(-1), new Cell(0) },
                { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) },
                { new Cell(0),  new Cell(-1), new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) },
                { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(-1), new Cell(0),  new Cell(0) },
                { new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) },
                { new Cell(0),  new Cell(0),  new Cell(-1), new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0),  new Cell(0) }};
            break;
            }
    }
    public void setUpMockBoardplus(int type)
    {
        switch (type){
            case 1:
                this.matrix= new Cell[][] { //8x8 amb 13 mines
                { new Cell(-1),  new Cell(0),  new Cell(0),  new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0) },
                { new Cell(0),  new Cell(0), new Cell(0),  new Cell(0),  new Cell(0), new Cell(-1), new Cell(0), new Cell(0)  },
                { new Cell(-1),  new Cell(-1),  new Cell(0),  new Cell(0),  new Cell(0),new Cell(0), new Cell(0), new Cell(0)  },
                { new Cell(0),  new Cell(0),  new Cell(0), new Cell(0),  new Cell(-1), new Cell(0), new Cell(0), new Cell(0)  },
                { new Cell(0), new Cell(-1),  new Cell(0),  new Cell(0),  new Cell(0), new Cell(0), new Cell(0), new Cell(0)  },
                {new Cell(0), new Cell(0),  new Cell(-1),  new Cell(0),  new Cell(0), new Cell(0), new Cell(0), new Cell(-1)},
                {new Cell(-1), new Cell(0),  new Cell(0),  new Cell(-1),  new Cell(0), new Cell(-1), new Cell(0), new Cell(0)},
                {new Cell(0), new Cell(0),  new Cell(0),  new Cell(-1),  new Cell(0), new Cell(0), new Cell(-1), new Cell(0) }};
                for (int i=0; i<8; i++)
                {
                    for (int j=0; j<8; j++)
                    {
                        if ((i == 0 && j == 1) || (i == 2 && j == 3) || (i == 4 && j == 4) ||
                        (i == 6 && j == 2) || (i == 7 && j == 7)) {
                            this.getCell(i, j).reveal();}
                        if ((i == 4 && j == 1) || (i == 0 && j == 4) ||   (i == 7 && j == 0)) {  
                            this.getCell(i, j).toggleFlag();
                        }
                    }
                }  
                break; 
            case 2:
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
                  
            case 3:
                this.matrix= new Cell[][] { //3x3 amb 1 mina
                { new Cell(0),  new Cell(0),  new Cell(0)},
                { new Cell(0),  new Cell(0), new Cell(0)},
                { new Cell(-1), new Cell(0),  new Cell(0)}
            
 
            };
        }
    }


}

    

