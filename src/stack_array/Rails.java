package stack_array;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 180502 on 8/29/2017.
 */
public class Rails {
    private static final String fileName = "C:\\Users\\180502\\IdeaProjects\\Stack\\src\\rails.txt";

    BufferedReader br;
    FileReader fr;

    ArrayStack<Integer> cars = new ArrayStack<>();
    ArrayStack<Integer> station = new ArrayStack<>();

    public Rails() {
        openStream();

        String currLine;

        try {
            while ((currLine = br.readLine()) != null) {
                //put logic here
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

            String currLine;

            while ((currLine = br.readLine()) != null) {

            }
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
