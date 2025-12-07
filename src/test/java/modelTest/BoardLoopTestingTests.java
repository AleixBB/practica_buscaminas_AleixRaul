package modelTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tqs.prac.model.Board;
import tqs.prac.model.Cell;
import tqs.prac.model.GenRandom;

public class BoardLoopTestingTests {
    //TESTOS DE CAIXA BLANCA
//LOOP TESTING


//clickAMina

@Test
void testClickAMina_0iteracions(){ //0 iteracions
    int size = 0;
    int nMines = 0;
    MockGenRandom random = new MockGenRandom(null);
    try
    {
        Board board = new Board(nMines, size, random);
        assertTrue(false);

    }catch(Exception e){}
}

@Test
void testClickAMina_1iteracio() {//1 iteració
    int size = 4;
    int nMines = 1;
    MockGenRandom mockGen = new MockGenRandom(0, 0);
    Board board = new Board(nMines, size, mockGen);
    board.putMinesintoBoard(2, 2);
    assertFalse(board.getCell(0, 0).isRevelaed());
    board.clickAMina();
    assertTrue(board.getCell(0, 0).isRevelaed());

}
@Test
void testClickAMina_MLessThanN() {
    int size = 5;
    int nMines = 4;
    MockGenRandom mockGen = new MockGenRandom(
        0, 0,
        1, 1,
        2, 2,
        2, 0
    );
    
    Board board = new Board(nMines, size, mockGen);
    board.putMinesintoBoard(4, 4); 
    
    assertFalse(board.getCell(0, 0).isRevelaed());
    assertFalse(board.getCell(1, 1).isRevelaed());
    assertFalse(board.getCell(2, 2).isRevelaed());
    assertFalse(board.getCell(2, 0).isRevelaed());
    
    board.clickAMina();
    assertTrue(board.getCell(0, 0).isRevelaed());
    assertTrue(board.getCell(1, 1).isRevelaed());
    assertTrue(board.getCell(2, 2).isRevelaed());
    assertTrue(board.getCell(2, 0).isRevelaed());
    
    int reveladas = 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).isRevelaed()) {
                reveladas++;
            }
        }
    }
    
    assertEquals(4, reveladas);
}
@Test
void testClickAMina_NMinusOne() { //n-1 iteracions
    
    int size = 5;
    int zonaProtegida = 9; // 3x3 alrededor de (2,2)
    int celdasDisponibles = size * size - zonaProtegida; // 25 - 9 = 16
    int nMines = celdasDisponibles - 1; // n-1 = 15
    
    
    MockGenRandom mockGen = new MockGenRandom(
        0,0, 0,1, 0,2, 0,3, 0,4,
        1,0, 1,4,
        2,0, 2,4,
        3,0, 3,4,
        4,0, 4,1, 4,2, 4,3
    );
    
    Board board = new Board(nMines, size, mockGen);
    board.putMinesintoBoard(2, 2);
    int mineCount = 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).getValue() == -1) {
                mineCount++;
            }
        }
    }
    assertEquals(15, mineCount);
    board.clickAMina();
    
    int reveladas = 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).isRevelaed()) {
                reveladas++;
            }
        }
    }
    
    assertEquals(15, reveladas);
    
    }
    @Test
void testClickAMina_NIterations() { //n iteracions
    
    int size = 5;
    int zonaProtegida = 4; 
    int nMines = size * size - zonaProtegida; // n = 21
    
    MockGenRandom mockGen = new MockGenRandom(
        0,2, 0,3, 0,4,
        1,2, 1,3, 1,4,
        2,0, 2,1, 2,2, 2,3, 2,4,
        3,0, 3,1, 3,2, 3,3, 3,4,
        4,0, 4,1, 4,2, 4,3, 4,4
    );
    Board board = new Board(nMines, size, mockGen);
    
    board.putMinesintoBoard(0, 0);
    int mineCount = 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).getValue() == -1) {
                mineCount++;
            }
        }
    }
    assertEquals(21, mineCount);
    board.clickAMina();
        int reveladas = 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).isRevelaed()) {
                reveladas++;
            }
        }
    }
    
    assertEquals(21, reveladas);
    }
