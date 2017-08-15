package com.wavvox.tabbedapplication;

/**
 * Created by jeffmcknight on 8/14/17.
 */

class IntegerPoint {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerPoint point = (IntegerPoint) o;

        if (x != point.x) return false;
        return y == point.y;

    }

    public IntegerPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int x;
    private int y;

}
