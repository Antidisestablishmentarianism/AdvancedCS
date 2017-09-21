package queue_array.guitar_string;

import queue_array.ArrayQueue;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by 180502 on 9/21/2017.
 */
public class Visualizer extends JComponent implements Runnable {
    JFrame frame;

    Thread thread;

    public LinkedList<Double> buffer;

    private LinkedList<Double> samples;

    public Visualizer() {
        frame = new JFrame("Visualizer");
        frame.setSize(800, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buffer = new LinkedList<>();
        samples = new LinkedList<>();

        thread = new Thread(this, "Visualizer");
    }

    @Override
    public void run() {
        if (samples.size() > frame.getWidth())
            samples.remove();

        samples.add(buffer.remove());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (int i = 0; i < samples.size(); i++)
            // I am so sorry
            g.drawLine(i, Integer.parseInt(samples.get(i).toString()), i, Integer.parseInt(samples.get(i).toString()));
    }
}
