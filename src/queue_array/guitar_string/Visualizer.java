package queue_array.guitar_string;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

/**
 * Created by Saif on 9/21/2017.
 */
public class Visualizer extends JComponent implements Runnable, KeyListener {
    private final int INIT_WIDTH = 800, INIT_HEIGHT = 200;
    private final int AMPLITUDE = INIT_HEIGHT * 3 / 4;

    // Lower number = higher accuracy, but may pick up weird artifacts
    private final int ACCURACY = 3;

    JFrame frame;

    Thread thread;

    public LinkedList<Double> samples;

    private static Object keyLock = new Object();
    private static LinkedList<Character> keysTyped = new LinkedList<>();

    Controller controller;

    public Visualizer(Controller controller) {
        this.controller = controller;

        frame = new JFrame("Visualizer");
        frame.setSize(INIT_WIDTH, INIT_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(this);
        frame.add(this);

        samples = new LinkedList<>();

        double middle = frame.getHeight() / 2;
        for (int i = 0; i < frame.getWidth() * 2; i++)
            samples.add(middle);

        thread = new Thread(this, "Visualizer");
        thread.start();

        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    @Override
    public void run() {
        while (true) {
            for (int i = samples.size(); i > frame.getWidth() * 2; i--)
                samples.remove();

            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        g.setColor(Color.WHITE);

        double middle = frame.getHeight() / 2;

        int lastY = getY(0, middle);

        for (int i = ACCURACY; i < samples.size(); i += ACCURACY) {
            int y = getY(i, middle);

            g.drawLine(i - ACCURACY - frame.getWidth(), lastY, i - frame.getWidth(), y);

            lastY = y;
        }
    }

    private int getY(int index, double middle) {
        double sample;

        try {
            // Sorry not sorry
            sample = Double.parseDouble(samples.get(index).toString());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            sample = 0;
        }

        double mult = sample * -AMPLITUDE;
        return (int) (mult + middle);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        synchronized (keyLock) {
            keysTyped.addFirst(e.getKeyChar());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static boolean hasNextKeyTyped() {
        synchronized (keyLock) {
            return !keysTyped.isEmpty();
        }
    }

    public static char nextKeyTyped() {
        synchronized (keyLock) {
            return keysTyped.removeLast();
        }
    }
}
