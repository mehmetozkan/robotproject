package com.mars.robotproject.util;

import java.io.*;

/**
 * @author <b> Rick Miller </b>
 * @version 1.0
 *
 */
class RobotRat {
    private BufferedReader console = null;

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


    public RobotRat(int rows, int cols) {
        console = new BufferedReader(new InputStreamReader(System.in));
        direction = EAST;
        pen_position = UP;
        current_row = 0;
        current_col = 0;
        floor = new boolean[rows][cols];
        initializeFloor();
    }

    private void initializeFloor() {
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[i].length; j++) {
                floor[i][j] = false;
            }
        }
    }

    public void run() {
        while (true) {
            printMenu();
            processMenuChoice();
        }
    }

    private void printMenu() {
        System.out.println("/n/n");
        System.out.println(" Robot Rat Control Menu");
        System.out.println("/n");
        System.out.println(" 1. Pen Up");
        System.out.println(" 2. Pen Down");
        System.out.println(" 3. Turn Right");
        System.out.println(" 4. Turn Left");
        System.out.println(" 5. Move Forward");
        System.out.println(" 6. Print Floor");
        System.out.println(" 7. Exit");
        System.out.println("/n/n/n");
    }

    private int getSpaces() {
        int temp = 0;
        try {
            temp = Integer.parseInt(console.readLine());
        } catch (Exception e) {
            System.out.println("Spaces has been set to zero!");
        }
        return temp;
    }

    private char readChar() {
        String s = null;
        System.out.print("Please select from the menu: ");
        try {
            s = console.readLine();
        } catch (Exception ignored) {
        }
        return s.charAt(0);
    }

    private void processMenuChoice() {

        switch (readChar()) {
            case '1':
                setPenUp();
                break;
            case '2':
                setPenDown();
                break;
            case '3':
                turnRight();
                break;
            case '4':
                turnLeft();
                break;
            case '5':
                move();
                break;
            case '6':
                printFloor();
                break;
            case '7':
                exit();
            default:
                printErrorMessage();
        }
    }

    /**
     * Sets the RobotRat's pen to the UP position
     */
    private void setPenUp() {
        pen_position = UP;
        System.out.println("The pen_position is UP");
    }

    /**
     * Sets the RobotRat's pen to the DOWN position
     */
    private void setPenDown() {
        pen_position = DOWN;
        System.out.println("pen_position is DOWN");
    }

    /**
     * Turns the RobotRat to the right
     */
    private void turnRight() {
        switch (direction) {
            case NORTH:
                direction = EAST;
                System.out.println("RobotRat facing EAST");
                break;
            case EAST:
                direction = SOUTH;
                System.out.println("RobotRat facing SOUTH");
                break;
            case SOUTH:
                direction = WEST;
                System.out.println("RobotRat facing WEST");
                break;
            case WEST:
                direction = NORTH;
                System.out.println("RobotRat facing NORTH");
                break;
            default:
                direction = EAST;
                System.out.println("RobotRat facing EAST");
        }
        System.out.println("turnRight() method");
    }

    /**
     * Turns the RobotRat to the left
     */
    private void turnLeft() {
        switch (direction) {
            case NORTH:
                direction = WEST;
                System.out.println("RobotRat facing WEST");
                break;
            case EAST:
                direction = NORTH;
                System.out.println("RobotRat facing NORTH");
                break;
            case SOUTH:
                direction = EAST;
                System.out.println("RobotRat facing EAST");
                break;
            case WEST:
                direction = SOUTH;
                System.out.println("RobotRat facing SOUTH");
                break;
            default:
                direction = EAST;
                System.out.println("RobotRat facing EAST");
        }
    }

    /**
     * This method moves the RobotRat about the floor.
     * @see #getSpaces()
     */
    private void move() {
        System.out.print("Please enter spaces to move: ");
        int spaces = getSpaces();
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

    /**
     * Prints the floor array pattern to the console
     * @see #floor
     */
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

    /**
     * Exits the RobotRat program
     * @see System#exit
     */
    private void exit() {
        System.exit(0);
    }

    /**
     * Prints error text message when invalid RobotRat menu item entered
     */
    private void printErrorMessage() {
        System.out.println("Please enter a valid menu choice!");
    }


    public static void main(String[] args) {
        RobotRat rr = new RobotRat(20, 20);
        rr.run();
    }
}
