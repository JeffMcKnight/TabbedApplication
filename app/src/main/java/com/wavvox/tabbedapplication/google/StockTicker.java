package com.wavvox.tabbedapplication.google;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by jeffmcknight on 11/6/17.
 */

public class StockTicker implements MaxTracker {

    private SortedSet<Integer> mMaxSet = new TreeSet<>();
    /**
     * A map to keep track of the number of each price for which we have gotten updates
     */
    private Map<Integer, Integer> mPriceCountMap = new HashMap<>();
    private Map<Integer, Integer> mPriceMap = new HashMap<>();
    private int mCurrent;
    private int mCurrentTime = 0;

    @Override
    public int getMax(){
        return mMaxSet.last();
    }

    @Override
    public int getCurrent(){
        return mCurrent;
    }

    /**
     * Update all the data structures.
     *  - Update the price in {@link #mPriceMap}
     *  - Update {@link #mPriceCountMap}, incrementing/adding the key with the new price, decrementing/removing the key with the old price
     *  - Remove the price from {@link #mMaxSet} if {@link #mPriceCountMap} no longer contains that price
     *
     * If the corrected value is higher than the current max, add
     * @param time
     * @param price
     */
    @Override
    public void correction(int time, int price){
        // Update the price value in the price map
        Integer oldPrice = mPriceMap.put(time, price);


        // Update old price count (or remove if there was only 1)
        Integer oldPriceCount = mPriceCountMap.get(oldPrice);
        if (oldPriceCount > 1){
            mPriceCountMap.put(oldPrice, oldPriceCount - 1);
        } else {
            mPriceCountMap.remove(oldPrice);
            mMaxSet.remove(oldPrice);
        }

        // Update the new price count (or add it if there was none)
        if (mPriceCountMap.containsKey(price)){
            mPriceCountMap.put(price, mPriceCountMap.get(price) + 1);
        } else {
            mPriceCountMap.put(price, 1);
            mMaxSet.add(price);
        }


    }

    /**
     *
     * @param time
     * @param price
     */
    @Override
    public void update(int time, int price){
        if (time > mCurrentTime){
            mCurrentTime = time;
            mCurrent = price;
        }
        mPriceMap.put(time, price);

        if (mPriceCountMap.containsKey(price)){
            mPriceCountMap.put(price, mPriceCountMap.get(price) + 1);
        } else {
            mPriceCountMap.put(price, 1);
            mMaxSet.add(price);
        }
    }
}
