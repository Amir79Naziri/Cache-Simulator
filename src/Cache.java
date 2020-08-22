import java.util.HashMap;

/**
 * this class represents Cache unified
 *
 * @author Amir Naziri
 */
public class Cache
{
    private int size; // size of cache
    private int blockSize;
    private String writePolicy;
    private String allocationPolicy;
    private int numOfOffset;
    private int numOfIndex;

    private HashMap<Long,SetOfCacheBlocks> listOfCacheBlocks; // list of cache block set

    /**
     * creates a new cache
     * @param size size
     * @param blockSize blockSize
     * @param associativity associativity
     * @param writePolicy writePolicy
     * @param allocationPolicy allocationPolicy
     */
    public Cache (int size, int blockSize, int associativity,
                  String writePolicy, String allocationPolicy)
    {
        this.size = size;
        this.blockSize = blockSize;
        this.writePolicy = writePolicy;
        this.allocationPolicy = allocationPolicy;
        int numOfBlocks = size / (blockSize * associativity);
        listOfCacheBlocks = new HashMap<> (numOfBlocks);
        numOfOffset = (int) (Math.log10 (blockSize)/Math.log10 (2));
        numOfIndex = (int) (Math.log10 (numOfBlocks)/Math.log10 (2));


        for (long i = 0; i < numOfBlocks; i++)
        {
            listOfCacheBlocks.put (i,new SetOfCacheBlocks (associativity));
        }


    }

    /**
     * access cache
     * @param hex address
     * @param type type : 0 , 1 , 2
     */
    public void access (String hex, int type)
    {
        String address = hexToBinary (hex);
        String index = findIndex (address);
        String tag = findTag (address);
        CacheBlock cacheBlock =
                getListOfCacheBlocks ().get (binaryToDecimal (index)).getCacheBlock (tag);

        switch (type)
        {
            case 0 :
                dataLoad (tag,cacheBlock);
                break;
            case 1 :
                dataStore (tag,cacheBlock);
                break;
            case 2 :
                instructionLoad (tag,cacheBlock);
        }
    }

    /**
     * load data
     * @param tag tag
     * @param cacheBlock cache block
     */
    public void dataLoad (String tag, CacheBlock cacheBlock)
    {
        Status.increaseDataAccess ();
        if (cacheBlock.isTagEquals (tag))
        {
            Status.increaseDataHitNumber ();
        } else
        {
            Status.increaseDataMissNumber ();
            Status.increaseDemandFetch (blockSize);
            if (cacheBlock.isValid ())
                Status.increaseDataReplace ();

            if (writePolicy.equals ("wb"))
            {
                if (cacheBlock.isDirty ())
                {
                    Status.increaseCopyBack (blockSize);

                    cacheBlock.setTag (tag);
                    cacheBlock.setDirty (false);
                }
                else
                {
                    cacheBlock.setTag (tag);
                }
            }
            else
            {
                cacheBlock.setTag (tag);
            }
        }
    }

    /**
     * store data
     * @param tag tag
     * @param cacheBlock cache block
     */
    public void dataStore (String tag, CacheBlock cacheBlock)
    {
        Status.increaseDataAccess ();
        if (cacheBlock.isTagEquals (tag))
        {
            Status.increaseDataHitNumber ();

            if (getWritePolicy ().equals ("wb"))
            {
                // data updated

                cacheBlock.setDirty (true);
            }
            else
            {
                // data updated

                Status.increaseCopyBack (4);
            }
        }
        else
        {
            Status.increaseDataMissNumber ();

            if (getWritePolicy ().equals ("wb"))
            {
                if (getAllocationPolicy ().equals ("wa"))
                {
                    Status.increaseDemandFetch (blockSize);
                    // change data

                    if (cacheBlock.isDirty ())
                    {
                        Status.increaseCopyBack (blockSize);
                    }
                    if (cacheBlock.isValid ())
                        Status.increaseDataReplace ();
                    cacheBlock.setTag (tag);
                    cacheBlock.setDirty (true);

                }
                else
                {
                    Status.increaseCopyBack (4);
                }
            }
            else
            {
                if (getAllocationPolicy ().equals ("wa"))
                {
                    Status.increaseCopyBack (4);
                    Status.increaseDemandFetch (blockSize);

                    // change
                    if (cacheBlock.isValid ())
                        Status.increaseDataReplace ();
                    cacheBlock.setTag (tag);
                }
                else
                {
                    Status.increaseCopyBack (4);
                }
            }

        }
    }

    /**
     * instruction load
     * @param tag tag
     * @param cacheBlock cache block
     */
    public void instructionLoad (String tag, CacheBlock cacheBlock)
    {
        Status.increaseInstructionAccess ();
        if (cacheBlock.isTagEquals (tag))
        {
            Status.increaseInstructionHitNumber ();
        } else
        {
            Status.increaseInstructionMissNumber ();
            Status.increaseDemandFetch (blockSize);
            if (cacheBlock.isValid ())
                Status.increaseInstructionReplace ();
            cacheBlock.setTag (tag);
        }
    }

    /**
     * convert binary to decimal
     * @param bin binary
     * @return decimal number
     */
    public Long binaryToDecimal (String bin)
    {
        if (bin.equals (""))
            return 0L;
        return Long.parseLong (bin,2);
    }

    /**
     * convert hex to binary
     * @param hex hex
     * @return binary number
     */
    public String hexToBinary (String hex)
    {
        long dec = Long.parseLong (hex,16);
        StringBuilder res = new StringBuilder (Long.toBinaryString (dec));
        int len = res.length ();
        if (len < 32)
        {
            for (int i = 0; i < 32 - len; i++)
                res.insert (0, "0");
        }
        return res.toString ();
    }

    /**
     * clear all dirty blocks and copy them to memory
     */
    public void clearCache ()
    {
        for (long index : getListOfCacheBlocks ().keySet ())
            Status.increaseCopyBack
                    (getListOfCacheBlocks ().get (index).numOfDirty () * blockSize);
    }

    /**
     * @return size of cache
     */
    public int getSize () {
        return size;
    }

    /**
     * @return allocationPolicy
     */
    public String getAllocationPolicy () {
        return allocationPolicy;
    }

    /**
     * @return writePolicy
     */
    public String getWritePolicy () {
        return writePolicy;
    }

    /**
     * @return numOfIndex bits
     */
    public int getNumOfIndex ()
    {
        return numOfIndex;
    }

    /**
     * @return numOfOffset bits
     */
    public int getNumOfOffset ()
    {
        return numOfOffset;
    }

    /**
     * find index in address
     * @param address address
     * @return index
     */
    public String findIndex (String address)
    {
        return address.
                substring (32 - getNumOfOffset () - getNumOfIndex (),32 - getNumOfOffset ());
    }

    /**
     * find tag in address
     * @param address address
     * @return tag
     */
    public String findTag (String address)
    {
        return address.
                substring (0,32 - getNumOfOffset () - getNumOfIndex ());
    }

    /**
     * @return listOfCacheBlocks
     */
    public HashMap<Long, SetOfCacheBlocks> getListOfCacheBlocks () {
        return listOfCacheBlocks;
    }
}
