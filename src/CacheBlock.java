/**
 * this class represents cache block
 *
 * @author Amir Naziri
 */
public class CacheBlock
{
    private String tag;
    private boolean valid; // valid bit
    private boolean dirty; // dirty bit

    /**
     * creates a new cache block
     */
    public CacheBlock ()
    {
        tag = null;
        valid = false;
        dirty = false;
    }

    /**
     * is Tag equals with input tag
     * @param tag tag
     * @return result
     */
    public boolean isTagEquals (String tag)
    {
        return isValid () && this.tag.equals (tag);
    }

    /**
     * set Tag
     * @param tag new Tag
     */
    public void setTag (String tag) {
        this.tag = tag;
        setValid (true);
    }

    /**
     * set Valid bit
     * @param valid  valid :    0 : false       1 : true
     */
    public void setValid (boolean valid) {
        this.valid = valid;
    }

    /**
     * @return is block valid
     */
    public boolean isValid () {
        return valid;
    }

    /**
     * set dirty bit
     * @param dirty  dirty : true       clean : false
     */
    public void setDirty (boolean dirty) {
        this.dirty = dirty;
    }

    /**
     * @return is block dirty
     */
    public boolean isDirty () {
        return dirty;
    }
}
