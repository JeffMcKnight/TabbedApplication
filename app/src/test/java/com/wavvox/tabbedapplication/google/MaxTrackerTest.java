package com.wavvox.tabbedapplication.google;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jeffmcknight on 11/6/17.
 */
public class MaxTrackerTest {
    public static final int PRICE_5 = 800;
    public static final int PRICE_7 = 900;
    public static final int PRICE_9 = 700;
    public static final int PRICE_NEW_MAX = 1000;
    public static final int PRICE_NOT_MAX = 700;
    private MaxTracker mStockTicker;
    private MaxTracker mNaiveStockTicker;

    @Before
    public void setUp() throws Exception {
        mStockTicker = new StockTicker();
        setUp(mStockTicker);

        mNaiveStockTicker = new NaiveStockTicker();
        setUp(mNaiveStockTicker);
    }

    private void setUp(MaxTracker maxTracker) {
        maxTracker.update(5, PRICE_5);
        maxTracker.update(7, PRICE_7);
        maxTracker.update(9, PRICE_9);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getMax() throws Exception {
        getMax(mStockTicker);
        getMax(mNaiveStockTicker);
    }

    private void getMax(MaxTracker maxTracker) {
        int actual;
        int expected = PRICE_7;

        actual = maxTracker.getMax();
        Assert.assertEquals(expected, actual);

        maxTracker.correction(5, PRICE_NEW_MAX);
        actual = maxTracker.getMax();
        expected = PRICE_NEW_MAX;
        Assert.assertEquals(expected, actual);

        maxTracker.correction(5, PRICE_NOT_MAX);
        actual = maxTracker.getMax();
        expected = PRICE_7;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCurrent() throws Exception {
        getCurrent(mStockTicker);
        getCurrent(mNaiveStockTicker);
    }

    private void getCurrent(MaxTracker maxTracker) {
        int actual;
        actual = maxTracker.getCurrent();
        Assert.assertEquals(PRICE_9, actual);

        maxTracker.update(11, 700);
        actual = maxTracker.getCurrent();
        Assert.assertEquals(700, actual);

        maxTracker.update(13, 913);
        actual = maxTracker.getCurrent();
        Assert.assertEquals(913, actual);

        maxTracker.update(12, 912);
        actual = maxTracker.getCurrent();
        Assert.assertEquals(913, actual);
    }

    @Test
    public void correction() throws Exception {

    }

    @Test
    public void update() throws Exception {
    }

}