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

        builder.append("Density, Table size, Creation time, Build read time, Build time, Build probes, Successful read time, Successful time, Successful probes, Unsuccessful read time, Unsuccessful time, Unsuccessful probes\n");

        double desiredDensity = 0.05;
        double increment = 0.05;
        int trials = 10;

        while (desiredDensity <= 1) {
            ArrayList<Long> creation = new ArrayList<>();
            ArrayList<Long> buildRead = new ArrayList<>();
            ArrayList<Long> successRead = new ArrayList<>();
            ArrayList<Long> unsuccessRead = new ArrayList<>();

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

                ArrayList<Integer> ints = new ArrayList<>();
                ArrayList<String> names = new ArrayList<>();

                if (i == 1)
                    builder.append(table.capacity() + ",");

                in = new Scanner(new File("text_files/Large Data Set.txt"));

                start = System.currentTimeMillis();
                while (in.hasNext()) {
                    String line = in.nextLine();
                    String[] parts = line.split(" ");
                    ints.add(Integer.parseInt(parts[0]));
                    names.add(parts[1] + parts[2]);
                }
                end = System.currentTimeMillis();
                elapsed = end - start;
                buildRead.add(elapsed);

                probes = 0;
                start = System.currentTimeMillis();
                for (int j = 0; j < ints.size(); j++)
                    table.put(ints.get(j), names.get(j));

                end = System.currentTimeMillis();
                elapsed = end - start;
                builds.add(elapsed);
                buildProbes.add(probes / 50000.0);
                System.out.println("Finish build " + i + " of " + trials + " for " + desiredDensity);

                in = new Scanner(new File("text_files/Successful Search.txt"));
                ints.clear();
                names.clear();

                start = System.currentTimeMillis();
                while (in.hasNext()) {
                    String line = in.nextLine();
                    String[] parts = line.split(" ");
                    ints.add(Integer.parseInt(parts[0]));
                }
                end = System.currentTimeMillis();
                elapsed = end - start;
                successRead.add(elapsed);

                probes = 0;
                start = System.currentTimeMillis();
                for (int j = 0; j < ints.size(); j++)
                    table.get(ints.get(j));

                end = System.currentTimeMillis();
                elapsed = end - start;
                success.add(elapsed);
                successProbes.add(probes / 1000.0);
                System.out.println("Finish successful " + i + " of " + trials + " for " + desiredDensity);

                in = new Scanner(new File("text_files/Unsuccessful Search.txt"));
                ints.clear();

                start = System.currentTimeMillis();
                while (in.hasNext()) {
                    String line = in.nextLine();
                    String[] parts = line.split(" ");
                    ints.add(Integer.parseInt(parts[0]));
                }
                end = System.currentTimeMillis();
                elapsed = end - start;
                unsuccessRead.add(elapsed);

                probes = 0;
                start = System.currentTimeMillis();
                for (int j = 0; j < ints.size(); j++)
                    table.get(ints.get(j));

                end = System.currentTimeMillis();
                elapsed = end - start;
                unsuccess.add(elapsed);
                unsuccessProbes.add(probes / 1000.0);
                System.out.println("Finish unsuccessful " + i + " of " + trials + " for " + desiredDensity);
            }

            builder.append(creation.stream().mapToLong(n -> n).sum() / creation.size() + ",");

            builder.append(buildRead.stream().mapToLong(n -> n).sum() / buildRead.size() + ",");
            builder.append(builds.stream().mapToLong(n -> n).sum() / builds.size() + ",");
            builder.append(buildProbes.stream().mapToDouble(n -> n).sum() / buildProbes.size() + ",");

            builder.append(successRead.stream().mapToLong(n -> n).sum() / successRead.size() + ",");
            builder.append(success.stream().mapToLong(n -> n).sum() / success.size() + ",");
            builder.append(successProbes.stream().mapToDouble(n -> n).sum() / successProbes.size() + ",");

            builder.append(unsuccessRead.stream().mapToLong(n -> n).sum() / unsuccessRead.size() + ",");
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

