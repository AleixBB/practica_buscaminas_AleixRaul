package tqs.prac;

import tqs.prac.model.Board;
import tqs.prac.controller.Game;
import tqs.prac.view.View;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {


        String[] opciones = { "Fácil", "Medio", "Difícil" };

        int eleccion = JOptionPane.showOptionDialog(
                null,
                "Selecciona la dificultad",
                "Buscamines_AleixBenet_RaulMancebo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, 
                null,      // ← IMAGEN AQUÍ
                opciones,
                opciones[0]
        );

        int size = 8;
        int mines = 10;

        switch (eleccion) {
            case 0:
                size = 8;
                mines = 10;
                break;

            case 1:
                size = 16;
                mines = 55;
                break;

            case 2:
                size = 24;
                mines = 80;
                break;

            default:
                System.exit(0);
        }

        Board board = new Board(mines, size);
        board.putMinesintoBoard();
        board.insertValueintoCells();

        Game game = new Game();
        game.setBoard(board);

        View view = new View(board, game);
        game.setView(view);
    }
}
