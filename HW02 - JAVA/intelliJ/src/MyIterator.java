import java.util.Iterator;

/**
 * @author Furkan Ozev
 * @since 08-03-2019
 * Iterator of the ExperimentList.
 */
public class MyIterator implements Iterator<Experiment> {

    /**
     * 	- list (ExperimentList): Keep list of experiment
     *	- cursor (Node): Cursor to track experiments.
     */
    private ExperimentList list;
    private Node cursor = null;

    /**
     * Constructor of Iterator that taken ExperimentList.
     * @param obj	ExperimentList to create Iterator.
     */
    MyIterator(ExperimentList obj)
    {
        list = obj;
        cursor = obj.gethead();
    }

    /*
     * Returns true if the iteration has more experiment.
     * @return boolean 	true or false
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext()
    {
        return cursor != null;
    }

    /*
     * Returns the next experiment in the iteration and advances the iterator.
     * @return data 	next Experiment ( Experiment)
     * @see java.util.Iterator#next()
     */
    @Override
    public Experiment next()
    {
        if(cursor == null)
        {
            //System.out.printf("\nInvalid request for Iterator Next. There is no next element .\n");
            return null;
        }
        Experiment data = cursor.data;
        cursor = cursor.next;
        return data;
    }

    /*
     * Removes from the underlying ExperimentList the last experiment returned by this iterator.
     * @see java.util.Iterator#remove()
     */
    @Override
    public void remove()
    {
        Node temp = list.gethead();
        Node lastreturn = null, befcursor = null;
        while(temp != cursor && temp != null)
        {
            befcursor = lastreturn;
            lastreturn = temp;
            temp = temp.next;
        }
        if(lastreturn != befcursor)
        {
            if(befcursor == null)
            {
                lastreturn.next = null;
                if(cursor != null && lastreturn.data.get_day() == cursor.data.get_day())
                {
                    cursor.nextday = lastreturn.nextday;
                }
                lastreturn.nextday = null;
                list.sethead(cursor);
                list.set_size(list.get_size()-1);
            }
            else
            {
                if(befcursor.data.get_day() != lastreturn.data.get_day())
                {
                    befcursor.next = cursor;
                    Node befday = list.getDayNode(befcursor.data.get_day());
                    befday.nextday = cursor;
                    if(cursor != null && lastreturn.data.get_day() == cursor.data.get_day())
                    {
                        cursor.nextday = lastreturn.nextday;
                    }
                    lastreturn.next = null;
                    lastreturn.nextday = null;
                    list.set_size(list.get_size()-1);
                }
                else
                {
                    befcursor.next = cursor;
                    list.set_size(list.get_size()-1);
                }
            }
        }
    }
}