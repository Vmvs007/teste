
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.concurrent.*;

public class Janela implements Runnable {

    protected Monitor m;
    protected int i;

    public Janela(Monitor m, int i) {
        this.m = m;
        this.i = i;
    }

    public void run() {
        String myname = Thread.currentThread().getName();
        JFrame f = new JFrame(myname);
        JLabel l = new JLabel("Quem és?");
        f.add(l);
        f.setSize(1500, 100);
        f.setLocation(300, i * 100);
        f.setVisible(true);

        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ie) {
            }
            l.setText("" + l.getText() + " Sou uma alface do LIDL ");
        }
        f.dispose();
    }

    public static void main(String args[]) {
        Monitor mon = new Monitor();
        Thread[] ths = new Thread[9];

        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(new Janela(mon, i), "Th" + i);
            ths[i].start();
        }
        System.out.println("[Main] All threads created!");
        System.out.println("[Main] Activting threads!");

        try {
            for (int i = 0; i < ths.length; i++) {
                ths[i].join();
            }
        } catch (InterruptedException ie) {
        }
        System.out.println("[Main] All threads ended!");
    }

}
