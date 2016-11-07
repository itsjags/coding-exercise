package com.jags.coding.exercises.boardgame;

/**
 * Created by Jags on 02/11/2016.
 */
public class Cell<T> {

    private Position position;

    private T value;
    private boolean mine = false;

    public Cell() {
    }

    public Cell(Position position) {
        this.position = position;
    }

    public Cell(Position position, T value) {
        this.position = position;
        this.value = value;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isMine() {
        return mine;
    }
}
