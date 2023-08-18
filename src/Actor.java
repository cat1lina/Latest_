

import java.util.ArrayList;

public class Actor {

    Node start, goal, curr;
    ArrayList<Node> openlist = new ArrayList<>();
    boolean goalreached = false;

    Node[][] node = Map.getNodeArray();
    int mrow=Map.getMrow();
    int mcol=Map.getMcol();

    public Actor(int xs,int ys,int xg,int yg)
    {
        setAsStart(xs,ys);
        setAsGoal(xg,yg);

    }


    public void setAsStart(int row, int col)
    {
        node[row][col].setAsStart();
        start= node[row][col];
        curr=start;
        curr.solid=true;
    }

    public void setAsGoal(int row, int col)
    {
        node[row][col].setAsGoal();
        goal= node[row][col];
    }

    public void setCost()
    {
        for(int i=0;i<mcol;i++)
        {
            for(int j=0;j<mrow;j++)
            {
                getCost(node[j][i]);
            }
        }
    }

    private void getCost(Node node)
    {
        node.cost=Math.abs(node.col-start.col) + Math.abs(node.row-start.row) + Math.abs(node.col - goal.col) + Math.abs(node.row - goal.row);

        node.setText("<html>C:" + node.cost + "</html>");

    }

    public void search()
    {
        while(!goalreached)
        {
            synchronized(this)
            {
                setCost();
                curr.solid=true;
//                try
//                {
//                    Thread.sleep(10);
//                }
//                catch (InterruptedException e)
//                {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                curr.setAsChecked();
                openlist.remove(curr);
                int x = curr.col;
                int y = curr.row;
                for (int i = (Math.max(x - 1, 0)); i < (Math.min(x + 2, 15)); i++)
                {
                    for (int j = (Math.max(y - 1, 0)); j < (Math.min(y + 2, 15)); j++)
                    {
                        if (!openlist.contains(node[j][i]) && !node[j][i].solid)
                        {
                            openNode(node[j][i]);
                            openlist.add(node[j][i]);
                        }
                    }
                }

                if(!openlist.isEmpty())
                {
                    Node minnode = openlist.get(0);

                    for(Node node : openlist)
                    {
                        if(node.cost < minnode.cost)
                        {
                            minnode=node;
                        }
                    }
                    curr.solid=false;
                    curr=minnode;
                    //System.out.println(" Curr: (" + curr.row + "," + curr.col + ")");
                    if(minnode == goal)
                    {
                        System.out.println("GOAL REACH!");
                        goalreached = true;
                        track();
                        openlist.clear();
                        return;
                    }
                }
            }
        }
    }

    private void openNode(Node node)
    {
        if(!openlist.contains(node) && !node.closed && !node.solid)
        {
            node.setAsOpen();
            node.parent = curr;
            openlist.add(node);
        }
    }

    private void track()
    {
        Node curr = goal;
        curr.solid=true;
        while(curr != start && curr.parent!=null)
        {
            curr = curr.parent;
            System.out.println("Curr node: " + curr.row + " " + curr.col);
            curr.open=false;
            curr.closed=false;
            curr.solid=false;
            if(curr!= start)
            {
                curr.setPath();

            }
        }
    }
}