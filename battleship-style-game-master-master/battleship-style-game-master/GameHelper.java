import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.function.IntBinaryOperator;

public class GameHelper
{

    private static String alphabet;
    private int gridLength;
    private int gridSize;
    private int[] grid;
    private int comCount;

    private int[][] gameBoard;

    private BufferedReader inputReader= new BufferedReader(new InputStreamReader(System.in));

    //TODO make it so gamehelper can take a gameHelper object to specify its settings
    public GameHelper()
    {
        alphabet = "abcdefg";
        gridLength = 7;
        gridSize = 49;
        grid = new int[gridSize];
        comCount = 0;

        gameBoard = new int[gridLength][gridLength];

    }

    public void gridDisplay(ArrayList<String> correctUserGuesses, ArrayList<String> wrongUserGuesses)
    {
        ArrayList<String> aList = new ArrayList<String>();
        aList.add("a");
        aList.add("b");
        aList.add("c");
        aList.add("d");
        aList.add("e");
        aList.add("f");
        aList.add("g");

        System.out.println("  + - + - + - + - + - + - + - +");

        for (String s : aList)
        {
            System.out.print(s + " |");
            for (int i = 0; i < gridSize / aList.size(); i++) {
                if (correctUserGuesses.contains(s + i)) {
                    System.out.print(" 1 ");
                } else if (wrongUserGuesses.contains(s + i)) {
                    System.out.print(" X ");
                }
                else {
                    System.out.print("   ");
                }
                System.out.print("|");
            }
            System.out.println("\n  + - + - + - + - + - + - + - +");
        }
        System.out.println("    0   1   2   3   4   5   6");

    }

    protected BufferedReader getReader(){return inputReader;}

    public String getUserInput(String prompt, BufferedReader inputReader) throws IOException {

        //Doesn't catch bad input from user. Records as a miss.
        String inputLine;

        System.out.print(prompt + " ");
        BufferedReader is = inputReader;
        inputLine = is.readLine();
        if (inputLine == null)
            return null;

        if (inputLine.length() > 2)
            throw new IOException("Input is too long.");
        else if(inputLine.length() < 2)
            throw new IOException("Input is too short.");

        //TODO Check make sure input contains a number
        //TODO make sure numbers is within bounds

        return inputLine.toLowerCase();
    }

    /*
    Find random location within grid to place ship of ship size
     */
    public ArrayList<String> placeDotCom(int shipLength)
    {
        ArrayList<String> alphaCells = new ArrayList<String>();
        String[] alphacoords = new String[shipLength];
        String temp = null;

        comCount++;                         //Number of ships?

        // directional variables for ship placement
        boolean isVertical = false;
        boolean up = true;
        boolean left = true;

        //TODO inject a seed for random to control output
        Random random = new Random();
        int num = random.nextInt(2)+1;

        if (num == 1)
            isVertical = true;

        //TODO throws error if ship size is too large.
        if (shipLength > gridLength)
            System.out.println("Should throw an error");

        int startingLocationX;
        int startingLocationY;

        List<Point> shipCoords = new ArrayList<>();

        boolean roomOnLeft = false;
        boolean roomOnRight = false;
        boolean roomAbove = false;
        boolean roomBelow = false;

        boolean found_shipPlacement = false;

        //TODO modularize code new method for selecting starting location and placement orientation

        // add way to check if placement is possible? have a limiter.
        while(!found_shipPlacement)
        {
            startingLocationX = (int)(Math.random()*gridLength);
            startingLocationY = (int)(Math.random()*gridLength);

            if(startingLocationX - shipLength > 0)
                roomOnLeft = true;
            if(startingLocationX + shipLength < gridLength -1)
                roomOnRight = true;

            if(startingLocationY -shipLength > 0)
                roomAbove = true;

            if(startingLocationY + shipLength < gridLength -1)
                roomBelow = true;

            if (gameBoard[startingLocationX][startingLocationY] == 0)
            {
                shipCoords.add( new Point(startingLocationX, startingLocationY));

                if(isVertical && up && roomAbove)
                {
                    shipCoords = placeVertically(startingLocationX, startingLocationY, shipLength, up,shipCoords);
                    found_shipPlacement = true;

                    if(shipCoords.isEmpty())
                    {
                        shipCoords.add( new Point(startingLocationX, startingLocationY));
                        found_shipPlacement =false;
                        up = false;
                    }
                }

                if(isVertical && !up && roomBelow)
                {
                    shipCoords = placeVertically(startingLocationX, startingLocationY, shipLength, up,shipCoords);
                    found_shipPlacement = !shipCoords.isEmpty();

                }

                if(!isVertical && left && roomOnLeft)
                {
                    shipCoords = placeHorizontally(startingLocationX, startingLocationY, shipLength, left,shipCoords);
                    found_shipPlacement = true;

                    if(shipCoords.isEmpty())
                    {
                        shipCoords.add(new Point(startingLocationX, startingLocationY));
                        left = false;
                        found_shipPlacement = false;
                    }
                }

                if(!isVertical && !left && roomOnRight)
                {
                    shipCoords = placeHorizontally(startingLocationX, startingLocationY, shipLength, left,shipCoords);
                    found_shipPlacement = !shipCoords.isEmpty();

                }

                if(!found_shipPlacement)
                    shipCoords.clear();
            }
        }

        int column;
        int row;

        //todo enhanced for loop?
        for (int i = 0; i < shipCoords.size(); i++)
        {
            column = shipCoords.get(i).x;
            row = shipCoords.get(i).y;
            gameBoard[row][column] = 1;

            temp = String.valueOf(alphabet.charAt(column));
            alphaCells.add(temp.concat(Integer.toString(row)));

        }

        return alphaCells;
    }
    public List<Point> placeVertically (int startingLocationX, int startingLocationY, int shipLength, boolean up, List<Point> shipCoords)
    {
        int direction = -1;
        for(int i = 1; i < shipLength; i++)
        {
            if(!up)
                i = i*direction;

            if(gameBoard[startingLocationX][startingLocationY-i] == 0) {
                shipCoords.add( new Point(startingLocationX, startingLocationY-i));
            }
            else {
                shipCoords.clear();
                return shipCoords;
            }
        }

        return shipCoords;
    }

    public List<Point> placeHorizontally (int startingLocationX, int startingLocationY, int shipLength, boolean left, List<Point> shipCoords)
    {
        int direction = -1;
        for(int i = 1; i < shipLength; i++)
        {
            if(!left)
                i = i*direction;

            if(gameBoard[startingLocationX-i][startingLocationY] == 0) {
                shipCoords.add( new Point(startingLocationX-i, startingLocationY));
            }
            else {
                shipCoords.clear();
                return shipCoords;
            }
        }
        return shipCoords;
    }
}