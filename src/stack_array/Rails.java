package stack_array;

import helper.InputStreamer;

/**
 * Created by Saif on 8/29/2017.
 */
public class Rails {
    //private static final String fileName = "/Users/Saif/Documents/Repositories/projects/src/stack_array/rails";
    private static final String fileName = "C:\\Users\\180502\\Desktop\\AdvancedCS\\src\\stack_array\\rails";

    InputStreamer streamer;

    ArrayStack<Integer> cars = new ArrayStack<>();
    ArrayStack<Integer> station = new ArrayStack<>();

    public Rails() {
        streamer = new InputStreamer(fileName);

        String currLine;

        while ((currLine = streamer.readLine()) != null) {
            String[] input = currLine.split(" ");

            if (input.length <= 1) continue;

            for (int i = input.length - 1; i >= 0; i--) {
                cars.push(Integer.parseInt(input[i]));
            }

            for (int i = 1; i <= input.length + 1; i++) {
                if (station.size() > 0) {
                    if (station.peek() == cars.peek()) {
                        station.pop();
                        cars.pop();
                        i--;
                        continue;
                    }
                }

                station.push(i);
            }

            System.out.println(cars.size() == 0 ? "YES" : "NO");

            cars.clear();
            station.clear();
        }

        streamer.closeStream();
    }

    public static void main(String[] args) {
        new Rails();
    }
}