@Test
void testClickAMina_NplusOne() {
    
    
    int size = 5;
    int zonaProtegida = 4; 
    int celdasDisponibles = size * size - zonaProtegida; // 21
    int nMines = celdasDisponibles + 1; // n+1 = 22
    
    int[] muchosValores = new int[50];
    for (int i = 0; i < 50; i++) {
        muchosValores[i] = 0;
    }
    MockGenRandom mockGen = new MockGenRandom(muchosValores);
    
    Board board = new Board(nMines, size, mockGen);
    
    try {
        board.putMinesintoBoard(0, 0);
        assertTrue(false);

    } catch (IllegalArgumentException e) {
        
        board.clickAMina();
    }
}
//putMinesIntoBoard
@Test
void testPutMinesIntoBoard_0iteracions(){ //0 iteracions
    int size = 4;
    int nMines = 0;
    MockGenRandom random = new MockGenRandom(null);
    Board board = new Board(nMines, size, random);
    board.putMinesintoBoard(1, 1);
    
    int mines = 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).getValue() == -1) {
                mines++;
            }
        }
    }
    assertEquals(0, mines);
    }


@Test
void testPutMinesIntoBoard_1iteracio() { //1 iteracio
    int size = 4;
    int nMines = 1;
    MockGenRandom random = new MockGenRandom(0,0);
    Board board = new Board(nMines, size, random);
    board.putMinesintoBoard(2, 2);
    int mines = 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).getValue() == -1) {
                mines++;
            }
        }
    }
    
    assertEquals(1, mines);
    assertEquals(-1, board.getCell(0, 0).getValue());
    }

@Test
void testPutMinesIntoBoard_2iteracions(){ //2 iteracions
    int size = 5;
    int nMines = 2;
    MockGenRandom mockGen = new MockGenRandom(
        0, 0,
        4, 4  
    );
    Board board = new Board(nMines, size, mockGen);    
    board.putMinesintoBoard(2, 2); 
    
    int mines = 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).getValue() == -1) {
                mines++;
            }
        }
    }
    
    assertEquals(2, mines);
    assertTrue(board.getCell(0, 0).getValue() == -1 || 
               board.getCell(4, 4).getValue() == -1);

}

@Test
void testPutMinesIntoBoard_4iteracions() {  // m < n i provem el continue de dins el loop
    int size = 4;
    int nMines = 2;
    
    MockGenRandom mockGen = new MockGenRandom(
        1, 1,  // continue
        1, 2,  // continue  
        3, 2,  // Válida - mina 1
        3, 3   //valida - mina 2
    );
    Board board = new Board(nMines, size, mockGen);
    board.putMinesintoBoard(1, 1); 
    int mines = 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).getValue() == -1) {
                mines++;
            }
        }
    }
    
    assertEquals(2, mines);
}

