/**
 * @author Furkan Ozev
 * @since 16-03-2019
 * Stack Structure.
 * E Generic Type
 */
public class Stack<E> {

    LinkedList<E> list = new LinkedList<E>();

    /**
     * Pushes an item onto the top of this stack.
     * @param data	data-the data to be pushed onto this stack. (E)
     * @return data the data argument. (E)
     */
    public E push(E data)
    {
        list.addFirst(data);
        return data;
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function.
     * @return E	The object at the top of this stack.
     */
    public E pop()
    {
        return list.removeFirst();
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     * @return E	The object at the top of this stack.
     */
    public E peek() {
        return list.peekFirst();
    }

    /**
     * Tests if this stack is empty.
     * @return boolean 		true if and only if this stack contains no items; false otherwise.
     */
    public boolean empty() {
        return list.isEmpty();
    }
}