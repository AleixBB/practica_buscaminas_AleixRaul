package controllerTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import tqs.prac.controller.Game;
import tqs.prac.model.Cell;
import tqs.prac.model.Board;
import tqs.prac.Main;
import controllerTest.MockGame;
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
        Board b = new Board(3,3);
        game.setBoard(b);

        assertEquals(b, game.getBoard());



    }
    /*TESTOS METODE ACT */

    //test1: flagejar una cel.la no win
    @Test
    public void testActFlagCellnWin(){
        MockGame game = new MockGame();
        game.setUpMockGame(1);
        game.act("FLAG", 1,0);
        assertFalse(game.getWin());
        assertFalse(game.getGameOver());
    }
    
    //test2: revelar cel.la amb mina
    @Test
    public void testActRevealCellwMine(){
        MockGame game = new MockGame();
        game.setUpMockGame(1);
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
    BUIDA, OCULTA, REVEAL
    BUIDA, REVELADA, FLAG
    BUIDA, FLAGUEJADA, REVEAL
    NUMERADA, REVELADA, REVEAL
    NUMERADA, FLAGUEJADA, REVEAL
    NUMERADA, OCULTA, FLAG
    AMB MINA, FLAGUEJADA, FLAG
    AMB MINA, OCULTA, REVEAL
    AMB MINA, REVELADA, REVEAL*/
    @Test
    public void testActPairwise(){
    MockGame game = new MockGame();
    game.setUpMockGame(3);
    //cas 1: BUIDA, OCULTA, REVEAL
    game.act("REVEAL", 2, 1);
    assertFalse(game.getWin());
    assertFalse(game.getGameOver());
    //cas 2: BUIDA, REVELADA, FLAG
    try{
        game.act("FLAG", 7,7);
    }catch(Exception e){}
    //cas 3: BUIDA, FLAGUEJADA, REVEAL
    game.act("REVEAL", 7, 0);
    assertFalse(game.getWin());
    assertFalse(game.getGameOver());
    //cas 4: NUMERADA, REVELADA, REVEAL
    try{
        game.act("REVEAL", 0,1);
    }catch(Exception e) {}
    //cas 5: NUMERADA, FLAGUEJADA, REVEAL
    game.act("REVEAL", 0,4);
    assertFalse(game.getWin());
    assertFalse(game.getGameOver());
    //cas 6: NUMERADA, OCULTA, FLAG
    game.act("FLAG", 6,6);
    assertFalse(game.getWin());
    assertFalse(game.getGameOver());
    //cas 7: AMB MINA, FLAGUEJADA, FLAG
    game.act("FLAG", 4, 1);
    assertFalse(game.getWin());
    assertFalse(game.getGameOver());
    //cas 8; AMB MINA, OCULTA, REVEAL
    game.act("REVEAL", 0,0);
    assertFalse(game.getWin());
    assertTrue(game.getGameOver()); 
    //cas 9: AMB MINA, REVELADA, REVEAL
    try{
        game.act("REVEAL", 0,0);
    }catch(Exception e){}
    }

    @Test
    //testejerem el flux d'una partida i mirarem els resultats
    public void startedGameTestFinishWin()
    {
        MockGame game = new MockGame();
        game.setUpMockGame(4);
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(1);
        game.startedGame();
        assertFalse(game.getGameOver());
        assertTrue(game.getWin());
    }
    
    

}
