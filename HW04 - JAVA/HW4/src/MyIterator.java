import java.util.Iterator;

/***
 *  @author Furkan Ozev
 *  @since 30-03-2019
 *  @param <E> Generic type
 *  Homework 4 - 161044036
 */
public class MyIterator<E> implements Iterator<E> {
    private ArrayList<E> list;
    private int cursor = 0;
    private E[] arr = null;
    private int total = 0;

    /***
     * Constructor of MyIterator.
     * @param list_ Take an ArrayList to create iterator
     */
    public MyIterator(ArrayList<E> list_)
    {
        list = list_;
        if(list.flag() == 1)
        {
            int r = list.getData().length;
            int c = list.getData()[0].length;
            int x = 0;
            int y = 0;
            total = r*c;
            recursive(r,c,x,y,0,0,1,list.getData());
        }
        arr = list.getArr();
    }

    /****
     * This will traverse a given 2D array spirally clockwise starting at the top left element.
     * Adds elements to the list while spiraling.
     * @param r     row(int)
     * @param c     column(int)
     * @param x     x (int)
     * @param y     y (int)
     * @param i     index(int)
     * @param f     flag(int) : number of rounds
     * @param d     direction (int)
     * @param data  2D Generic array.
     */
    private void recursive(int r, int c, int x, int y, int i, int f, int d, E[][] data)
    {
        if(i == total)
        {
            return ;
        }
        else if(d == 1)
        {

            if(y < c)
            {
                list.add(data[x][y]);
                recursive(r,c,x,y+1,i+1,f,1, data);
            }
            else recursive(r,c,x+1,y-1,i,f,2, data);
        }
        else if(d == 2)
        {
            if(x < r)
            {
                list.add(data[x][y]);
                recursive(r,c,x+1,y,i+1,f,2, data);
            }
            else recursive(r,c,x-1,y-1,i,f,3, data);
        }
        else if(d == 3)
        {
            if(y >= f)
            {
                list.add(data[x][y]);
                recursive(r,c,x,y-1,i+1,f,3, data);
            }
            else recursive(r,c,x-1,y+1,i,f,4, data);
        }
        else if(d == 4)
        {
            if(x > f)
            {
                list.add(data[x][y]);
                recursive(r,c,x-1,y,i+1,f,4, data);
            }
            else recursive(r-1,c-1,f+1,f+1,i,f+1,1, data);
        }
    }

    /***
     * Returns true if the iteration has more elements.
     * @return boolean 	true or false
     */
    public boolean hasNext() {
        return cursor < list.size();
    }

    /***
     * Returns the next element in the iteration and advances the iterator.
     * @return next element ( E )
     */
    public E next() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        return arr[cursor++];
    }
}
