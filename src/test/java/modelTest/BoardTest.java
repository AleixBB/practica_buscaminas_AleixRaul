package modelTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 
import tqs.prac.model.Board;
import tqs.prac.model.Cell;
import tqs.prac.model.GenRandom;
import tqs.prac.Main;
import java.util.ArrayList;


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

        // Comprobar que la zona del clic no te minas:
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
    void testInsertValuesParticionsequivalent_capMina(){

    //tauler sense mines, tots els valors han de ser zero
    int size = 4;
    int nMines = 0;
    MockGenRandom random = new MockGenRandom(null);
    Board b = new Board(nMines, size, random);
    b.putMinesintoBoard(0, 0);
    b.insertValueintoCells();
    for (int i=0; i<size; i++)
    {
        for (int j=0; j<size; j++)
        {
            assertEquals(b.getCell(i, j).getValue(), 0);
        }
    }
    }
    
    @Test
    void testInsertValuesParticionsequivalent_unaMina(){

    //tauler ammb una mina, tots els valors del voltant han de ser 1 i la mina -1
    int size = 4;
    int nMines = 1;
    MockGenRandom random = new MockGenRandom(1,1);
    Board b = new Board(nMines, size, random);
    b.putMinesintoBoard(3, 3);
    b.insertValueintoCells();
    assertEquals(b.getCell(1, 1).getValue(), -1);
    assertEquals(b.getCell(0, 0).getValue(), 1);
    assertEquals(b.getCell(0, 1).getValue(), 1);
    assertEquals(b.getCell(0, 2).getValue(), 1);
    assertEquals(b.getCell(1, 2).getValue(), 1);
    assertEquals(b.getCell(1, 0).getValue(), 1);
    assertEquals(b.getCell(2, 1).getValue(), 1);
    assertEquals(b.getCell(2, 2).getValue(), 1);
    assertEquals(b.getCell(2, 0).getValue(), 1);

    }
    @Test
    void testInsertValuesParticionsequivalent_duesMines(){

    //tauler ammb dues mines, tots els valors del voltant han de ser 1 o 2 i la mina -1
    int size = 4;
    int nMines = 2;
    MockGenRandom random = new MockGenRandom(1,1, 1,0);
    Board b = new Board(nMines, size, random);
    b.putMinesintoBoard(3, 3);
    b.insertValueintoCells();
    assertEquals(b.getCell(1, 1).getValue(), -1);
    assertEquals(b.getCell(1, 0).getValue(), -1);

    assertEquals(b.getCell(0, 0).getValue(), 2);
    assertEquals(b.getCell(0, 1).getValue(), 2);
    assertEquals(b.getCell(0, 2).getValue(), 1);
    assertEquals(b.getCell(1, 2).getValue(), 1);
    assertEquals(b.getCell(2, 1).getValue(), 2);
    assertEquals(b.getCell(2, 2).getValue(), 1);
    assertEquals(b.getCell(2, 0).getValue(), 2);
    }

    @Test
    void testInsertValuesParticionsequivalent_tresMines(){

    //tauler ammb tres mines, tots els valors del voltant han de ser 1,2 o 3 i la mina -1
    int size = 4;
    int nMines = 3;
    MockGenRandom random = new MockGenRandom(1,1, 1,0,0,0);
    Board b = new Board(nMines, size, random);
    b.putMinesintoBoard(3, 3);
    b.insertValueintoCells();
    assertEquals(b.getCell(1, 1).getValue(), -1);
    assertEquals(b.getCell(1, 0).getValue(), -1);
    assertEquals(b.getCell(0, 0).getValue(), -1);

    assertEquals(b.getCell(0, 1).getValue(), 3);
    assertEquals(b.getCell(0, 2).getValue(), 1);
    assertEquals(b.getCell(1, 2).getValue(), 1);
    assertEquals(b.getCell(2, 1).getValue(), 2);
    assertEquals(b.getCell(2, 2).getValue(), 1);
    assertEquals(b.getCell(2, 0).getValue(), 2);
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
    void testFirstClick_ParticionsEquivalents_clickAMina(){
        //clic a mina al mig
        int size = 4;
        int nMines =1;
        MockGenRandom random = new MockGenRandom(1,1);
        Board b = new Board(nMines, size, random);
        b.putMinesintoBoard(3, 3);
        b.insertValueintoCells();
        boolean resultat = b.firstClick(1, 1);
        assertFalse(resultat);
    }
    @Test
    void testFirstClick_particioCero() {
    //clic a cel.la buida amb mina al mig (posem una altra mina per impedir que la primera obertura deixi algun 0)
    int size = 4;
    int nMines = 2;
    MockGenRandom random = new MockGenRandom(1,0,3,2); // mina al centre + mina extrem
    Board b = new Board(nMines, size, random);
    b.putMinesintoBoard(0, 3); 
    b.insertValueintoCells();
    boolean result = b.firstClick(3, 0);
    assertTrue( result);
    }
    @Test
    void testFirstClick_particioValorUn() {
    //clic a cel.la amb valor 1 amb mina al mig (posem una altra mina per impedir que la primera obertura deixi algun 0)
    int size = 4;
    int nMines = 2;
    MockGenRandom random = new MockGenRandom(1,0,3,2); // mina al centre + mina extrem
    Board b = new Board(nMines, size, random);
    b.putMinesintoBoard(0, 3); 
    b.insertValueintoCells();
    boolean result = b.firstClick(3, 1);
    assertTrue( result);
    }
    @Test
    void testFirstClick_particioValorDos() {
    //clic a cel.la amb valor 1 amb mina al mig (posem una altra mina per impedir que la primera obertura deixi algun 0)
    int size = 4;
    int nMines = 2;
    MockGenRandom random = new MockGenRandom(1,0,3,2); // mina al centre + mina extrem
    Board b = new Board(nMines, size, random);
    b.putMinesintoBoard(0, 3); 
    b.insertValueintoCells();
    boolean result = b.firstClick(1, 1);
    assertTrue( result);
    assertTrue(b.getCell(1, 1).isRevelaed());
    }
     @Test
    void testFirstClick_particioFilaiColumnaInvalides() {
    int size = 4;
    int nMines = 2;
    MockGenRandom random = new MockGenRandom(1,0,3,2); // mina al centre + mina extrem
    Board b = new Board(nMines, size, random);
    b.putMinesintoBoard(0, 3); 
    b.insertValueintoCells();
    //fila es -1 (valor limit) i columna 1(limit)
    try{
        boolean result = b.firstClick(-1, 1);
        assertTrue(false);
    }catch(Exception e){}

    //columna es -1 (valor limit), fila es 0 (valor frontera)
    try{
        boolean result = b.firstClick(0, -1);
        assertTrue(false);

    }catch(Exception e){}

    //columna es 0 (valor frontera) i fila -1 (valor limit)
     try{
        boolean result = b.firstClick(-1, 0);
        assertTrue(false);

    }catch(Exception e){}

    //columna es 2 (valor dins rang) i fila -1 (valor limit)
     try{
        boolean result = b.firstClick(-1, 2);
        assertTrue(false);

    }catch(Exception e){}

    //fila es -2 (fora rang) i columna es 4 (valor limit)
    try{
        boolean result = b.firstClick(-2, 4);
        assertTrue(false);

    }catch(Exception e){}
    //fila es 4 (valor limit) i columna es 0 (valor limit)
    try{
        boolean result = b.firstClick(4, 0);
        assertTrue(false);

    }catch(Exception e){}
    boolean result = b.firstClick(3,3); //fila i columna son valors frontera
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
            assertEquals(boardResultant[i][j],b.getCell(i, j).isRevelaed());
            }
        }
    }
    @Test
    void testExpandZeros_ParticionsEquivalentFilaiColumnaInvalides(){
       
       //hauria de fer return, per tant no revela res
        int nMines = 1;
        int size = 5;
        MockGenRandom mockGen = new MockGenRandom(3,1 );        
        Board b = new Board(nMines, size, mockGen); 
        b.putMinesintoBoard(4,4);
        b.insertValueintoCells();
        ArrayList<Cell> llistaRevelades = new ArrayList<>();
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                if (b.getCell(i, j).isRevelaed())
                {
                 llistaRevelades.add(b.getCell(i, j)); 
                }
            }
        }
        ArrayList<Cell> llistaReveladesDespres = new ArrayList<>();

        b.expandZeros(-1, 0);
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                if (b.getCell(i, j).isRevelaed())
                {
                 llistaReveladesDespres.add(b.getCell(i, j)); 
                }
            }
        }
        assertEquals(llistaRevelades,llistaReveladesDespres);
    }

   @Test
   void testExpandZeros_ParticionsEquivalents_CellaAmbZero()
   {
    int nMines = 2;
    int size = 5;
    MockGenRandom mockGen = new MockGenRandom(1,0,3,2 );        
    Board b = new Board(nMines, size, mockGen); 
    b.putMinesintoBoard(0,4);
    b.insertValueintoCells();
    b.expandZeros(4, 0);
    //les del voltant es revelen 
    assertTrue(b.getCell(4, 0).isRevelaed()); 
    assertTrue(b.getCell(3, 0).isRevelaed()); 
    assertTrue(b.getCell(4, 1).isRevelaed()); 
    assertTrue(b.getCell(3, 1).isRevelaed()); 
    assertTrue(b.getCell(2, 1).isRevelaed()); 
    assertTrue(b.getCell(2, 0).isRevelaed()); 
   } 
   @Test
    void testExpandZeros_CellaAmbNum() {
    
    int nMines = 2;
    int size = 5;
    MockGenRandom mockGen = new MockGenRandom(1, 0,3,2); 
    Board b = new Board(nMines, size, mockGen);
    b.putMinesintoBoard(0, 4);
    b.insertValueintoCells();
   
    b.expandZeros(4, 1);
    //les del voltant no es revelen
    assertTrue(b.getCell(4, 1).isRevelaed()); 
    assertFalse(b.getCell(4, 0).isRevelaed());
    assertFalse(b.getCell(3, 0).isRevelaed()); 
    assertFalse(b.getCell(3, 1).isRevelaed()); 
    assertFalse(b.getCell(3, 2).isRevelaed()); 
    assertFalse(b.getCell(4, 2).isRevelaed()); 
    }
    @Test
