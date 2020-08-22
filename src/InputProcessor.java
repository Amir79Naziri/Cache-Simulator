import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * this class represented for input handling
 *
 * @author Amir Naziri
 */
public class InputProcessor
{
    private Scanner scanner;

    /**
     * creates new InputProcessor
     */
    public InputProcessor ()
    {
        scanner = new Scanner (System.in);
    }


    public InputProcessor(File file)
    {
        try {
            scanner = new Scanner (file);
        } catch (IOException e)
        {
            e.printStackTrace ();
        }
    }
    /**
     * creates a new Control unit from user input
     * @return Control unit
     */
    public Control createControl ()
    {

        String[] fLineData = scanner.nextLine ().trim ().split (" - ");
        String[] sLineData = scanner.nextLine ().trim ().split (" - ");

        try {
            int[] size = new int[sLineData.length];

            for (int i = 0; i < sLineData.length; i++)
                size[i] = Integer.parseInt (sLineData[i]);

            return new Control (Integer.parseInt (fLineData[0]),
                    Integer.parseInt (fLineData[1]),Integer.parseInt (fLineData[2]),
                    fLineData[3],fLineData[4],size);
        } catch (NumberFormatException e)
        {
            e.printStackTrace ();
            return null;
        }
    }

    /**
     * reads a line
     * @return read line's splits
     */
    public String[] readAccessLine ()
    {
        String[] line = scanner.nextLine ().trim ().split (" ");
        if (line.length >= 2)
            return new String[]{line[0], line[1]};
        else
            return null;
    }



}
