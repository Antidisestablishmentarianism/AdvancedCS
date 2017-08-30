package stack_array;

import java.util.Scanner;

/**
 * Created by Saif on 8/26/17.
 */
public class RPNCalculator {
    Scanner in = new Scanner(System.in);

    ArrayStack<Double> stack = new ArrayStack<>();

    public RPNCalculator() {
        String input = in.nextLine();

        while (!input.equals("exit")) {
            System.out.println(evaluate(input));
            stack.clear();
            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        new RPNCalculator();
    }

    public double evaluate(String input) {
        String[] values = input.split(" ");

        for (int i = 0; i < values.length; i++) {
            double a = 1;
            double b = 0;

            if (values[i].matches("[-+*/]")) {
                a = stack.pop();
                b = stack.pop();
            }

            if (values[i].equals("+")) {
                double result = b + a;
                stack.push(result);
            } else if (values[i].equals("-")) {
                double result = b - a;
                stack.push(result);
            } else if (values[i].equals("*")) {
                double result = b * a;
                stack.push(result);
            } else if (values[i].equals("/")) {
                double result = b / a;
                stack.push(result);
            } else {
                stack.push(Double.parseDouble(values[i]));
            }
        }

        return stack.peek();
    }
}
