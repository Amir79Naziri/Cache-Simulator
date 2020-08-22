/**
 * this class represents a node in set of block
 *
 * @author Amir Naziri
 */
public class Node
{
    private CacheBlock cacheBlock;
    private Node previous;
    private Node next;

    /**
     * creates a new node
     * @param cacheBlock cacheBlock
     */
    public Node (CacheBlock cacheBlock)
    {
        this.cacheBlock = cacheBlock;
    }

    /**
     * @return cache block in node
     */
    public CacheBlock getCacheBlock () {
        return cacheBlock;
    }

    /**
     * set next node
     * @param next next node
     */
    public void setNext (Node next) {
        this.next = next;
    }

    /**
     * set previous node
     * @param previous previous node
     */
    public void setPrevious (Node previous) {
        this.previous = previous;
    }

    /**
     * @return next node
     */
    public Node getNext () {
        return next;
    }

    /**
     * @return previous node
     */
    public Node getPrevious () {
        return previous;
    }
}
