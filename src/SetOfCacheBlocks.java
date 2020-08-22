/**
 * this class represents a set in LRU replacement
 *
 * @author Amir Naziri
 */
public class SetOfCacheBlocks
{
    private Node last; // most recently node
    private Node first; // least recently node
    private Node[] elements; // list of nodes

    /**
     * creates a new set
     * @param size size of set
     */
    public SetOfCacheBlocks (int size)
    {
        elements = new Node[size];
        for (int i = 0;i < size; i++)
        {
            elements[i] = new Node(new CacheBlock ());
            add (elements[i]);
        }
    }

    /**
     * @param tag tag
     * @return cache block which is match with tag , OW is the least recently node
     */
    public CacheBlock getCacheBlock (String tag)
    {
        CacheBlock cacheBlock;
        for (Node node : elements)
            if (node.getCacheBlock ().isTagEquals (tag))
            {
                cacheBlock = node.getCacheBlock ();
                remove (node);
                add (node);
                return cacheBlock;
            }
        cacheBlock = first.getCacheBlock ();
        Node node = first;
        remove (node);
        add (node);
        return cacheBlock;
    }

    /**
     * remove a node from list
     * @param node node
     */
    private void remove (Node node)
    {
        if (node.getPrevious () != null)
            node.getPrevious ().setNext (node.getNext ());
        else
            first = node.getNext ();

        if (node.getNext () != null)
            node.getNext ().setPrevious (node.getPrevious ());
        else
            last = node.getPrevious ();
    }

    /**
     * add a node to list
     * @param node node
     */
    private void add (Node node)
    {
        if (last != null)
            last.setNext (node);

        node.setPrevious (last);
        node.setNext (null);
        last = node;

        if (first == null)
            first = last;
    }

    /**
     * @return number of dirty bits
     */
    public int numOfDirty ()
    {
        int counter = 0;
        for (Node node : elements) {
            if (node.getCacheBlock ().isDirty ())
                counter++;
        }
        return counter;
    }
}
