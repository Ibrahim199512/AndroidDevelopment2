package com.ucas.android2.EventBus;

public class MyEvent {

    private int number;
    private int type;

    public int getNumber() {
        return number;
    }

    public MyEvent(int number, int type) {
        this.number = number;
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
