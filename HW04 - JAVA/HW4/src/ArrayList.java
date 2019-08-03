import java.util.Iterator;

/***
 *  @author Furkan Ozev
 *  @since 30-03-2019
 *  @param <E> Generic type
 *  Homework 4 - 161044036
 */
public class ArrayList<E> implements Iterable<E> {

    private static int capacity = 10;

    private int size = 0;
    private E[] arr = null;
    private E[][] data_ARR = null;
    private int flag = 0;

    /***
     * Constructs an empty ArrayList.
     */
    public ArrayList() {
        size = 0;
        arr = (E[]) new Object[capacity];
    }

    /***
     * Constructs an ArrayList that are taken an 2D array that keep elements.
     * @param data      2D generic array
     */
    public ArrayList(E[][] data) {
        size = 0;
        arr = (E[]) new Object[capacity];
        data_ARR = data;
        flag = 1;
    }

    /***
     * Increases the capacity of this ArrayList
     * @param newCapacity   integer new capacity
     */
    private void setNewCapacity(int newCapacity) {
        if (newCapacity < size) return;

        E[] temp = arr;
        arr = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            arr[i] = temp[i];
        }
        capacity = newCapacity;
    }

    /***
     * Returns the number of components in this ArrayList.
     * @return size     (integer) the number of components in this ArrayList.
     */
    public int size() {
        return size;
    }

    /***
     * Tests if this ArrayList has no components.
     * @return  boolean     true if this ArrayList has no components; false otherwis
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /***
     * Returns the element at the specified position in this ArrayList.
     * @param index - index of element to return.
     * @return  the element at the specified position in this ArrayList.
     * @throws IndexOutOfBoundsException - index is out of range
     */
    public E get(int index)throws  ArrayIndexOutOfBoundsException{
        if (index < 0 || index >= size) throw new ArrayIndexOutOfBoundsException();
        else return arr[index];
    }

    /***
     * Replaces the element at the specified position in this ArrayList with the specified element.
     * @param index - index of element to replace.
     * @param val - element to be stored at the specified position.
     * @return (E) the element previously at the specified position.
     * @throws IndexOutOfBoundsException - index out of range
     */
    public E set(int index, E val)throws  ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new ArrayIndexOutOfBoundsException();
        else {
            E temp = arr[index];
            arr[index] = val;
            return temp;
        }
    }

    /***
     * Inserts the specified element at the specified position in this ArrayList.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     * @param index  - index at which the specified element is to be inserted.
     * @param element - element to be inserted.
     */
    public void add(int index, E element) {
        if (capacity == size) setNewCapacity(size * 2);
        for (int i = index; i < size; i++) {
            arr[i + 1] = arr[i];
        }
        arr[index] = element;
        size++;
    }

    /***
     * Appends the specified element to the end of this ArrayList.
     * @param element - element to be appended to this ArrayList.
     * @return true (as per the general contract of Collection.add).
     */
    public boolean add(E element) {
        if (capacity == size) setNewCapacity(size * 2);
        arr[size] = element;
        size++;
        return true;
    }

    /***
     * Removes the element at the specified position in this ArrayList.
     * Shifts any subsequent elements to the left (subtracts one from their indices)
     * @param index  - the index of the element to removed.
     * @return the element that was removed from the ArrayList.
     * @throws IndexOutOfBoundsException - index out of range
     */
    public E remove(int index)throws  ArrayIndexOutOfBoundsException {
        if(index <0 || index >= size)  throw new ArrayIndexOutOfBoundsException();
        E item = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return item;
    }

    /***
     * Get array that are in ArrayList
     * @return  E[] array
     */
    public E[] getArr()
    {
        return arr;
    }

    /***
     * Get data (2D Array)
     * @return  data
     */
    public E[][] getData()
    {
        return data_ARR;
    }

    /***
     * Get flag
     * @return      flag integer
     */
    public int flag()
    {
        return flag;
    }

    /***
     * Returns an Iterator of ArrayList.
     * @return an Iterator over the elements in this List in proper sequence.
     */
    public Iterator<E> iterator() {
        return new MyIterator<E>(this);
    }
}