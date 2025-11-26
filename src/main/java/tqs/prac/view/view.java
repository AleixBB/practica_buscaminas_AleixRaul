package tqs.prac.view;

public class View {
    private int size;

    public View(){
        this.size = 8;
    }
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
            for (int j = 0; j < size; j++) {
                System.out.print("|   ");
            }
            System.out.println("|");
        }
        for (int j = 0; j < size; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    
}
