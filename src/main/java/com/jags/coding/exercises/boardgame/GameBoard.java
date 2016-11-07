package com.jags.coding.exercises.boardgame;

public abstract class GameBoard {

    protected int height;

    protected int width;

    protected GameStatus gameStatus = GameStatus.NOT_STARTED;

    public GameBoard(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public abstract GameStatus start();

//    public abstract GameStatus makeAMove(GameMove gameMove);

}
