package tqs.prac;

import tqs.prac.model.Board;
import tqs.prac.controller.Game;
import tqs.prac.view.View;

public class Main {

    public static void main(String[] args) {

        // Parámetros del juego
        int size = 10;     // tamaño del tablero 10x10
        int mines = 10;    // número de minas

        // Crear modelo
        Board board = new Board(mines, size);
        board.putMinesintoBoard();
        board.insertValueintoCells();

        // Crear controlador
        Game game = new Game();
        game.setBoard(board);

        // Crear vista (esto abre la ventana Swing)
        View view = new View(board, game);

        // Asociar vista al controlador
        game.setView(view);

        // ¡Listo! El usuario juega desde la interfaz gráfica
    }
}
