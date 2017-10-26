package com.wavvox.tabbedapplication.uber;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by jeffmcknight on 10/26/17.
 */

public class MazeSolver {

    int[][] maze;

    public MazeSolver(int[][] maze) {
        this.maze = maze;
    }

    public int findShortestPath(){
        Node current = new Node(0, 0, null, 0);
        Node nextNode;
        Queue<Node> queue = new LinkedBlockingQueue<>();
        queue.add(current);
        while (!queue.isEmpty() && !current.isExit(maze)){
            current = queue.poll();
            // Create node one space up from the current node
            nextNode = new Node(current.row - 1, current.col, current, current.distance + 1);
            if (canGoToNext(current, nextNode)){
                queue.add(nextNode);
            }
            // Create node one space down from the current node
            nextNode = new Node(current.row + 1, current.col, current, current.distance + 1);
            if (canGoToNext(current, nextNode)){
                queue.add(nextNode);
            }
            // Create node one space left of the current node
            nextNode = new Node(current.row , current.col - 1, current, current.distance + 1);
            if (canGoToNext(current, nextNode)){
                queue.add(nextNode);
            }
            // Create node one space right of the current node
            nextNode = new Node(current.row , current.col + 1, current, current.distance + 1);
            if (canGoToNext(current, nextNode)){
                queue.add(nextNode);
            }
        }
        return current.distance;
    }

    private boolean canGoToNext(Node node, Node nextNode) {
        if (!isValidNode(nextNode)){
            return false;
        }
        if (isAlreadyVisited(node, nextNode)){
            return false;
        }
        if (isPathBlocked(nextNode)){
            return false;
        }
        return true;
    }

    public boolean isPathBlocked(Node node) {
        return maze[node.row][node.col] == 1;
    }

    public boolean isAlreadyVisited(Node node, Node nextNode) {
        return (node.lastNode != null) && (node.lastNode.row == nextNode.row) && (node.lastNode.col == nextNode.col);
    }

    public boolean isValidNode(Node node) {
        return (node.row > -1) && (node.row < maze.length) && (node.col > -1) && (node.col < maze[0].length);
    }

    public static class Node {
        private int row;
        private int col;
        public Node lastNode;
        public int distance;

        public Node(int r, int c, Node last, int dist) {
            row = r;
            col = c;
            lastNode = last;
            distance = dist;
        }

        public boolean isExit(int[][] maze) {
            return (row == maze.length - 1) && (col == maze[0].length - 1);
        }
    }
}
