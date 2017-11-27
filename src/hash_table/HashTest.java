package hash_table;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Saif on 11/2/2017.
 */
public class HashTest {
    public static int probes = 0;

    public static void main(String[] args) {
        Scanner in;

        double desiredDensity = 0.05;
        double increment = 0.05;
        int trials = 10;

        while (desiredDensity <= 1) {
            ArrayList<Long> builds = new ArrayList<>();
            ArrayList<Long> creation = new ArrayList<>();
            ArrayList<Long> success = new ArrayList<>();
            ArrayList<Long> unsuccess = new ArrayList<>();

            ArrayList<Integer> buildProbes = new ArrayList<>();
            ArrayList<Integer> successProbes = new ArrayList<>();
            ArrayList<Integer> unsuccessProbes = new ArrayList<>();

            System.out.println("Density: " + desiredDensity);

            for (int i = 0; i < trials; i++) {
                int load = 50000;
                long start = System.currentTimeMillis();
                HashTable<Integer, String> table = new HashTable<>(nextPrime((int) (load * (1 / desiredDensity))));
                long end = System.currentTimeMillis();
                long elapsed = end - start;

                creation.add(elapsed);

                if (i == 0)
                System.out.println("Table size: " + table.capacity());

                try {
                    in = new Scanner(new File("Large Data Set.txt"));
                } catch (FileNotFoundException e) {
                    in = new Scanner(System.in);
                    e.printStackTrace();
                }

                probes = 0;
                start = System.currentTimeMillis();
                while (in.hasNext()) {
                    String line = in.nextLine();
                    String[] parts = line.split(" ");
                    table.put(Integer.parseInt(parts[0]), parts[1]);
                }
                end = System.currentTimeMillis();
                elapsed = end - start;
                builds.add(elapsed);
                buildProbes.add(probes);

                try {
                    in = new Scanner(new File("Successful Search.txt"));
                } catch (FileNotFoundException e) {
                    in = new Scanner(System.in);
                    e.printStackTrace();
                }

                probes = 0;
                start = System.currentTimeMillis();
                while (in.hasNext()) {
                    String line = in.nextLine();
                    String[] parts = line.split(" ");
                    table.get(Integer.parseInt(parts[0]));
                }
                end = System.currentTimeMillis();
                elapsed = end - start;
                success.add(elapsed);
                successProbes.add(probes);

                try {
                    in = new Scanner(new File("Unsuccessful Search.txt"));
                } catch (FileNotFoundException e) {
                    in = new Scanner(System.in);
                    e.printStackTrace();
                }

                probes = 0;
                start = System.currentTimeMillis();
                while (in.hasNext()) {
                    String line = in.nextLine();
                    String[] parts = line.split(" ");
                    table.get(Integer.parseInt(parts[0]));
                }
                end = System.currentTimeMillis();
                elapsed = end - start;
                unsuccess.add(elapsed);
                unsuccessProbes.add(probes);
            }

            System.out.println("Average creation time: " + creation.stream().mapToLong(n -> n).sum() / creation.size());

            System.out.println("Average build time: " + builds.stream().mapToLong(n -> n).sum() / builds.size());
            System.out.println("Average build probes: " + buildProbes.stream().mapToLong(n -> n).sum() / buildProbes.size());

            System.out.println("Average successful time: " + success.stream().mapToLong(n -> n).sum() / success.size());
            System.out.println("Average successful probes: " + successProbes.stream().mapToLong(n -> n).sum() / successProbes.size());

            System.out.println("Average unsuccessful time: " + unsuccess.stream().mapToLong(n -> n).sum() / unsuccess.size());
            System.out.println("Average unsuccessful probes: " + unsuccessProbes.stream().mapToLong(n -> n).sum() / unsuccessProbes.size());

            System.out.println();

            desiredDensity += increment;
        }
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

