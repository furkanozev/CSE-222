import java.util.Iterator;

/**
 * @author Furkan Ozev
 * @since 08-03-2019
 * The ExperimentList is a single linked list to keep the experiments.
 */
public class ExperimentList implements Iterable<Experiment> {

    /**
     *	- head (Node): 	Keep head of ExperimentList.
     *	- size (int): 	Size of ExperimentList.
     */
    private Node head = null;
    private int size = 0;

    /**
     * Insert experiment to the end of the day
     * @param exp 	Experiment to add to ExperimentList (Experiment)
     */
    public void addExp(Experiment exp)
    {
        if(head==null) {
            head = new Node(exp);
            size++;
        }
        else
        {
            Node lastexpday = null;
            try
            {
                lastexpday = getlastNodeinDay(exp.get_day());
            }
            catch(NullPointerException e)
            {
                lastexpday = null;
            }

            if(lastexpday != null)
            {
                Node newnode = new Node(exp, lastexpday.next);
                lastexpday.next = newnode;
                size++;
            }
            else if(lastexpday == null)
            {
                Node tempday = head;
                Node temp = head;

                if(exp.get_day() < tempday.data.get_day())
                {
                    Node newnode = new Node(exp, temp, temp);
                    head = newnode;
                    size++;
                }

                else
                {
                    while(tempday != null && exp.get_day() > tempday.data.get_day())
                    {
                        temp = tempday;
                        tempday = tempday.nextday;
                    }

                    Node newnode = new Node(exp, temp.nextday, temp.nextday);
                    Node beforelastexpday = getlastNodeinDay(temp);
                    beforelastexpday.next = newnode;
                    temp.nextday = newnode;
                    size++;
                }


            }
        }
    }

    /**
     * Get the experiment with the given day and position
     * @param day		Day information of the experiment to be gotten (int).
     * @param index		Index of the experiment to be gotten (int).
     * @return data		If there is no experiments in given day and index, returns null,
     * 					returns the experiments if the experiments exists. (Experiment or null)
     * @throws NullPointerException Can throws Null pointer
     */
    public Experiment getExp(int day, int index) throws NullPointerException
    {
        if(day < 0 || index < 0) return null;
        Node pointer = getDayNode(day);
        for(int i = 0 ; i < index && pointer != null ; i++)
        {
            if(pointer.next.data.get_day() != day) return null;
            pointer = pointer.next;
        }
        if(pointer == null) return null;
        return pointer.data;
    }

    /**
     * Set the experiment with the given day and position.
     * If there is no experiment in the given day and index, the set is not done.
     * The day information of the given day and the index experiment must be the same as
     the day information of the experiment to be set.
     * @param day		Day information of the experiment to be set (int).
     * @param index		Index of the experiment to be set (int).
     * @param exp		Experiment to be set (Experiment).
     */
    public void setExp(int day, int index, Experiment exp)
    {
        if((day >= 0 && index >= 0) && day == exp.get_day())
        {
            Node pointer = head;
            try
            {
                pointer = getDayNode(day);
            }
            catch(NullPointerException e)
            {
                pointer = null;
            }
            if(pointer != null)
            {
                int i;
                for(i = 0 ; i < index && pointer.next != null ; i++)
                {
                    if(pointer.next.data.get_day() != day) break;
                    pointer = pointer.next;
                }
                if(i == index)
                {
                    pointer.data = exp;
                }
                else
                {
                    System.out.printf("\nInvalid request for set experiment. There is no experiment for the given position.\n");
                }
            }
            else
            {
                System.out.printf("\nInvalid request for set experiment. There is no experiment for the given day.\n");
            }

        }
        else
        {
            System.out.printf("\nInvalid request for set experiment. Check again! : day and day of the Experiment to be set .\n");
        }

    }

