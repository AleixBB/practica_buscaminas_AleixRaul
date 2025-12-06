package modelTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 
import tqs.prac.model.Board;
import tqs.prac.model.Cell;
import tqs.prac.model.GenRandom;
import tqs.prac.Main;


public class BoardTest {
    //test que comprova el constructor per defecte per la matrix
    @Test
    void testInitpt1()
    {
    int mines = 4;
    int size = 6;
    MockGenRandom  rand = new MockGenRandom(null);
    Board tauler = new Board(mines, size, rand);
    for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertEquals(0 ,tauler.getCell(i, j).getValue());
            }
        }
    }

    //test que comprova què passa si s'introdueixen valors fora de rang en nMines i size. Haurien de saltar excepcions.
    @Test
    void testInitnegatius()
    {
        MockGenRandom  rand = new MockGenRandom(null);
        //valorsfora rang
        try{
            Board tauler=new Board(-2, -4, rand);
            assertTrue(false);

        //valor frontera i valor fora rang  
        }catch (Exception e){}
        try{
            Board tauler=new Board(-1, 0, rand);
            assertTrue(false);

        //valor correcte i valor fora rang
        }catch (Exception e){}
        try{
            Board tauler=new Board(2, -1, rand);
            assertTrue(false);

        //valor correcte i valor fora rang   
        }catch (Exception e){}
        try{
            Board tauler=new Board(0, 0, rand);
            assertTrue(false);
        
        //valor frontera
        }catch (Exception e){}
        
    }

    //test que comprova que el nombre de mines que s'ha introduit al tauler de forma random es correcte
    @Test
    void testRandommines()
    {
    int size = 6;
    int nMines = 10;
    int contador = 0;
    GenRandom  rand = new GenRandom();
    Board tauler = new Board(nMines, size, rand);
    tauler.putMinesintoBoard(9,9);
    for (int i=0; i<size; i++)
    {
        for (int j=0; j<size; j++)
        {
            if (tauler.getCell(i, j).getValue() == -1)
            {
                contador += 1;
            }
        }
    }
    assertEquals(contador, nMines);
    }
    //test que comprova que les mines que s'introdueixen al tauler estiguin al voltant de la primera clic
    @Test void testPutMinesavoidingFirstClick()
    {
        int firstX = 2;
        int firstY = 2;
        int size = 5;
        int nMines = 5;
        MockGenRandom mockGen = new MockGenRandom(
        2,2, //no permes
        1,2, //no permes
        2,3, //no permes
        0,0, //permes
        4,4, //permes
        0,4, //permes
        4,0, //permes
        1,4, //permes 
        3,3);  //no permes 5 mines superades         
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard(firstX,firstY);
        
        
        b.firstClick(firstX, firstY);

        // Comprobar que la zona del clic no tiene minas:
        for (int i = firstX - 1; i <= firstX + 1; i++) {
            for (int j = firstY - 1; j <= firstY + 1; j++) {
                if (i >= 0 && i < 5 && j >= 0 && j < 5) {
                    assertNotEquals(-1, b.getCell(i, j).getValue());
                }
            }
        }
    }
    @Test
