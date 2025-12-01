
package tqs.prac;

import tqs.prac.model.Board;
import tqs.prac.controller.Game;
import tqs.prac.view.View;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {


        String[] opciones = { "Fàcil", "Mitjà", "Difícil" };

        int eleccion = JOptionPane.showOptionDialog(
                null,
                "Selecciona la dificultat",
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
                size = 6;
                mines = 6;
                break;

            case 1:
                size = 10;
                mines = 20;
                break;

            case 2:
                size = 14;
                mines = 50;
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
