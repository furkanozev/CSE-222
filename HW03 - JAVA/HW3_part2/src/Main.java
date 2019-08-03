import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

/***
 * @author Furkan Ozev
 * @since 16-03-2019
 * Homework 3 - Part 2
 */
public class Main {
    /***
     *
     * @param args  String
     */
    public static void main(String args[]){
        String  infix = new String();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            while(line != null)
            {
                infix += line;
                infix += " \n";
                line = reader.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("File not found!\n");
            return;
        }
        catch (IOException e) {
            System.out.printf(e.getMessage());
            return;
        }

        System.out.printf("Entered Infix Expression:\n%s\n",infix);

        InfixToPostfix intopost = new InfixToPostfix();
        String postfix = intopost.convert(infix);
        System.out.printf("\nConverted Postfix Expression:\n%s\n",postfix);

        PostfixEval evaluate = new PostfixEval();
        double result = evaluate.eval(postfix);
        System.out.printf("\nEvaluated Postfix Expression: %f\n", result);

    }
}
