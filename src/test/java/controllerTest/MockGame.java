package controllerTest;
import tqs.prac.Main;
import tqs.prac.model.Board;
import tqs.prac.model.Cell;
import modelTest.MockBoard;
import modelTest.MockBoardValues;
import tqs.prac.controller.Game;


public class MockGame extends Game {
    public MockGame() {
        super();
    }

    public void setUpMockGame(int type)
    {   
        switch(type){
            case 1:
                MockBoard b = new MockBoard(1,3);
                b.setUpMockBoard(4); //matriu 3x3 amb 1 mina
                this.tauler = b;
                break;
            case 2:
                MockBoard board = new MockBoard(6,8);
                board.setUpMockBoard(3);  //matriu 3x3 amb 1 mina
                 for (int i=0; i<3; i++)
                {
                    for (int j=0; j<3; j++)
                    {
                        if (i!=2 || j!=0)
                        {
                        board.getCell(i,j).reveal();
                        }
                    
                    }
                }
                this.tauler = board;
                 break;  
            case 3:
                MockBoardValues t = new MockBoardValues(6,8);
                t.setUpMockBoard(3);
                for (int i=0; i<8; i++)
                {
                    for (int j=0; j<8; j++)
                    {
                        if ((i == 0 && j == 1) || (i == 2 && j == 3) || (i == 4 && j == 4) ||
                        (i == 6 && j == 2) || (i == 7 && j == 7)) {
                            t.getCell(i, j).reveal();}
                        if ((i == 4 && j == 1) || (i == 0 && j == 4) ||   (i == 7 && j == 0)) {  
                            t.getCell(i, j).toggleFlag();
                        }
                    }
                }   
                this.tauler = t;
                break; 
            case 4:
                MockBoardValues bb = new MockBoardValues(6,8);
                bb.setUpMockBoard(3);
                this.tauler = bb;
                break;
        }
    }
}