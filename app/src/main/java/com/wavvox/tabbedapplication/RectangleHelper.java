package com.wavvox.tabbedapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by jeffmcknight on 8/14/17.
 */

class RectangleHelper {

    /**
     *
     * @param pointArray
     * @return
     */
    public static int smallestRectangle(IntegerPoint[] pointArray) {
        int smallestRect = Integer.MAX_VALUE;
        int area;
        List<Integer> rectangleList = new Stack<>();
        for (int i = 0; i < pointArray.length - 3; i++) {
            for (int j = 1; j < pointArray.length - 2; j++) {
                for (int k = 2; k < pointArray.length - 1; k++) {
                    for (int l = 3; l < pointArray.length; l++) {
                        if (isRectangle(pointArray[i], pointArray[j], pointArray[k], pointArray[l])) {
                            area = RectangleHelper.area(pointArray[i], oppositeCorner(
                                    pointArray[i],
                                    pointArray[j],
                                    pointArray[k],
                                    pointArray[l])
                            );
                            rectangleList.add(area);
                            if (area < smallestRect) {
                                smallestRect = area;
                            }
                        }
                    }
                }
            }
        }
//        for (Integer eachArea : rectangleList){
//            System.out.println("smallestRectangle() -- eachArea: " + eachArea);
//        }
        return smallestRect;
    }

    /**
     * Create two {@link Map}s that contain sets of points that share either the same x or same y
     * coordinate (one map for each).  Then for each point in {@code pointArray}, use the {@link Map}s
     * to obtain a list of possible adjacent vertices of a possible rectangle, and then cross check those adjacent
     * vertices to see if any pair has a corresponding opposite vertex.  If there <b>is</b> an opposite
     * vertex, then we know we have found a rectangle, so we then use that opposite vertex along with
     * the original point/vertex to calculate the area of the rectangle.
     * @param pointArray
     * @return
     */
    public static int smallestRectangleFast(IntegerPoint[] pointArray) {
        Map<Integer, List<Integer>> xAdjacentMap = new HashMap<>();
        Map<Integer, List<Integer>> yAdjacentMap = new HashMap<>();
        for (int i = 0; i < pointArray.length; i++) {
            populateMap(xAdjacentMap, pointArray[i].getX(), pointArray[i].getY());
            populateMap(yAdjacentMap, pointArray[i].getY(), pointArray[i].getX());
        }
        int smallestArea = Integer.MAX_VALUE;
        for (int i = 0; i < pointArray.length; i++) {
            List<Integer> yList = xAdjacentMap.get(pointArray[i].getX());
            for (Integer eachY : yList) {
                List<Integer> xList = yAdjacentMap.get(pointArray[i].getY());
                for (Integer eachX : xList) {
                    // Check for opposite vertex
                    if (yAdjacentMap.get(eachY).contains(eachX) && xAdjacentMap.get(eachX).contains(eachY)){
                        int area = area(pointArray[i], new IntegerPoint(eachX, eachY));
                        if (area < smallestArea && area > 0) {
                            smallestArea = area;
                        }
                    }
                }

            }
        }
        return smallestArea;
    }

    /**
     *
     * @param adjacentMap
     * @param adjacentSide
     * @param oppositeSide
     */
    public static void populateMap(Map<Integer, List<Integer>> adjacentMap,
                                   int adjacentSide,
                                   int oppositeSide) {
        if (adjacentMap.containsKey(adjacentSide)) {
            // Add the opposite side coord to the oppositeSideList
            adjacentMap.get(adjacentSide).add(oppositeSide);
        } else {
            // Create a new list of opposite side coords
            ArrayList<Integer> oppositeSideList = new ArrayList<>();
            oppositeSideList.add(oppositeSide);
            adjacentMap.put(adjacentSide, oppositeSideList);
        }
    }

    /**
     *
     * @param points
     * @return
     */
    public static IntegerPoint oppositeCorner(IntegerPoint... points) {
        IntegerPoint oppositeCorner = null;
        for (int i = 1; i < points.length; i++) {
            if (!isAdjacent(points[0], points[i])) {
                return points[i];
            }
        }
        return oppositeCorner;
    }

