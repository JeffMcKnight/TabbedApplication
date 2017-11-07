package com.wavvox.tabbedapplication.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeffmcknight on 11/6/17.
 */

public class NaiveStockTicker implements MaxTracker {
    private Map<Integer, Integer> mUpdateMap = new HashMap<>();
    private int mCurrent = 0;

    @Override
    public int getMax() {
        int max = 0;
        for (Integer eachValue : mUpdateMap.values()){
            if (eachValue > max){
                max = eachValue;
            }
        }
        return max;
    }

    @Override
    public int getCurrent() {
        int maxTime = 0;
        for (Integer eachTime : mUpdateMap.keySet()){
            if (eachTime > maxTime){
                maxTime = eachTime;
            }
        }
        return mUpdateMap.get(maxTime);
    }

    @Override
    public void correction(int time, int price) {
        mUpdateMap.put(time, price);
    }

    @Override
    public void update(int time, int price) {
        mCurrent = price;
        mUpdateMap.put(time, price);
    }
}
