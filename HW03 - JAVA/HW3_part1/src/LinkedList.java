/**
 * @author Furkan Ozev
 * @since 16-03-2019
 * LinkedList Structure.
 * E Generic Type
 */
public class LinkedList<E> {

    private Node<E> head = null;

    /**
     * Inserts the specified element at the beginning of this list.
     * @param data	data - the element to add
     */
    public void addFirst(E data) {
        Node<E> new_node = new Node<E>(data);
        new_node.next = head;
        head = new_node;
    }

    /**
     * Removes and returns the first element from this list.
     * @return	E	the first element from this list
     */
    public E removeFirst() {
        if(head == null) return null;
        Node<E> temp = head;
        head = head.next;
        return temp.data;
    }

    /**
     * Returns true if this list contains no elements.
     * @return	boolean     true if this list contains no elements
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
     * @return E 	the first element of this list, or null if this list is empty
     */
    public E peekFirst()
    {
        return head.data;
    }
}