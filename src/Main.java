import javax.swing.JFrame;

public class Main{

    public static void main(String[] args)
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        Map map = new Map();
        window.add(map);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Thread_runnable thread = new Thread_runnable(0,0,4,6);
        Thread_runnable thread2 = new Thread_runnable(10,4,12,12);

        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread2);

        t1.start();
        t2.start();

    }


}
