import java.util.*;

/***
 * @author Furkan Ozev
 * @since 21-04-2019
 * Homework 6 - 161044036
 */
public class File_Map implements Map
{
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    Your should provide and explain the complexities for each method in your report.
    * */
    public ArrayList<String> fnames;
    public ArrayList<List<Integer>> occurances;

    /***
     * Constructor no parameter.
     */
    public File_Map()
    {
        fnames = new ArrayList<String>();
        occurances = new ArrayList<List<Integer>>();
    }

    /***
     * Returns the number of key-value mappings in this map.
     * @return int  the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return fnames.size();
    }

    /***
     * Returns true if this map contains no key-value mappings.
     * @return boolean true if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return fnames.isEmpty();
    }

    /***
     * Returns true if this map contains a mapping for the specified key.
     * @param key   -   The key whose presence in this map is to be tested
     * @return boolean true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(Object key) {
        return fnames.contains((String)key);
    }

    /***
     * Returns true if this map maps one or more keys to the specified value.
     * @param value -   value whose presence in this map is to be tested
     * @return boolean true if this map maps one or more keys to the specified value
     */
    @Override
    public boolean containsValue(Object value) {
        return occurances.contains((List<Integer>)value);
    }

    /***
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key   -   the key whose associated value is to be returned
     * @return boolean the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    @Override
    public Object get(Object key) {
        int index;
        if (containsKey((String)key)) {
            index = fnames.indexOf(key);
            return occurances.get(index);
        }
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
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value) {
        if(!containsKey((String)key))
        {
            fnames.add((String)key);
            occurances.add((List<Integer>)value);
            return value;
        }
        else
        {
            int index = fnames.indexOf((String)key);
            List<Integer> old = occurances.get(index);
            List<Integer> keep = occurances.get(index);

            value = (List<Integer>) value;
            Iterator iter = ((List<Integer>) value).iterator();
            int temp;
            while(iter.hasNext())
            {
                temp = (int) iter.next();
                if(!old.contains((int)temp))
                {
                    keep.add(temp);
                }
            }
            occurances.set(index,(List<Integer>)keep);
            return old;
        }
    }

    /***
     * Removes the mapping for the specified key from this map if present.
     * @param key - key whose mapping is to be removed from the map
     * @return  Object  the previous value associated with key, or null if there was no mapping for key.
     */
    @Override
    public Object remove(Object key) {
        if(fnames.contains((String)key));
        {
            int index = fnames.indexOf((String) key);
            occurances.remove(index);
            return fnames.remove(index);
        }
    }

    /***
     * Copies all of the mappings from the specified map to this map.
     These mappings will replace any mappings that this map had for any of the keys currently in the specified map.
     * @param m - mappings to be stored in this map
     */
    @Override
    public void putAll(Map m) {
        Set<Entry> entries = m.entrySet();
        Iterator iter = entries.iterator();
        Entry temp;
        while(iter.hasNext())
        {
            temp = (Entry) iter.next();
            put(temp.getKey() , temp.getValue());
        }

    }

    /***
     * Removes all of the mappings from this map. The map will be empty after this call returns.
     */
    @Override
    public void clear() {
        fnames.clear();
        occurances.clear();
    }

    /***
     * Returns a Set view of the keys contained in this map.
     * @return  Set -  a set view of the keys contained in this map
     */
    @Override
    public Set keySet() {
        Set keyset = new HashSet();
        Iterator iter = fnames.iterator();
        String temp;
        while(iter.hasNext())
        {
            temp = (String) iter.next();
            keyset.add(temp);
        }
        return keyset;
    }

    /***
     * Returns a Collection view of the values contained in this map.
     * @return  Collection(ArrayList) - a view of the values contained in this map
     */
    @Override
    public Collection values() {
        return occurances;
    }

    /***
     * Returns a Set view of the mappings contained in this map.
     * @return  Set -   a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry> entrySet() {
        Set<Entry> entryset = new HashSet<Entry>();
        Iterator iter = fnames.iterator();
        Iterator iter2 = occurances.iterator();
        Entry obj;
        while(iter.hasNext())
        {
            final String t1 = (String)iter.next();
            final List<Integer> t2 = (List<Integer>)iter2.next();
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
                    return t2.set(fnames.indexOf(t1),(Integer)value);
                }

                @Override
                public String toString() {
                    return "\n\nKey: " + getKey() + "\nValue: " + getValue() + "\n";
                }
            };
            entryset.add(obj);
        }
        return entryset;

    }

    /***
     * Compares the specified object with this map for equality.
     * @param obj   -   object to be compared for equality with this map
     * @return  boolean -   true if the specified object is equal to this map
     */
    @Override
    public boolean equals(Object obj) {
        obj = (File_Map)obj;
        return (fnames.containsAll(((File_Map) obj).fnames) && occurances.containsAll(((File_Map) obj).occurances));
    }

    /***
     * Returns a string representation of this map.
     * @return  String  -   a string representation of this map.
     */
    @Override
    public String toString() {
        String str = new String();
        for(int i = 0; i < size(); i++)
        {
            str += "\n\t\t File name: " + fnames.get(i) + " => Positions: " + occurances.get(i);
        }
        str += '\n';
        return str;
    }
}
