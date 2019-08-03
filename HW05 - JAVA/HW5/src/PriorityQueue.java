import java.util.Comparator;

/***
 * @author Furkan Ozev
 * @since 14-04-2019
 * @param <E>   E - the type of elements held in this collection
 * Homework 5 - 161044036
 */
public class PriorityQueue<E> {

    private ArrayList<E> datas;
    private Comparator<E> comparator = null;

    /***
     * Creates a PriorityQueue with the default initial capacity.
     */
    public PriorityQueue()
    {
        datas = new ArrayList<>();
    }

    /***
     * Creates a PriorityQueue with the specified initial capacity that orders its elements according to the specified comparator.
     * @param i     the initial capacity for this priority queue
     * @param comp     the comparator that will be used to order this priority queue. If null, the natural ordering of the elements will be used.
     */
    public PriorityQueue(int i, Comparator<E> comp) {
        if(i>1)
        {
            datas = new ArrayList<E>(i);
            comparator = comp;
        }
    }

    /***
     * Inserts the specified element into this priority queue.
     * @param item  the element to add
     * @return  true
     * @throws NullPointerException if the item to be inserted is null.
     */
    public synchronized boolean offer(E item) throws NullPointerException{
        datas.add(item);
        int child = datas.size() - 1;
        int parent = (child - 1) / 2;
        while (parent >= 0 && compare(datas.get(parent), datas.get(child)) > 0) {
            swap(parent, child);
            child = parent;
            parent = (child - 1) / 2;
        }
        notify();
        return true;
    }

    /***
     * Inserts the specified element into this priority queue.
     * @param item  the element to add
     * @return  true
     */
    public boolean add(E item)
    {
        return offer(item);
    }

    /***
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     * @return  the head of this queue, or null if this queue is empty
     * @throws InterruptedException     wait() method may throw exception.
     */
    public synchronized E poll() throws InterruptedException {
        if (datas.isEmpty())
        {
            wait();
        }
        E result = datas.get(0);

        if (datas.size() == 1) {
            datas.remove(0);
            return result;
        }

        datas.set(0, datas.remove(datas.size() - 1));

        int parent = 0;
        int flag = 0;
        while (flag == 0) {
            int leftChild = 2 * parent + 1;
            if (leftChild >= datas.size()) {
                flag = -1;
            } else {
                int rightChild = leftChild + 1;
                int minChild = leftChild;

                if (rightChild < datas.size() && compare(datas.get(leftChild), datas.get(rightChild)) > 0) {
                    minChild = rightChild;
                }

                if (compare(datas.get(parent), datas.get(minChild)) > 0) {
                    swap(parent, minChild);
                    parent = minChild;
                } else {
                    flag = -1;
                }
            }
        }
        return result;
    }

    /**
     @pre: If comparator is null, left and right implement Comparable<E>.
     @param left One item
     @param right The other item
     @return Negative int if left less than right,
     0 if left equals right,
     positive int if left > right
     @throws ClassCastException if items are not Comparable
     */

    /***
     * Compare two items using either a Comparator object's compare method
            or their natural ordering using method compareTo.
     * @param left      One item
     * @param right     The other item
     * @return      Negative int if left less than right,
        0 if left equals right, positive int if left > right
     */
    private int compare(E left, E right) {
        if (comparator != null)
        {
            return comparator.compare(left, right);
        }
        else
        {
            return ((Comparable<E>) left).compareTo(right);
        }
    }

    /***
     * Swap the elements.
     * @param p     First element
     * @param c     Second element
     */
    private void swap(int p, int c)
    {
        E temp = datas.get(p);
        datas.set(p,datas.get(c));
        datas.set(c,temp);
    }

    /***
     * Get size
     * @return  size (int)
     */
    public int size()
    {
        return datas.size();
    }

    /***
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     * @return  the head of this queue, or null if this queue is empty.
     */
    public E peek()
    {
        if(datas.isEmpty())   return null;
        return datas.get(0);
    }

}