import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main
{
    public static void main (String[] args) {
        int res;
        do {
            Scanner scanner = new Scanner (System.in);
            System.out.println ("Choose input Type: ");
            System.out.println ("1) File");
            System.out.println ("2) Terminal");
            try {
                res = Integer.parseInt (scanner.nextLine ());
            } catch (NumberFormatException e)
            {
                res = 0;
            }
            if (res != 1 && res != 2)
                System.out.println ("You're input should be 1 or 2\n\n");
        } while (res != 1 && res != 2);

        if (res == 1)
        {
            Scanner scanner = new Scanner (System.in);
            String address;
            do {
                System.out.println ("Enter Your Absolute File Address");
                address = scanner.nextLine ();
                if (Files.exists (Paths.get (address))) {
                    File file = new File (address);
                    if (file.isAbsolute ())
                        break;
                }
                System.out.println ("Try Again!\n\n");
            } while (true);
            FileRead read = new FileRead (address, 1);
            read.simulate ();
        }
        else {
            System.out.println ("Waiting for your input.....");
            FileRead read = new FileRead (null, 2);
            read.simulate ();
        }
    }
}
