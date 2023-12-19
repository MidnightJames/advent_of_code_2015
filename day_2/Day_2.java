package _2015.day_2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day_2
{
    static final int INVALID_FILENAME = -1;
    static final int FILE_NOT_FOUND = -2;

    public static String getFilename()
    {
        Scanner keyboardScanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String filename = keyboardScanner.nextLine();

        if (filename.isEmpty() || filename.isBlank() || filename == null)
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
        int wrappingPaperNeeded = 0;
        int ribbonNeeded = 0;

        long startTime = System.nanoTime();

        try
        {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine())
            {
                String[] lineSplit = fileScanner.nextLine().split("x");
                int length = Integer.parseInt(lineSplit[0]);
                int width = Integer.parseInt(lineSplit[1]);
                int height = Integer.parseInt(lineSplit[2]);

                Gift gift = new Gift(length, width, height);
                wrappingPaperNeeded += gift.getWrappingPaperArea();
                ribbonNeeded += gift.getRibbionArea();
            }
            fileScanner.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found.");
            System.exit(FILE_NOT_FOUND);
        }

        System.out.println("Total wrapping paper: " + wrappingPaperNeeded);
        System.out.println("Total ribbon: " + ribbonNeeded);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time: " + duration + " ms");
    }
}
