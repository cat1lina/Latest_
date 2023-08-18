import java.util.ArrayList;

public class Thread_runnable implements Runnable
{

    Actor x;

    public Thread_runnable() {
    }
    public Thread_runnable(int xs,int ys,int xg,int yg)
    {
        x= new Actor(xs,ys,xg,yg);
    }
    @Override
    public void run()
    {
        x.search();
        System.out.println("Here is a thread running");
    }

}