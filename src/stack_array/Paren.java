package stack_array;

import java.util.Scanner;

/**
 * Created by Saif on 8/26/17.
 */
public class Paren {
    Scanner in = new Scanner(System.in);

    ArrayStack<String> stack = new ArrayStack<>();

    public Paren() {
        String input = in.nextLine();

        while (!input.equals("exit")) {
            System.out.println(checkParen(input));
            stack.clear();
            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        new Paren();
    }

    public String checkParen(String input) {
        String[] values = input.split("");
        String answer = "YES";

        if (!input.equals("")) {
            for (int i = 0; i < values.length; i++) {
                if (values[i].matches("[(\\[{]")) {
                    stack.push(values[i]);
                } else if (values[i].matches("[)\\]}]")) {
                    boolean matched = false;

                    if (!stack.isEmpty()) {
                        String open = stack.pop();

                        if (values[i].equals(")") && open.equals("(")) {
                            matched = true;
                        }

                        if (values[i].equals("]") && open.equals("[") && !matched) {
                            matched = true;
                        }

                        if (values[i].equals("}") && open.equals("{") && !matched) {
                            matched = true;
                        }
                    }

                    if (!matched) {
                        answer = "NO";
                        break;
                    }
                }
            }
        }

        if (stack.isEmpty())
            return answer;
        else
            return "NO";
    }
}
