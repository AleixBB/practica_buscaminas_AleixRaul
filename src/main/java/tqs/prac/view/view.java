package tqs.prac.view;

import java.util.LinkedList;
import java.util.Queue;


public class View {
    private Queue<String> actions = new LinkedList<>();
    private Queue<Integer> xs = new LinkedList<>();
    private Queue<Integer> ys = new LinkedList<>();
    public void addAction(String action, int x, int y)
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
