/**
 * @author Furkan Ozev
 * @since 08-03-2019
 * The Node class is for building nodes of experiments.
 */
public class Node
{
    /**
     *	- data (Experiment): Keep Experiment
     *	- next (Node): Keep next experiment's node
     *	- nextday (Node): Keep next day's experiment's node.
     */
    public Experiment data;
    public Node next;
    public Node nextday;

    /**
     * Node constructor that take an experiment.
     * @param dataItem	Experiment to add to node (Experiment)
     */
    public Node(Experiment dataItem) {
        data = dataItem;
        next = null;
        nextday = null;
    }

    /**
     * Node constructor that take an experiment and node of next experiment.
     * @param dataItem	Experiment to add to node (Experiment)
     * @param nodeRef	Node of next experiment (Node)
     */
    public Node(Experiment dataItem, Node nodeRef) {
        data = dataItem;
        next = nodeRef;
        nextday = null;
    }

    /**
     * Node constructor that take an experiment , node of next experiment and node of next day experiment.
     * @param dataItem	Experiment to add to node (Experiment)
     * @param nodeRef	Node of next experiment (Node)
     * @param nodeday	Node of next day experiment (Node)
     */
    public Node(Experiment dataItem, Node nodeRef, Node nodeday) {
        data = dataItem;
        next = nodeRef;
        nextday = nodeday;
    }
}