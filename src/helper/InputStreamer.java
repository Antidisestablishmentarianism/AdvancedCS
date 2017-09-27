package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Saif on 8/30/17.
 */
public class InputStreamer {
    private BufferedReader br;
    private FileReader fr;

    public InputStreamer(String fileName) {
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFile(String file) {
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean hasNext() {
        try {
            return br.ready();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
