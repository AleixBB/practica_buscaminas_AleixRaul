package viewTest;
import tqs.prac.view.View;
import java.util.LinkedList;
import java.util.Queue;

public class MockView extends View {
    private Queue<String> actions = new LinkedList<>();
    private Queue<Integer> xs = new LinkedList<>();
    private Queue<Integer> ys = new LinkedList<>();
    private String lastMessage;
    public void addAction(string action, int x, int y)
    {
        actions.add(action);
        xs.add(x);
        ys.add(y);
    }
    public String getAction()
    {
        return actions.poll();
    }
    public int getX()
    {
        return xs.poll();
    }
    public int getY()
    {
        return ys.poll();
    }
    
    
}
