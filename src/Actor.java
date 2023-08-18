import java.util.ArrayList;


public class Actor {


    final static int mcol = 15;
    final static int mrow = 15;

    public static Node[][] Cost_Map = new Node[15][15];

    public static Node[][] SolidsMap = Map.Solids_Map;

    public void setMap()
    {
        for(int i=0;i<mrow;i++)
        {
            for(int j=0;j<mcol;j++)
            {
                Cost_Map[i][j] = new Node(i,j);
            }
        }
    }




    Node start, goal, curr;
    ArrayList<Node> openlist = new ArrayList<>();

    boolean goalreached = false;

    //Node[][] node6 = getNodeArray();


    public Actor() {
        setMap();
    }
    public Actor(int xs,int ys,int xg,int yg)
    {
        setMap();
        setAsStart(xs,ys);
        setAsGoal(xg,yg);

    }


    public void setAsStart(int row, int col)
    {
        if(!SolidsMap[row][col].solid)
        {
            SolidsMap[row][col].setAsStart();
            start = Cost_Map[row][col];
            curr = start;
            curr.solid = true;
        }
        else
        {
            System.out.println("Start node is a solid!! Choose another.");
        }
    }
    public void setAsGoal(int row, int col)
    {
        if(!SolidsMap[row][col].solid) {
            SolidsMap[row][col].setAsGoal();
            goal = Cost_Map[row][col];
        }
        else {
            System.out.println("Goal node is a solid!! Choose another.");
        }
    }
    public void setCost()
    {
        for(int i=0;i<mcol;i++)
        {
            for(int j=0;j<mrow;j++)
            {
                getCost(SolidsMap[j][i]);
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
        setCost();
        while(!goalreached)
        {
            synchronized(this)
            {

                curr.solid=true;
                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                curr.setAsChecked();
                openlist.remove(curr);
                int x = curr.col;
                int y = curr.row;
                for (int i = (Math.max(x - 1, 0)); i < (Math.min(x + 2, 15)); i++)
                {
                    for (int j = (Math.max(y - 1, 0)); j < (Math.min(y + 2, 15)); j++)
                    {
                        if (!openlist.contains(Cost_Map[i][j]) && !SolidsMap[i][j].solid)
                        {
                            openNode(SolidsMap[j][i]);
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
                    if(minnode.row == goal.row && minnode.col == goal.col)
                    {
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

        Node curr = SolidsMap[goal.row][goal.col];
        curr.solid=true;
        while(curr.col != start.col && curr.row != start.row  /*curr.parent != null*/)
        {
            System.out.println("END REACHED");
            curr = curr.parent;
            curr.open=false;
            curr.closed=false;
            curr.solid=false;

            if(curr.col != start.col && curr.row != start.row)
            {

                curr.setPath();

            }
        }
    }
}