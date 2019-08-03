import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/***
 * @author Furkan Ozev
 * @since 21-04-2019
 * Homework 6 - 161044036
 */

public class Main {
    public static void main(String args[])
    {
        File file = new File("./input.txt");
        try {
            Scanner scl = new Scanner(file);
            Scanner scw;
            String str, str2;

            NLP ne = new NLP();
            ne.readDataset("./dataset");
            System.out.println();
            while (scl.hasNextLine()) {
                scw = new Scanner(scl.nextLine());
                str = scw.next();

                if (str.equals("bigram")) {
                    str = scw.next();
                    System.out.println(ne.bigrams(str) + "\n");
                }
                else if (str.equals("tfidf")) {
                    str = scw.next();
                    str2 = scw.next();
                    System.out.println(ne.tfIDF(str, str2) + "\n");
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
    }
}
