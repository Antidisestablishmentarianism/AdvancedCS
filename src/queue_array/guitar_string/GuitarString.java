package queue_array.guitar_string;

import queue_array.ArrayQueue;

/**
 * Created by Saif on 9/5/2017.
 */
public class GuitarString {

    /* Sample rates to try:
        8000
        11025
        16000
        22050
        32000
        37800
        44100 STANDARD
        47250
        48000
        50000
        54000
        88200
        96000
        176400
        192000
        352800
        2822400
        5644800
     */
    private final int SAMPLE_RATE = 44100;

    /* Decay factors to try:
        0.994 STANDARD
        0.95
        1.0
        0.999
        0.6 (with higher sample rates)
     */
    private final double DECAY_FACTOR = 1.0;
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

    public void clear() {
        buffer.clear();
        time = 0;
    }

    public double sample() {
        return buffer.isEmpty() ? 0 : buffer.peek();
    }

    public int time() {
        return time;
    }

    private double randomWave() {
        return Math.random() - 0.5;
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
