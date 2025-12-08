package modelTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tqs.prac.model.Board;
import tqs.prac.model.Cell;

public class BoardDecisionCoveragePutMinesTest {
    /*TESTOS DE CAIXA BLANCA
    DECISION COVERAGE
    
    */
    @Test
    void testPutMines_Decision1_DemasiadasMinas() {
        MockGenRandom random = new MockGenRandom();
        Board board = new Board(1,2,random);

        try{
            board.putMinesintoBoard(0, 0);
            
        }catch(Exception e){}
    }
    // TEST 2: nMines válid, zona NO protegida
    @Test
    void testPutMines_Decision2_ZonaNoProtegida() {
        MockGenRandom random = new MockGenRandom(0,0,1,1);
        Board board = new Board(2, 4, random);
        board.putMinesintoBoard(3, 3);
        
        // Verificar minas colocadas
        assertTrue(board.getCell(0, 0).getValue() == -1);
        assertTrue(board.getCell(1, 1).getValue() == -1);
        
        // Verificar zona protegida SIN minas
        assertFalse( board.getCell(2, 2).getValue() == -1);
        assertFalse(board.getCell(3, 3).getValue() == -1);
    }
    //Random cae a zona protegida
    @Test
    void testPutMines_Decision3_ZonaProtegida() {
        MockGenRandom random = new MockGenRandom(3,2, 1,0, 0,0); // (2,2) es zona protegida
        
        Board board = new Board(2, 4, random);
        board.putMinesintoBoard(3, 3);
        
        // Solo (0,0) debería tener mina
        assertTrue(board.getCell(0, 0).getValue() == -1);
        assertFalse(board.getCell(2, 2).getValue() == -1);
        assertFalse(board.getCell(3, 2).getValue() == -1);
    }

    //celda ja te mina 
    @Test
    void testPutMines_Decision4_CellAmbMina() {
        MockGenRandom random = new MockGenRandom(0,0, 0,0, 1,1);
        
        Board board = new Board(2, 4, random);
        board.putMinesintoBoard(3, 3);
        
        int minas = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getCell(i, j).getValue() == -1) {
                    minas++;
                }
            }
        }
        
        assertEquals(2, minas);
        assertTrue(board.getCell(0, 0).getValue() == -1);
        assertTrue(board.getCell(1, 1).getValue() == -1);
    }

    //sense mines
    @Test
    void testPutMines_Decision5_0Mines() {
        MockGenRandom random = new MockGenRandom(0,0,1,1);
        Board board = new Board(0, 4, random); // 0 minas
                
        board.putMinesintoBoard(2, 2);
            for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertFalse(board.getCell(i, j).getValue() == -1);
            }
        }
    }

}

