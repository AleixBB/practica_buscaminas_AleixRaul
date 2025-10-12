package tqs.prac.model;
import java.util.Random;

public class board {
    private int nMines;
    private int size;
    private Cell[][] matrix;


    public board(int nMines, int size)
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
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {   if (matrix[i][j].getValue()!= -1)
                {
                if (j>0){
                    if (matrix[i][j-1].getValue()== -1)
                    {
                        matrix[i][j].setValue(matrix[i][j].getValue()+1);
                    }
                
                }
                if (i>0)
                {
                     if (matrix[i-1][j].getValue()== -1)
                    {
                        matrix[i][j].setValue(matrix[i][j].getValue()+1);
                    }
                }
                if(j<(size-1)){
                    if (matrix[i][j+1].getValue()== -1)
                    {
                        matrix[i][j].setValue(matrix[i][j].getValue()+1);
                    }
                }
                if(i<(size-1)){
                    if (matrix[i+1][j].getValue()== -1)
                    {
                        matrix[i][j].setValue(matrix[i][j].getValue()+1);
                    }
                }
                if(i<(size-1) && j<(size-1) ){
                    if (matrix[i+1][j+1].getValue()== -1)
                    {
                        matrix[i][j].setValue(matrix[i][j].getValue()+1);
                    }
                
                }
                if(i>0 && j>0 ){
                    if (matrix[i-1][j-1].getValue()== -1)
                    {
                        matrix[i][j].setValue(matrix[i][j].getValue()+1);
                    }
                
                }
                if(i<(size-1) && j>0 ){
                    if (matrix[i+1][j].getValue()== -1)
                    {
                        matrix[i][j].setValue(matrix[i][j].getValue()+1);
                    }
                
                }
                if(i>0 && j>(size-1) ){
                    if (matrix[i+1][j].getValue()== -1)
                    {
                        matrix[i][j].setValue(matrix[i][j].getValue()+1);
                    }
                
                }
                }
            }
         
        }


    } 
    public void printBoard()
    {
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                System.out.println((matrix[i][j]).getValue());
            }
            System.out.println("\n");
        }
    }


}
    