import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

/***
 * @author Furkan Ozev
 * @since 21-04-2019
 * Homework 6 - 161044036
 */
public class Word_Map implements Map, Iterable
{

    final static int INITCAP=10;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    final static float LOADFACT = 0.75f;
    private Node table[];

    private int numKeys = 0;
    private int numDeletes = 0;
    private final Node DELETED = new Node(null, null);
    private Node head = null;
    private Node last = null;

    /***
     * Constructor no parameter.
     */
    public Word_Map() {
        this.table = new Node[INITCAP];
    }

    /***
     * Get iterator of Word_Map.
     * @return  iterator
     */
    @Override
    public Iterator iterator() {
        Iterator iter = new Iterator<>()
        {
            private Node cursor = head;
            private Node temp = null;

            public boolean hasNext()
            {
                return cursor != null;
            }

            public Node next()
            {
                if(hasNext())
                {
                    Node data = cursor;
                    temp = cursor;
                    cursor = cursor.next;
                    return data;
                }
                else return null;
            }
        };
        return iter;
    }

    static class Node
    {
        private final String key;
        private Map value;
        private Node next = null;

        /***
         * Node constructor
         * @param key - String  key of Node(Entry)
         */
        public Node(String key)
        {
            this.key = key;
            value = new File_Map();
        }
        /***
         * Node constructor
         * @param key - String  key of Node(Entry)
         * @param value - Map  value of Node(Entry)
         */
        public Node(String key, Map value) {
            this.key = key;
            this.value = value;
        }

        /***
         * Get key of Node(Entry)
         * @return  String  -   key of Node(Entry)
         */
        public String getKey() {
            return key;
        }
        /***
         * Get value of Node(Entry)
         * @return  Map  -   value of Node(Entry)
         */
        public Map getValue() {
            return value;
        }
        /***
         * Set value of Node(Entry)
         * @return  Map  -   old value of Node(Entry)
         */
        public Map setValue(Map val) {
            Map oldVal = value;
            value.putAll((File_Map)val);
            return oldVal;
        }
    }

    /***
     * Returns the number of key-value mappings in this map.
     * @return int  the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return numKeys;
    }

    /***
     * Returns true if this map contains no key-value mappings.
     * @return boolean true if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        if(numKeys == 0) return true;
        else return false;
    }

    /***
     * Returns true if this map contains a mapping for the specified key.
     * @param key   -   The key whose presence in this map is to be tested
     * @return boolean true if this map contains a mapping for the specified key.
     */
    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsKey(Object key)
    {
        int index = find(key);
        if (table[index] != null)
            return true;
        else
            return false;
    }

