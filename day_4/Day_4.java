package _2015.day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Day_4
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

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String filename = getFilename();
        File file = new File(filename);
        String secretKey = null;

        long startTime = System.nanoTime();

        try
        {
            Scanner fileScanner = new Scanner(file);
            secretKey = fileScanner.nextLine();
            fileScanner.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found.");
            System.exit(FILE_NOT_FOUND);
        }

        int index = 0;
        boolean partOneSolved = false;
        boolean partTwoSolved = false;
        while(!partOneSolved || !partTwoSolved)
        {
            String testKey = secretKey + index;
            byte[] keyBytes = testKey.getBytes();
            MessageDigest md5MessageDigest = MessageDigest.getInstance("MD5");
            byte[] keyDigested = md5MessageDigest.digest(keyBytes);

            // Turning the MD5 into hex
            String keyHex = "";
            for (byte j : keyDigested)
            {
                keyHex += String.format("%02X", j);
            }

            // Checking if part 1 is solved
            if (!partOneSolved && keyHex.startsWith("00000"))
            {
                System.out.println("Part 1: " + index);
                partOneSolved = true;
            }

            // Checking if part 2 is solved
            if (!partTwoSolved && keyHex.startsWith("000000"))
            {
                System.out.println("Part 2: " + index);
                partTwoSolved = true;
            }

            ++index;
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time: " + duration + " ms");
    }
}
