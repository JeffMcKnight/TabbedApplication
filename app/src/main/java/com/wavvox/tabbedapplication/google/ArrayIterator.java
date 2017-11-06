package com.wavvox.tabbedapplication.google;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by jeffmcknight on 11/6/17.
 */

public class ArrayIterator implements Iterator<Integer> {

    private final int mSize;
    /**
     * The number of remaining un-iterated elements
     */
    private int mSizeRemaining;
    /**
     * The current index of {@link #mArray}
     */
    private int mIndex;
    /**
     * The number of iterations remaining for the current count-value pair
     */
    private int mCountdown;
    /**
     * An array with an even number of elements, for example {3, 8, 0, 12, 2, 5}
     */
    private final int[] mArray;

    public ArrayIterator(int[] array) {
        mArray = array;
        mCountdown = array[0];
        mIndex = 0;

        int count = 0;
        // Iterate by 2 so as to treat the array as a set of count-value pairs. Set iteration limit
        // to mArray.length - 1 so we exclude the last element if there is a dangling pair
        for (int i = 0; i < mArray.length - 1; i += 2) {
            count += mArray[i];
        }
        mSize = count;
        mSizeRemaining = count;
    }

    @Override
    public boolean hasNext() {
        return mSizeRemaining > 0;
    }

    /**
     * Get the next available element.  Proceeds to the next pair of elements from the source array
     * if there are no remaining items in the current pair.  Throws {@link NoSuchElementException}
     * if there are no remaining pairs in the array.
     * @return
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (mCountdown < 1) {
            mIndex += 2;
            mCountdown = mArray[mIndex];
        }
        mSizeRemaining--;
        mCountdown--;
        return mArray[mIndex + 1];
    }

    public int getSizeRemaining() {
        return mSizeRemaining;
    }

    int size() {
        return mSize;
    }


}
