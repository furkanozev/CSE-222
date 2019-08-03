/**
 * @author Furkan Ozev
 * @since 16-03-2019
 * Node Class
 * E Generic Type
 */
public class Node<E> {

    public E data;
    public Node<E> next = null;

    /**
     * Node Class's Constructor
     * @param data2	Take Data (E)
     */
    public Node (E data2) {
        data = data2;
    }
}