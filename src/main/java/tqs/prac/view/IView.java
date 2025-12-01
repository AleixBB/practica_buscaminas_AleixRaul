
package tqs.prac.view;
public interface IView {
    String getAction();
    int getClickedX();
    int getClickedY();
    void refresh(); // Incluir este método para evitar NullPointerException si Game lo llama.
} 
