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
    {
        MockBoard mockB = new MockBoard(0,3);

        switch(type){
            case 1:
                mockB.setUpMockBoard(6); //matriu 3x3 amb 0 mines
                break;
            case 2:
                 mockB.setUpMockBoard(4); //matriu 3x3 amb 1 mines
                 break;


                

        }




    }





}