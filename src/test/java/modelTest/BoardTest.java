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
        GenRandom rand = new GenRandom();

        Board tauler = new Board(nMines, size, rand);
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
        tauler = new Board(nMines, size, rand);
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
            tauler = new Board(nMines, size, rand);
            tauler.putMinesintoBoard();
            assertTrue(false);
        }catch (Exception e){}  
        //4. 10 mines en tauler 3x3(valor fora rang)
        size = 3;
        nMines = 10;
        contador = 0;
        try{
            tauler = new Board(nMines, size, rand);
            tauler.putMinesintoBoard();
            assertTrue(false);
        }catch (Exception e){}  

    }
    @Test
    //aquest Test avalua mitjançant un mock object del tauler si els valors que prenen 
    // en relacio a les mines les diferents celes son correctes
void testValorsCelles()
{
    int size = 5;
    int nMines = 4;

    // Mock que genera exactamente las minas del type 1
    MockGenRandom mockGen = new MockGenRandom(
        0,3,
        1,1,
        3,2,
        4,0
    );

    Board b = new Board(nMines, size, mockGen);
    b.putMinesintoBoard();     
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
        b.putMinesintoBoard();
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
    void firstClickTest8x8()
    {
        int size = 8;
        int nMines = 16;
        MockGenRandom mockGen = new MockGenRandom(
        0,3, 0,5, 0,7, 1,0, 1,7, 2,5, 3,3, 3,4, 4,0,
        4,7, 5,7, 5,1, 5,3, 7,0, 7,2, 7,4 );        
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard();
        b.insertValueintoCells();
        System.out.println("______________");
        Boolean[][] boardResultant = {
        {false,false, false, false, false, false, false, false },
        { false, false,  false,  false,  false,  false,  false, false },
        { false,  true,  false,  false,  false, false,  false, false},
        {  false,  false,  false, false, false,  false,  false,  false},
        { false,  false,  false,  false,  false,  false,  false, false },
        {  false, false, false, false,  false,  false,  false, false },
        {  false,  false,  false,  false,  false,  false,  false,  false },
        { false,  false, false,  false, false,  false,  false,  false }};

        b.firstClick(2, 1);
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant[i][j],b.getCell(i, j).isRevelaed());
            }
        }
    }
     
    @Test
    void firstClick2ndTest8x8()
    {
        int size = 8;
        int nMines = 10;
        MockGenRandom mockGen = new MockGenRandom(
        0,0, 1,5, 2,1, 3,4, 4,1, 5,2, 5,7, 6,0, 6,3, 6,5 );        
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard();
        b.insertValueintoCells();
        System.out.println("______________");
        Boolean[][] boardResultant2 = {
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,true,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false}};

        b.firstClick(4, 4);
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant2[i][j],b.getCell(i, j).isRevelaed());
            }
        }
    }
    @Test
    void firstClick3x3amb1mina()
    {
        int size = 3;
        int nMines = 1;
        MockGenRandom mockGen = new MockGenRandom(2,0);
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard();
        b.insertValueintoCells();
        Boolean[][] boardResultant2 = {
        {true,true,true},
        {true,true, true},
        {false,true, true}};
        
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
        int size = 3;
        int nMines = 1;
        MockGenRandom mockGen = new MockGenRandom(2,0 );        
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard();
        b.insertValueintoCells();
        Boolean[][] boardResultant2 = {
        {false,false,false},
        {true,false, false},
        {false,false, false}};
        
        b.firstClick(1, 0);
        System.out.println("After first click:");
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
        b.putMinesintoBoard();
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
    void testClickAMina()
    {
        int size = 8;
        int nMines = 16;
        MockGenRandom mockGen = new MockGenRandom(0,3, 0,5, 0,7, 1,0, 1,7, 2,5, 3,3, 3,4, 4,0,
        4,7, 5,7, 5,1, 5,3, 7,0, 7,2, 7,4);        
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard();
        b.insertValueintoCells();
        b.firstClick(1, 0); //click a mina
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
            assertEquals(boardResultant[i][j],b.getCell(i, j).isRevelaed());
            }
        }
    } 
    @Test
    void testClickAMina3x3()
    {
        int size = 3;
        int nMines = 1;
        MockGenRandom mockGen = new MockGenRandom(2,0 );        
        Board b = new Board(nMines, size, mockGen);
        b.putMinesintoBoard();
        b.insertValueintoCells();
        b.firstClick(2, 0); //click a mina
        Boolean[][] boardResultant = {
        { false, false, false},
        { false, false, false },
        { true, false, false }};
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
        b.putMinesintoBoard();
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
        b.putMinesintoBoard();
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