    /**
     * Remove the experiment specified as index from given day.
     * If there is no experiment in the given day and index, the remove is not done.
     * @param day		Day information of the experiment to be removed (int).
     * @param index		Index of the experiment to be removed (int).
     */
    public void removeExp(int day, int index)
    {

        if((day >= 0 && index >= 0))
        {
            Node pointer = head;
            Node temp = head;

            while(pointer != null && pointer.data.get_day() != day)
            {
                temp = pointer;
                pointer = pointer.nextday;
            }
            if(pointer != null)
            {
                if(index == 0)
                {
                    if(pointer.next == null)
                    {
                        if(pointer == head)
                        {
                            head = null;
                            size--;
                        }
                        else
                        {
                            Node endtemp = getlastNodeinDay(temp);
                            temp.nextday = null;
                            endtemp.next = null;
                            size--;
                        }
                    }
                    else if(pointer.data.get_day() == pointer.next.data.get_day())
                    {
                        if(pointer == head)
                        {
                            head = pointer.next;
                            (pointer.next).nextday = pointer.nextday;
                            size--;
                        }
                        else
                        {
                            Node endtemp = getlastNodeinDay(temp);
                            temp.nextday = pointer.next;
                            endtemp.next = pointer.next;
                            (pointer.next).nextday = pointer.nextday;
                            size --;
                        }
                    }
                    else
                    {
                        if(pointer == head)
                        {
                            head = pointer.next;
                            size--;
                        }
                        else
                        {
                            Node endtemp = getlastNodeinDay(temp);
                            temp.nextday = pointer.nextday;
                            endtemp.next = pointer.nextday;
                            size--;
                        }
                    }
                }
                else
                {
                    int i;
                    for(i = 0 ; i < index && pointer.next != null ; i++)
                    {
                        if(pointer.next.data.get_day() != day) break;
                        temp = pointer;
                        pointer = pointer.next;
                    }
                    if(i == index)
                    {
                        temp.next = pointer.next;
                        size--;
                    }
                    else
                    {
                        System.out.printf("\nInvalid request for remove experiment. There is no experiment for the given position.\n");
                    }
                }
            }
            else
            {
                System.out.printf("\nInvalid request for remove experiment. There is no experiment for the given day.\n");
            }
        }
        else
        {
            System.out.printf("\nInvalid request for remove experiment. Check again! : day and index.\n");
        }
    }

    /**
     * List (Print) all completed experiments in a given day.
     * If there is no experiment in the given day, nothing prints.
     * @param day		Day information of the experiments to be listed (int).
     */
    public void listExp(int day)
    {
        Node pointer;
        try {
            pointer = getDayNode(day);
        }
        catch(NullPointerException e)
        {
            pointer = null;
        }

        while (pointer != null && pointer.data.get_day() == day)
        {
            if(pointer.data.get_completed() == true)
            {
                System.out.printf("%s\n", pointer.data);
            }
            pointer = pointer.next;
        }
    }

    /**
     * Remove all experiments in a given day.
     * If there is no experiment in the given day, nothing removes.
     * @param day		Day information of the experiments to be removed (int).
     */
    public void removeDay(int day)
    {
        Node daynode = head;
        Node temp = head;

        while (daynode != null && daynode.data.get_day() != day)
        {
            temp = daynode;
            daynode = daynode.nextday;
        }

        if(daynode == null)
        {
            System.out.printf("\nInvalid request for removeDay. Check again! : day .\n");
        }
        else
        {
            if(daynode == head)
            {
                head = daynode.nextday;
            }
            else
            {
                Node endtemp = getlastNodeinDay(temp);
                temp.nextday = daynode.nextday;
                endtemp.next = daynode.nextday;
            }
            while(daynode != null && daynode.data.get_day() == day)
            {
                daynode = daynode.next;
                size--;
            }
        }


    }

    /**
     * Sorts the experiments in a given day according to the accuracy, the changes will be done on the list.
     * If there is no experiment in the given day, nothing orders.
     * @param day		Day information of the experiments to be ordered (int).
     */
    public void orderDay(int day)
    {
        Node node1, node2, node3, iter;
        Node daynode = head;
        Node beforeday = head;
        Node lastexpbefday;

        while (daynode != null && daynode.data.get_day() != day)
        {
            beforeday = daynode;
            daynode = daynode.nextday;
        }
        lastexpbefday = getlastNodeinDay(beforeday);

        if(daynode == null)
        {
            System.out.printf("\nInvalid request for orderDay. Check again! : day .\n");
        }
        else
        {
            int n = 0;
            node1 = daynode;
            node2 = daynode;
            node3 = daynode;
            iter = daynode;
            while(node1 != null && node1 != daynode.nextday)
            {
                n++;
                node1 = node1.next;
            }
            node1 = daynode;
            for(int i = 0; i < n-1 ; i++)
            {
                iter = daynode;
                for(int j = 0; j < n-i-1 ; j++)
                {
                    node3 = iter.next;
                    node2 = iter;
                    if(node2.data.get_accuracy() > node3.data.get_accuracy())
                    {
                        if(node2 == daynode)
                        {
                            if(daynode == head)
                            {
                                head = node3;
                                node2.next = node3.next;
                                node3.next = node2;
                                node3.nextday = node2.nextday;
                                node2.nextday = null;
                                iter = node3;
                                daynode = node3;
                            }
                            else
                            {
                                beforeday.nextday = node3;
                                lastexpbefday.next = node3;
                                node2.next = node3.next;
                                node3.next = node2;
                                node3.nextday = node2.nextday;
                                node2.nextday = null;
                                iter = node3;
                                daynode = node3;
                            }
                        }
                        else
                        {
                            node1.next = node3;
                            node2.next = node3.next;
                            node3.next = node2;
                            iter = node3;
                        }

                    }
                    node1 = iter;
                    iter = iter.next;
                }
            }
        }

    }