void testRandommines2() {
    // 1. 5 mines en tauler 4x4 (valor frontera)
        int size = 4;
        int nMines = 5;
        int contador = 0;
        GenRandom rand = new GenRandom();

        Board tauler = new Board(nMines, size, rand);
        tauler.putMinesintoBoard(3, 3);
        for (int i = 0; i < size; i++) {       
            for (int j = 0; j < size; j++) {
                if (tauler.getCell(i, j).getValue() == -1) {
                    contador += 1;
                }
            }
        }
        assertEquals(contador, nMines);
        
        // 2. 0 mines en tauler 3x3 (valor frontera)
        size = 3;
        nMines = 0;
        contador = 0;
        tauler = new Board(nMines, size, rand);
        tauler.putMinesintoBoard(2, 2);
        for (int i = 0; i < size; i++) {    
            for (int j = 0; j < size; j++) {
                if (tauler.getCell(i, j).getValue() == -1) {
                    contador += 1;
                }
            }   
        }
        assertEquals(contador, nMines);
        
        // 3. -1 mina en tauler 3x3 (valor fora rang)
        size = 3;
        nMines = -1;
        contador = 0;
        try {
            tauler = new Board(nMines, size, rand);
            tauler.putMinesintoBoard(2, 2);
            assertTrue(false);
        } catch (Exception e) {}  
        
        // 4. 10 mines en tauler 3x3 (valor fora rang)
        size = 3;
        nMines = 10;
        contador = 0;
        try {
            tauler = new Board(nMines, size, rand);
            tauler.putMinesintoBoard(2, 2);
            assertTrue(false);
        } catch (Exception e) {}  
    }
    

         
    

    @Test
    //aquest Test avalua mitjançant un mock object del tauler si els valors que prenen 
    // en relacio a les mines les diferents celes son correctes
    void testValorsCelles() {
        int size = 5;
        int nMines = 4;

        MockGenRandom mockGen = new MockGenRandom(
            0, 3,
            1, 1,
            3, 2,
            4, 0
        );

        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard(4, 4);     
        b.insertValueintoCells(); 

        int[][] boardResultant = {
            { 1,  1,  2, -1,  1 },
            { 1, -1,  2,  1,  1 },
            { 1,  2,  2,  1,  0 },
            { 1,  2, -1,  1,  0 },
            { -1, 2,  1,  1,  0 }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertEquals(boardResultant[i][j], b.getCell(i, j).getValue());
            }
        }
    }


    @Test
    void firstClickTest()
    {
        int size = 5;
        int nMines = 4;
        MockGenRandom mockGen = new MockGenRandom(
        0,3,
        1,1,
        3,2,
        4,0
        );
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard(6,6);
        b.insertValueintoCells();
        System.out.println("______________");
        Boolean[][] boardResultant = {
        { false,  false,  false, false,  false },
        { false, false,  false,  false,  false },
        { false,  false,  false,  false,  false },
         { false,  false, false,  true, false},
        { false,false,  false,  false,  false }};
        b.firstClick(3, 3);
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant[i][j],b.getCell(i, j).isRevelaed());
            }
        }


    }

    @Test
    void firstClickTest8x8() {
        int size = 8;
        int nMines = 16;
        
        MockGenRandom mockGen = new MockGenRandom(
            0,3, 0,5, 0,7, 1,0, 1,7, 2,5, 3,3, 3,4, 4,0,
            4,7, 5,7, 5,1, 5,3, 7,0, 7,2, 7,4
        );        
        
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard(7, 7);
        b.insertValueintoCells();
        
        Boolean[][] boardResultant = {
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, true, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false}
        };

        b.firstClick(2, 1);
        
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertEquals(boardResultant[i][j], b.getCell(i, j).isRevelaed());
            }
        }
    }
     
    @Test
    void firstClick2ndTest8x8() {
        int size = 8;
        int nMines = 10;
        
        MockGenRandom mockGen = new MockGenRandom(
            0,0, 1,5, 2,1, 3,4, 4,1, 5,2, 5,7, 6,0, 6,3, 6,5
        );        
        
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard(7, 7);
        b.insertValueintoCells();
        
        Boolean[][] boardResultant2 = {
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, true, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false}
        };

        b.firstClick(4, 4);
        
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertEquals(boardResultant2[i][j], b.getCell(i, j).isRevelaed());
            }
        }
    }
    @Test
    void firstClick3x3amb1mina()
    {
        int size = 4;
        int nMines = 1;
        MockGenRandom mockGen = new MockGenRandom(2,0);
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard(3,3);
        b.insertValueintoCells();
        Boolean[][] boardResultant2 = {
        {true,true,true, true},
        {true,true, true, true},
        {false,true, true, true},
        {false, true, true, true}};
        
        b.firstClick(0, 0);
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            System.out.println(b.getCell(i, j).isRevelaed() + " ");
            assertEquals(boardResultant2[i][j],b.getCell(i, j).isRevelaed());

            }

        }
    }
    @Test
    void firstClick3x3Clickingotherplace()
    {
        int size = 4;
        int nMines = 1;
        MockGenRandom mockGen = new MockGenRandom(2,0 );        
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard(3,3);
        b.insertValueintoCells();
        Boolean[][] boardResultant2 = {
        {false,false,false, false},
        {true,false, false, false},
        {false,false, false, false},
        {false, false, false, false}};
        b.firstClick(1,0);
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant2[i][j],b.getCell(i, j).isRevelaed());

            }

        }
    
    }
    @Test
    void testExpandZeros()
    {
        int nMines = 1;
        int size = 5;
        MockGenRandom mockGen = new MockGenRandom(3,1 );        
        Board b = new Board(nMines, size, mockGen); 
        b.putMinesintoBoard(4,4);
        b.insertValueintoCells();
        b.expandZeros(0, 0);
        Boolean[][] boardResultant = {
        {true, true, true, true, true},
        {true, true, true, true, true},
        {true, true, true, true, true},
        {false, false, true, true, true}, 
        {false, false, true, true, true}
        };
        for(int i=0; i<5; i++)
        {
            for (int j=0; j<5; j++)
            {
            System.out.println(b.getCell(i, j).isRevelaed() + " ");
            assertEquals(boardResultant[i][j],b.getCell(i, j).isRevelaed());
            }
        }
        
    }
    @Test
