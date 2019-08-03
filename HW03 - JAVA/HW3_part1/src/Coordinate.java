/**
 * @author Furkan Ozev
 * @since 16-03-2019
 * Coordinate class to keep row , column and value.
 */
public class Coordinate {
    private int val;
    private int row;
    private int column;

    /**
     * Constructor that take value, row and column.
     * @param val2		value(int)
     * @param row2		row(int)
     * @param column2	column(int)
     */
    Coordinate(int val2, int row2, int column2)
    {
        val = val2;
        row = row2;
        column = column2;
    }

    /**
     * Get Value
     * @return	val		value(int)
     */
    public int get_val(){
        return val;
    }

    /**
     * Get Column
     * @return	row		row(int)
     */
    public int get_row(){
        return row;
    }
    /**
     * Get Column
     * @return	column	column(int)
     */
    public int get_column(){
        return column;
    }

    /**
     * Set Value
     * @param val2		value(int)
     */
    public void set_val(int val2)
    {
        val = val2;
    }
}
