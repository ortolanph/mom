package org.mom.maze;

public class MazeUtils {
    public static int calculateHash(int x, int y) {
        return (x * 1000) + y;
    }
}
