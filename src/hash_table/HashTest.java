package hash_table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Saif on 11/2/2017.
 */
public class HashTest {
    public static void main(String[] args) {
        Scanner in;

        double desiredDensity = 0.67;
        int load = 10120;
        HashTable<String, Long> table = new HashTable<>(nextPrime((int)(load * (1 / desiredDensity))));
        System.out.println("Table size: " + table.capacity());

        try {
            in = new Scanner(new File("build.txt"));
        } catch (FileNotFoundException e) {
            in = new Scanner(System.in);
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] parts = line.split(" ");
            table.put(parts[0], Long.parseLong(parts[1]));
        }
        long end = System.currentTimeMillis();
        long elapsed = end - start;
        System.out.println("Build time: " + elapsed + " ms");

        try {
            in = new Scanner(new File("successful.txt"));
        } catch (FileNotFoundException e) {
            in = new Scanner(System.in);
            e.printStackTrace();
        }

        start = System.currentTimeMillis();
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] parts = line.split(" ");
            table.get(parts[0]);
        }
        end = System.currentTimeMillis();
        elapsed = end - start;
        System.out.println("Success time: " + elapsed + " ms");

        try {
            in = new Scanner(new File("unsuccessful.txt"));
        } catch (FileNotFoundException e) {
            in = new Scanner(System.in);
            e.printStackTrace();
        }

        start = System.currentTimeMillis();
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] parts = line.split(" ");
            table.get(parts[0]);
        }
        end = System.currentTimeMillis();
        elapsed = end - start;
        System.out.println("Unsuccessful time: " + elapsed + " ms");
    }

    public static int nextPrime(int n) {
        while (!isPrime(n) && n < Integer.MAX_VALUE - 1)
            n++;

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