    /**
     *
     * @param origin
     * @param point
     * @return
     */
    public static boolean isAdjacent(IntegerPoint origin, IntegerPoint point) {
        return isXAdjacent(origin, point) || isYAdjacent(origin, point);
    }

    /**
     *
     * @param origin
     * @param point
     * @return
     */
    private static boolean isXAdjacent(IntegerPoint origin, IntegerPoint point) {
        return origin.getX() == point.getX();
    }

    /**
     *
     * @param origin
     * @param point
     * @return
     */
    private static boolean isYAdjacent(IntegerPoint origin, IntegerPoint point) {
        return origin.getY() == point.getY();
    }

    /**
     *
     * @param point0
     * @param point1
     * @param point2
     * @param point3
     * @return
     */
    public static boolean isRectangle(IntegerPoint point0,
                                      IntegerPoint point1,
                                      IntegerPoint point2,
                                      IntegerPoint point3) {
        IntegerPoint xAdjacent = findXAdjacent(point0, point1, point2, point3);
        IntegerPoint yAdjacent = findYAdjacent(point0, point1, point2, point3);
        IntegerPoint opposite = oppositeCorner(point0, point1, point2, point3);
        return (xAdjacent != null && yAdjacent != null && opposite != null);
    }

    /**
     *
     * @param points
     * @return
     */
    public static IntegerPoint findXAdjacent(IntegerPoint... points) {
        for (int i = 1; i < points.length; i++) {
            if (isXAdjacent(points[0], points[i])) {
                return points[i];
            }
        }
        return null;
    }

    /**
     *
     * @param points
     * @return
     */
    public static IntegerPoint findYAdjacent(IntegerPoint... points) {
        for (int i = 1; i < points.length; i++) {
            if (isYAdjacent(points[0], points[i])) {
                return points[i];
            }
        }
        return null;
    }

    /**
     *
     * @param vertex0
     * @param vertex1
     * @return
     */
    public static int area(IntegerPoint vertex0, IntegerPoint vertex1) {
        return Math.abs((vertex0.getX() - vertex1.getX()) * (vertex0.getY() - vertex1.getY()));

    }

    /**
     *
     * @param count
     * @param insertTestPoints
     * @return
     */
    public static IntegerPoint[] testPoints(int count, boolean insertTestPoints) {
        IntegerPoint[] points = new IntegerPoint[count];
        for (int i = 0; i < count; i++) {
            points[i] = new IntegerPoint(
                    ((int) (Integer.MAX_VALUE * Math.random())),
                    (int) (Integer.MAX_VALUE * Math.random())
            );
        }
        if (insertTestPoints) {
            insertTestPoints(points);
        }
        return points;
    }

    /**
     *
     * @param points
     */
    private static void insertTestPoints(IntegerPoint[] points) {
        int quarterLength = points.length / 4;
        int index00 = (int) (quarterLength * Math.random());
        points[index00] = testPoint(0);
        int index01 = (int) (quarterLength * (Math.random() + 1));
        points[index01] = testPoint(1);
        int index10 = (int) (quarterLength * (Math.random() + 2));
        points[index10] = testPoint(10);
        int index11 = (int) (quarterLength * (Math.random() + 3));
        points[index11] = testPoint(11);
    }

    /**
     *
     * @param i
     * @return
     */
    public static IntegerPoint testPoint(int i) {
        IntegerPoint point;
        switch (i) {
            case 0:
                point = new IntegerPoint(0, 0);
                break;
            case 1:
                point = new IntegerPoint(0, 1);
                break;
            case 2:
                point = new IntegerPoint(2, 2);
                break;
            case 3:
                point = new IntegerPoint(3, 3);
                break;
            case 10:
                point = new IntegerPoint(1, 0);
                break;
            case 11:
                point = new IntegerPoint(1, 1);
                break;
            default:
                point = new IntegerPoint(0, 0);
                break;
        }
        return point;
    }
}
