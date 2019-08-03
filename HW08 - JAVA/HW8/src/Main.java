import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/***
 * @author Furkan Ozev
 * @since 09-05-2019
 * Homework 8 - 161044036
 */

public class Main {

    public static void main(String args[])
    {
        File file = new File(".\\input.txt");
        GraphAjdacencyMatrix graph = null;
        try {
            Scanner scan = new Scanner(file);
            Scanner line;
            int N;
            int M;
            int source, destination;
            if(scan.hasNextLine())
            {
                line = new Scanner(scan.nextLine());
                N = line.nextInt();
                M = line.nextInt();
                graph = new GraphAjdacencyMatrix(N,true);

                for(int i = 0 ; i < M && scan.hasNextLine() ; i++)
                {
                    line = new Scanner(scan.nextLine());
                    source = line.nextInt();
                    destination = line.nextInt();
                    graph.addEdge(source-1,destination-1);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("\nFile not found.");
        }
        catch (NoSuchElementException e)
        {
            System.out.println("\n !!! An error was encountered in the input file. Please check again. !!!");
        }

        if(graph != null)
        {
            //graph.printGraph();
            System.out.println("\nOutput: " + graph.popularNumber());
        }
    }
}
