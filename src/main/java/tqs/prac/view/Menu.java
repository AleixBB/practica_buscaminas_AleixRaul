package tqs.prac.view;

import javax.swing.JOptionPane;

public class Menu {
    public int menu(){
         String[] opciones = { "Fàcil", "Mitjà", "Difícil" };

        int eleccion = JOptionPane.showOptionDialog(
                null,
                "Selecciona la dificultat",
                "Buscamines_AleixBenet_RaulMancebo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, 
                null,      
                opciones,
                opciones[0]
        );
        return eleccion;


    }
    
}
