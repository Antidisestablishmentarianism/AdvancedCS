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

        for (int i = 0; i < buffer.capacity(); i++)
            buffer.enqueue(randomWave());
    }

    public void tick() {
        if (buffer.isEmpty()) return;

        double a = buffer.dequeue();
        double b = buffer.peek();

        buffer.enqueue((a + b) / 2 * DECAY_FACTOR);

        time++;
    }

    public double sample() {
        return buffer.isEmpty() ? 0 : buffer.peek();
    }

    public int time() {
        return time;
    }

    private double randomWave() {
        return Math.random() -0.5;
    }

    private double triangleWave(int i) {
        return (double) 1 / buffer.capacity() * i - 0.5;
    }

    private double squareWave(int i) {
        return Math.signum(Math.sin(buffer.size() / (i + 1))) / 2;
    }

    private double tanWave(int i) {
        return Math.tan(i);
    }

    private double cotanWave(int i) {
        if (Math.sin(i) != 0)
            return Math.cos(i) / Math.sin(i);
        else
            return Math.cos(i);
    }
}
