package question3.basics;

import java.util.ArrayList;
import java.util.Arrays;


public class Graph {

    private ArrayList<Town> towns;

    public Graph() {
        this.towns = new ArrayList<Town>();
    }

    public void addTown(String name){
        this.towns.add(new Town(name));
    }

    public void addPath(String source, String target, int distance){
        Town sourceTown = this.searchTown(source);
        if(sourceTown == null){
            sourceTown = new Town(source);
            this.towns.add(sourceTown);
        }

        Town targetTown = this.searchTown(target);
        if(targetTown == null){
            targetTown = new Town(target);
            this.towns.add(targetTown);
        }

        sourceTown.addPath(new Path(targetTown, distance));
    }

    private Town searchTown(String name){
        return this.towns.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
    }

    public int getDistanceOf(String... towns){
        int totalDistance = 0;

        Town currentTown = null;
        for(String town : towns){

            if(currentTown==null){
                currentTown = this.searchTown(town);
            }else{
                Path townPath = currentTown.getPathTo(town);
                if(townPath == null){
                    totalDistance=-1;
                    break;
                }else{
                    totalDistance+=townPath.getDistance();
                    currentTown = townPath.getNode();
                }
            }
        }

        return totalDistance;
    }

    public int getNumberOfPathsWithMaxSize(String source, String target, int maxStops){
        ArrayList<ArrayList<Town>> allPossiblePaths = new ArrayList<ArrayList<Town>>();

        for(int i = 2; i<=maxStops;i++){
            allPossiblePaths.addAll(this.getAllPossiblePathsWithSize(source, i+1));
        }

        return (int) allPossiblePaths.stream().filter(l -> l.get(l.size()-1).getName().equals(target)).count();
    }

    public int getNumberOfPathsWithSize(String source, String target, int numberOfStops){
        ArrayList<ArrayList<Town>> allPossiblePaths = this.getAllPossiblePathsWithSize(source, numberOfStops+1);
        return (int) allPossiblePaths.stream().filter(l -> l.get(l.size()-1).getName().equals(target)).count();
    }

    public int getShortestRouteLenght(String source, String target){
        ArrayList<ArrayList<Path>> allPossiblePaths = this.getAllPossiblePathsForTarget(source,target);

        return allPossiblePaths.stream().mapToInt(
                p -> p.stream().mapToInt(p2->p2.getDistance()).sum()
        ).min().orElse(-1);
    }

    private ArrayList<ArrayList<Town>> getAllPossiblePathsWithSize(String source, int numberOfStops){
        Town sourceTown = this.searchTown(source);

        ArrayList<ArrayList<Town>> allPaths = new ArrayList<ArrayList<Town>>();
        allPaths = new ArrayList<ArrayList<Town>>();
        ArrayList<Town> initialPath = new ArrayList<Town>();
        initialPath.add(sourceTown);

        this.navigateAllPossiblePaths(initialPath, numberOfStops, allPaths);

        return allPaths;
    }

    private ArrayList<ArrayList<Path>> getAllPossiblePathsForTarget(String source, String target){
        Town sourceTown = this.searchTown(source);

        ArrayList<ArrayList<Path>> allPaths = new ArrayList<ArrayList<Path>>();
        allPaths = new ArrayList<ArrayList<Path>>();
        ArrayList<Path> initialPath = new ArrayList<Path>();
        initialPath.add(new Path(sourceTown,0));

        this.navigateAllPossiblePathsToTarget(initialPath, target, allPaths);

        return allPaths;
    }

    private ArrayList<Town> navigateAllPossiblePaths(ArrayList<Town> currentPath, int numberOfStops, ArrayList<ArrayList<Town>> allPaths) {
        if(currentPath.size() >= numberOfStops){
            allPaths.add(currentPath);
            return currentPath;
        }
        else
        {
            for(Path possiblePath:currentPath.get(currentPath.size()-1).getPaths()){
                ArrayList<Town> newPath = new ArrayList<Town>(currentPath);
                newPath.add(possiblePath.getNode());
                this.navigateAllPossiblePaths(newPath, numberOfStops, allPaths);
            }
            return currentPath;
        }
    }

    private ArrayList<Path> navigateAllPossiblePathsToTarget(ArrayList<Path> currentPath, String target, ArrayList<ArrayList<Path>> allPaths) {
        if(currentPath.size()>1 && currentPath.get(currentPath.size()-1).getNode().getName().equals(target)){
            allPaths.add(currentPath);
            return currentPath;
        }
        else
        {
            for(Path possiblePath:currentPath.get(currentPath.size()-1).getNode().getPaths()){
                ArrayList<Path> newPath = new ArrayList<Path>(currentPath);
                newPath.add(possiblePath);
                this.navigateAllPossiblePathsToTarget(newPath, target, allPaths);
            }
            return currentPath;
        }
    }

    private void printAllPaths(ArrayList<ArrayList<Path>> paths){
        paths.stream().forEach(p-> {
            System.out.print("Path = [");
            p.stream().forEach(p2-> System.out.println(p2.getNode().getName() + ","));
            System.out.println("]");
        });
    }

}
