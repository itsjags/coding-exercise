package com.jags.coding.exercises.boardgame;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by Jags on 03/11/2016.
 */
public class RandomTest {

    public static void main(String[] args) {
        Random random = new Random();
//        System.out.println(random.ints(0, 20).count());
        random.ints(8, 0, 8).forEach(value -> System.out.println(value) );
        System.out.println(random.nextInt());

        System.out.println(3/2);
        System.out.println(2%8);

//        IntStream.range()
    }
}
