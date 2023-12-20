package _2015.day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day_3
{
    static final int INVALID_FILENAME = -1;
    static final int FILE_NOT_FOUND = -2;

    public static String getFilename()
    {
        Scanner keyboardScanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String filename = keyboardScanner.nextLine();

        if (filename.isEmpty() || filename.isBlank())
        {
            System.out.println("Invalid filename entered.");
            System.exit(INVALID_FILENAME);;
        }

        keyboardScanner.close();
        return filename;
    }

    public static int partOne(char[] directions)
    {
        // Initializing
        int x = 0;
        int y = 0;
        String coordinates = x + "," + y;

        // Setting first coordinate
        HashMap<String, Integer> housesVisited = new HashMap<String, Integer>();
        housesVisited.put(coordinates, 1);

        // Loop through the input, updating coordinates and putting into map
        for (char direction : directions)
        {
            // Getting new coordinates
            if (direction == '<')
            {
                --x;
            }
            else if (direction == '>')
            {
                ++x;
            }
            else if (direction == '^')
            {
                --y;
            }
            else
            {
                ++y;
            }

            // Updating map with new coordinates
            coordinates = x + "," + y;
            if (housesVisited.containsKey(coordinates))
            {
                int newValue = housesVisited.get(coordinates) + 1;
                housesVisited.put(coordinates, newValue);
            }
            else
            {
                housesVisited.put(coordinates, 1);
            }
        }

        return housesVisited.keySet().size();
    }

    public static int partTwo(char[] directions)
    {
        // Initializing
        int santaX = 0;
        int santaY = 0;
        int roboX = 0;
        int roboY = 0;
        boolean santaMove = true;
        String santaCoordinates = santaX + "," + santaY;
        String roboCoordinates;

        // Setting first coordinate
        HashMap<String, Integer> housesVisited = new HashMap<String, Integer>();
        housesVisited.put(santaCoordinates, 1);

        // Loop through the input, updating coordinates and putting into map
        for (char direction : directions)
        {
            // Getting new coordinates if santa
            if (santaMove)
            {
                if (direction == '<')
                {
                    --santaX;
                }
                else if (direction == '>')
                {
                    ++santaX;
                }
                else if (direction == '^')
                {
                    --santaY;
                }
                else
                {
                    ++santaY;
                }
                // Updating map with new coordinates
                santaCoordinates = santaX + "," + santaY;
                if (housesVisited.containsKey(santaCoordinates))
                {
                    int newValue = housesVisited.get(santaCoordinates) + 1;
                    housesVisited.put(santaCoordinates, newValue);
                }
                else
                {
                    housesVisited.put(santaCoordinates, 1);
                }

            }
            else
            {
                if (direction == '<')
                {
                    --roboX;
                }
                else if (direction == '>')
                {
                    ++roboX;
                }
                else if (direction == '^')
                {
                    --roboY;
                }
                else
                {
                    ++roboY;
                }

                // Updating map with new coordinates
                roboCoordinates = roboX + "," + roboY;
                if (housesVisited.containsKey(roboCoordinates))
                {
                    int newValue = housesVisited.get(roboCoordinates) + 1;
                    housesVisited.put(roboCoordinates, newValue);
                }
                else
                {
                    housesVisited.put(roboCoordinates, 1);
                }
            }

            santaMove = !santaMove;
        }

        return housesVisited.keySet().size();
    }

    public static void main(String[] args)
    {
        String filename = getFilename();
        File file = new File(filename);

        long startTime = System.nanoTime();

        char[] directions = null;
        try
        {
            Scanner fileScanner = new Scanner(file);
            directions = fileScanner.nextLine().toCharArray();
            fileScanner.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found.");
            System.exit(FILE_NOT_FOUND);
        }

        System.out.println("Part 1: " + partOne(directions));
        System.out.println("Part 2: " + partTwo(directions));

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time: " + duration + " ms");
    }
}

