package org.mom.maze;

public class MazeTester {

    public static void main(String[] args) {
        int x = 100;
        int y = 150;

        MazeCreator mazeCreator = new MazeCreator(x, y);

        mazeCreator.create();

        GraphicMaze graphicMaze = new GraphicMaze(x, y, mazeCreator.getMaze(), "maze.png");
        graphicMaze.generate();

        System.out.println(mazeCreator.toString());
    }

}
