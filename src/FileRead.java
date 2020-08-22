import java.io.File;

public class FileRead
{
    private InputProcessor inputProcessor; // input handler
    private Control control; // control caches

    /**
     * starts a new File
     * @param address address
     * @param type type
     */
    public FileRead(String address, int type)
    {
        if (type == 1)
        {
            File file = new File (address);
            inputProcessor = new InputProcessor (file);
        }
        else
        {
            inputProcessor = new InputProcessor ();
        }
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
