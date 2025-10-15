package tqs.prac.model;
import java.util.Random;

public class Board {
    protected int nMines;
    protected int size;
    protected Cell[][] matrix;


    public Board(int nMines, int size)
    {
        if (nMines <= 0 || size <= 0)
        {
            throw new IllegalArgumentException("nombre de mines o mida fora de rang");
        }
        this.nMines = nMines;
        this.size = size;
        matrix = new Cell[size][size];
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                matrix[i][j] = new Cell(0);
            }
        }
       

    }
    public Cell getCell(int fila, int col){
        return matrix[fila][col];
    }
    public void putMinesintoBoard()
    {
        Random rand = new Random();
        int count = 0;
        while (count < nMines) {
            int r = rand.nextInt(size);
            int c = rand.nextInt(size);
            if (matrix[r][c].getValue() == 0) {
                matrix[r][c].setValue(-1);
                count++;
            }
        }   
    }  
    public void insertValueintoCells()
    {
        for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (matrix[i][j].getValue() == -1) //si es mina
                continue;

            int count = 0;
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {
+                    if (di == 0 && dj == 0)
                        continue;

                    int ni = i + di;
                    int nj = j + dj;
                    if (ni >= 0 && ni < size && nj >= 0 && nj < size) {
                        if (matrix[ni][nj].getValue() == -1)
                            count++;
                    }
                }
            }

            matrix[i][j].setValue(count);
        }
    }
}
        
   public void printBoard() {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            System.out.print(matrix[i][j].getValue() + " ");
        }
        System.out.println(); 
    }
    }

    public void firstClick(int fila, int columna){
        
    



    }
    
    public void openCell(int fila, int columna){



    }



}
    