/***
 * @author Furkan Ozev
 * @since 09-05-2019
 * Homework 8 - 161044036
 * Graph AdjacencyMatrix (Concrete class)
 */

public class GraphAjdacencyMatrix extends AbstractGraph {

    /** The number of vertices */
    int vertex;
    /** 2D array to keep vertexs and to determine edges. */
    int matrix[][];
    /** Flag to indicate whether this is a directed graph */
    boolean directed;

    /***
     * Construct a graph with the specified number of vertices and directionality.
     * @param vertex The number of vertices
     * @param directed The directionality flag
     */
    public GraphAjdacencyMatrix(int vertex, boolean directed) {
        super(vertex,directed);
        this.directed = directed;
        this.vertex = vertex;
        matrix = new int[vertex][vertex];
    }

    /***
     * Add edge to graph
     * @param source source vertex
     * @param destination destination vertex
     */
    public void addEdge(int source, int destination) {
        matrix[source][destination] = 1;
        if(directed == false)
        {
            matrix[destination][source] = 1;
        }
    }

    /***
     * Remove edge from to graph
     * @param source source vertex
     * @param destination destination vertex
     */
    public void removeEdge(int source, int destination) {
        matrix[source][destination] = 0;
        if(directed == false)
        {
            matrix[destination][source] = 0;
        }
    }

    /** Insert a new edge into the graph.
     @param edge The new edge
     */
    public void insert(Edge edge) {
        matrix[edge.source][edge.destination] = 1;
        if(directed == false)
        {
            matrix[edge.destination][edge.source] = 1;
        }
    }

    /** Determine whether an edge exists.
     @param source The source vertex
     @param destination The destination vertex
     @return true if there is an edge from source to dest
     */
    public boolean isEdge(int source, int destination) {
        if(matrix[source][destination] == 1)    return true;
        else return false;
    }

    /** Get the edge between two vertices.
     @param source The source
     @param dest The destination
     @return the edge between these two vertices
     or null if an edge does not exist.
     */
    public Edge getEdge(int source, int dest) {
        return new Edge(source,dest);
    }

    /***
     * Print graph
     */
    public void printGraph() {
        System.out.println("Graph: (Adjacency Matrix)");
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < vertex; i++) {
            System.out.print("Vertex " + i + " is connected to:");
            for (int j = 0; j < vertex; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }

    /***
     * Determine connections for one vertex.
     * @param arr       to keep connections in array
     * @param index     Which number are we determining for?
     * @param target    Which number are we looking to determine connection?
     */
    private void connection(int[] arr, int index, int target)
    {
        if(index < 0 || index > vertex) return;
        else if (validate(arr,target) == 1) return;
        else
        {
            for(int i = 0 ; i < vertex ; i++)
            {
                if(arr[i] != 1)
                {
                    if(matrix[i][target] == 1)
                    {
                        arr[i] = 1;
                        connection(arr,0,i);
                    }
                }
            }
        }
    }

    /***
     * Finds the people who are considered popular by every other person.
     * @return popularNumber int
     */
    public int popularNumber()
    {
        int number = 0;
        int[] flags = new int[vertex];
        for(int i = 0; i < vertex; i++)
        {
            init(flags);
            connection(flags,0, i);
            number += validate(flags, i);
        }
        return number;
    }

    /***
     * initilaze 0 into array.
     * @param arr   flag array to initilaze
     */
    private void init(int[] arr)
    {
        for(int i = 0 ; i < arr.length ; i++)
        {
            arr[i] = 0;
        }
    }

    /***
     * Check whether an vertex has all connections to other vertices.
     * @param arr
     * @param index
     * @return  int     If each vertex has a connections to other vertices, it returns 1, else return 0.
     */
    private int validate(int[] arr, int index)
    {
        for(int i = 0 ; i < arr.length ; i++)
        {
            if(i != index && arr[i] == 0) return 0;
        }
        return 1;
    }

}