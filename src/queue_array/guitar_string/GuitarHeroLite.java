package queue_array.guitar_string;

import queue_array.ArrayQueue;

/*****************************************************************************
 *  Compilation:  javac queue_array.guitar_string.GuitarHeroLite.java
 *  Execution:    java  queue_array.guitar_string.GuitarHeroLite
 *  Dependencies: queue_array.guitar_string.StdAudio.java queue_array.guitar_string.StdDraw.java GuitarString.java
 *
 *  Plays two guitar strings (concert A and concert C) when the user
 *  types the lowercase letters 'a' and 'c', respectively in the 
 *  standard drawing window.
 *
 ****************************************************************************/

public class GuitarHeroLite { 

    public static void main(String[] args) {

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        GuitarString[] strings = new GuitarString[keyboard.length()];

        for (int i = 0; i < strings.length; i++)
            strings[i] = new GuitarString(440 * Math.pow(1.05956, 3.0));

        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();


            }

            // compute the superposition of the samples
            //double sample = stringA.sample() + stringC.sample();

            // send the result to standard audio
            //StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            //stringA.tick();
            //stringC.tick();
        }
    }

}