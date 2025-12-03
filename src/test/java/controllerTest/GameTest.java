package controllerTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import tqs.prac.controller.Game;
import tqs.prac.model.Cell;
import tqs.prac.model.Board;
import tqs.prac.Main;
import modelTest.MockBoard;
import modelTest.MockGenRandom;
import viewTest.MockView;
import viewTest.viewTest;

import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    void testConstructor(){
        Game game = new Game();
        assertFalse(game.getGameOver());
        assertFalse(game.getWin());
        assertNull(game.getBoard());
    }
    //amb aquests testos avaluem a la vegada els getters i setters
     @Test
    void testGameOver() {
        Game game = new Game();
        game.gameOver();
        assertTrue(game.getGameOver());
    }

    @Test
    void testWin() {
        Game game = new Game();
        game.win();

        assertTrue(game.getWin());
    }
    @Test
    void testGetSetBoard(){
        Game game = new Game();
        MockGenRandom rand = new MockGenRandom(null);
        Board b = new Board(3,3, rand);
        game.setBoard(b);

        assertEquals(b, game.getBoard());



    }
    /*TESTOS METODE ACT */

    //test1: flagejar una cel.la no win
    @Test
    public void testActFlagCellnWin(){
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(1, 3, random);
        mockB.setUpMockBoardplus(3);
        game.setBoard(mockB);
        game.act("FLAG", 1,0);
        assertFalse(game.getWin());
        assertFalse(game.getGameOver());
    }
    
    //test2: revelar cel.la amb mina
    @Test
    public void testActRevealCellwMine(){
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(1, 3, random);
        mockB.setUpMockBoardplus(3);
        game.setBoard(mockB);
        game.act("REVEAL", 2,0);
        assertFalse(game.getWin());
        assertTrue(game.getGameOver());
    }
    

    /*PAIRWISE TESTING al metode act
    CASOS: 
    - TIPUS DE CASELLA: BUIDA (0), NUMERADA O AMB MINA
    - ESTAT DE LA CASELLA: OCULTA, REVELADA O FLAGUEJADA
    - ACCIO DEL JUGADOR: FLAG, REVEAL
    En total tindriem 18 casos possibles. 
    Utilitzan pairwise només en tindrem 9:
    BUIDA, REVELADA, FLAG
    BUIDA, FLAGUEJADA, REVEAL
    NUMERADA, REVELADA, REVEAL
    NUMERADA, FLAGUEJADA, REVEAL
    NUMERADA, OCULTA, FLAG
    AMB MINA, FLAGUEJADA, FLAG
    AMB MINA, OCULTA, REVEAL
    AMB MINA, REVELADA, REVEAL
    BUIDA, OCULTA, REVEAL
*/
    @Test
    public void testActPairwise(){
    Game game = new Game();
    MockGenRandom random = new MockGenRandom(null);
    MockBoard mockB = new MockBoard(13, 8, random);
    mockB.setUpMockBoardplus(1);
    game.setBoard(mockB);
    
    //cas 1: BUIDA, REVELADA, FLAG
    try{
        game.act("FLAG", 7,7);
    }catch(Exception e){}
    //cas 2: BUIDA, FLAGUEJADA, REVEAL
    try{
    game.act("REVEAL", 7, 0);
    }catch(Exception e){}
    //cas 3: NUMERADA, REVELADA, REVEAL
    try{
        game.act("REVEAL", 0,1);
    }catch(Exception e) {}
    //cas 4: NUMERADA, FLAGUEJADA, REVEAL
    try{
    game.act("REVEAL", 0,4);
    }catch(Exception e){}

    //cas 5: NUMERADA, OCULTA, FLAG
    random = new MockGenRandom(null);
    mockB = new MockBoard(13, 8, random);
    mockB.setUpMockBoardplus(1);
    game.setBoard(mockB);
    game.act("FLAG", 6,6);
 
    //cas 6: AMB MINA, FLAGUEJADA, FLAG
    random = new MockGenRandom(null);
    mockB = new MockBoard(13, 8, random);
    mockB.setUpMockBoardplus(1);
    game.setBoard(mockB);
    game.act("FLAG", 4, 1);

    //cas 7; AMB MINA, OCULTA, REVEAL
    random = new MockGenRandom(null);
    mockB = new MockBoard(13, 8, random);
    mockB.setUpMockBoardplus(1);
    game.setBoard(mockB);
    game.act("REVEAL", 0,0);
 
    //cas 8: AMB MINA, REVELADA, REVEAL
    try{
        game.act("REVEAL", 0,0);
    }catch(Exception e){}

    //cas 9: BUIDA, OCULTA, REVEAL
    MockGenRandom rand = new MockGenRandom(null);
    mockB = new MockBoard(13, 8, rand);
    mockB.setUpMockBoardplus(1);
    game.setBoard(mockB);

    game.act("REVEAL", 6, 6);
}

    @Test
    //testejerem el flux d'una partida i mirarem els resultats
    public void startedGameTestFinishWin()
    {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(6, 8, random);
        mockB.setUpMockBoardplus(2);
        game.setBoard(mockB);
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(1);
        while (view.getnActions() > 0){
        game.startedGame();
        }
        assertFalse(game.getGameOver());
        assertTrue(game.getWin());
        
    }
    @Test
    public void startedGameTestFinishLose()
    {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(6, 8, random);
        mockB.setUpMockBoardplus(2);
        game.setBoard(mockB);
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(2);
        while ((view.getnActions() > 0)){
        game.startedGame();
        }
        assertTrue(game.getGameOver());
        assertFalse(game.getWin());
    }
    @Test
     public void startedGameTestFinishLoseMoreActions()
    {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(6, 8, random);
        mockB.setUpMockBoardplus(2);
        game.setBoard(mockB);
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(3);
        while ((view.getnActions() > 0)){
        game.startedGame();
        }
        assertTrue(game.getGameOver());
        assertFalse(game.getWin());
    }
    
    
    

}
