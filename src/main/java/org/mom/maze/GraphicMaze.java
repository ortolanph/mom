package org.bulk.maze.graphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.bulk.maze.Exit;
import org.bulk.maze.Room;

public class GraphicMaze {

    private final int width;
    private final int height;
    private final Map<Integer, Room> myMaze;
    private final String fileName;

    private Graphics2D canvas;

    public GraphicMaze(int width, int height, Map<Integer, Room> myMaze, String fileName) {
        this.width = width;
        this.height = height;
        this.myMaze = myMaze;
        this.fileName = fileName;
    }

    public void generate() {
        BufferedImage image = new BufferedImage((width * 10), (height * 10), BufferedImage.TYPE_INT_ARGB);

        canvas = (Graphics2D) image.getGraphics();
        canvas.setColor(Color.WHITE);
        canvas.fillRect(0, 0, width * 10, height * 10);
        canvas.setColor(Color.BLACK);
        canvas.drawRect(0, 0, (width * 10) - 1, (height * 10) - 1);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int key = calculateHash(x, y);
                Room room = myMaze.get(key);
                drawRoom(x, y, room, canvas);
            }
        }

        try {
            ImageIO.write(image, "PNG", new File(String.format("%s%s%s", System.getProperty("user.home"), System.getProperty("file.separator"), fileName)));
        } catch (IOException ex) {
            Logger.getLogger(GraphicMaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int calculateHash(int x, int y) {
        return (x * 1000) + y;
    }

    private void drawRoom(int x, int y, Room room, Graphics2D canvas) {
        switch(room.getType()) {
            case BEGIN:
                canvas.setColor(Color.GREEN);
                break;
            case NORMAL:
                canvas.setColor(Color.BLACK);
                break;
            case END:
                canvas.setColor(Color.RED);
        }

        canvas.fillRect((y * 10) + 2, (x * 10) + 2, 6, 6);
        canvas.setColor(Color.BLACK);
        
        for(Exit exit : room.getExits()) {
            switch(exit) {
                case NORTH:
                    canvas.fillRect((y * 10) + 2, (x * 10), 6, 2);
                    break;
                case EAST: 
                    canvas.fillRect((y * 10) + 8, (x * 10) + 2, 2, 6);
                    break;
                case SOUTH: 
                    canvas.fillRect((y * 10) + 2, (x * 10) + 8, 6, 2);
                    break;
                case WEST: 
                    canvas.fillRect((y * 10), (x * 10) + 2, 2, 6);
                    break;
            }
        }
    }

}
