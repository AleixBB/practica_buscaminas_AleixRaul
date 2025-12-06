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


public class GameConditionCoverageTest {
    //aplicat a startedGame
    // 3 condiciones: firstClick, win, gameOver
    // Necessitem cubrir todas las combinaciones true/false
        
    @Test
    void testStartedGame_Case1_firstClickTrue_winFalse_gameOverFalse() {
        // Cas A: firstClick=true, win=false, gameOver=false
        Game game = new Game();
        game.setFirstClick(true);
        
        MockGenRandom random = new MockGenRandom(0,0, 1,2);
        MockBoard mockB = new MockBoard(2, 4, random);
        mockB.setUpMockBoardplus(5);
        game.setBoard(mockB);
        
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(5); 
        
        game.startedGame();
        
        assertFalse(game.getWin());
        assertFalse(game.getGameOver());
    }
    @Test
    void testStartedGame_Case2_firstClickTrue_winFalse_gameOverTrue() {
        // Caso B: firstClick=true, win=false, gameOver=true
        Game game = new Game();
        
        MockGenRandom random = new MockGenRandom(0,0,1,2);
        MockBoard mockB = new MockBoard(2, 4, random);
        mockB.setUpMockBoardplus(5);
        game.setBoard(mockB);
        
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(6);
        while (view.getnActions()>0) //recordem que hem posat que no es pot perdre al primer clic
        {
        game.startedGame();
        }  
        assertTrue(game.getGameOver());
        assertFalse(game.getWin());
    }
    @Test
    void testStartedGame_Case3_firstClickTrue_winTrue_gameOverFalse() {
        Game game = new Game();
        game.setFirstClick(true);
        
        MockGenRandom random = new MockGenRandom(0,0);
        MockBoard mockB = new MockBoard(1, 4, random);
        mockB.setUpMockBoardplus(5);
        game.setBoard(mockB);
        
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(5);
        game.startedGame();
        
        assertTrue(game.getWin());
        assertFalse(game.getGameOver());
    }
     @Test
    void testStartedGame_Case4_firstClickFalse_winFalse_gameOverFalse() {
        Game game = new Game();
        game.setFirstClick(false); // No es primer click
        
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(2, 4, random);
        mockB.setUpMockBoardplus(6);
        game.setBoard(mockB);
        
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(5);
        
        game.startedGame();
        assertFalse(game.getWin());
        assertFalse(game.getGameOver());
    }
    @Test
    void testStartedGame_Case5_firstClickFalse_winFalse_gameOverTrue() {
        Game game = new Game();
        game.setFirstClick(false);
        
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(2, 4, random);
        mockB.setUpMockBoardplus(6);
        game.setBoard(mockB);
        
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(7);
        
        game.startedGame();
        
        assertFalse(game.getWin());
        assertTrue(game.getGameOver());
    }
    @Test
    void testStartedGame_Case6_firstClickFalse_winTrue_gameOverFalse() {
        Game game = new Game();
        game.setFirstClick(false);
        
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(2, 4, random);
        mockB.setUpMockBoardplus(6);
        game.setBoard(mockB);
        
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(8);
        game.startedGame();
        assertTrue(game.getWin());
        assertFalse(game.getGameOver());
    }
    
    
}
