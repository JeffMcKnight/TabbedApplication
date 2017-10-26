package com.wavvox.tabbedapplication;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jeffmcknight on 8/14/17.
 */

public class SmallestRectangleTest {

    private IntegerPoint[] mSimpleTestPoints;

    @Before
    public void setUp(){
        mSimpleTestPoints = RectangleHelper.testPoints(4,true);
    }

    /**
     * Create an instance of {@link RectangleHelper} just to get line coverage
     * @throws Exception
     */
    @Test
    public void constructor() throws Exception{
        RectangleHelper rectangleHelper = new RectangleHelper();
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void area() throws Exception {
        int actual;
        int expected;
        IntegerPoint vertex0;
        IntegerPoint vertex1;

        vertex0 = RectangleHelper.testPoint(0);
        vertex1 = RectangleHelper.testPoint(11);
        actual = RectangleHelper.area(vertex0, vertex1);
        expected = 1;
        assertEquals(expected, actual);

        vertex0 = RectangleHelper.testPoint(0);
        vertex1 = RectangleHelper.testPoint(2);
        actual = RectangleHelper.area(vertex0, vertex1);
        expected = 4;
        assertEquals(expected, actual);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testPoints() throws Exception {
        int count = 4;
        IntegerPoint[] testPoints = RectangleHelper.testPoints(count, true);
        IntegerPoint actual;
        IntegerPoint expected;

        actual = testPoints[0];
        expected = RectangleHelper.testPoint(0);
        assertEquals(expected, actual);

        actual = testPoints[1];
        expected = RectangleHelper.testPoint(1);
        assertEquals(expected, actual);

        actual = testPoints[2];
        expected = RectangleHelper.testPoint(10);
        assertEquals(expected, actual);

        actual = testPoints[3];
        expected = RectangleHelper.testPoint(11);
        assertEquals(expected, actual);

        // Check default case of switch statement
        actual =  new IntegerPoint(0, 0);
        expected = RectangleHelper.testPoint(99);
        assertEquals(expected, actual);

        assertEquals(count, testPoints.length);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void smallestRect() throws Exception {
        int actual;
        int expected;

        expected = 1;
        actual = RectangleHelper.smallestRectangle(RectangleHelper.testPoints(50, true));
        assertEquals(expected, actual);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void smallestRectFast() throws Exception {
        int actual;
        int expected;

        expected = 1;
        actual = RectangleHelper.smallestRectangleFast(RectangleHelper.testPoints(10000, true));
        assertEquals(expected, actual);
    }


    /**
     *
     * @throws Exception
     */
    @Test
    public void isRectangle() throws Exception {
        assertTrue(RectangleHelper.isRectangle(
                mSimpleTestPoints[0],
                mSimpleTestPoints[1],
                mSimpleTestPoints[2],
                mSimpleTestPoints[3])
        );
        assertFalse(RectangleHelper.isRectangle(
                RectangleHelper.testPoint(0),
                RectangleHelper.testPoint(1),
                RectangleHelper.testPoint(2),
                RectangleHelper.testPoint(3))
        );
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void oppositeCorner() throws Exception {
        IntegerPoint actual;
        IntegerPoint expected;

        actual = RectangleHelper.oppositeCorner(mSimpleTestPoints);
        expected = mSimpleTestPoints[3];
        assertThat(actual, is(expected));

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void isAdjacent() throws Exception {
        boolean actual;
        mSimpleTestPoints = RectangleHelper.testPoints(4, true);

        actual = RectangleHelper.isAdjacent(mSimpleTestPoints[0], mSimpleTestPoints[1]);
        assertTrue(actual);

        actual = RectangleHelper.isAdjacent(mSimpleTestPoints[0], mSimpleTestPoints[2]);
        assertTrue(actual);

        actual = RectangleHelper.isAdjacent(mSimpleTestPoints[0], mSimpleTestPoints[3]);
        assertFalse(actual);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void findXAdjacent() throws Exception {
        IntegerPoint actual = RectangleHelper.findXAdjacent(mSimpleTestPoints);
        IntegerPoint expected = RectangleHelper.testPoint(1);
        assertThat(actual, is(expected));
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void findYAdjacent() throws Exception {
        IntegerPoint actual = RectangleHelper.findYAdjacent(mSimpleTestPoints);
        IntegerPoint expected = RectangleHelper.testPoint(10);
        assertThat(actual, is(expected));
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void populateMap() throws Exception {
        Map<Integer, List<Integer>> xAdjacentMap = new HashMap<>();
        Map<Integer, List<Integer>> yAdjacentMap = new HashMap<>();
        for (int i = 0; i < mSimpleTestPoints.length; i++) {
            RectangleHelper.populateMap(xAdjacentMap, mSimpleTestPoints[i].getX(), mSimpleTestPoints[i].getY());
            RectangleHelper.populateMap(yAdjacentMap, mSimpleTestPoints[i].getY(), mSimpleTestPoints[i].getX());
        }
        List<Integer> xListExpected = new ArrayList<>();
        xListExpected.add(0);
        xListExpected.add(1);

        List<Integer> yListExpected = new ArrayList<>();
        yListExpected.add(0);
        yListExpected.add(1);

        assertThat(xAdjacentMap.get(0), is(yListExpected));
        assertThat(yAdjacentMap.get(0), is(xListExpected));
    }

}
