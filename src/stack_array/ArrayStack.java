package stack_array;

import java.util.NoSuchElementException;

/**
 * Created by Saif on 8/25/17.
 */

public class ArrayStack<Item> implements IStack<Item> {
    protected final static int INIT_SIZE = 10;

    protected Item[] container;
    protected int top;

    public ArrayStack() {
        container = (Item[]) new Object[INIT_SIZE];
        top = -1;
    }

    @Override
    public void push(Item value) {
        if (isFull()) {
            Item[] temp = (Item[]) new Object[(int) (container.length * 1.5)];

            for (int i = 0; i < container.length; i++) {
                temp[i] = container[i];
            }

            container = temp;
        }

        container[++top] = value;
    }

    @Override
    public Item pop() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        return container[top--];
    }

    @Override
    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        return container[top];
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return top <= -1;
    }

    @Override
    public boolean isFull() {
        return top >= container.length - 1;
    }

    @Override
    public void clear() {
        container = (Item[]) new Object[container.length];
        top = -1;
    }

    public String toString() {
        String out = "[";

        for (int i = 0; i < top + 1; i++) {
            out += container[i];

            if (i != top) out += ", ";
        }

        return out + "]";
    }
}
