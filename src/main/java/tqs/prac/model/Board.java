package tqs.prac.model;

public class Board {
    protected int nMines;
    protected int size;
    protected Cell[][] matrix;
    private GenRandom random;

    public Board(int nMines, int size, GenRandom rand)  {
        //precondicions
        if (nMines < 0) {
            throw new IllegalArgumentException("nombre de mines insuficient");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("mida fora de rang");
        }

        this.nMines = nMines;
        this.size = size;
        this.random = rand;
        matrix = new Cell[size][size];

        for (int i=0; i<size; i++) { // Totes les cel·les amb valor 0
            for (int j=0; j<size; j++) {
                matrix[i][j] = new Cell(0);
            }
        }  
    }

    public Cell getCell(int fila, int col){
        return matrix[fila][col];
    }

    public void putMinesintoBoard(int fila, int columna)  { // Colocar les mines de manera aleatoria
        // Calculem la zona protegida 3x3
        int zonaProtegidaFiles = (Math.min(fila + 1, size-1) - Math.max(fila - 1, 0)) + 1;
        int zonaProtegidaCols = Math.min(columna + 1, size - 1) - Math.max(columna - 1, 0) + 1;
        int tamanyZonaProtegida = zonaProtegidaFiles * zonaProtegidaCols;
        int celdasDisponibles = size * size - tamanyZonaProtegida;
    
        // Precondició de Caixa Negra
        if (nMines > celdasDisponibles) { // Més mines que espais 
            throw new IllegalArgumentException("El nombre de mines excedeix la mida del tauler");
        }

        int count = 0;
        while (count < nMines) { 
            int r = random.nextInt(size);
            int c = random.nextInt(size);
            //Evitar la zona protegida
            if (Math.abs(r - fila) <= 1 && Math.abs(c - columna) <= 1) {
                continue;
            }
            // Col·loca la mina si no té encara una
            if (matrix[r][c].getValue() == 0) {
                matrix[r][c].setValue(-1);
                count++;
            }
        }  
    }  

    public void insertValueintoCells() {  // Assignar el valor referent a les mines adjacents.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].getValue() == -1) { // si es mina
                    continue;
                }
                int count = 0;

                for (int di = -1; di <= 1; di++) { // Comptar veïns
                    for (int dj = -1; dj <= 1; dj++) {
                        if (di == 0 && dj == 0) {
                            continue; 
                        }
                        int ni = i + di;
                        int nj = j + dj;

                        // Comprovar límits del tauler
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
          
    public int getSize() {
        return this.size;
    }
    
    public Boolean firstClick(int fila, int columna){
        if (fila >= this.size || fila < 0)
        {
            throw new IllegalArgumentException("Fila fora rang");
        }
        if (columna >= this.size || columna < 0)
        {
            throw new IllegalArgumentException("Columna fora rang");
        }

        Cell clicked = matrix[fila][columna];
        // Clic a una mina
        if (matrix[fila][columna].getValue() == -1)  {
            clickAMina();
            return false;
        } else { 
            if(clicked.getValue() == 0) { // Clic a un zero --> Expansió
                expandZeros(fila, columna);
            } else  { // Clic a numero --> Revelació
                clicked.reveal();
            }
        }  
        return true;      
    }

    public void expandZeros(int fila, int columna) {
        // Fora del tauler
        if (fila < 0||fila > (size-1) || columna < 0 ||columna > (size-1))  {
            return;
        }
        
        Cell cell = matrix[fila][columna];
        
        // Ja revelada o és una mina
        if (cell.isRevelaed() || cell.getValue() == -1) {
            return;
        }
        
        cell.reveal();
        
        if (cell.getValue() > 0) { // La cel·la es un número
            return;
        }
        
        for (int i = -1; i <= 1; i++) { // Recusiu Cell==0
            for (int j = -1; j <= 1; j++) {
                if (i==0 && j==0) {
                    continue;
                }
                expandZeros(fila+i, columna+j);
            }
        }
    }

    public void clickAMina() { // Recorrem tot el tauler
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].getValue() == -1) {
                    matrix[i][j].reveal(); // Revelem la mina
                }
            }
        }
    }

    public Boolean isWin() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = matrix[i][j];
                // Si trobem una cel·la no-mina que no estigui revelada
                if (cell.getValue() != -1 && !cell.isRevelaed()) {
                    return false; // No guanyem
                }
            }
        }
        return true; // Si totes les cell no-mines estan revelades es guanya
    }   
}





    
