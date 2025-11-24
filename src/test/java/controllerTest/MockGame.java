package controllerTest;
import tqs.prac.Main;
import tqs.prac.model.Board;
import tqs.prac.model.Cell;
import modelTest.MockBoard;
import tqs.prac.controller.Game;


public class MockGame extends Game {
    public MockGame() {
        super();
    }

    public void setUpMockGame(int type)
    {   MockBoard b = new MockBoard(1,3);
        switch(type){
            case 1:
                b.setUpMockBoard(4); //matriu 3x3 amb 1 mina
                
                break;
            case 2:
                 b.setUpMockBoard(4);  //matriu 3x3 amb 1 mina
                 for (int i=0; i<3; i++)
                {
                    for (int j=0; j<3; j++)
                    {
                        if (i!=2 || j!=0)
                        {
                        b.getCell(i,j).reveal();
                        }
                    
                    }
                }
                 break;      

        }
        this.tauler = b;

        




    }





}