void testClickAMina() {
        int size = 8;
        int nMines = 16;
        MockGenRandom mockGen = new MockGenRandom(
            0,3, 0,5, 0,7, 1,0, 1,7, 2,5, 3,3, 3,4, 4,0,
            4,7, 5,7, 5,1, 5,3, 7,0, 7,2, 7,4
        );        
        
        Board b = new Board(nMines, size, mockGen);
        
        
        b.putMinesintoBoard(7, 7);  
        
        b.insertValueintoCells();
        b.firstClick(1, 0); 
        
    // Resultado esperado
        Boolean[][] boardResultant = {
            { false, false, false, true, false, true, false, true },
            { true, false, false, false, false, false, false, true },
            { false, false, false, false, false, true, false, false },
            { false, false, false, true, true, false, false, false },
            { true, false, false, false, false, false, false, true },
            { false, true, false, true, false, false, false, true },
            { false, false, false, false, false, false, false, false },
            { true, false, true, false, true, false, false, false }
        };

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertEquals(
                  boardResultant[i][j], b.getCell(i, j).isRevelaed());
        }
        }
    }
    @Test
    void testClickAMina3x3()
    {
        int size = 4;
        int nMines = 1;
        MockGenRandom mockGen = new MockGenRandom(2,0 );        
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard(3,3);
        b.insertValueintoCells();
        b.firstClick(2, 0); //click a mina
        Boolean[][] boardResultant = {
        { false, false, false, false},
        { false, false, false, false },
        { true, false, false, false },
        {false, false, false, false}};
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant[i][j],b.getCell(i, j).isRevelaed());
            }
        }
    }

     
    @Test
    void testIsWin()
    {
        int size = 5;
        int nMines = 1;
        MockGenRandom mockGen = new MockGenRandom(1,1 );        
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard(4,3);
        b.insertValueintoCells();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (b.getCell(i, j).getValue() != -1) {
                    b.getCell(i, j).reveal();
                }
            }       
    }
        assertTrue(b.isWin());
    }
    @Test
    void SegontestIsWin()
    {
        int size = 3;
        int nMines = 1;
        MockGenRandom mockGen = new MockGenRandom(2,0 );        
        Board b = new Board(nMines, size, mockGen);
        b.insertValueintoCells();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (b.getCell(i, j).getValue() != -1) {
                    if (i!=0 || j!=2) 
                    {// Dejo una celda sin revelar
                    b.getCell(i, j).reveal();
                    }
                }
            }       
    }
        assertFalse(b.isWin());
    }
    
 


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
    
    // Necesitamos valores para 17 minas (34 números)
    // Pero también debemos considerar que si hay colisiones,
    // el bucle podría necesitar MÁS intentos
    
    int[] muchosValores = new int[100];
    for (int i = 0; i < 100; i++) {
        muchosValores[i] = 0; // Todos 0, siempre fuera de zona protegida
    }
    
    MockGenRandom mockGen = new MockGenRandom(muchosValores);
    Board board = new Board(nMines, size, mockGen);
    
    try {
        board.putMinesintoBoard(2, 2);
        fail("Debería haber lanzado IllegalArgumentException");
    } catch (IllegalArgumentException e) {
        // ¡Éxito! Se lanzó la excepción por demasiadas minas
        assertTrue(e.getMessage().contains("excedeix"));
    }
}
}
