package com.wavvox.tabbedapplication.uber;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jeffmcknight on 10/26/17.
 */
public class MazeSolverTest {
    private static final int[][] maze = new int[][]
            {
                    {0, 0, 0},
                    {1, 1, 0}
            };
    private static final int[][] medMaze = new int[][]
            {
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 1, 1, 0, 1, 0, 1, 0},
                    {0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}
            };

    private static final int[][] longerMaze = new int[][]
            {
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 1, 1, 0, 1, 0, 1, 0},
                    {0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0},
                    {0, 0, 0, 0, 0, 0, 1, 0}
            };

    private MazeSolver solver;
    private MazeSolver medSolver;
    private MazeSolver longerSolver;

    @Before
    public void setUp() throws Exception {
        solver = new MazeSolver(maze);
        medSolver = new MazeSolver(medMaze);
        longerSolver = new MazeSolver(longerMaze);
//        System.out.println("maze rows: " + solver.maze.length);
//        System.out.println("maze columns: " + solver.maze[0].length);

    }

    @Test
    public void findShortestPath() throws Exception {
        int expected;
        int actual;
        actual = solver.findShortestPath();
        expected = 3;
        Assert.assertEquals(expected, actual);

        actual = medSolver.findShortestPath();
        expected = 11;
        Assert.assertEquals(expected, actual);

        actual = longerSolver.findShortestPath();
        expected = 15;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isPathBlocked() throws Exception {
        MazeSolver.Node node;
        node = new MazeSolver.Node(0, 0, null, 0);
        assertFalse(solver.isPathBlocked(node));

        node = new MazeSolver.Node(1, 0, null, 0);
        assertTrue(solver.isPathBlocked(node));

    }

    @Test
    public void isAlreadyVisited() throws Exception {
        MazeSolver.Node previous = new MazeSolver.Node(0, 0, null, 0);
        MazeSolver.Node current = new MazeSolver.Node(0, 1, previous, 0);
        MazeSolver.Node next = new MazeSolver.Node(1, 1, current, 0);

        assertTrue(solver.isAlreadyVisited(current, previous));
        assertTrue(solver.isAlreadyVisited(next, current));
        assertFalse(solver.isAlreadyVisited(current, next));
    }

    @Test
    public void isValidNode() throws Exception {
        MazeSolver.Node node;
        node = new MazeSolver.Node(0, 0, null, 0);
        assertTrue(solver.isValidNode(node));

        node = new MazeSolver.Node(0, solver.maze[0].length - 1, null, 0);
        assertTrue(solver.isValidNode(node));

        node = new MazeSolver.Node(solver.maze.length - 1, 0, null, 0);
        assertTrue(solver.isValidNode(node));

        node = new MazeSolver.Node(-1, 0, null, 0);
        assertFalse(solver.isValidNode(node));

        node = new MazeSolver.Node(0, -1, null, 0);
        assertFalse(solver.isValidNode(node));

        node = new MazeSolver.Node(0, solver.maze[0].length, null, 0);
        assertFalse(solver.isValidNode(node));

        node = new MazeSolver.Node(solver.maze.length, 0, null, 0);
        assertFalse(solver.isValidNode(node));


        node = new MazeSolver.Node(0, 0, null, 0);
        assertTrue(medSolver.isValidNode(node));

        node = new MazeSolver.Node(0, medSolver.maze[0].length - 1, null, 0);
        assertTrue(medSolver.isValidNode(node));

        node = new MazeSolver.Node(medSolver.maze.length - 1, 0, null, 0);
        assertTrue(medSolver.isValidNode(node));

        node = new MazeSolver.Node(-1, 0, null, 0);
        assertFalse(medSolver.isValidNode(node));

        node = new MazeSolver.Node(0, -1, null, 0);
        assertFalse(medSolver.isValidNode(node));

        node = new MazeSolver.Node(0, medSolver.maze[0].length, null, 0);
        assertFalse(medSolver.isValidNode(node));

        node = new MazeSolver.Node(medSolver.maze.length, 0, null, 0);
        assertFalse(medSolver.isValidNode(node));

    }

}