void testExpandZeros_CeldaAmbMina() {
    

    int nMines = 2;
    int size = 5;
    MockGenRandom mockGen = new MockGenRandom(1,0,3,2 ); 
    Board b = new Board(nMines, size, mockGen);
    b.putMinesintoBoard(0, 4);
    b.insertValueintoCells();
    b.expandZeros(1, 0);
    //hauria de fer un return i no fer res
    ArrayList<Cell> llistaRevelades = new ArrayList<>();
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                if (b.getCell(i, j).isRevelaed())
                {
                 llistaRevelades.add(b.getCell(i, j)); 
                }
            }
        }
        ArrayList<Cell> llistaReveladesDespres = new ArrayList<>();

        b.expandZeros(-1, 0);
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                if (b.getCell(i, j).isRevelaed())
                {
                 llistaReveladesDespres.add(b.getCell(i, j)); 
                }
            }
        }
        assertEquals(llistaRevelades,llistaReveladesDespres);
    }
    @Test
    void testExpandZeros_CellYaRevelada() {
    int nMines = 2;
    int size = 5;
    MockGenRandom mockGen = new MockGenRandom(1,0,3,2 ); 
    Board b = new Board(nMines, size, mockGen);
    b.putMinesintoBoard(0, 4);
    b.insertValueintoCells();
    b.expandZeros(0, 4); //revelada ja per la primera expansio
    b.expandZeros(1, 1);

    //no hauria de canviar res
    ArrayList<Cell> llistaRevelades = new ArrayList<>();
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                if (b.getCell(i, j).isRevelaed())
                {
                 llistaRevelades.add(b.getCell(i, j)); 
                }
            }
        }
        ArrayList<Cell> llistaReveladesDespres = new ArrayList<>();

        b.expandZeros(-1, 0);
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                if (b.getCell(i, j).isRevelaed())
                {
                 llistaReveladesDespres.add(b.getCell(i, j)); 
                }
            }
        }
        assertEquals(llistaRevelades,llistaReveladesDespres);
    

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
   void testParticionsEquivalents_ClickAMinaTaulerSenseMines()
   {
    int size = 3;
    int nMines = 0;
    MockGenRandom  random = new MockGenRandom();
    Board b = new Board(nMines, size, random);
    b.insertValueintoCells();
    b.clickAMina();
    //no es revela cap perq no hi ha cap mina
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            assertFalse(b.getCell(i, j).isRevelaed());
        }
    }
    
   }
   @Test
   void testParticionsEquivalents_ClickAMinaTaulerAmbUnaMina()
   {
    int size = 4;
    int nMines = 1;
    MockGenRandom  random = new MockGenRandom(0,0);
    Board b = new Board(nMines, size, random);
    b.putMinesintoBoard(3,3);
    b.insertValueintoCells();
    b.clickAMina();
    //nomes es revela la mina
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (i==0 && j==0)
            {
                assertTrue(b.getCell(i, j).isRevelaed());
            }
            else{
            assertFalse(b.getCell(i, j).isRevelaed());
            }
        }
    }
    
   }
  @Test
    void testParticionsEquivalents_ClickAMinaTaulerDosMines() {
    int size = 4;
    int nMines = 2;
    MockGenRandom mockGen = new MockGenRandom(3,2,1,0
    );
    Board b = new Board(nMines, size, mockGen);
    b.putMinesintoBoard(0, 3);
    b.insertValueintoCells();
    
    b.clickAMina();
    
    // Todas las celdas deberían estar reveladas
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if ((i==1 && j==0) || (i==3 && j==2))
            {
            assertTrue(b.getCell(i, j).isRevelaed());
            }
            else{
            assertFalse(b.getCell(i, j).isRevelaed());
            }
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