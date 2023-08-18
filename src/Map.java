import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;


public class Map extends JPanel{
    /**
     *
     */

    private static final long serialVersionUID = 1L;
    final static int mcol = 15;
    final static int mrow = 15;
    final int node_size = 60;
    final int screenx = node_size * mcol;
    final int screeny= node_size * mrow;

    public static Node[][] Solids_Map = new Node[mrow][mcol];

    public Map()
    {
        this.setPreferredSize(new Dimension(screenx, screeny));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(mrow,mcol));
//	this.addKeyListener(new KeyHandler(this,this));
        this.setFocusable(true);

        setMap();

        setAsSolid( 2,3);
        setAsSolid( 3,3);
        setAsSolid( 4,4);
        setAsSolid( 5,4);
        setAsSolid( 6,6);
        setAsSolid( 5,6);
        setAsSolid( 7,6);
        setAsSolid( 7,7);
        setAsSolid( 5,5);
        setAsSolid( 8,7);
        setAsSolid( 9,7);
        setAsSolid( 10,7);
        setAsSolid( 11,7);

    }
    public void setMap()
    {
        int col=0;
        int row=0;

        while(col<mcol && row < mrow)
        {
            Solids_Map[row][col] = new Node(row,col);
            this.add(Solids_Map[row][col]);

            col++;
            if(col == mcol)
            {
                col =0;
                row++;
            }
        }
    }

//    public void resetMap()
//    {
//        for(int i=0;i<mcol;i++)
//        {
//            for(int j=0;j<mrow;j++)
//            {
//                node[i][j].setAsUnchecked();
//            }
//        }
//    }
    private void setAsSolid(int row, int col)
    {
        Solids_Map[row][col].setSolid();
    }
}