/***
 * @author Furkan Ozev
 * @since 16-03-2019
 * Evaluate Postfix expression.
 */
public class PostfixEval {

    /***
     * Determine that which operation is.
     * Pops two operands from stack.
     * Apply operations and return result.
     * @param op            operator (char)
     * @param operands      operands (Stack Double)
     * @return  result      result of evelation process (double)
     */
    private  double evalOp(char op, Stack<Double> operands) {
        double right = operands.pop();
        double left = operands.pop();
        double result = 0;
        switch (op) {
            case '+' : result = left + right;
                break;
            case '-' : result = left - right;
                break;
            case '/' : result = left / right;
                break;
            case '*' : result = left * right;
                break;
        }
        return result;
    }

    /**
     * Determines whether the character is an operator.
     * @param ch            The character to check (character)
     * @return boolean      if it is operator then return true, else return false.
     */
    private boolean isOp(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')';
    }

    /***
     * Evaluates postfix expression.
     * @param expression    The expression to be evaluated.
     * @return double       The value of the expression.
     */
    public double eval(String expression) {

        Stack<Double> operands = new Stack<Double>();
        String[] tokens = expression.split("\\s+");
        for (String str : tokens) {
            char firstChar = str.charAt(0);
            if (Character.isDigit(firstChar)) {
                double value = Double.parseDouble(str);
                operands.push(value);
            }
            else if (isOp(firstChar)) {
                if(str.length() >= 2 && Character.isDigit(str.charAt(1)))
                {
                    double value = Double.parseDouble(str);
                    operands.push(value);
                }
                else
                {
                    double result = evalOp(firstChar, operands);
                    operands.push(result);
                }
            }
            else if(str.compareTo("cos") == 0)
            {
                double degree = operands.pop();
                double result = Math.cos(degree);
                operands.push(result);
            }
            else if(str.compareTo("sin") == 0)
            {
                double degree = operands.pop();
                double result = Math.sin(degree);
                operands.push(result);
            }
            else if(str.compareTo("abs") == 0)
            {
                double num = operands.pop();
                double result = Math.abs(num);
                operands.push(result);
            }
        }
        double answer = operands.pop();
        if (operands.empty()) {
            return answer;
        }
        return 0;
    }
}
