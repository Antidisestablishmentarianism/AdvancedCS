package hash_table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Saif on 11/2/2017.
 */
public class HashTest {
    public static long probes = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;

        PrintWriter writer = new PrintWriter(new File("hashtest.csv"));
        StringBuilder builder = new StringBuilder();

        builder.append("Density,Table size,Creation time,Build time,Build probes,Successful time,Successful probes,Unsuccessful time,Unsuccessful probes\n");

        double desiredDensity = 0.05;
        double increment = 0.05;
        int trials = 10;

        ArrayList<Integer> intsLarge = new ArrayList<>();
        ArrayList<String> namesLarge = new ArrayList<>();
        ArrayList<Integer> intsSucc = new ArrayList<>();
        ArrayList<Integer> intsUnsucc = new ArrayList<>();

        in = new Scanner(new File("text_files/Large Data Set.txt"));
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] parts = line.split(" ");
            intsLarge.add(Integer.parseInt(parts[0]));
            namesLarge.add(parts[1] + parts[2]);
        }

        in = new Scanner(new File("text_files/Successful Search.txt"));
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] parts = line.split(" ");
            intsSucc.add(Integer.parseInt(parts[0]));
        }

        in = new Scanner(new File("text_files/Unsuccessful Search.txt"));
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] parts = line.split(" ");
            intsUnsucc.add(Integer.parseInt(parts[0]));
        }

        while (desiredDensity <= 1) {
            ArrayList<Long> creation = new ArrayList<>();

            ArrayList<Long> builds = new ArrayList<>();
            ArrayList<Long> success = new ArrayList<>();
            ArrayList<Long> unsuccess = new ArrayList<>();

            ArrayList<Double> buildProbes = new ArrayList<>();
            ArrayList<Double> successProbes = new ArrayList<>();
            ArrayList<Double> unsuccessProbes = new ArrayList<>();

            builder.append(desiredDensity + ",");

            for (int i = 1; i <= trials; i++) {
                int load = 500000;
                long start = System.currentTimeMillis();
                HashTableQuad<Integer, String> table = new HashTableQuad<>(nextPrime((int) (load * (1 / desiredDensity))));
                long end = System.currentTimeMillis();
                long elapsed = end - start;
                creation.add(elapsed);

                if (i == 1)
                    builder.append(table.capacity() + ",");

                probes = 0;
                start = System.currentTimeMillis();
                for (int j = 0; j < intsLarge.size(); j++)
                    table.put(intsLarge.get(j), namesLarge.get(j));

                end = System.currentTimeMillis();
                elapsed = end - start;
                builds.add(elapsed);
                buildProbes.add(probes / 50000.0);
                System.out.println("Finish build " + i + " of " + trials + " for " + desiredDensity);



                probes = 0;
                start = System.currentTimeMillis();
                for (int j = 0; j < intsSucc.size(); j++)
                    table.get(intsSucc.get(j));

                end = System.currentTimeMillis();
                elapsed = end - start;
                success.add(elapsed);
                successProbes.add(probes / 1000.0);
                System.out.println("Finish successful " + i + " of " + trials + " for " + desiredDensity);



                probes = 0;
                start = System.currentTimeMillis();
                for (int j = 0; j < intsSucc.size(); j++)
                    table.get(intsSucc.get(j));

                end = System.currentTimeMillis();
                elapsed = end - start;
                unsuccess.add(elapsed);
                unsuccessProbes.add(probes / 1000.0);
                System.out.println("Finish unsuccessful " + i + " of " + trials + " for " + desiredDensity);
            }

            builder.append(creation.stream().mapToLong(n -> n).sum() / creation.size() + ",");

            builder.append(builds.stream().mapToLong(n -> n).sum() / builds.size() + ",");
            builder.append(buildProbes.stream().mapToDouble(n -> n).sum() / buildProbes.size() + ",");

            builder.append(success.stream().mapToLong(n -> n).sum() / success.size() + ",");
            builder.append(successProbes.stream().mapToDouble(n -> n).sum() / successProbes.size() + ",");

            builder.append(unsuccess.stream().mapToLong(n -> n).sum() / unsuccess.size() + ",");
            builder.append(unsuccessProbes.stream().mapToDouble(n -> n).sum() / unsuccessProbes.size());

            builder.append("\n");

            System.out.println("Finish density " + desiredDensity);
            desiredDensity = Math.round((desiredDensity + increment) * 100.0) / 100.0;
        }

        writer.write(builder.toString());
        writer.close();
    }

    public static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;

        while (!isPrime(n) && n < Integer.MAX_VALUE - 1)
            n += 2;

        return n;
    }

    public static boolean isPrime(int n) {
        if (n % 2 == 0) return false;

        int max = (int) (Math.sqrt(n)) + 1;

        for (int i = 3; i < max; i += 2)
            if (i % n == 0)
                return false;

        return true;
    }
}

