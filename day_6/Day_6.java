package _2015.day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day_6
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

    public static void partOne(int[][] lights, String instruction)
    {
        String[] instructionArray = instruction.split(" ");
        String[] beginCoordinates = null;
        String[] endCoordinates = null;
        boolean toggle = false;
        boolean turnOn = false;

        // If size = 4 we are toggling, otherwise we turn on or off
        if (instructionArray.length == 4)
        {
            toggle = true;
            beginCoordinates = instructionArray[1].split(",");
            endCoordinates = instructionArray[3].split(",");
        }
        // Otherwise, we are turning on or off
        else
        {
            if (instructionArray[1].equals("on"))
            {
                turnOn = true;
            }

            beginCoordinates = instructionArray[2].split(",");
            endCoordinates = instructionArray[4].split(",");
        }

        // Getting coordinates
        int beginColumn = Integer.parseInt(beginCoordinates[0]);
        int beginRow = Integer.parseInt(beginCoordinates[1]);
        int endColumn = Integer.parseInt(endCoordinates[0]);
        int endRow = Integer.parseInt(endCoordinates[1]);

        // If toggling, off lights turn on, on lights turn off
        if (toggle)
        {
            for (int column = beginColumn; column <= endColumn; ++column)
            {
                for (int row = beginRow; row <= endRow; ++row)
                {
                    if (lights[column][row] == 0)
                    {
                        lights[column][row] = 1;
                    }
                    else
                    {
                        lights[column][row] = 0;
                    }
                }
            }
        }
        // Otherwise we are turning all the lights in the range on or off depending on turnOn
        else
        {
            if (turnOn)
            {
                for (int column = beginColumn; column <= endColumn; ++column)
                {
                    for (int row = beginRow; row <= endRow; ++row)
                    {
                        lights[column][row] = 1;
                    }
                }
            }
            else
            {
                for (int column = beginColumn; column <= endColumn; ++column)
                {
                    for (int row = beginRow; row <= endRow; ++row)
                    {
                        lights[column][row] = 0;
                    }
                }
            }
        }
    }

    public static void partTwo(int[][] lights, String instruction)
    {
        String[] instructionArray = instruction.split(" ");
        String[] beginCoordinates = null;
        String[] endCoordinates = null;
        boolean toggle = false;
        boolean brightnessUp = false;

        // If size = 4 we are toggling
        if (instructionArray.length == 4)
        {
            toggle = true;
            beginCoordinates = instructionArray[1].split(",");
            endCoordinates = instructionArray[3].split(",");
        }
        // Otherwise, we are increasing or decreasing brightness
        else
        {
            if (instructionArray[1].equals("on"))
            {
                brightnessUp = true;
            }

            beginCoordinates = instructionArray[2].split(",");
            endCoordinates = instructionArray[4].split(",");
        }

        // Getting coordinates
        int beginColumn = Integer.parseInt(beginCoordinates[0]);
        int beginRow = Integer.parseInt(beginCoordinates[1]);
        int endColumn = Integer.parseInt(endCoordinates[0]);
        int endRow = Integer.parseInt(endCoordinates[1]);

        // If toggling, increase lights in range by 2
        if (toggle)
        {
            for (int column = beginColumn; column <= endColumn; ++column)
            {
                for (int row = beginRow; row <= endRow; ++row)
                {
                    lights[column][row] += 2;
                }
            }
        }
        // Otherwise we are increasing or decreasing the brightness depending on brightnessUp
        else
        {
            // Increasing brightness by 1
            if (brightnessUp)
            {
                for (int column = beginColumn; column <= endColumn; ++column)
                {
                    for (int row = beginRow; row <= endRow; ++row)
                    {
                        lights[column][row] += 1;
                    }
                }
            }
            // Decreasing brightness by 1 as long as we aren't already at 0
            else
            {
                for (int column = beginColumn; column <= endColumn; ++column)
                {
                    for (int row = beginRow; row <= endRow; ++row)
                    {
                        if (lights[column][row] != 0)
                        {
                            lights[column][row] -= 1;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args)
    {
        String filename = getFilename();
        File file = new File(filename);
        int[][] lightsPartOne = new int[1000][1000];
        int[][] lightsPartTwo = new int[1000][1000];

        long startTime = System.nanoTime();

        try
        {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine())
            {
                String instruction = fileScanner.nextLine();
                partOne(lightsPartOne, instruction);
                partTwo(lightsPartTwo, instruction);
            }
            fileScanner.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found.");
            System.exit(FILE_NOT_FOUND);
        }

        // Part 1
        int numberOfLightsOnPart1 = 0;
        for (int column = 0; column < lightsPartOne.length; ++column)
        {
            for (int row = 0; row < lightsPartOne.length; ++row)
            {
                if (lightsPartOne[column][row] == 1)
                {
                    ++numberOfLightsOnPart1;
                }
            }
        }

        // Part 2
        int numberOfLightsOnPart2 = 0;
        for (int column = 0; column < lightsPartOne.length; ++column)
        {
            for (int row = 0; row < lightsPartOne.length; ++row)
            {
                numberOfLightsOnPart2 += lightsPartTwo[column][row];
            }
        }

        System.out.println("Part 1: " + numberOfLightsOnPart1);
        System.out.println("Part 2: " + numberOfLightsOnPart2);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time: " + duration + " ms");
    }
}
