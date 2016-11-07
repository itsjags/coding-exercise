package com.jags.coding.exercises.boardgame;

/**
 * Created by Jags on 05/11/2016.
 */
public class StringValuedMove implements GameMove<String> {

    private final String value;

    public StringValuedMove(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

//    @Override
//    public void setValue(String value) {
//        this.value = value;
//    }
}
