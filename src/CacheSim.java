/**
 * this class represents a cache simulator
 *
 * @author Amir Naziri
 */
public class CacheSim
{
    private Control control; // control caches
    private InputProcessor inputProcessor; // input handler

    /**
     * create a new Cache simulator
     */
    public CacheSim ()
    {
        inputProcessor = new InputProcessor ();
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
