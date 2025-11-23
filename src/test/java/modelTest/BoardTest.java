package modelTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 
import tqs.prac.model.Board;
import tqs.prac.model.Cell;
import tqs.prac.Main;


public class BoardTest {
    //test que comprova el constructor per defecte per la matrix
    @Test
    void testInitpt1()
    {
    int mines = 4;
    int size = 6;
    Board tauler = new Board(mines, size);
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
        //valorsfora rang
        try{
            Board tauler=new Board(-2, -4);
            assertTrue(false);

        //valor frontera i valor fora rang  
        }catch (Exception e){}
        try{
            Board tauler=new Board(-1, 0);
            assertTrue(false);

        //valor correcte i valor fora rang
        }catch (Exception e){}
        try{
            Board tauler=new Board(2, -1);
            assertTrue(false);

        //valor correcte i valor fora rang   
        }catch (Exception e){}
        try{
            Board tauler=new Board(0, 0);
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
    Board tauler = new Board(nMines, size);
    tauler.putMinesintoBoard();
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
    @Test
    void testRandommines2()
    {
        //test que comprova com es comporta el putMinesintoBoard segons el nombre de mines i la mida del tauler
        //1. 9 mines en tauler 3x3 (valor frontera)
        int size = 3;
        int nMines = 9;
        int contador = 0;
        Board tauler = new Board(nMines, size);
        tauler.putMinesintoBoard();
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
        //2. 0 mines en tauler 3x3 (valor frontera)
        size = 3;
        nMines = 0;
        contador = 0;
        tauler = new Board(nMines, size);
        tauler.putMinesintoBoard();
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
        //3. -1 mina en tauler 3x3 (valor fora rang)
        size = 3;
        nMines = -1;
        contador = 0;
        try{
            tauler = new Board(nMines, size);
            tauler.putMinesintoBoard();
            assertTrue(false);
        }catch (Exception e){}  
        //4. 10 mines en tauler 3x3(valor fora rang)
        size = 3;
        nMines = 10;
        contador = 0;
        try{
            tauler = new Board(nMines, size);
            tauler.putMinesintoBoard();
            assertTrue(false);
        }catch (Exception e){}  

    }
    @Test
    //aquest Test avalua mitjançant un mock object del tauler si els valors que prenen en relacio a les mines les diferents celes son correctes
    void testValorsCelles()
    {   
        int size = 5;
        int nMines = 4;
        MockBoard mockB = new MockBoard(nMines,size);
        mockB.setUpMockBoard(1);
        mockB.insertValueintoCells();
        int[][] boardResultant = {
        { 1,  1,  2, -1,  1 },
        { 1, -1,  2,  1,  1 },
        { 1,  2,  2,  1,  0 },
         { 1,  2, -1,  1,  0 },
        { -1, 2,  1,  1,  0 }};
        mockB.printBoard();
        for (int i =0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                assertEquals(boardResultant[i][j], mockB.getCell(i, j).getValue());
            }
        }

    }

    @Test
    void firstClickTest()
    {
        int size = 5;
        int nMines = 4;
        MockBoard mockB = new MockBoard(nMines,size);
        mockB.setUpMockBoard(1);
        mockB.insertValueintoCells();
        System.out.println("______________");
        mockB.printBoard();
        Boolean[][] boardResultant = {
        { false,  false,  false, false,  false },
        { false, false,  false,  false,  false },
        { false,  false,  false,  false,  false },
         { false,  false, false,  true, false},
        { false,false,  false,  false,  false }};
        mockB.firstClick(3, 3);
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant[i][j],mockB.getCell(i, j).isRevelaed());
            }
        }
        System.out.println("________________");


    }
   @Test 
    void firstClickTest8x8()
    {
        int size = 8;
        int nMines = 16;
        MockBoard mockB = new MockBoard(nMines,size);
        mockB.setUpMockBoard(2);
        mockB.insertValueintoCells();
        System.out.println("______________");
        mockB.printBoard();
        Boolean[][] boardResultant = {
        {false,false, false, false, false, false, false, false },
        { false, false,  false,  false,  false,  false,  false, false },
        { false,  true,  false,  false,  false, false,  false, false},
        {  false,  false,  false, false, false,  false,  false,  false},
        { false,  false,  false,  false,  false,  false,  false, false },
        {  false, false, false, false,  false,  false,  false, false },
        {  false,  false,  false,  false,  false,  false,  false,  false },
        { false,  false, false,  false, false,  false,  false,  false }};

        mockB.firstClick(2, 1);
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant[i][j],mockB.getCell(i, j).isRevelaed());
            }
        }
        mockB.printBoardBools();
    }
    @Test
    void firstClick2ndTest8x8()
    {
        int size = 8;
        int nMines = 10;
        MockBoard mockB = new MockBoard(nMines,size);
        mockB.setUpMockBoard(3);
        mockB.insertValueintoCells();
        System.out.println("______________");
        mockB.printBoard();
        Boolean[][] boardResultant2 = {
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,true,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false}};

        mockB.firstClick(4, 4);
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant2[i][j],mockB.getCell(i, j).isRevelaed());
            }
        }
        mockB.printBoardBools();
    }
    @Test
    void firstClick3x3amb1mina()
    {
        int size = 3;
        int nMines = 1;
        MockBoard mockB = new MockBoard(nMines,size);
        mockB.setUpMockBoard(4);
        mockB.insertValueintoCells();
        //mockB.printBoard();
        Boolean[][] boardResultant2 = {
        {true,true,true},
        {true,true, true},
        {false,true, true}};
        
        mockB.firstClick(0, 0);
        System.out.println("After first click:");
        mockB.printBoardBools();
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            System.out.println(mockB.getCell(i, j).isRevelaed() + " ");
            assertEquals(boardResultant2[i][j],mockB.getCell(i, j).isRevelaed());

            }

        }
    }
    @Test
    void firstClick3x3Clickingotherplace()
    {
        int size = 3;
        int nMines = 1;
        MockBoard mockB2 = new MockBoard(nMines,size);
        mockB2.setUpMockBoard(4);
        mockB2.insertValueintoCells();
        mockB2.printBoard();
        Boolean[][] boardResultant2 = {
        {false,false,false},
        {true,false, false},
        {false,false, false}};
        
        mockB2.firstClick(1, 0);
        System.out.println("After first click:");
        mockB2.printBoardBools();
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            System.out.println(mockB2.getCell(i, j).isRevelaed() + " ");
            assertEquals(boardResultant2[i][j],mockB2.getCell(i, j).isRevelaed());

            }

        }
    
    }
    @Test
    void testExpandZeros()
    {
        
        MockBoard b = new MockBoard(1,5);
        b.setUpMockBoard(5);
        b.insertValueintoCells();
        b.printBoard();
        b.expandZeros(0, 0);
        b.printBoardBools();
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
    void testClickAMina()
    {
        int size = 8;
        int nMines = 16;
        MockBoard mockB = new MockBoard(nMines,size);
        mockB.setUpMockBoard(2);
        mockB.insertValueintoCells();
        mockB.printBoard();
        mockB.firstClick(1, 0); //click a mina
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

        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant[i][j],mockB.getCell(i, j).isRevelaed());
            }
        }
    }
    @Test
    void testClickAMina3x3()
    {
        int size = 3;
        int nMines = 1;
        MockBoard mockB = new MockBoard(nMines,size);
        mockB.setUpMockBoard(4);
        mockB.insertValueintoCells();
        mockB.firstClick(2, 0); //click a mina
        Boolean[][] boardResultant = {
        { false, false, false},
        { false, false, false },
        { true, false, false }};
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant[i][j],mockB.getCell(i, j).isRevelaed());
            }
        }
    }


    @Test
    void testIsWin()
    {
        int size = 5;
        int nMines = 1;
        MockBoardValues mockB = new MockBoardValues(nMines, size);
        mockB.setUpMockBoard(1);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mockB.getCell(i, j).getValue() != -1) {
                    mockB.getCell(i, j).reveal();
                }
            }       
    }
    // Ahora isWin() debería devolver true
        assertTrue(mockB.isWin());
    }
    @Test
    void SegontestIsWin()
    {
        int size = 3;
        int nMines = 1;
        MockBoardValues mockB = new MockBoardValues(nMines, size);
        mockB.setUpMockBoard(2);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mockB.getCell(i, j).getValue() != -1) {
                    if (i!=0 || j!=2) 
                    {// Dejo una celda sin revelar
                    mockB.getCell(i, j).reveal();
                    }
                }
            }       
    }
        assertFalse(mockB.isWin());
    }
}   

