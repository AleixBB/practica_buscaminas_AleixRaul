package tqs.prac.model;
import java.util.Random;

public class model {
    private int nMines;
    private int size;
    private char[][] matriu;
    private char[][] mines;


    public model(int nMines, int size)
    {
        this.nMines = nMines;
        this.size = size;
    }
    public void init()
    {
        matriu = new char[size][size];
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                matriu[i][j] = '-';
            }
        }
    }
    public void introMines()
    {
        Random rand = new Random();
        int count = 0;
        mines = new char[size][size];
        while (count < nMines) {
            int r = rand.nextInt(size);
            int c = rand.nextInt(size);
            if (mines[r][c] == 0) {
                mines[r][c] = 1;
                count++;
            }
        }
    }
        


    
    public char getCell(int fila, int columna)
    {
        return matriu[fila][columna];
    }
    
}
