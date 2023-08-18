
public class Thread_runnable implements Runnable
{

    Actor x;

    public Thread_runnable(int xs,int ys,int xg,int yg)
    {
        x= new Actor(xs,ys,xg,yg);
    }
    @Override
    public void run()
    {
        x.search();
        System.out.println("Thread is running. Goal is :" + x.goal.row + " " + x.goal.col);
    }

}