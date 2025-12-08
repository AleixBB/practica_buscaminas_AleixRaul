package tqs.prac.model;

public class Board {
    protected int nMines;
    protected int size;
    protected Cell[][] matrix;
    private GenRandom random;
    /*
    Representa el tauler de joc del Buscamines (Minesweeper).
    S'encarrega de gestionar la disposició de les mines, 
    els valors de les cel·les (nombre de mines adjacents), 
    i la lògica principal de les interaccions del jugador 
    (clics, revelació i expansió de zeros).
    */

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

    /*
    Col·loca les mines aleatòriament al tauler garantint que la zona 3x3 centrada a fila,columna
    quedi lliure de mines, respectant el primer moviment del jugador.
    */
    public void putMinesintoBoard(int fila, int columna)  { 
        // Calculem la zona protegida (depen de on tinguem la mina)
        int zonaProtegidaFiles = (Math.min(fila + 1, size-1) - Math.max(fila - 1, 0)) + 1;
        int zonaProtegidaCols = Math.min(columna + 1, size - 1) - Math.max(columna - 1, 0) + 1;
        int tamanyZonaProtegida = zonaProtegidaFiles * zonaProtegidaCols;
        int celdasDisponibles = size * size - tamanyZonaProtegida;
    
        // Precondició 
        if (nMines > celdasDisponibles) { // Més mines que espais disponibles 
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
    /*
    Assigna a cada cel·la sense mina el valor que indica el nombre de mines 
    adjacents a la seva àrea veïna (8 direccions).
    */
    public void insertValueintoCells() {  
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

    /*
    Gestiona l'acció de fer clic en una cel·la del tauler, aplicant la lògica 
    de joc: mina (fi), zero (expansió) o número (revelació).
    */
    public Boolean clickACell(int fila, int columna){
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
    /*
     Implementa l'expansió recursiva. Revela la cel·la actual i les seves veïnes
     si tenen valor 0, aturant-se quan arriba a una cel·la numerada, una mina,
     o el límit del tauler.
    */
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

    /*
    Recorrem tot el tauler i a on el valor és -1 (hi ha mina)
    la revelem
     */
    public void clickAMina() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].getValue() == -1) {
                    matrix[i][j].reveal(); // Revelem la mina
                }
            }
        }
    }
    /*
    Verifica la condició de victòria: s'ha guanyat si totes les cel·les 
    que no són mines han estat revelades.
    */

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





    
