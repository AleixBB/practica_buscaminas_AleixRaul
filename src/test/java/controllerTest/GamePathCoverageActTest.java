package controllerTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tqs.prac.controller.Game;
import tqs.prac.model.Cell;
import tqs.prac.model.Board;
import modelTest.MockBoard;
import modelTest.MockGenRandom;

public class GamePathCoverageActTest {
    /*TESTOS DE CAIXA BLANCA
    PATH COVERAGE
    amb els seguents testos analitzarem TOTS els camins 
    viables i possibles que pot prendre el metode act
    */

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
    public void testAct_Path6_Variant_REVEAL_Mina_() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7);
        game.setBoard(mockBoard);
        game.act("REVEAL", 0, 0);
        assertTrue(game.getGameOver()); 
        assertFalse(game.getWin());
        assertTrue(game.getBoard().getCell(0, 0).isRevelaed()); 
    }

     @Test
    public void testAct_Path7_Variant_REVEAL_LeadsToWin() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7);
        game.setBoard(mockBoard);
        game.act("REVEAL", 3, 0);        
        assertTrue(game.getWin()); 
        assertFalse(game.getGameOver()); 
        assertTrue(mockBoard.getCell(3, 0).isRevelaed()); 
    }
    @Test
    public void testAct_Path_REVEAL_CeldaNormalNoGanadora() {
    Game game = new Game();
    MockGenRandom random = new MockGenRandom(null);
    MockBoard mockBoard = new MockBoard(2, 4, random);
    mockBoard.setUpMockBoardplus(7);
    game.setBoard(mockBoard);
    
    game.act("REVEAL", 1, 1);
    
    assertFalse(game.getGameOver());  // No ha acabat
    assertFalse(game.getWin());       // No ha guanyat
    assertTrue(mockBoard.getCell(1, 1).isRevelaed());  // Però està revelada
}
//a partir d'aqui venen testos que no passen les precondicions
@Test
public void testAct_AccionInvalida() {
    Game game = new Game();
    MockGenRandom random = new MockGenRandom(null);
    MockBoard mockBoard = new MockBoard(2, 4, random);
    mockBoard.setUpMockBoardplus(7);
    game.setBoard(mockBoard);
    try{
        game.act("ACCIOINVALIDA", 1,1);
        assertTrue(false);
    }catch(Exception e){}
    
    assertFalse(game.getGameOver());
    assertFalse(game.getWin());
    assertFalse(mockBoard.getCell(1, 1).isRevelaed());
    assertFalse(mockBoard.getCell(1, 1).isFlagged());
}
@Test
    public void testAct_CoordenadaXY_ForaRang() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7);
        game.setBoard(mockBoard);
        
        // X negativa
        try{
            game.act("FLAG", -1, 1);

        }catch(Exception e ){}
        
        
        // Y negativa
        try{
            game.act("FLAG", 1, -1);

        }catch(Exception e){}
        
    }
    @Test
    public void testAct_CoordenadaXY_MesqSize() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7);
        game.setBoard(mockBoard);
        
        // X fora rang
        try{
            game.act("FLAG", 5, 1);

        }catch(Exception e ){}
        
        
        // Y fora rang
        try{
            game.act("FLAG", 3, 4);

        }catch(Exception e){}
        
    }
    @Test
    public void testAct_GameOver() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7);
        game.setBoard(mockBoard);
        game.gameOver();
        try{
            game.act("FLAG", 1, 1);

        }catch(Exception e ){}
        
    }
    @Test
    public void testAct_Win() {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockBoard = new MockBoard(2, 4, random);
        mockBoard.setUpMockBoardplus(7);
        game.setBoard(mockBoard);
        game.win();
        try{
            game.act("FLAG", 1, 1);

        }catch(Exception e ){}
        
    }
    

        
      
        
    
}




 