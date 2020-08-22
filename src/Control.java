/**
 * this class represents the cache handlers
 *
 * @author Amir Naziri
 */
public class Control
{
    private int unifiedOrSeparated;
    private String writePolicy;
    private String allocationPolicy;
    private int associativity;
    private int blockSize;
    private Cache[] caches;

    /**
     * creates a new control unit
     * @param blockSize blockSize
     * @param unifiedOrSeparated unifiedOrSeparated
     * @param associativity associativity
     * @param writePolicy writePolicy
     * @param allocationPolicy allocationPolicy
     * @param size size
     */
    public Control (int blockSize, int unifiedOrSeparated, int associativity, String writePolicy,
                    String allocationPolicy, int[] size)
    {
        this.unifiedOrSeparated = unifiedOrSeparated;
        this.allocationPolicy = allocationPolicy;
        this.writePolicy = writePolicy;
        this.blockSize = blockSize;
        this.associativity = associativity;

        if (unifiedOrSeparated == 0)
        {
            caches = new Cache[1];
            caches[0] = new Cache (size[0],blockSize,associativity,writePolicy,allocationPolicy);
        }
        else
        {
            caches = new Cache[2];
            caches[0] = new InstructionCache (size[0],blockSize,associativity,writePolicy,
                    allocationPolicy);
            caches[1] = new DataCache (size[1],blockSize,associativity,writePolicy,
                    allocationPolicy);
        }
    }

    /**
     * access caches
     * @param hex address
     * @param type type : 0 , 1 , 2
     */
    public void access (String hex, int type)
    {
        if (unifiedOrSeparated == 0)
        {
            caches[0].access (hex, type);
        }
        else
        {
            if (type == 2)
                caches[0].access (hex, type);
            else
                caches[1].access (hex, type);
        }
    }

    /**
     * printUnifiedHeader
     */
    private void printUnifiedHeader ()
    {
        System.out.println ("***CACHE SETTINGS***");
        System.out.println ("Unified I- D-cache");
        System.out.println ("Size: " + caches[0].getSize ());
    }

    /**
     * printSplitHeader
     */
    private void printSplitHeader ()
    {
        System.out.println ("***CACHE SETTINGS***");
        System.out.println ("Split I- D-cache");
        System.out.println ("I-cache size: " + caches[0].getSize ());
        System.out.println ("D-cache size: " + caches[1].getSize ());
    }

    /**
     * printBodyResult
     */
    private void printBodyResult ()
    {
        System.out.println ("Associativity: " + associativity);
        System.out.println ("Block size: " + blockSize);
        System.out.print ("Write policy: ");
        if (writePolicy.equals ("wb"))
            System.out.println ("WRITE BACK");
        else
            System.out.println ("WRITE THROUGH");

        System.out.print ("Allocation policy: ");
        if (allocationPolicy.equals ("wa"))
            System.out.println ("WRITE ALLOCATE");
        else
            System.out.println ("WRITE NO ALLOCATE");
        System.out.println ();
        System.out.println ("***CACHE STATISTICS***");
        System.out.println ("INSTRUCTIONS");
        System.out.println ("accesses: " + Status.getInstructionAccess ());
        System.out.println ("misses: " + Status.getInstructionMissNumber ());
        System.out.printf ("miss rate: %.4f (hit rate %.4f)%n",
                Status.getInstructionAccess () != 0 ?
                        (float)Status.getInstructionMissNumber () /
                                Status.getInstructionAccess () : 0f,
                Status.getInstructionAccess () != 0 ?
                        (float)Status.getInstructionHitNumber () /
                                Status.getInstructionAccess () : 0f);
        System.out.println ("replace: " + Status.getInstructionReplace ());

        System.out.println ("DATA");
        System.out.println ("accesses: " + Status.getDataAccess ());
        System.out.println ("misses: " + Status.getDataMissNumber ());
        System.out.printf ("miss rate: %.4f (hit rate %.4f)%n",
                Status.getDataAccess () != 0 ?
                        (float)Status.getDataMissNumber () /
                                Status.getDataAccess () : 0f,
                Status.getDataAccess () != 0 ?
                        (float)Status.getDataHitNumber () /
                                Status.getDataAccess () : 0f);
        System.out.println ("replace: " + Status.getDataReplace ());

        System.out.println ("TRAFFIC (in words)");
        System.out.println ("demand fetch: " + Status.getDemandFetch () / 4);
        System.out.println ("copies back: " + Status.getCopyBack () / 4);
    }

    /**
     * print result
     */
    public void printResult ()
    {
        if (unifiedOrSeparated == 0)
        {
            printUnifiedHeader ();
            caches[0].clearCache ();
        }
        else
        {
            printSplitHeader ();
            caches[1].clearCache ();
        }

        printBodyResult ();

    }
}
