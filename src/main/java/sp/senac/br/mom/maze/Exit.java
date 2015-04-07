package sp.senac.br.mom.maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Exit {
    NORTH(0, 2, -1, 0),
    EAST(1, 3, 0, 1),
    SOUTH(2, 0, 1, 0),
    WEST(3, 1, 0, -1);
    
    private final int id;
    private final int opposite;
    private final int dx;
    private final int dy;
    public static final List<Exit> ALL_EXITS = Arrays.asList(NORTH, EAST, SOUTH, WEST);
    
    private Exit(int id, int opposite, int dx, int dy) {
        this.id = id;
        this.opposite = opposite;
        this.dx = dx;
        this.dy = dy;
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
        for(Exit exit : values()) {
            if(exit.getId() == opposite) {
                return exit;
            }
        }
        
        throw new IllegalArgumentException(name());
    }
    
    public static List<Exit> getRemainingExits(List<Exit> usedExits) {
        List<Exit> remainingExits = new ArrayList<>(ALL_EXITS);
        
        remainingExits.removeAll(usedExits);
        
        return remainingExits;
    }
}
