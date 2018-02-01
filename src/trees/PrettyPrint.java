package trees;

import java.util.*;
import java.util.Collections.*;
import java.io.*;
import java.util.stream.*;

/**
 * Created by Saif on 1/31/2018.
 */
public class PrettyPrint {
	static HashMap<Integer, Integer> layers;
	static int[] tree, line;

    public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("text_files/pretty_print/open.dat"));

		while (in.hasNext()) {
			layers = new HashMap<>();
			line = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			tree = new int[line.length];

			for (int i = 0; i < tree.length; i++)
				tree[i] = Integer.MIN_VALUE; 

			for (int i = 0; i < tree.length; i++)
				insert(i, line[i]);

			count(0, 0);

			int[] sorted = layers.keySet().stream().mapToInt(n -> n).sorted().toArray();
			int[] reversed = sorted.clone();

			for (int i = 0; i < reversed.length / 2; i++) {
				int temp = reversed[i];
				reversed[i] = reversed[reversed.length - i - 1];
				reversed[reversed.length - i - 1] = temp;
			}

			for (int n : reversed) {
				String spaces = "";

				for (int i = 0; i < layers.get(n); i++)
					spaces += " ";

				System.out.printf("%s%d\n", spaces, n);
			}

			String inOrder = Arrays.stream(sorted).mapToObj(n -> ""+n).collect(Collectors.joining(","));

			System.out.printf("In order: %s\n", inOrder);

			System.out.println();
		}
    }

	public static void count(int index, int depth) {
		if (index >= tree.length)
			return;

		layers.put(tree[index], depth);

		count(index * 2 + 1, depth + 1);
		count(index * 2 + 2, depth + 1);
	}

	public static void insert(int tryIndex, int num) {
		if (tryIndex >= tree.length)
			return;

		if (tree[tryIndex] == Integer.MIN_VALUE) {
			tree[tryIndex] = num;
			return;
		}

		if (num > tree[tryIndex]) {
			insert(tryIndex * 2 + 2, num);
			return;
		}

		if (num < tree[tryIndex]) {
			insert(tryIndex * 2 + 1, num);
			return;
		}
	}
}
