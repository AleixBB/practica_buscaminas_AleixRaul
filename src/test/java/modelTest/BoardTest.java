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
        //valors fora rang
        try{
            Board tauler=new Board(-2, -4, rand);
            assertTrue(false);
        //valor limit (-1) i valor frontera (0)  
        }catch (Exception e){}
        try{
            Board tauler=new Board(-1, 0, rand);
            assertTrue(false);

        //valor correcte i valor fora rang
        }catch (Exception e){}
        try{
            Board tauler=new Board(2, -1, rand);
            assertTrue(false);

        //valor frontera (0) i valor limit (0)  
        }catch (Exception e){}
        try{
            Board tauler=new Board(0, 0, rand);
            assertTrue(false);
        }catch (Exception e){}
        //valor correcte i valor correcte
        Board tauler = new Board(1,2, rand);
        assertNotNull(tauler);
        
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
void testPutMinesParticionsEquivalentsCentre() {
        

    // 1. 5 mines en tauler 5x5, fila i columna son centre (valor dins rang)
        int size = 5;
        int nMines = 5;
        int contador = 0;
        GenRandom rand = new MockGenRandom(4,0,4,1,4,2,4,3,4,4);

        Board tauler = new Board(nMines, size, rand);
        tauler.putMinesintoBoard(2, 2);
        for (int i = 0; i < size; i++) {       
            for (int j = 0; j < size; j++) {
                if (tauler.getCell(i, j).getValue() == -1) {
                    contador += 1;
                }
            }
        }
        assertEquals(contador, nMines);
        
        // 2. 7 mines en tauler 4x4, fila i columna son centre (valor frontera)
        size = 4;
        nMines = 7;
        contador = 0;
        rand = new MockGenRandom(0,0,0,1,0,2,0,3,1,3,2,3,3,3);
        tauler = new Board(nMines, size, rand);
        tauler.putMinesintoBoard(2, 1);
        for (int i = 0; i < size; i++) {    
            for (int j = 0; j < size; j++) {
                if (tauler.getCell(i, j).getValue() == -1) {
                    contador += 1;
                }
            }   
        }
        assertEquals(contador, nMines);
        
        // 3. 8 mines en tauler 4x4, fila i columna son centre (valor limit)
        size = 4;
        nMines = 8;
        contador = 0;
        rand = new MockGenRandom(0,0,0,1,0,2,0,3,1,3,2,3,3,3,1,2);

        try {
            tauler = new Board(nMines, size, rand);
            tauler.putMinesintoBoard(2, 2);
            assertTrue(false);
        } catch (Exception e) {}  
        
        // 4. 6 mines en tauler 4x4, fila i columna son centre (valor limit)
        size = 4;
        nMines = 6;
        contador = 0;
        rand = new MockGenRandom(0,0,0,1,0,2,0,3,1,3,2,3);
        tauler = new Board(nMines, size, rand);
        tauler.putMinesintoBoard(2, 1);
        for (int i = 0; i < size; i++) {    
            for (int j = 0; j < size; j++) {
                if (tauler.getCell(i, j).getValue() == -1) {
                    contador += 1;
                }
            }   
        }
        assertEquals(contador, nMines);
    }
   @Test 
    void testPutMinesParticionsEquivalentsCantonada() {
        

    // 1. 5 mines en tauler 5x5, fila i columna son cantonada (valor dins rang)
        int size = 5;
        int nMines = 5;
        int contador = 0;
        GenRandom rand = new MockGenRandom(0,0,0,1,0,2,0,3,0,4);

        Board tauler = new Board(nMines, size, rand);
        tauler.putMinesintoBoard(3, 0);
        for (int i = 0; i < size; i++) {       
            for (int j = 0; j < size; j++) {
                if (tauler.getCell(i, j).getValue() == -1) {
                    contador += 1;
                }
            }
        }
        assertEquals(contador, nMines);
        
        // 2. 12 mines en tauler 4x4, fila i columna son cantonada (valor frontera)
        size = 4;
        nMines = 12;
        contador = 0;
        rand = new MockGenRandom(0,0,0,1,0,2,0,3,1,0,1,1,1,2,1,3,2,2,2,3,3,2,3,3);
        tauler = new Board(nMines, size, rand);
        tauler.putMinesintoBoard(3, 0);
        for (int i = 0; i < size; i++) {    
            for (int j = 0; j < size; j++) {
                if (tauler.getCell(i, j).getValue() == -1) {
                    contador += 1;
                }
            }   
        }
        assertEquals(contador, nMines);
        
        // 3. 13 mines en tauler 4x4, fila i columna son centre (valor limit)
        size = 4;
        nMines = 13;
        contador = 0;
        rand = new MockGenRandom(0,0,0,1,0,2,0,3,1,0,1,1,1,2,1,3,2,2,2,3,3,2,3,3,2,0);

        try {
            tauler = new Board(nMines, size, rand);
            tauler.putMinesintoBoard(3, 0);
            assertTrue(false);
        } catch (Exception e) {}  
        
        // 4. 11 mines en tauler 4x4, fila i columna son centre (valor limit)
        size = 4;
        nMines = 11;
        contador = 0;
        rand = new MockGenRandom(0,0,0,1,0,2,0,3,1,0,1,1,1,2,1,3,2,2,2,3,3,3);
        tauler = new Board(nMines, size, rand);
        tauler.putMinesintoBoard(3, 0);
        for (int i = 0; i < size; i++) {    
            for (int j = 0; j < size; j++) {
                if (tauler.getCell(i, j).getValue() == -1) {
                    contador += 1;
                }
            }   
        }
        assertEquals(contador, nMines);
    }

    @Test
    void testPutMinesParticionsEquivalentsExtremSupInfLat() {
        

    // 1. 3 mines en tauler 4x4, fila i columna son extrem superior (valor dins rang)
        int size = 4;
        int nMines = 3;
        int contador = 0;
        GenRandom rand = new MockGenRandom(3,0,3,1,3,2);

        Board tauler = new Board(nMines, size, rand);
        tauler.putMinesintoBoard(0, 2);
        for (int i = 0; i < size; i++) {       
            for (int j = 0; j < size; j++) {
                if (tauler.getCell(i, j).getValue() == -1) {
                    contador += 1;
                }
            }
        }
        assertEquals(contador, nMines);
        
        // 2. 10 mines en tauler 4x4, fila i columna son extrem superior (valor frontera)
        size = 4;
        nMines = 10;
        contador = 0;
        rand = new MockGenRandom(0,0,1,0,2,0,3,0,2,1,2,2,2,3,3,1,3,2,3,3);
        tauler = new Board(nMines, size, rand);
        tauler.putMinesintoBoard(0, 2);
        for (int i = 0; i < size; i++) {    
            for (int j = 0; j < size; j++) {
                if (tauler.getCell(i, j).getValue() == -1) {
                    contador += 1;
                }
            }   
        }
        assertEquals(contador, nMines);
        
        // 3. 11 mines en tauler 4x4, fila i columna son extrem superior (valor limit)
        size = 4;
        nMines = 11;
        contador = 0;
        rand = new MockGenRandom(0,0,0,1,0,2,0,3,0,2,1,2,2,2,3,3,1,3,2,3,3,1,3);

        try {
            tauler = new Board(nMines, size, rand);
            tauler.putMinesintoBoard(0, 2);
            assertTrue(false);
        } catch (Exception e) {}  
        
        // 4. 9 mines en tauler 4x4, fila i columna son extrem superior (valor limit)
        size = 4;
        nMines = 9;
        contador = 0;
        rand = new MockGenRandom(0,0,1,0,2,0,3,0,2,1,2,2,2,3,3,1,3,3,3,2);
        tauler = new Board(nMines, size, rand);
        tauler.putMinesintoBoard(0, 2);
        for (int i = 0; i < size; i++) {    
            for (int j = 0; j < size; j++) {
                if (tauler.getCell(i, j).getValue() == -1) {
                    contador += 1;
                }
            }   
        }
        assertEquals(contador, nMines);
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
    
 


}