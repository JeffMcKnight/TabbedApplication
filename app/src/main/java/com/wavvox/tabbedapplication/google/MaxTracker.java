package com.wavvox.tabbedapplication.google;

/**
 * Created by jeffmcknight on 11/6/17.
 */

interface MaxTracker {
    int getMax();

    int getCurrent();

    void correction(int time, int price);

    void update(int time, int price);
}
