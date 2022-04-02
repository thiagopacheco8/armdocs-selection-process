package question2;

import question2.basics.EDirection;
import question2.basics.Map;
import question2.basics.Rover;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            Scanner scanner = new Scanner(new File("inputQuestion2.txt"));

            if(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineItens = line.split(" ");
                int xAxisSize = Integer.parseInt(lineItens[0]);
                int yAxisSize = Integer.parseInt(lineItens[1]);
                Map map = new Map(xAxisSize, yAxisSize);

                while(scanner.hasNextLine()){
                    //Reads the line X Y Direction
                    String roverLine = scanner.nextLine();
                    String[] roverLineItens = roverLine.split(" ");
                    int initialXPosition = Integer.parseInt(roverLineItens[0]);
                    int initialYPosition = Integer.parseInt(roverLineItens[1]);
                    EDirection initialDirection  = EDirection.valueOf(roverLineItens[2]);

                    Rover rover = new Rover(initialXPosition, initialYPosition, initialDirection);
                    map.addRover(rover);

                    if(scanner.hasNextLine()){
                        String movements = scanner.nextLine();

                        for(char move : movements.toCharArray())
                        {
                            switch(move){
                                case 'M':
                                    rover.move();
                                    break;
                                case 'L':
                                    rover.turnLeft();
                                    break;
                                case 'R':
                                    rover.turnRight();
                            }
                        }
                        System.out.println(rover.toString());
                    }
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}