@Test
void testPutMinesIntoBoard_Nmenys1iteracions(){ //n-1 iteracions
    int size = 4; //16 cel.les
    int nMines = 6; //16-9 = n = 7
    MockGenRandom mockGen = new MockGenRandom(
        2, 0, 2, 1, 2, 2, 2, 3,
        3, 0, 3, 1, 3, 2
    );
    Board board = new Board(nMines, size, mockGen);
    
    board.putMinesintoBoard(0, 0);
    
    int mineCount = 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).getValue() == -1) {
                mineCount++;
            }
        }
    }
    
    assertEquals(6, mineCount);
    
}
@Test
void testPutMinesLoop_NIterations_Maxim() { //n iteracions
    int size = 5;
    int nMines = size * size - 9; // 25 - 9 = 16 minas (máximo)
    MockGenRandom mockGen = new MockGenRandom(
        0,0, 0,1, 0,2, 0,3, 0,4,
        1,0,                 1,4,
        2,0,                 2,4,
        3,0,                 3,4,
        4,0, 4,1, 4,2, 4,3, 4,4
    );
    
    Board board = new Board(nMines, size, mockGen);
    board.putMinesintoBoard(2, 2);
    int mines= 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).getValue() == -1) {
                mines++;
            }
        }
    }
    
    assertEquals(nMines, mines);
}
@Test
void testPutMinesLoop_Nplus1() { //n+1
 
    int size = 5;
    int maxMines = size * size - 9; // 25 - 9 = 16
    int nMines = maxMines + 1; // 17 > 16
    
    int[] muchosValores = new int[100];
    for (int i = 0; i < 100; i++) {
        muchosValores[i] = 0; 
    }
    
    MockGenRandom mockGen = new MockGenRandom(muchosValores);
    Board board = new Board(nMines, size, mockGen);
    
    try {
        board.putMinesintoBoard(2, 2);
        assertTrue(false);

    } catch (IllegalArgumentException e) {}
    }


    //insertValueIntoCells
    //bucle interior sempre son 9 iteracions (fixa)
    //bucle exterior anem testejant
    @Test
    void testInsertValue_ZeroIterations() { //0 iteracions
    try {
        int size = 0;
        int nMines = 0;
        GenRandom rand = new GenRandom();
        Board board = new Board(nMines, size, rand);
        board.putMinesintoBoard(0, 0);
        board.insertValueintoCells();
        } catch (IllegalArgumentException e) {
    }
    }

    @Test
    void testInsertValue_OneIterations(){ //1 iteracio (la del bucle exterior)
        int size = 1;
        int nMines = 0;
        GenRandom rand = new GenRandom();
        Board board = new Board(nMines, size, rand);
        board.putMinesintoBoard(0, 0);
        board.insertValueintoCells();
        assertEquals(0, board.getCell(0, 0).getValue());
    }
    @Test
    void testInsertValue_TwoIterations(){ //2 iteracions al bucle exterior
    int size = 2;
    int nMines = 0;
    
    // Mina en (0,0)
    MockGenRandom mockGen = new MockGenRandom();
    Board board = new Board(nMines, size, mockGen);
    board.putMinesintoBoard(1, 1);
    board.insertValueintoCells();    
    assertEquals(0, board.getCell(0, 0).getValue());
    assertEquals(0, board.getCell(0, 1).getValue());
    assertEquals(0, board.getCell(1, 0).getValue());
    assertEquals(0, board.getCell(1, 1).getValue());
    }

    @Test
    void testInsertValue_ThreeIterations(){ //3 iteracions
    int size = 4;  
    int nMines = 2;  
    
   
    MockGenRandom mockGen = new MockGenRandom(2, 0, 3, 3);
    Board board = new Board(nMines, size, mockGen);
    
    board.putMinesintoBoard(0, 2);
    board.insertValueintoCells();
    
    assertEquals(-1, board.getCell(2, 0).getValue());
    assertEquals(-1, board.getCell(3, 3).getValue());
    assertEquals(0, board.getCell(0, 0).getValue());

    }
    @Test
    void testInsertValue_NminusOneIteracions(){ //n-1 iteracions
    int size = 4;
    int zonaProtegida = 4; 
    int maxMines = size * size - zonaProtegida; // 16 - 4 = 12
    int nMines = maxMines - 1; // n-1 = 11 minas
    MockGenRandom mockGen = new MockGenRandom(
        2,0, 2,1, 2,2, 2,3,
        3,0, 3,1, 3,2, 3,3,
        0,2, 0,3, 1,2  
    );
    
    Board board = new Board(nMines, size, mockGen);
    board.putMinesintoBoard(0, 0); 
    board.insertValueintoCells();
    
    int mineCount = 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).getValue() == -1) {
                mineCount++;
            }
        }
    }
    
    assertEquals(nMines, mineCount);
    }


    @Test
    void testInsertValue_NIteracions(){ //n iteracions
    int size = 4;
    int zonaProtegida = 4; 
    int maxMines = size * size - zonaProtegida; // 16 - 4 = 12
    
    MockGenRandom mockGen = new MockGenRandom(
        2,0, 2,1, 2,2, 2,3,
        3,0, 3,1, 3,2, 3,3,
        0,2, 0,3, 1,2, 1,3
    );
    
    Board board = new Board(maxMines, size, mockGen);
    board.putMinesintoBoard(0, 0);
    board.insertValueintoCells();
    
    int mineCount = 0;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board.getCell(i, j).getValue() == -1) {
                mineCount++;
            }
        }
    }
    
    assertEquals(maxMines, mineCount);
    }

@Test
void testInsertValue_NplusOne() { 
    int size = 4;
    int zonaProtegida = 4; 
    int maxMines = size * size - zonaProtegida; // 16 - 4 = 12
    int nMines = maxMines + 1; 
    
    MockGenRandom mockGen = new MockGenRandom(
        2,0, 2,1, 2,2, 2,3,
        3,0, 3,1, 3,2, 3,3,
        0,2, 0,3, 1,2, 1,3
    );
    
    Board board = new Board(nMines, size, mockGen);
    
    try {
        board.putMinesintoBoard(0, 0);
        assertTrue(false);
    } catch (IllegalArgumentException e) {
    }
} 

}

    

