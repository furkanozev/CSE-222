/**
 * @author Furkan Ozev
 * @since 16-03-2019
 * Pair Class to keep variables and their values.
 */
public class Pair {
    private String[] var = new String[5];
    private String[] val = new String[5];
    private int size = 0;
    private int capacity = 5;

    /***
     * Add new variable and value.
     * @param a String  variable
     * @param b String  value
     */
    public void add(String a, String b)
    {
        if(size < capacity)
        {
            var[size] = a;
            val[size] = b;
            size++;
        }
        else
        {
            String[] new_var = new String[capacity*2];
            String[] new_val = new String[capacity*2];

            for (int i = 0; i <capacity; i++) {
                new_var[i] = var[i];
                new_var[i] = val[i];
            }
            new_var[size] = a;
            new_val[size] = b;
            size++;
            capacity *= 2;
        }
    }

    /***
     * Get index of variable
     * @param a     (String) variable
     * @return index    (int)
     */
    public int get_index(String a)
    {
        int i;
        for(i=0 ; i<size && a.compareTo(var[i]) != 0 ; i++);

        if(i < size) return i;
        else return -1;
    }

    /***
     * Get variable
     * @param i     index of variable
     * @return String   variable
     */
    public String get_var(int i)
    {
        return var[i];
    }

    /***
     * Get Value
     * @param i     index of variable
     * @return String   variable's value.
     */
    public String get_val(int i)
    {
        return val[i];
    }
}
