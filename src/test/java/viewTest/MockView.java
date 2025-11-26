package viewTest;
import tqs.prac.view.View;
import java.util.LinkedList;
import java.util.Queue;

public class MockView extends View {
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
    public void setUpActions(int type)
    {
        switch (type){
            case 1:
                addAction("REVEAL", 0,7);
                addAction("REVEAL", 2,0);
                addAction("REVEAL", 7,7);
                addAction("REVEAL", 1,0);
                addAction("REVEAL", 0,1);
                addAction("REVEAL", 1,2);
                addAction("REVEAL", 0,2);
                addAction("REVEAL", 0,3);
                addAction("REVEAL", 0,4);
                addAction("REVEAL", 2,2);
                addAction("REVEAL", 2,3);
                addAction("REVEAL", 2,4);
                addAction("REVEAL", 1,4);
                addAction("REVEAL", 1,1);
                addAction("REVEAL", 5,0);
                addAction("REVEAL", 5,1);
                addAction("REVEAL", 5,2);
                addAction("REVEAL", 6,0);
                addAction("REVEAL", 6,2);
                addAction("REVEAL", 7,3);
            break;


        }
    }
    
    
    
}
