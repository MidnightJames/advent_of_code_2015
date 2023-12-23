package _2015.day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day_5
{
    static final int INVALID_FILENAME = -1;
    static final int FILE_NOT_FOUND = -2;

    static final char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};
    static final String[] badStrings = new String[] {"ab", "cd", "pq", "xy"};

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

    public static int partOne(String line)
    {
        // Check if any bad strings are present
        for (String bad : badStrings)
        {
            if (line.contains(bad))
            {
                return 0;
            }
        }

        // Checking for vowels and double letters
        char[] lineAsChar = line.toCharArray();
        int vowelsCount = 0;
        boolean repeatedLetter = false;

        for (int i = 0; i < lineAsChar.length; ++i)
        {
            // Checking for vowels
            for (int j = (vowels.length - 1); j >= 0; --j)
            {
                if (vowels[j] == lineAsChar[i])
                {
                    ++vowelsCount;
                }
            }

            // Checking for repeated char
            if (!repeatedLetter && i != 0 && lineAsChar[i] == lineAsChar[i - 1])
            {
                repeatedLetter = true;
            }
        }

        if (repeatedLetter && vowelsCount >= 3)
        {
            return 1;
        }

        return 0;
    }

    public static int partTwo(String line)
    {
        // Checking for vowels and double letters
        char[] lineAsChar = line.toCharArray();
        boolean repeatedLetter = false;
        boolean pairExists = false;

        for (int i = 1; i < lineAsChar.length; ++i)
        {
            // Checking for pairs of letters
            if (!pairExists)
            {
                String pair = "" + lineAsChar[i - 1] + lineAsChar[i];
                char[] modifiedLine = line.replace(pair, "0").toCharArray();
                int numberRepeats = 0;

                // Replaced each pair with '0' so finding number of 0's
                for (char test : modifiedLine)
                {
                    if (test == '0')
                    {
                        ++numberRepeats;
                    }
                }

                // If we find two 0's, at least two unique instances of the substring exists
                if (numberRepeats >= 2)
                {
                    pairExists = true;
                }
            }

            // Checking for repeated char
            if (!repeatedLetter && i >= 2 && lineAsChar[i] == lineAsChar[i - 2])
            {
                repeatedLetter = true;
            }

            // Can end early if both requirements are true
            if (repeatedLetter && pairExists)
            {
                return 1;
            }
        }

//        System.out.println(line + " does not meet requirements, returning 0");
        return 0;
    }

    public static void main(String[] args)
    {
        String filename = getFilename();
        File file = new File(filename);

        long startTime = System.nanoTime();

        int niceStringCountPart1 = 0;
        int niceStringCountPart2 = 0;
        try
        {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine())
            {
                String line = fileScanner.nextLine();

                niceStringCountPart1 += partOne(line);
                niceStringCountPart2 += partTwo(line);
            }
            fileScanner.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found.");
            System.exit(FILE_NOT_FOUND);
        }

        System.out.println("Part 1: " + niceStringCountPart1);
        System.out.println("Part 2: " + niceStringCountPart2);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time: " + duration + " ms");
    }
}
