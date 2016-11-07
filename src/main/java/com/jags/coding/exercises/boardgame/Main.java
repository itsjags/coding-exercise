package com.jags.coding.exercises.boardgame;

import java.util.Scanner;

/**
 * Created by Jags on 02/11/2016.
 */
public class Main {

    public static void main(String[] args) {
        int[][] arr = new int[2][2];

        for (int i=0; i<2; i++) {
            for (int j = 0 ; j < 2; j++) {

                System.out.print(arr[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }

        MinesBoard minesBoard = new MinesBoard(8, 8);
        GameStatus gameInPlay = minesBoard.start();
        Scanner scanner = new Scanner(System.in);
//        GameMove move = new StringValuedMove();
//        move.setValue("");
        String next = null;
        while (gameInPlay == GameStatus.IN_PROGRESS) {
            System.out.println("Make your move: Press U (up), D (down), L (left) or R (right)");
            next = scanner.next();
            gameInPlay = minesBoard.move(next);
        }
    }



}
