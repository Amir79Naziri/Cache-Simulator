import java.util.InputMismatchException;

/**
 * this class represents a  instruction cache
 *
 * @author Amir Naziri
 */
public class InstructionCache extends Cache
{
    /**
     * creates a new instruction cache
     * @param size size
     * @param blockSize blockSize
     * @param associativity associativity
     * @param writePolicy writePolicy
     * @param allocationPolicy allocationPolicy
     */
    public InstructionCache (int size, int blockSize, int associativity,
                             String writePolicy, String allocationPolicy)
    {
        super(size, blockSize, associativity, writePolicy, allocationPolicy);
    }

    @Override
    public void access (String hex, int type)
    {
        if (type != 2)
            throw new InputMismatchException ("Wrong type for Instruction cache");
        String address = hexToBinary (hex);
        String index = findIndex (address);
        String tag = findTag (address);
        CacheBlock cacheBlock = getListOfCacheBlocks ()
                .get (binaryToDecimal (index)).getCacheBlock (tag);

        instructionLoad (tag,cacheBlock);
    }
}
