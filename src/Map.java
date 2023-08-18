

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.Serial;

import javax.swing.JPanel;


public class Map extends JPanel{
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;
    final static int mcol = 15;
    final static int mrow = 15;
    final int node_size = 60;
    final int screenx = node_size * mcol;
    final int screeny= node_size * mrow;


    public static Node[][] node = new Node[mrow][mcol];

    public static Node[][] getNodeArray()
    {
        return node;
    }

    public static int getMcol()
    {
        return mcol;
    }

    public static int getMrow()
    {
        return mrow;
    }

    public Map()
    {
        this.setPreferredSize(new Dimension(screenx, screeny));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(mrow,mcol));
//	this.addKeyListener(new KeyHandler(this,this));
        this.setFocusable(true);

        int col=0;
        int row=0;

        while(col<mcol && row < mrow)
        {
            node[row][col] = new Node(row,col);
            this.add(node[row][col]);

            col++;
            if(col == mcol)
            {
                col =0;
                row++;
            }
        }
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

    private void setAsSolid(int row, int col)
    {
        node[row][col].setSolid();
    }
}