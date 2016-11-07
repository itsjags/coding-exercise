package com.jags.coding.exercises.boardgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jags on 02/11/2016.
 */
public class BoardTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGameResultForFailWhenZeroMinesAreAllowed() {

        int rows = 5;
        int cols = 5;
        MinesBoard board = new MinesBoard(rows, cols, 0);

        GameStatus gameInPlay = makeMovesTillEnd(rows, cols, board);
        assertEquals(GameStatus.LOST, gameInPlay);
    }

    @Test
    public void testGameResultForPassWhenMaximumMinesAreAllowed() {

        int rows = 5;
        int cols = 5;
        MinesBoard board = new MinesBoard(rows, cols, ((rows*cols)/2) + 1);

        GameStatus gameInPlay = makeMovesTillEnd(rows, cols, board);
        assertEquals(GameStatus.WON, gameInPlay);
    }

    private GameStatus makeMovesTillEnd(int row, int cols, MinesBoard board) {
        String directionOfMove = "R";

        GameStatus gameInPlay = board.start();
        for (int r = row - 1; r >=0 ; r--) {

            for (int c = 0; c < cols; c++) {
                gameInPlay = board.move(directionOfMove);
                if (gameInPlay != GameStatus.IN_PROGRESS) {
                    break;
                }
            }
            if (gameInPlay != GameStatus.IN_PROGRESS) {
                break;
            }
            gameInPlay = board.move("U");
            if (gameInPlay != GameStatus.IN_PROGRESS) {
                break;
            }
            directionOfMove = directionOfMove.equals("L") ? "R" : "L";
        }
        return gameInPlay;
    }

}