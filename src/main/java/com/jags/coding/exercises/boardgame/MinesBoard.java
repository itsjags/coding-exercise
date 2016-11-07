package com.jags.coding.exercises.boardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Jags on 02/11/2016.
 */
public class MinesBoard extends GameBoard {

    private static final String INITIAL_VALUE = "0";
    private static final String FLAGGED_VALUE = "1";
    private static final String CURRENT_CELL_INDICATOR = "*";
    private static final String MINES_VALUE = "X";

    private static final Random random = new Random();

/*
    private int height;

    private int width;
*/

    private int allowedMines = 2;

    private Cell<String>[][] boardCells;

    private int minesHit;

    private Position currentPosition;

//    private GameStatus gameStatus = GameStatus.NOT_STARTED;


    /*
     * Scope of this constructor is limited as a utility method to use during testing.
     */
    public MinesBoard(int height, int width, int allowedMines) {
        this(height, width);
        this.allowedMines = allowedMines;
    }

    public MinesBoard(int height, int width) {
//        this.height = height;
//        this.width = width;
        super(height, width);
        initBoard();
        initRandomMines();
        initStartingPosition();
    }

    private void initStartingPosition() {
        currentPosition = new Position(height-1, 0);
        if(boardCells[currentPosition.getX()][currentPosition.getY()].isMine()) {
            boardCells[currentPosition.getX()][currentPosition.getY()].setValue(MINES_VALUE + CURRENT_CELL_INDICATOR);
            allowedMines++;
        } else {
            boardCells[currentPosition.getX()][currentPosition.getY()].setValue(FLAGGED_VALUE + CURRENT_CELL_INDICATOR);
        }
    }

    private void initRandomMines() {
        final List<Integer> randomCellsForMines = getRandomCellLocations();
        for (Integer random : randomCellsForMines) {
            int x = random.intValue() / height;
            int y = random.intValue() % width;
            boardCells[x][y].setMine(true);
            System.out.println("Random : " + random + " : [" + x + "][" + y + "]");
        }
    }

    private List<Integer> getRandomCellLocations() {
        final int totalMines = random.ints(0, (height*width)/2).findAny().getAsInt();
        System.out.println("Total MInes : " + totalMines);
        ArrayList<Integer> list = new ArrayList<>();
        for ( int i = 0 ; i < height * width ; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        return list.subList(0, totalMines);
    }

    private void initBoard() {

        boardCells = new Cell[height][width];
        int rowIndex = 0;
        for (Cell[] cell : boardCells) {
            populateRow(cell, rowIndex++);
        }
        currentPosition = new Position(height -1, 0);
        boardCells[currentPosition.getX()][currentPosition.getY()].setValue(FLAGGED_VALUE + CURRENT_CELL_INDICATOR);
    }

    private void populateRow(Cell[] cell, int rowIndex) {

        for (int i = 0 ; i < cell.length; i++) {
            cell[i] = new Cell(new Position(rowIndex, i), INITIAL_VALUE);
        }
    }

    @Override
    public GameStatus start() {
        gameStatus = GameStatus.IN_PROGRESS;
        initStartingPosition();
        display();
        return validateWinOrLose();
    }

    private void display() {
        for (Cell[] cell : boardCells) {
            printRow(cell);
            System.out.println();
        }
        System.out.println();
    }

    private void printRow(Cell[] cells) {
        for (Cell cell: cells) {
            System.out.print(cell.getValue());
            System.out.print( "\t");
        }
    }

    public GameStatus move(String next) {
        resetCurrentCellIndicator();
        switch (next) {
            case "U":
                if (validUpMove()) {
                    currentPosition.setX(currentPosition.getX() - 1);
                    break;
                }
                System.out.println("");
                break;
            case "D":
                if (validDownMove()) {
                    currentPosition.setX(currentPosition.getX() + 1);
                }
                break;
            case "L":
                if (validLeftMove()) {
                    currentPosition.setY(currentPosition.getY() - 1);
                }
                break;
            case "R":
                if (validRightMove()) {
                    currentPosition.setY(currentPosition.getY() + 1);
                }
                break;
            default:
                System.out.println("Invalid Key pressed");
                break;
        }

        if (boardCells[currentPosition.getX()][currentPosition.getY()].isMine()) {
            minesHit +=1;
            boardCells[currentPosition.getX()][currentPosition.getY()].setValue(MINES_VALUE + CURRENT_CELL_INDICATOR);
        } else {
            boardCells[currentPosition.getX()][currentPosition.getY()].setValue(FLAGGED_VALUE + CURRENT_CELL_INDICATOR);
        }
        display();
        return validateWinOrLose();
    }

    private GameStatus validateWinOrLose() {
        if (currentPosition.getX() != 0 && minesHit > allowedMines) {
            System.out.println("You lost by crossing maximum allowed mines");
            gameStatus = GameStatus.LOST;
        } else if (currentPosition.getX() == 0 && minesHit <= allowedMines) {
            System.out.println("Reached top row. Game Won");
            gameStatus = GameStatus.WON;
        }
        return gameStatus;
    }

    private boolean validLeftMove() {
        return currentPosition.getY() > 0;
    }

    private boolean validRightMove() {
        return currentPosition.getY() < width - 1;
    }

    private boolean validDownMove() {
        return currentPosition.getX() < height - 1;
    }

    private boolean validUpMove() { return currentPosition.getX() > 0; }

    private void resetCurrentCellIndicator() {
        boardCells[currentPosition.getX()][currentPosition.getY()].setValue(
                boardCells[currentPosition.getX()][currentPosition.getY()].isMine() ? MINES_VALUE : FLAGGED_VALUE);
    }

}