    /***
     * Returns true if this map maps one or more keys to the specified value.
     * @param value -   value whose presence in this map is to be tested
     * @return boolean true if this map maps one or more keys to the specified value
     */
    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value)
    {
        Node temp = head;
        Map v = (Map) value;
        while(temp != null && !(temp.getValue().equals(v)))
        {
            temp = temp.next;
        }
        if(temp != null) return true;
        else return false;
    }

    /***
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key   -   the key whose associated value is to be returned
     * @return boolean the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    @Override
    public Object get(Object key)
    {
        int index = find(key);
        if (table[index] != null)
            return table[index].getValue();
        else
            return null;
    }

    /***
     * Associates the specified value with the specified key in this map.
     If the map previously contained a mapping for the key, the old value is replaced.
     * @param key   - key with which the specified value is to be associated
     * @param value - value to be associated with the specified key
     * @return Object the previous value associated with key, or null if there was no mapping for key.
     */
    @Override
    /*
    Use linear probing in case of collision
    * */
    public Object put(Object key, Object value) {
        int index = find(key);

        if (table[index] == null)
        {
            table[index] = new Node((String)key, (Map)value);
            if(numKeys == 0)
            {
                head = table[index];
            }
            else
            {
                last.next = table[index];
            }
            last = table[index];
            numKeys++;
            double loadFactor =(double) (numKeys + numDeletes) / CURRCAP;
            if (loadFactor > LOADFACT)
            {
                rehash();
            }
            return null;
        }

        Map oldVal = table[index].getValue();
        table[index].setValue((Map)value);
        return oldVal;
    }

    /***
     * Removes the mapping for the specified key from this map if present.
     * @param key - key whose mapping is to be removed from the map
     * @return  Object  the previous value associated with key, or null if there was no mapping for key.
     */
    @Override
    /*You do not need to implement remove function
     * */
    public Object remove(Object key) {
        int index = find(key);
        if(table[index] == null)
            return null;
        else
        {
            table[index] = DELETED;
            numDeletes++;
            numKeys--;
            Node temp = head;
            Node temp2 = null;
            while(temp != null && (!key.equals(table[index].getKey())))
            {
                temp = temp.next;
                temp2 = temp;
            }
            if(temp == null)
            {
                //System.out.println("\nNot Removed");
                return null;
            }
            else
            {
                if(temp2 == null)
                {
                    Node keep = head;
                    head = head.next;
                    return keep;
                }
                else
                {
                    temp2.next = temp.next;
                    return temp;
                }
            }
        }
    }

    /***
     * Copies all of the mappings from the specified map to this map.
     These mappings will replace any mappings that this map had for any of the keys currently in the specified map.
     * @param m - mappings to be stored in this map
     */
    @Override
    public void putAll(Map m)
    {
        final Word_Map map = (Word_Map) m;
        final Iterator it = map.iterator();
        Node element;

        while(it.hasNext())
        {
            element = (Node)it.next();
            put((String)element.getKey(),(Map)element.getValue());
        }
    }

    /***
     * Removes all of the mappings from this map. The map will be empty after this call returns.
     */
    @Override
    public void clear()
    {
        for(int i=0; i < CURRCAP; i++)
        {
            table[i] = null;
        }
        numKeys = 0;
        numDeletes = 0;

        head = null;
        last = null;
    }

    /***
     * Returns a Set view of the keys contained in this map.
     * @return  Set -  a set view of the keys contained in this map
     */
    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set keySet() {
        HashSet<String> keys = new HashSet<>();
        Node temp = head;
        while(temp != null)
        {
            keys.add(temp.getKey());
            temp = temp.next;
        }
        return keys;
    }

    /***
     * Returns a Collection view of the values contained in this map.
     * @return  Collection(ArrayList) - a view of the values contained in this map
     */
    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values() {
        ArrayList<File_Map> values = new ArrayList<File_Map>();
        Node temp = head;
        while(temp != null)
        {
            values.add((File_Map) temp.getValue());
            temp = temp.next;
        }
        return values;
    }

    /***
     * Returns a Set view of the mappings contained in this map.
     * @return  Set -   a set view of the mappings contained in this map
     */
    @Override
    /*You do not need to implement entrySet function
     * */
    public Set<Entry> entrySet() {
        Set<Entry> entryset = new HashSet<Entry>();
        Node temp = head;
        Entry obj;
        while(temp != null)
        {
            final String t1 = (String) temp.getKey();
            final File_Map t2 = (File_Map) temp.getValue();
            obj = new Entry() {
                @Override
                public Object getKey() {
                    return t1;
                }

                @Override
                public Object getValue() {
                    return t2;
                }

                @Override
                public Object setValue(Object value) {
                    int index = find((String)t1);
                    Map oldVal = t2;
                    table[index].setValue((File_Map)value);
                    return oldVal;

                }

                @Override
                public String toString() {
                    return "\n\nKey: " + getKey() + "\nValue: " + getValue() + "\n";
                }
            };
            entryset.add(obj);
            temp = temp.next;
        }
        return entryset;
    }

    /** Finds either the target key or the first empty slot in the
     search chain using linear probing.
     @param key The key of the target object
     @return The position of the target or the first empty slot if
     the target is not in the table.
     */
    private int find(Object key)
    {
        int index = key.hashCode() % CURRCAP;
        if (index < 0)
        {
            index += CURRCAP;
        }

        while ((table[index] != null) && (!key.equals(table[index].getKey())))
        {
            index++;
            if (index >= CURRCAP)
            {
                index = 0;
            }
        }
        return index;
    }

    /****
     * Rehash the table.
     */
    private void rehash()
    {
        Node[] oldTable = table;
        CURRCAP = 2 * CURRCAP + 1;
        table = new Node[CURRCAP];

        numKeys = 0;
        numDeletes = 0;

        Node temp = head;

        while(temp != null)
        {
            put((String)temp.getKey(),(Map)temp.getValue());
            temp = temp.next;
        }
    }

    /***
     * Wrapper FileMap to NLP
     * @param fname String  filename
     * @param i int -   position of word
     * @return  File_Map object
     */
    public File_Map wrapperFileMap(String fname, int i)
    {
        File_Map fmap = new File_Map();
        ArrayList <Integer> arrlist = new ArrayList<Integer>();
        arrlist.add(i);
        fmap.put(fname,arrlist);
        return fmap;
    }

    /***
     * Returns a string representation of this map.
     * @return  String  -   a string representation of this map.
     */
    @Override
    public String toString() {
        Iterator iter = iterator();
        String str = "\n";
        Node node;
        File_Map fmap;
        while (iter.hasNext())
        {
            node = (Word_Map.Node) iter.next();
            fmap = (File_Map) node.getValue();
            str += ("Word: " + node.getKey() + "\n");
            for(int i = 0; i < fmap.size(); i++)
            {
                str += ("\t\t File name: " + fmap.fnames.get(i) + ", Positions: " + fmap.occurances.get(i) + "\n");
            }
            str += "\n";
        }
        return str;
    }
}
