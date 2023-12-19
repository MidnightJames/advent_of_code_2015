package _2015.day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day_1
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

    public static void main(String[] args)
    {
        String filename = getFilename();
        File file = new File(filename);
        StringBuilder stringBuilder = new StringBuilder();

        long startTime = System.nanoTime();

        try
        {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine())
            {
                stringBuilder.append(fileScanner.nextLine());
            }
            fileScanner.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found.");
            System.exit(FILE_NOT_FOUND);
        }

        char[] directions = stringBuilder.toString().toCharArray();

        // Find floor
        int floor = 0;
        int indexOfBasement = -1;
        boolean basementIndexFound = false;

        for (int i = 0; i < directions.length; ++i)
        {
            if (directions[i] == '(')
            {
                ++floor;
            }
            else
            {
                --floor;
            }

            if (floor < 0 && !basementIndexFound)
            {
                indexOfBasement = i + 1;
                basementIndexFound = true;
            }
        }

        System.out.println("Part 1: " + floor);
        System.out.println("Part 2: " + indexOfBasement);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time: " + duration + " ms");
    }
}

