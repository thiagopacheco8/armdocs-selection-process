package question2.basics;

import java.util.ArrayList;

public class Map {
    private ArrayList<Rover> rovers;
    private int xAxisSize;
    private int yAxisSize;

    public Map(int xAxisSize, int yAxisSize) {
        this.xAxisSize = xAxisSize;
        this.yAxisSize = yAxisSize;
        this.rovers = new ArrayList<Rover>();
    }

    public void addRover(Rover rover){
        this.rovers.add(rover);
    }
}
