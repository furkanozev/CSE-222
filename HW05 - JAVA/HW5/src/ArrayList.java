/***
 * @author Furkan Ozev
 * @since 14-04-2019
 * @param <E>   E - the type of elements held in this collection
 * Homework 5 - 161044036
 */
public class ArrayList<E> {

    private int capacity;

    private int size = 0;
    private E[] arr = null;

    /***
     * Constructs an empty ArrayList.
     */
    public ArrayList() {
        capacity = 32;
        arr = (E[]) new Object[capacity];
    }

    /***
     * Constructor that take capacity.
     * @param x initial capacity (int):
     */
    public ArrayList(int x) {
        capacity = x;
        arr = (E[]) new Object[x];
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

}