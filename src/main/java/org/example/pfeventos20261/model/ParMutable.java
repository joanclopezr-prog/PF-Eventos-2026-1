package org.example.pfeventos20261.model;

public class ParMutable {
    private int x;
    private int y;

    public ParMutable(int key, int value) {
        this.x = key;
        this.y = value;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
