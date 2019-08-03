import java.util.StringJoiner;

/***
 * @author Furkan Ozev
 * @since 16-03-2019
 * Convert Infix expression to Postfix expression.
 */
public class InfixToPostfix {

    private final Stack<Character> stack = new Stack<Character>();
    private Pair pairs = new Pair();

    private final StringJoiner postfix = new StringJoiner(" ");

    /***
     * Convert string from infix to postfix.
     * @param infix     The infix expression (String).
     * @return String   postfix expression.
     */
    public String convert(String infix)
    {
        convert_postfix(infix);
        return get_postfix();
    }

    /***
     * Determines whether the character is an operator.
     * @param ch            The character to check (character)
     * @return boolean      if it is operator then return true, else return false.
     */
    private boolean isOp(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')';
    }

    /***
     * Determine the precedence of an operator.
     * @param op     The operator (char)
     * @return int   the precedence of operator.
     */
    private int prece(char op) {
        switch(op)
        {
            case '(':
                return 1;
            case ')' :
                return 1;
            case '-':
                return 2;
            case '+' :
                return 2;
            case '*':
                return 3;
            case '/' :
                return 3;
        }
        return -1;

    }

    /***
     * Return the final postfix string.
     * @return String   The postfix string
     */
    private String get_postfix() {
        if(stack.empty() == false)
        {
            char op;
            while(stack.empty() != true)
            {
                op = stack.pop();
                if(op != '(' && op != ')')
                {
                    postfix.add(Character.toString(op));
                }
            }
        }
        return postfix.toString();
    }

    /***
     * Convert infix string to postfix string with use stack.
     * @param infix     The string to convert to postfix (String).
     */
    private void convert_postfix(String infix)
    {
        int i=0;
        while(i < infix.length())
        {
            if((Character.isLetter(infix.charAt(i))))
            {
                int temp = i;
                while(i < infix.length() && infix.charAt(i) != ' ' && infix.charAt(i) != '(')
                {
                    i++;
                }
                if(i < infix.length() && infix.charAt(i) == '(')
                {
                    int temp2 = i;
                    int flag = 1;
                    while(temp2+1 < infix.length() && flag != 0)
                    {
                        if(infix.charAt(temp2+1) == '(') flag++;
                        else if(infix.charAt(temp2+1) == ')' ) flag--;
                        temp2++;
                    }
                    convert_postfix(infix.substring(i,temp2+1));
                    postfix.add(infix.substring(temp,i));
                    i = temp2+1;


                }
                else if(i+1 < infix.length() && infix.charAt(i+1) == '=')
                {
                    String t1 = infix.substring(temp,i);
                    i = i + 3;
                    int temp2=i;
                    while(i < infix.length() && infix.charAt(i) != ' ' && infix.charAt(i) != '\n')
                    {
                        i++;
                    }
                    String t2 = infix.substring(temp2, i);
                    pairs.add(t1,t2);

                }
                else
                {
                    String t = infix.substring(temp,i);
                    int x = pairs.get_index(t);
                    if(x != -1) postfix.add(pairs.get_val(x));
                    else postfix.add(infix.substring(temp,i));
                    i++;
                }
            }
            else if((Character.isDigit(infix.charAt(i))))
            {
                int temp = i;
                while(i < infix.length() && infix.charAt(i) != ' ')
                {
                    i++;
                }
                postfix.add(infix.substring(temp,i));
            }
            else if((isOp(infix.charAt(i))))
            {
                if(i+1 < infix.length() && Character.isDigit(infix.charAt(i + 1)))
                {
                    int temp2 = i+1;
                    while(temp2 < infix.length() && infix.charAt(temp2) != ' ' && infix.charAt(temp2) != '\n')
                    {
                        temp2++;
                    }
                    postfix.add(infix.substring(i,temp2));
                    i = temp2;
                }
                else
                {
                    processOp(infix.charAt(i));
                    i++;
                }
            }
            else i++;
        }
    }

    /***
     * Process operators.
     * @param op        The operator (char)
     */
    private void processOp(char op) {
        if (stack.empty() || op == '(')     stack.push(op);
        else {
            char topOp = stack.peek();
            if (prece(op) > prece(topOp))      stack.push(op);
            else {
                int flag = 0;
                while (!stack.empty() && prece(op) <= prece(topOp) && flag != -1)
                {
                    stack.pop();
                    if (topOp == '(')
                    {
                        flag = -1;
                    }
                    else
                    {
                        postfix.add(Character.toString(topOp));
                        if (!stack.empty())
                        {
                            topOp = stack.peek();
                        }
                    }
                }

                if (op != ')')      stack.push(op);
            }
        }
    }

}
