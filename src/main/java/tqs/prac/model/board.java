package tqs.prac.model;
import java.util.Random;

public class board {
    private int nMines;
    private int size;
    private char[][] matriuVisible;
    private int[][] mines; //mines = -1


    public board(int nMines, int size)
    {
        if (nMines <= 0 || size <= 0)
        {
            throw new IllegalArgumentException("nombre de mines o mida fora de rang");
        }
        
        this.nMines = nMines;
        this.size = size;
        matriuVisible = new char[size][size];
        mines = new int[size][size];
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                matriuVisible[i][j] = '-';
            }
        }
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                mines[i][j] = 0;
            }
        }

    }
    public char getCellMatriuV(int fila, int col){
        return matriuVisible[fila][col];
    }
    public int getCellMines(int fila, int col){
        return mines[fila][col];
    }
    public void putMinesintoBoard()
    {
        Random rand = new Random();
        int count = 0;
        while (count < nMines) {
            int r = rand.nextInt(size);
            int c = rand.nextInt(size);
            if (mines[r][c] == 0) {
                mines[r][c] = -1;
                count++;
            }
        }   
    }   


}
    