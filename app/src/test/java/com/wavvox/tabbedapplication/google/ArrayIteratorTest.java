package com.wavvox.tabbedapplication.google;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jeffmcknight on 11/6/17.
 */
public class ArrayIteratorTest {
    private static final int[] TEST_ARRAY = new int[]{3, 8, 0, 12, 2, 5};
    private static final int[] TRAILING_ZERO_ARRAY = new int[]{3, 8, 0, 12, 2, 5, 0, 42};
    private static final int[] DANGLING_PAIR_ARRAY = new int[]{3, 8, 0, 12, 2, 5, 42};
    private static int[] EXPECTED_NEXT = new int[]{8, 8, 8, 5, 5};

    /**
     * An instance of {@link ArrayIterator} used to check a well-formed array
     */
    private ArrayIterator mArrayIterator;

    /**
     * An instance of {@link ArrayIterator} used to check an array whose final pair has a zero-count
     */
    private ArrayIterator mTrailingZeroIterator;

    /**
     * An instance of {@link ArrayIterator} used to check an array whose element is an incomplete pair
     */
    private ArrayIterator mDanglingPairIterator;

    @Before
    public void setUp() throws Exception {
        mArrayIterator = new ArrayIterator(TEST_ARRAY);
        mTrailingZeroIterator = new ArrayIterator(TRAILING_ZERO_ARRAY);
        mDanglingPairIterator = new ArrayIterator(DANGLING_PAIR_ARRAY);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void hasNext() throws Exception {
        Assert.assertTrue(mArrayIterator.hasNext());
        Assert.assertTrue(mTrailingZeroIterator.hasNext());
        Assert.assertTrue(mDanglingPairIterator.hasNext());

        for (int i = 0; i< EXPECTED_NEXT.length; i++){
            mArrayIterator.next();
            mTrailingZeroIterator.next();
            mDanglingPairIterator.next();
        }

        Assert.assertFalse(mArrayIterator.hasNext());
        Assert.assertFalse(mTrailingZeroIterator.hasNext());
        Assert.assertFalse(mDanglingPairIterator.hasNext());
    }

    @Test
    public void next() throws Exception {
        int actual;
        for (int i = 0; i< EXPECTED_NEXT.length; i++){
            actual = mArrayIterator.next();
            Assert.assertEquals(EXPECTED_NEXT[i], actual);

            actual = mTrailingZeroIterator.next();
            Assert.assertEquals(EXPECTED_NEXT[i], actual);

            actual = mDanglingPairIterator.next();
            Assert.assertEquals(EXPECTED_NEXT[i], actual);
        }

    }

    @Test
    public void size() throws Exception {
        int expected = EXPECTED_NEXT.length;
        int actual;

        actual = mArrayIterator.size();
        Assert.assertEquals(expected, actual);

        actual = mTrailingZeroIterator.size();
        Assert.assertEquals(expected, actual);

        actual = mDanglingPairIterator.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getRemainingCount() {
        int expected;
        int actual;
        for (int i = 0; i < mArrayIterator.size(); i++) {
            expected = mArrayIterator.size() - i;

            actual = mArrayIterator.getSizeRemaining();
            Assert.assertEquals(expected, actual);
            mArrayIterator.next();

            actual = mTrailingZeroIterator.getSizeRemaining();
            Assert.assertEquals(expected, actual);
            mTrailingZeroIterator.next();

            actual = mDanglingPairIterator.getSizeRemaining();
            Assert.assertEquals(expected, actual);
            mDanglingPairIterator.next();
        }

        expected = 0;

        actual = mArrayIterator.getSizeRemaining();
        Assert.assertEquals(expected, actual);

        actual = mTrailingZeroIterator.getSizeRemaining();
        Assert.assertEquals(expected, actual);

        actual = mDanglingPairIterator.getSizeRemaining();
        Assert.assertEquals(expected, actual);
    }
}