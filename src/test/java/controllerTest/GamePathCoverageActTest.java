package controllerTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tqs.prac.controller.Game;
import tqs.prac.model.Cell;
import tqs.prac.model.Board;
import modelTest.MockBoard;
import modelTest.MockGenRandom;

public class GamePathCoverageActTest {
    @Test
    public void testAct_Path1_FLAG_CeldaNoRevelada() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7); 
        
        game.setBoard(mockBoard);
        game.act("FLAG", 1,1);
        assertTrue(mockBoard.getCell(1, 1).isFlagged());
    }

   @Test
    public void testAct_Path2_FLAG_CeldaRevelada() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7);
        game.setBoard(mockBoard);
        game.act("REVEAL", 0, 1);
        try{
            game.act("FLAG", 0,1);
            assertTrue(false);
        }catch(Exception e) {}
        
        assertFalse(game.getGameOver());
        assertFalse(game.getWin());
    } 
    @Test
    public void testAct_Path3_REVEAL_CeldaRevelada_() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7);
        game.setBoard(mockBoard);

        game.act("REVEAL", 0,1);
        try{
            game.act("REVEAL", 0,1);
            assertTrue(false);
        }catch(Exception e){}
        
        
    }
     @Test
    public void testAct_Path4_REVEAL_CeldaNoReveladaPeroFlagged() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7);
        game.setBoard(mockBoard);
        game.act("FLAG", 1, 2);
        try{
            game.act("REVEAL", 1,2);
            assertTrue(false);
        }catch(Exception e) {}
    }
    @Test
    public void testAct_Path5_REVEAL_CeldaNoReveladaNoFlagged() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7);
        game.setBoard(mockBoard);
        game.act("REVEAL",1,2);
        assertTrue(game.getBoard().getCell(1, 2).isRevelaed());
    }

    @Test
    public void testAct_Path5_Variant_REVEAL_Mina_() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7);
        game.setBoard(mockBoard);
        game.act("REVEAL", 0, 0);
        assertTrue(game.getGameOver()); // Debe terminar el juego
        assertFalse(game.getWin()); // No se gana al pisar mina
        assertTrue(game.getBoard().getCell(0, 0).isRevelaed()); // La mina debe revelarse
    }

     @Test
    public void testAct_Path5_Variant_REVEAL_LeadsToWin() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7);
        game.setBoard(mockBoard);
        game.act("REVEAL", 3, 0);        
        assertTrue(game.getWin()); // Debe ganar
        assertFalse(game.getGameOver()); // No debe terminar por mina
        assertTrue(mockBoard.getCell(3, 0).isRevelaed()); // Debe revelarse
    }

        
      
        
        

}




 