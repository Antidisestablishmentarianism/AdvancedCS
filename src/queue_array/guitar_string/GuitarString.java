package queue_array.guitar_string;

import queue_array.ArrayQueue;

/**
 * Created by 180502 on 9/5/2017.
 */
public class GuitarString {
    private final int SAMPLE_RATE = 44100;
    private final double DECAY_FACTOR = 0.994;
    private final ArrayQueue<Double> buffer;

    private int time = 0;

    public GuitarString(double frequency) {
        buffer = new ArrayQueue<>((int) Math.ceil(SAMPLE_RATE / frequency));
    }

    public GuitarString(double[] init) {
        buffer = new ArrayQueue<>(init.length);

        for (double element : init)
            buffer.enqueue(element);
    }

    public void pluck() {
        buffer.clear();

        for (int i = 0; i < buffer.maxSize(); i++)
            buffer.enqueue(Math.random() - 0.5);
    }

    public void tick() {
        if (buffer.isEmpty()) return;

        double a = buffer.dequeue();
        double b = buffer.peek();

        buffer.enqueue((a + b) / 2 * DECAY_FACTOR);

        time++;
    }

    public double sample() {
        if (buffer.isEmpty()) return 0;

        return buffer.peek();
    }

    public int time() {
        return time;
    }
}
