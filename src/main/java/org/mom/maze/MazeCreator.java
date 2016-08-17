package org.mom.maze;

import java.util.*;
import java.util.stream.IntStream;

import static org.mom.maze.MazeUtils.between;
import static org.mom.maze.MazeUtils.calculateHash;

public class MazeCreator {

    private static final String EMPTY_ROOM = "[               ]";
    private final Map<Integer, Room> maze;
    private final int width;
    private final int height;
    private final int totalRooms;
    private final Random random = new Random();
    private int visitedRooms;


    public MazeCreator(int width, int height) {
        this.width = width;
        this.height = height;
        maze = new HashMap<>();
        visitedRooms = 0;
        totalRooms = width * height;
    }

    public Map<Integer, Room> getMaze() {
        return new HashMap<>(maze);
    }

    public void create() {
        Room room = createFirstRoom();

        while (room != null) {
            room = walk(room);
        }

        hunt();

        boolean foundLastRoom = false;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int key = calculateHash(x, y);
                Room lastRoom = maze.get(key);

                if (lastRoom.getExits().size() == 1 && lastRoom.getType().equals(RoomType.NORMAL)) {
                    lastRoom.setType(RoomType.END);
                    maze.put(key, lastRoom);
                    foundLastRoom = true;
                    break;
                }
            }
            if (foundLastRoom) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        IntStream.range(0, height).forEach(y -> {
            IntStream.range(0, width).forEach(x -> {
                Room room = maze.get(calculateHash(x, y));

                builder.append((Objects.nonNull(room)) ? room.toString() : EMPTY_ROOM);
            });
            builder.append('\n');
        });

        return builder.toString();
    }

    private Room createFirstRoom() {
        Room room = newRoom(random.nextInt(width), random.nextInt(height));

        room.setType(RoomType.BEGIN);

        maze.put(room.hashCode(), room);

        visitedRooms++;

        return room;
    }

    private Room walk(Room room) {
        Room nextRoom = null;

        List<Exit> exits = getAvailableExits(room.getX(), room.getY(), room.getExits());

        if (!exits.isEmpty()) {
            Exit exit = getRandomExit(exits);

            room.addExit(exit);

            int nx = room.getX() + exit.getDx();
            int ny = room.getY() + exit.getDy();

            nextRoom = newRoom(nx, ny);

            nextRoom.setType(RoomType.NORMAL);

            nextRoom.addExit(exit.getOppositeExit());

            maze.put(nextRoom.hashCode(), nextRoom);
        }

        return nextRoom;
    }

    private void hunt() {
        while (visitedRooms <= totalRooms) {
            IntStream.range(0, width).forEach(x -> {
                IntStream.range(0, height).forEach(y -> {
                    int key = calculateHash(x, y);

                    if (Objects.isNull(maze.get(key))) {
                        Map<Exit, Room> possibleExits = lookupVisitedNeighbours(x, y);

                        if (!possibleExits.isEmpty()) {
                            int size = possibleExits.keySet().size();
                            Exit exits[] = possibleExits.keySet().toArray(new Exit[size]);

                            Exit choosenExit = exits[random.nextInt(size)];

                            Room room = newRoom(x, y);
                            room.setType(RoomType.NORMAL);

                            int otherKey = calculateHash(x + choosenExit.getDx(), y + choosenExit.getDy());
                            Room otherRoom = maze.get(otherKey);
                            otherRoom.addExit(choosenExit.getOppositeExit());

                            maze.put(calculateHash(x, y), room);

                            room.addExit(choosenExit);

                            while (room != null) {
                                room = walk(room);
                            }
                        }
                    }
                });
            });
        }
    }

    private Room newRoom(int x, int y) {
        Room room = new Room();

        room.setX(x);
        room.setY(y);

        visitedRooms++;

        return room;
    }

    private List<Exit> getAvailableExits(int x, int y, List<Exit> openedExits) {
        List<Exit> availableExits = new ArrayList<>();

        if (Objects.isNull(openedExits)) {
            openedExits = new ArrayList<>();
        }

        Exit
                .getRemainingExits(openedExits)
                .forEach(e -> {
                    int nx = x + e.getDx();
                    int ny = y + e.getDy();

                    int key = calculateHash(nx, ny);

                    if (between(nx, width) && between(ny, height)) {
                        if (Objects.isNull(maze.get(key))) {
                            availableExits.add(e);
                        }
                    }
                });

        return availableExits;
    }

    private Map<Exit, Room> lookupVisitedNeighbours(int x, int y) {
        Map<Exit, Room> visitedNeighbours = new HashMap<>();

        Exit.ALL_EXITS.forEach(
                e -> {
                    int nx = x + e.getDx();
                    int ny = y + e.getDy();

                    int key = calculateHash(nx, ny);

                    if (between(nx, width) && between(ny, height)) {
                        if (Objects.nonNull(maze.get(key))) {
                            visitedNeighbours.put(e, maze.get(key));
                        }
                    }
                }
        );

        return visitedNeighbours;
    }

    private Exit getRandomExit(List<Exit> availableExits) {
        return availableExits.get(random.nextInt(availableExits.size()));
    }
}
