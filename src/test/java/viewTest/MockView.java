package viewTest;
import tqs.prac.view.View;
import java.util.LinkedList;
import java.util.Queue;
import tqs.prac.view.IView;

public class MockView implements IView {
    private Queue<String> actions = new LinkedList<>();
    private Queue<Integer> xs = new LinkedList<>();
    private Queue<Integer> ys = new LinkedList<>();
    public void addAction(String action, int x, int y)
    {
        actions.add(action);
        xs.add(x);
        ys.add(y);
    }
    public int getnActions()
    {
        return actions.size();
    }
    public String getAction()
    {
        return actions.poll();
    }
    public int getClickedX()
    {
        return xs.poll();
    }

    public int getClickedY()
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
                addAction("REVEAL", 0,1);
                addAction("REVEAL", 0,2);
                addAction("REVEAL", 0,3);
                addAction("REVEAL", 2,3);
                addAction("REVEAL", 2,4);
                addAction("REVEAL", 5,0);
                addAction("REVEAL", 5,1);
                addAction("REVEAL", 5,2);
                addAction("REVEAL", 6,0);
                addAction("REVEAL", 6,2);
                addAction("REVEAL", 4,0);
                addAction("REVEAL", 3,3);
                addAction("REVEAL", 2,7);


            break;
            case 2:
                addAction("FLAG", 2, 4);
                addAction("REVEAL", 3, 3);
                addAction("REVEAL", 0,0);
            break;
            case 3:
                addAction("FLAG", 2, 4);
                addAction("REVEAL", 3, 3);
                addAction("REVEAL", 0,0);
                addAction("FLAG", 2,3);
                addAction("REVEAL", 4,6);




        }
    }
    public void refresh() {
    }
    
    
    
}
