import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Furkan Ozev
 * @since 16-03-2019
 * Homework 3 - Part 1
 */
public class Main {

    public static void main(String args[]) {
        String infix;
        if(args.length < 1)
        {
            System.out.printf("File not found!\n");
            return;
        }
        BufferedReader reader;
        Scanner scanner;
        int row = 0, column = 0, chars = 0;
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            scanner = new Scanner(new File(args[0]));

            String line=reader.readLine();
            chars = line.length();
            column = ((chars - 1) / 2) + 1;

            while (line != null) {
                row++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException a) {
            System.out.printf("File not found!");
            return;
        }

        Coordinate[][] Arr = new Coordinate[row][column];

        for(int i=0 ; i<row ; i++)
        {
            for(int j=0 ; j<column ; j++)
            {
                Arr[i][j] = new Coordinate(scanner.nextInt(), i, j);
            }
        }
        scanner.close();

        int counter = 0;
        Stack<Coordinate> stack = new Stack<Coordinate>();
        int column2, row2;
        Coordinate temp;

        for(int i=0 ; i<row ; i++)
        {
            for(int j=0 ; j<column ; j++)
            {
                if(Arr[i][j].get_val() == 1)
                {
                    stack.push(Arr[i][j]);
                    while(!stack.empty())
                    {
                        temp = stack.pop();
                        temp.set_val(0);
                        row2 = temp.get_row();
                        column2 = temp.get_column();

                        if(row2+1 < row && Arr[row2+1][column2].get_val() == 1)
                        {
                            stack.push(Arr[row2+1][column2]);
                        }
                        if(row2-1 >= 0 && Arr[row2-1][column2].get_val() == 1)
                        {
                            stack.push(Arr[row2-1][column2]);
                        }
                        if(column2+1 < column && Arr[row2][column2+1].get_val() == 1)
                        {
                            stack.push(Arr[row2][column2+1]);
                        }
                        if(column2-1 >= 0 && Arr[row2][column2-1].get_val() == 1)
                        {
                            stack.push(Arr[row2][column2-1]);
                        }
                    }

                    counter++;
                }
            }
        }

        System.out.printf("White components: %d\n", counter);
    }
}