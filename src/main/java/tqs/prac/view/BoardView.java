package tqs.prac.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tqs.prac.model.Board;
import tqs.prac.model.Cell;
import tqs.prac.controller.Game;

public class BoardView extends JPanel {

    private Board board;
    private Game game;
    private View view;

    private JButton[][] buttons;

    public BoardView(Board board, Game game, View view) {
        this.board = board;
        this.game = game;
        this.view = view;

        int size = board.getSize();

        setLayout(new GridLayout(size, size));
        buttons = new JButton[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(40, 40));
                btn.setFont(new Font("Arial", Font.BOLD, 14));

                final int x = i;
                final int y = j;

                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        // acción del jugador
                        if (SwingUtilities.isRightMouseButton(e)) {
                            view.setAction("FLAG");
                        } else {
                            view.setAction("REVEAL");
                        }

                        view.setCoords(x, y);

                        game.startedGame();

                        // mostrar resultado
                        if (game.getGameOver()) {
                            JOptionPane.showMessageDialog(null, "💥 Has perduuut!");
                        }
                        if (game.getWin()) {
                            JOptionPane.showMessageDialog(null, "🎉 ¡Has guanyaaat!");
                        }

                        view.refresh();
                    }
                });

                buttons[i][j] = btn;
                add(btn);
            }
        }

        refresh();
    }

    public void refresh() {

    for (int i = 0; i < board.getSize(); i++) {
        for (int j = 0; j < board.getSize(); j++) {

            Cell c = board.getCell(i, j);
            JButton btn = buttons[i][j];

            // Estado: bandera
            if (c.isFlagged()) {
                btn.setText("🚩");
                btn.setForeground(Color.RED);
                btn.setBackground(new Color(30, 30, 30));
                btn.setEnabled(true);
                continue;
            }

            // Estado: no revelado
            if (!c.isRevelaed()) {
                btn.setText("");
                btn.setBackground(new Color(70, 70, 70)); // gris suave
                btn.setEnabled(true);
                continue;
            }

            // Estado: revelado
            styleRevealedButton(btn);

            // Mina
            if (c.getValue() == -1) {
                btn.setText("💣");
                btn.setForeground(Color.RED);
                btn.setBackground(new Color(120, 0, 0));
                continue;
            }

            // Casilla vacía
            if (c.getValue() == 0) {
                btn.setText("");
                btn.setBackground(new Color(90, 90, 90));
                continue;
            }

            // Número 1-8
            btn.setText(String.valueOf(c.getValue()));
            btn.setForeground(getColorForNumber(c.getValue()));
            btn.setBackground(new Color(30, 30, 30));
        }
    }
    }
    private Color getColorForNumber(int n) {
    return switch (n) {
        case 1 -> new Color(25, 118, 210);   // azul
        case 2 -> new Color(56, 142, 60);    // verde
        case 3 -> new Color(211, 47, 47);    // rojo
        case 4 -> new Color(123, 31, 162);   // púrpura
        case 5 -> new Color(255, 111, 0);    // naranja
        case 6 -> new Color(0, 151, 167);    // turquesa
        case 7 -> Color.BLACK;               // negro
        case 8 -> Color.GRAY;                // gris
        default -> Color.WHITE;
    };
    }
    private void styleRevealedButton(JButton btn) {
        btn.setEnabled(false);
        btn.setOpaque(true);
        btn.setBackground(new Color(50, 50, 50)); // gris oscuro elegante
        btn.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 2));
}


}
