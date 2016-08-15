package org.mom.maze;

import java.util.ArrayList;
import java.util.List;

import static org.mom.maze.MazeUtils.calculateHash;

public class Room {
    private final List<Exit> exits;
    private int x;
    private int y;
    private RoomType type;

    public Room() {
        exits = new ArrayList<Exit>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addExit(Exit exit) {
        exits.add(exit);
    }

    public List<Exit> getExits() {
        return exits;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return calculateHash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[").append(String.format("%06d", hashCode())).append("]")
                .append("[").append(type.getCode()).append("][")
                .append((exits.contains(Exit.NORTH)) ? "N" : " ")
                .append((exits.contains(Exit.EAST)) ? "E" : " ")
                .append((exits.contains(Exit.SOUTH)) ? "S" : " ")
                .append((exits.contains(Exit.WEST)) ? "W" : " ")
                .append("]");

        return builder.toString();
    }

}
