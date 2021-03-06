package org.mom.maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Exit {
    NORTH(0, 2, 0, -1),
    EAST(1, 3, 1, 0),
    SOUTH(2, 0, 0, 1),
    WEST(3, 1, -1, 0);

    public static final List<Exit> ALL_EXITS = Arrays.asList(NORTH, EAST, SOUTH, WEST);
    private final int id;
    private final int opposite;
    private final int dx;
    private final int dy;

    Exit(int id, int opposite, int dx, int dy) {
        this.id = id;
        this.opposite = opposite;
        this.dx = dx;
        this.dy = dy;
    }

    public static List<Exit> getRemainingExits(List<Exit> usedExits) {
        List<Exit> remainingExits = new ArrayList<>(ALL_EXITS);

        remainingExits.removeAll(usedExits);

        return remainingExits;
    }

    public int getId() {
        return id;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Exit getOppositeExit() {
        return Arrays
                .stream(values())
                .filter(e -> e.getId() == opposite)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(name()));
    }
}