    /**
     * Sorts all the experiments in the list according to the accuracy,
     the original list should not be changed since the day list may be damage.
     * @return newlist	List of all experiments sorted by accuracy (ExperimentList).
     */
    public ExperimentList orderExperiments()
    {
        ExperimentList newlist = new ExperimentList();
        Node node = head;
        Node new_node = null, befnode = null;

        if(head != null)
        {
            new_node = new Node(node.data);
            newlist.head = new_node;
            befnode = new_node;
            newlist.size += 1;
            node = node.next;
            while(node != null)
            {
                new_node = newlist.head;
                while(new_node != null && node.data.get_accuracy() > new_node.data.get_accuracy())
                {
                    befnode = new_node;
                    new_node = new_node.next;
                }
                if(new_node == null)
                {
                    befnode.next = new Node(node.data);
                    newlist.size += 1;
                }
                else
                {
                    if(new_node == newlist.head)
                    {
                        newlist.head = new Node(node.data);
                        newlist.head.next = new_node;
                        newlist.size += 1;
                    }
                    else
                    {
                        befnode.next = new Node(node.data);
                        befnode.next.next = new_node;
                        newlist.size += 1;
                    }
                }
                node = node.next;
            }
        }

        return newlist;
    }



    /**
     * Get day node by taken day information.
     * @param day		Day information of the node to be gotten (int).
     * @return daynode	The node of the experiments in the given day (Node).
     * @throws NullPointerException Can throws Null pointer
     */
    public Node getDayNode(int day) throws NullPointerException  {
        Node daynode = head;

        while (daynode != null && daynode.data.get_day() != day) {
            daynode = daynode.nextday;
        }

        return daynode;
    }

    /**
     * Get the node of the last experiment of the day by taken day information.
     * @param day		Day information of the node to be gotten (int).
     * @return temp		The node of the last experiments in the given day (Node).
     */
    public Node getlastNodeinDay(int day)
    {
        Node daynode = null;
        try
        {
            daynode = getDayNode(day);
        }
        catch(NullPointerException e)
        {
            daynode = null;
        }
        Node node = daynode;
        Node temp = node;

        while (node != daynode.nextday && node != null) {
            temp = node;
            node = node.next;
        }

        return temp;
    }

    /**
     * Get the node of the last experiment of the day by taken node of day.
     * @param daynode		Day information of the node to be gotten (int).
     * @return temp			The node of the last experiments in the given day (Node).
     */
    public Node getlastNodeinDay(Node daynode)
    {

        Node node = daynode;
        Node temp = node;

        while (node != daynode.nextday && node != null) {
            temp = node;
            node = node.next;
        }

        return temp;
    }

    /**
     * Get head of ExperimentList.
     * @return head		(Node)
     */
    public Node gethead()
    {
        return head;
    }

    /**
     * Set head of ExperimentList.
     * @param newhead		New head to set head of ExperimentList (Node).
     */
    public void sethead(Node newhead)
    {
        head = newhead;
    }

    /*
     * Return this ExperimentList object's iterator object.
     * @see java.lang.Iterable#iterator()
     * @return MyIterator
     */
    public Iterator<Experiment> iterator()
    {
        return new MyIterator(this);
    }

    /**
     * Set the size of ExperimentList.
     * @param nsize		new size (int).
     */
    public void set_size(int nsize)
    {
        if(size >= 0)	size = nsize;
    }

    /**
     * Get the size of ExperimentList.
     * @return	size (int).
     */
    public int get_size()
    {
        return size;
    }

    /**
     * Print all experiments using next node.
     */
    public void printallexp()
    {
        Node node = head;
        int c = 0, tempday = 0;
        if(node != null) tempday = node.data.get_day();
        System.out.printf("\n\t\t\t\t\t\tEXPERIMENTS");
        System.out.printf("\n--------------------------------------------------------------\n");
        while (node != null) {
            if(tempday != node.data.get_day())
            {
                System.out.printf("\n");
                tempday = node.data.get_day();
            }
            System.out.print(node.data);
            node = node.next;
            c++;
        }
        System.out.printf("\nTotal experiment : %d \n", c);
        System.out.printf("--------------------------------------------------------------\n");

    }

    /**
     * Print all experiments using next node and Prints first experiment of everyday using nextday node.
     */
    public void listAll()
    {
        System.out.println("List experiment view:");
        Node last = head;
        while( last != null) {
            System.out.println(last.data.toString());
            last = last.next;
        }
        System.out.println("List day view:");
        last = head;
        while( last != null) {
            System.out.println(last.data.toString());
            last = last.nextday;
        }
    }

}
