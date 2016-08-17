package org.mom.maze;

public class MazeUtils {
    public static int calculateHash(int x, int y) {
        return (x * 1000) + y;
    }

    public static boolean between(int coord, int limit) {
        return (coord >= 0) && (coord < limit);
    }
}
