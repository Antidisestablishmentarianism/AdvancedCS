package trees.rake_em_up;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Saif on 1/29/2018.
 */
public class Rake {
    static HashMap<Integer, Integer> map;
    static int[] line;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("text_files/rake_em_up/rake.dat"));

        while (in.hasNext()) {
            map = new HashMap<>();

            line = Arrays.stream(in.nextLine().split(" ")).mapToInt(n -> Integer.parseInt(n)).toArray();

            read(0, 0);


        }
    }

    static void read(int column, int index) {
        if (line[index] == -1)
            return;

        map.put(column, map.containsKey(column) ? line[index] : map.get(column) + line[index]);

        if (index * 2 + 1 < line.length)
            read(column - 1, index * 2 + 1);

        if (index * 2 + 2 < line.length)
            read(column + 1, index * 2 + 2);
    }
}
