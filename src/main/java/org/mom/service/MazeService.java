package org.mom.service;

import org.mom.maze.MazeCreator;
import org.mom.maze.MazeUtils;
import org.mom.maze.Room;
import org.mom.maze.RoomType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MazeService {

    private Map<Integer, Room> currentMaze;

    public MazeService() {
        currentMaze = createMaze();
    }

    public Room firstRoom() {
        return currentMaze
                .values()
                .stream()
                .filter(
                        r -> r
                                .getType()
                                .equals(RoomType.BEGIN))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Room roomAt(int x, int y) {
        return currentMaze.get(MazeUtils.calculateHash(x, y));
    }

    public void endMaze() {
        currentMaze = createMaze();
    }

    private Map<Integer, Room> createMaze() {
        MazeCreator mazeCreator = new MazeCreator(10, 10);

        mazeCreator.create();
        System.out.println(mazeCreator.toString());
        return  mazeCreator.getMaze();
    }
}
