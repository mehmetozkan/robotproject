package com.mars.robotproject.util;

import com.mars.robotproject.model.Location;
import com.mars.robotproject.model.Position;
import com.mars.robotproject.model._inputClass;
import com.mars.robotproject.model._outputClass;

import java.util.ArrayList;
import java.util.List;


public class RobotRat {

    private int pen_position = 0;

    private int direction = 0;

    private boolean floor[][] = null;

    private int current_row = 0;

    private int current_col = 0;

    private static final int NORTH = 0;

    private static final int SOUTH = 1;

    private static final int EAST = 2;

    private static final int WEST = 3;

    private static final int UP = 0;

    private static final int DOWN = 1;

    private static List<String> samplesCollected = new ArrayList<>();
    private static List<Location> visitedCells = new ArrayList<>();
    private static long battery = 0;

    private static String[] command1 = {"E", "R", "F"};
    private static String[] command2 = {"E", "L", "F"};
    private static String[] command3 = {"E", "L", "L", "F"};
    private static String[] command4 = {"E", "B", "R", "F"};
    private static String[] command5 = {"E", "B", "B", "L", "F"};
    private static String[] command6 = {"E", "F", "F"};
    private static String[] command7 = {"E", "F", "L", "F", "L", "F"};


    public _outputClass Run(_inputClass input) {
        Position currentPosition = input.getInitialPosition();
        visitedCells.clear();
        samplesCollected.clear();

        direction = EAST;
        pen_position = UP;
        current_row = (int) currentPosition.getLocation().getY();
        current_col = (int) currentPosition.getLocation().getX();
        floor = new boolean[input.getTerrain().length][input.getTerrain()[0].length];
        battery = input.getBattery();
        initializeFloor();
        String[][] terrain = input.getTerrain();
        String pFacing = currentPosition.getFacing();
        visitedCells.add(new Location(current_row, current_col));


        String[] commands = input.getCommands();
        commandRun(terrain, commands, true);

        _outputClass outputClass = new _outputClass();
        Location[] visitedCellsArray = new Location[visitedCells.size()];
        for (int i = 0; i < visitedCells.size(); i++) {
            visitedCellsArray[i] = visitedCells.get(i);
        }
        outputClass.setVisitedCells(visitedCellsArray);
        outputClass.setBattery(battery);
        String dir = direction == 0 ? "North" : direction == 1 ? "South" : direction == 2 ? "East" : direction == 3 ? "West" : "";

        outputClass.setFinalPosition(new Position(new Location(current_row, current_col), dir));

        String[] samplesCollectedArray = new String[samplesCollected.size()];
        for (int i = 0; i < samplesCollected.size(); i++) {
            samplesCollectedArray[i] = samplesCollected.get(i);
        }
        outputClass.setSamplesCollected(samplesCollectedArray);
        printFloor();
        return outputClass;
    }

    private void commandProcess(String[][] terrain, String Islem) {

        if (Islem.equals("S")) {
            battery = battery - 8;
            samplesCollected.add(terrain[current_row][current_col]);
        }
        if (Islem.equals("F")) {
            battery = battery - 3;
            move(1);
            visitedCells.add(new Location(current_row, current_col));
        }
        if (Islem.equals("R")) {
            battery = battery - 2;
            turnRight();

        }
        if (Islem.equals("L")) {
            battery = battery - 2;
            turnLeft();
        }
        if (Islem.equals("B")) {
            battery = battery - 3;
            move(-1);
        }
        if (Islem.equals("E")) {
            battery = battery - 1;
            battery = battery + 10;
        }
    }


