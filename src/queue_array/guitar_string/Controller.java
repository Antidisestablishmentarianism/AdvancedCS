package queue_array.guitar_string;

/**
 * Created by Saif on 9/21/2017.
 */
public class Controller {

    GuitarHeroLite gh;
    Visualizer v;

    public Controller() {
        v = new Visualizer(this);
        gh = new GuitarHeroLite(this);
    }

    public void addSample(double sample) {
        v.samples.add(sample);
    }

    public static void main(String[] args) {
        new Controller();
    }
}
