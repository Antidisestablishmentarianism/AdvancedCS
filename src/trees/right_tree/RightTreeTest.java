package trees.right_tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Saif on 1/12/2018.
 */
public class RightTreeTest {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("text_files/right_tree/righttree.dat"));

        int cases = Integer.parseInt(in.nextLine());

        for (int caseNum = 1; caseNum <= cases; caseNum++) {
            String line = in.nextLine();

            int[] tree = line.chars().map(o -> Integer.parseInt(o + "")).toArray();

            for (int i = 0; i < tree.length; i++) {
                if (tree[i * 2 + 1] > tree[i * 2 + 2])
                    System.out.printf("");
            }
        }
    }
}
