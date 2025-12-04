
package tqs.prac.model;

public class Board {
    protected int nMines;
    protected int size;
    protected Cell[][] matrix;
    private GenRandom random;


    public Board(int nMines, int size, GenRandom rand)
    {
        //precondicions
        if (nMines < 0 || size <= 0)
        {
            throw new IllegalArgumentException("nombre de mines o mida fora de rang");
        }
        this.nMines = nMines;
        this.size = size;
        this.random = rand;
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
    public void putMinesintoBoard(int fila, int columna)
    {
        if (nMines > (size*size-8)){
            throw new IllegalArgumentException("El nombre de mines excedeix la mida del tauler");
        }
        int count = 0;
        while (count < nMines) {
        int r = random.nextInt(size);
        int c = random.nextInt(size);

        // Evitar primera celda y alrededores (opcional mejora)
        if (Math.abs(r - fila) <= 1 && Math.abs(c - columna) <= 1) {
            continue;
        }

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
                    if (di == 0 && dj == 0)
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
        
   
    public int getSize(){
        return this.size;
    }
    
    
   public Boolean firstClick(int fila, int columna){
    Cell clicked = matrix[fila][columna];
    
    if (matrix[fila][columna].getValue() == -1)
    {
        clickAMina();
        return false;
    }
    else
    {
        if(clicked.getValue() == 0)
        {
            expandZeros(fila, columna);
        }
        else
        {
            clicked.reveal();
        }
    }  
    return true;      
}



public void expandZeros(int fila, int columna){
    if (fila < 0||fila > (size-1) || columna < 0 ||columna > (size-1))
    {
        return;
    }
    Cell cell = matrix[fila][columna];
    if (cell.isRevelaed() || cell.getValue() == -1)
    {
        return;
    }
    
    cell.reveal();
    
    if (cell.getValue() > 0)
    {
        return;
    }
    
    for (int i = -1; i<= 1; i++)
    {
        for (int j=-1; j<=1; j++)
        {
            if (i==0 && j==0) continue;
            expandZeros(fila+i, columna+j);
        }
    }
 }

public void clickAMina()
{
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (matrix[i][j].getValue() == -1) {
                matrix[i][j].reveal();
            }
        }
    }
}


public Boolean isWin()
{
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            Cell cell = matrix[i][j];
            if (cell.getValue() != -1 && !cell.isRevelaed()) {
                return false;
            }
        }
    }
    return true;
    }
}




    
