package question3.basics;

import java.util.ArrayList;

public class Town {

    private String name;
    private ArrayList<Path> paths;

    public Town(String name) {
        this.name = name;
        this.paths = new ArrayList<Path>();
    }

    public void addPath(Path path){
        this.paths.add(path);
    }

    public String getName() {
        return name;
    }

    public Path getPathTo(String target){
        return paths.stream().filter(p->p.getNode().getName().equals(target)).findFirst().orElse(null);
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
