import java.util.InputMismatchException;

/**
 * this class represents a data cache
 *
 * @author Amir Naziri
 */
public class DataCache extends Cache
{
    /**
     * creates a new D cache
     * @param size size
     * @param blockSize blockSize
     * @param associativity associativity
     * @param writePolicy writePolicy
     * @param allocationPolicy allocationPolicy
     */
    public DataCache (int size, int blockSize, int associativity,
                      String writePolicy, String allocationPolicy)
    {
        super(size, blockSize, associativity,writePolicy,allocationPolicy);
    }

    @Override
    public void access (String hex, int type)
    {
        if (type == 2)
            throw new InputMismatchException ("Wrong type for Data cache");
        String address = hexToBinary (hex);
        String index = findIndex (address);
        String tag = findTag (address);
        CacheBlock cacheBlock = getListOfCacheBlocks ()
                .get (binaryToDecimal (index)).getCacheBlock (tag);
        switch (type)
        {
            case 0 :
                dataLoad (tag,cacheBlock);
                break;
            case 1 : dataStore (tag,cacheBlock);
                break;
        }
    }
}