    private boolean commandRun(String[][] terrain, String[] commands, boolean start) {
        int i = 0;
        for (i = 0; i < commands.length; i++) {

            String command = commands[i];
            String terC = terrain[current_row][current_col];

            if (terC.equals("Obs") && start) {
                battery = battery + 3;
                visitedCells.remove(visitedCells.size() - 1);
                current_row = (int) visitedCells.get(visitedCells.size()-1).getY();
                current_col = (int) visitedCells.get(visitedCells.size()-1).getX();

                if (commandRun(terrain, command1, true)) {System.out.println("1. Strategy was run");
                } else if (commandRun(terrain, command2, true)) {System.out.println("2. Strategy was run");
                } else if (commandRun(terrain, command3, true)) {System.out.println("3. Strategy was run");
                } else if (commandRun(terrain, command4, true)) {System.out.println("4. Strategy was run");
                } else if (commandRun(terrain, command5, true)) {System.out.println("5. Strategy was run");
                } else if (commandRun(terrain, command6, true)) {System.out.println("6. Strategy was run");
                } else if (commandRun(terrain, command7, true)) {System.out.println("7. Strategy was run");
                }

               /*for (int y = 0; y < command1.length; y++) {
                   commandProcess(terrain, command1[y]);
                }*/

                for (int j = i; j < commands.length; j++) {
                    String command2 = commands[i];
                    commandProcess(terrain, command2);
                }
            } else {
                commandProcess(terrain, command);
            }

        }
        return (i) == commands.length ? true : false;
    }

    private void initializeFloor() {
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[i].length; j++) {
                floor[i][j] = false;
            }
        }
    }

    private void setPenUp() {
        pen_position = UP;
        System.out.println("The pen_position is UP");
    }


    private void setPenDown() {
        pen_position = DOWN;
        System.out.println("pen_position is DOWN");
    }

    private void turnRight() {
        setDirection(EAST, SOUTH, WEST, NORTH);
    }

    private void setDirection(int east, int south, int west, int north) {
        switch (direction) {
            case NORTH:
                direction = east;
                break;
            case EAST:
                direction = south;
                break;
            case SOUTH:
                direction = west;
                break;
            case WEST:
                direction = north;
                break;
            default:
                direction = EAST;
        }
    }

    private void turnLeft() {
        setDirection(WEST, NORTH, EAST, SOUTH);
    }

    private void move(int spaces) {
        switch (pen_position) {
            case UP:
                switch (direction) {
                    case NORTH:
                        if ((current_row - spaces) <= 0)
                            current_row = 0;
                        else
                            current_row = current_row - spaces;
                        break;
                    case SOUTH:
                        if ((current_row + spaces) >= (floor[0].length - 1))
                            current_row = (floor[0].length - 1);
                        else
                            current_row = current_row + spaces;
                        break;
                    case EAST:
                        if ((current_col + spaces) >= (floor.length - 1))
                            current_col = (floor.length - 1);
                        else
                            current_col = current_col + spaces;
                        break;
                    case WEST:
                        if ((current_col - spaces) <= 0)
                            current_col = 0;
                        else
                            current_col = current_col - spaces;
                        break;
                }
                break;
            case DOWN:
                switch (direction) {
                    case NORTH:
                        if ((current_row - spaces) <= 0) {
                            while (current_row >= 1)
                                floor[current_row--][current_col] = true;
                        } else
                            while (spaces-- > 0)
                                floor[current_row--][current_col] = true;
                        break;
                    case SOUTH:
                        if ((current_row + spaces) >= (floor[0].length - 1)) {
                            while (current_row < (floor[0].length - 1))
                                floor[current_row++][current_col] = true;
                        } else
                            while (spaces-- > 0)
                                floor[current_row++][current_col] = true;
                        break;
                    case EAST:
                        if ((current_col + spaces) >= (floor.length - 1)) {
                            while (current_col < (floor.length - 1))
                                floor[current_row][current_col++] = true;
                        } else
                            while (spaces-- > 0)
                                floor[current_row][current_col++] = true;
                        break;
                    case WEST:
                        if ((current_col - spaces) <= 0) {
                            while (current_col >= 1)
                                floor[current_row][current_col--] = true;
                        } else
                            while (spaces-- > 0)
                                floor[current_row][current_col--] = true;
                        break;
                }
                break;
        }
    }


    private void printFloor() {
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[i].length; j++) {
                if (floor[i][j] == true)
                    System.out.print('*');
                else
                    System.out.print('0');
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }

}
