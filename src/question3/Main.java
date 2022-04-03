package question3;

import question3.basics.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String file = "inputQuestion3.txt";

        try {
            Scanner scanner = new Scanner(new File(file));

            Graph graph = new Graph();

            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineItens = line.split(" ");

                //Ignores the first item "Graph:"
                for(int i = 1;i< lineItens.length; i++){
                    String source = Character.toString(lineItens[i].toCharArray()[0]);
                    String target = Character.toString(lineItens[i].toCharArray()[1]);
                    int distance = Integer.parseInt(Character.toString(lineItens[i].toCharArray()[2]));

                    graph.addPath(source, target, distance);
                }
            }

            printOutput(1, graph.getDistanceOf("A","B","C"));
            printOutput(2, graph.getDistanceOf("A","D"));
            printOutput(3, graph.getDistanceOf("A","D","C"));
            printOutput(4, graph.getDistanceOf("A","E","B","C","D"));
            printOutput(5, graph.getDistanceOf("A","E","D"));
            printOutput(6, graph.getNumberOfPathsWithMaxSize("C", "C", 3));
            printOutput(7, graph.getNumberOfPathsWithSize("A", "C", 4));
            printOutput(8, graph.getShortestRouteLenght("A", "C"));
            printOutput(9, graph.getShortestRouteLenght("B", "B"));


        }catch(FileNotFoundException e){
            System.err.println("File " + file + " not found.");
        }
    }

    private static void printOutput(int number, int result){
        System.out.print("Output #" + number + ": ");
        if(result!=-1){
            System.out.println(result);
        }else{
            System.out.println("NO SUCH ROUTE");
        }
    }
}

/*
1. The distance of the route A-B-C.
2. The distance of the route A-D.
3. The distance of the route A-D-C.
4. The distance of the route A-E-B-C-D.
5. The distance of the route A-E-D.
6. The number of trips starting at C and ending at C with a maximum of 3 stops. In the sample data below, there are two such
trips: C-D-C (2 stops) and C-E-B-C (3 stops).
7. The number of trips starting at A and ending at C with exactly 4 stops. In the sample data below, there are three such trips: A
to C (via B, C, D); A to C (via D, C, D); and A to C (via D, E, B).
8. The length of the shortest route (in terms of distance to travel) from A to C.
9. The length of the shortest route (in terms of distance to travel) from B to B.
10. The number of different routes from C to C with a distance of less than 30. In the sample data, the trips are: CDC, CEBC,
CEBCDC, CDCEBC, CDEBC, CEBCEBC, and CEBCEBCEBC.

Output #1: 9
Output #2: 5
Output #3: 13
Output #4: 22
Output #5: NO SUCH ROUTE
Output #6: 2
Output #7: 3
Output #8: 9
Output #9: 9
Output #10: 7
 */