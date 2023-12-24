package _2015.day_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day_7
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

    public static void partOne(String line, HashMap<String, Integer> wires)
    {
        String[] lineArray = line.split(" ");

        // If length = 3:
        // Direct assignment "123 -> x" or "x -> y"
        if (lineArray.length == 3)
        {
            String wire = lineArray[2];
            String signal = lineArray[0];
            int signalInt = 0;

            // Signal is a number
            if (signal.charAt(0) >= 48 && signal.charAt(0) <= 57)
            {
                signalInt = Integer.parseInt(signal);
            }
            // Signal is a variable
            else
            {
                signalInt = wires.get(signal);
            }

            wires.put(wire, signalInt);
        }
        // If length = 4:
        // Bitwise complement "NOT x -> h"
        else if (lineArray.length == 4)
        {
            String wireTo = lineArray[3];
            String wireFrom = lineArray[1];
            int signal = wires.getOrDefault(wireFrom, 0);
            int signalComplement = ~signal;

            // Add 2^4 if we are negative
            if (signalComplement < 0)
            {
                signalComplement += 65536;
            }

            wires.put(wireTo, signalComplement);
        }
        // If length = 5:
        // Left shift "x LSHIFT 2 -> f"
        // Right shift "y RSHIFT 2 -> g"
        // And "x AND y -> d"
        // Or "x OR y -> e"
        else if (lineArray.length == 5)
        {
            String wireTo = lineArray[4];
            String wireLeft = lineArray[0];
            int signalLeft = wires.getOrDefault(wireLeft, 0);
            String wireRight = lineArray[2];
            int signalRight = 0;

            switch (lineArray[1])
            {
                case "LSHIFT":
                    signalRight = Integer.parseInt(wireRight);
                    int signalLeftShift = signalLeft << signalRight;
                    wires.put(wireTo, signalLeftShift);
                    break;
                case "RSHIFT":
                    signalRight = Integer.parseInt(wireRight);
                    int signalRightShift = signalLeft >> signalRight;
                    wires.put(wireTo, signalRightShift);
                    break;
                case "AND":
                    signalRight = wires.getOrDefault(wireRight, 0);
                    int signalAnd = signalLeft & signalRight;
                    wires.put(wireTo, signalAnd);
                    break;
                case "OR":
                    signalRight = wires.getOrDefault(wireRight, 0);
                    int signalOr = signalLeft | signalRight;
                    wires.put(wireTo, signalOr);
                    break;
            }
        }
        else
        {
            System.out.println("Invalid line length");
        }
    }

    public static void main(String[] args)
    {
        String filename = getFilename();
        File file = new File(filename);

        long startTime = System.nanoTime();

        HashMap<String, Integer> wires = new HashMap<>();
        try
        {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine())
            {
                String line = fileScanner.nextLine();
                partOne(line, wires);
            }
            fileScanner.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found.");
            System.exit(FILE_NOT_FOUND);
        }

        // Printing all keys and values
        for (String key : wires.keySet())
        {
            System.out.println(key + ": " + wires.get(key));
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time: " + duration + " ms");
    }
}
