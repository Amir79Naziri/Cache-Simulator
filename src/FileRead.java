import java.io.File;

public class FileRead
{
    private InputProcessor inputProcessor; // input handler
    private Control control; // control caches


    public FileRead(String address)
    {
        File file = new File (address);
        inputProcessor = new InputProcessor (file);
        control = inputProcessor.createControl ();
    }


    /**
     * start simulation
     */
    public void simulate ()
    {
        String[] data;
        while ((data = inputProcessor.readAccessLine ()) != null)
        {
            try {
                control.access (data[1],Integer.parseInt (data[0]));
            } catch (NumberFormatException e)
            {
                e.printStackTrace ();
            }
        }

        control.printResult ();
    }
}
