package queue_array.guitar_string;

import queue_array.ArrayQueue;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Saif on 9/21/2017.
 */
public class Visualizer extends JComponent implements Runnable {
    private final int amplitude = 100;

    JFrame frame;

    Thread thread;

    public LinkedList<Double> samples;

    public Visualizer() {
        frame = new JFrame("Visualizer");
        frame.setSize(800, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);

        samples = new LinkedList<>();

        double middle = frame.getHeight() / 2;
        for (int i = 0; i < frame.getWidth(); i++)
            samples.add(middle);

        thread = new Thread(this, "Visualizer");
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            for (int i = samples.size(); i > frame.getWidth(); i--)
                samples.remove();

            frame.paintComponents(frame.getGraphics());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        double middle = frame.getHeight() / 2;

        int lastY;
        double tempSample = Double.parseDouble(samples.get(0).toString());
        double tempMult = tempSample * amplitude;
        lastY = (int) (tempMult + middle);

        for (int i = 1; i < samples.size(); i++) {
            // I am so sorry
            double sample = Double.parseDouble(samples.get(i).toString());
            double mult = sample * amplitude;
            int y = (int) (mult + middle);

            g.drawLine(i - 1, lastY, i, y);

            lastY = y;
        }
    }
}
