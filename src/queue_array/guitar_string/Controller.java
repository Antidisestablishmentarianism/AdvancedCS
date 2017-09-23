package queue_array.guitar_string;

/**
 * Created by 180502 on 9/21/2017.
 */
public class Controller {

    GuitarHeroLite gh;
    Visualizer v;

    public Controller() {
        gh = new GuitarHeroLite(this);
        v = new Visualizer();
    }

    public void addSample(double sample) {
        v.samples.add(sample);
    }

    public static void main(String[] args) {
        new Controller();
    }
}
