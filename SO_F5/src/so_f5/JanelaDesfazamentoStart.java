
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JanelaDesfazamentoStart implements Runnable {

    protected Monitor m;
    protected int i;

    public JanelaDesfazamentoStart(Monitor m, int i) {
        this.m = m;
        this.i = i;
    }

    public void run() {

        String myname = Thread.currentThread().getName();
        JFrame f = new JFrame(myname);
        JLabel l = new JLabel("Thread?");
        f.add(l);
        f.setSize(1500, 100);
        f.setLocation(300, i * 100);
        f.setVisible(true);

        m.espera();
        
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
            }
            l.setText("" + l.getText() + " Sim");
        }
        f.dispose();
    }

    public static void main(String args[]) throws InterruptedException {
        Monitor mon = new Monitor();
        Thread[] ths = new Thread[8];

        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(new JanelaDesfazamentoStart(mon, i), "Th" + i);
            ths[i].start();
            Thread.sleep(1000);
        }
        System.out.println("[Main] All threads created!");
        System.out.println("[Main] Activating threads!");

        try {

            Thread.sleep(0);
            mon.acordaTodas();

            for (int i = 0; i < ths.length; i++) {
                ths[i].join();
            }
        } catch (InterruptedException ie) {
        }
        System.out.println("[Main] All threads ended!");
    }

}