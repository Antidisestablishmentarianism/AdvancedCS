package stack_array;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 180502 on 8/29/2017.
 */
public class Rails {
    private static final String fileName = "/Users/Saif/Documents/Repositories/projects/src/stack_array/rails";

    BufferedReader br;
    FileReader fr;

    ArrayStack<Integer> cars = new ArrayStack<>();
    ArrayStack<Integer> station = new ArrayStack<>();

    public Rails() {
        openStream();

        String currLine;

        try {
            while ((currLine = br.readLine()) != null) {
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

                if (cars.size() == 0)
                    System.out.println("YES");
                else
                    System.out.println("NO");

                cars.clear();
                station.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        closeStream();
    }

    public static void main(String[] args) {
        new Rails();
    }

    public void openStream() {
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeStream() {
        try {
            if (br != null) br.close();
            if (fr != null) fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
