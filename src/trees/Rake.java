package trees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Saif on 1/29/2018.
 */
public class Rake {
    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("text_files/rake_em_up/rake.dat"));

        int caseNum = 1;

        while (in.hasNext()) {
            map = new HashMap<>();

            read(0, 0, in);

            String out = map.keySet().stream().mapToInt(n -> n).sorted().map(n -> map.get(n)).mapToObj(Integer::toString).collect(Collectors.joining(" "));

            if (out.length() == 0) continue;

            System.out.printf("Case %d:\n", caseNum);
            System.out.printf(out);
            System.out.println();
            System.out.println();

            caseNum++;
        }
    }

    static void read(int column, int index, Scanner in) {
        int num = in.nextInt();

        if (num == -1)
            return;

        if (map.get(column) == null)
            map.put(column, 0);

        map.put(column, map.get(column) + num);

        int left = index + 1;
        int right = index + 2;

        read(column - 1, left, in);

        read(column + 1, right, in);
    }
}
