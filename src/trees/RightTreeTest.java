package trees;

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
            String line = in.nextLine() + "00000000000000000000000000000000000000000000000";

            int[] tree = line.chars().map(o -> o - 48).toArray();

            boolean rightTree = true;

            for (int i = tree.length - 1; i >= 0; i--) {
                int left = i * 2 + 1;
                int right = i * 2 + 2;

                if (left >= tree.length) continue;
                tree[i] += tree[left];

                if (right >= tree.length) continue;
                tree[i] += tree[right];

                if (tree[left] > tree[right]) {
                    System.out.printf("Tree %d is not a right-tree.\n", caseNum);
                    rightTree = false;
                    break;
                }
            }

            if (rightTree)
                System.out.printf("Tree %d is a right-tree.\n", caseNum);
        }
    }
}
