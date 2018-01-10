package hash_table.plant_tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Saif on 1/10/2018.
 */
public class PlantTreeTest {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("text_files/plant_tree/plant.dat"));

        int cases = Integer.parseInt(in.nextLine());
        in.nextLine();

        for (int i = 0; i < cases; i++) {
            HashMap<String, Integer> trees = new HashMap<>();
            int sum = 0;

            while (in.hasNext()) {
                String line = in.nextLine();
                if (line.equals("")) break;
                trees.put(line, trees.containsKey(line) ? trees.get(line) + 1 : 1);
                sum++;
            }

            ArrayList<String> sorted = new ArrayList<>();

            Iterator treeIt = trees.entrySet().iterator();

            while (treeIt.hasNext()) {
                Map.Entry tree = (Map.Entry) treeIt.next();
                DecimalFormat df = new DecimalFormat("#.####");
                df.setRoundingMode(RoundingMode.HALF_UP);
                sorted.add(String.format("%s %s", tree.getKey(), df.format((int)tree.getValue() / (float)sum * 100f)));
            }

            Collections.sort(sorted, Comparator.naturalOrder());

            sorted.stream().forEach(System.out::println);

            System.out.println();
        }
    }
}
