/***
 * @author Furkan Ozev
 * @since 16-03-2019
 * Math class to implemets some methods : sin, cos, abs.
 */
public class Math {
    public static double PI = 3.14159;

    /***
     * Calculate sinus value of degree.
     * @param x     degree (double)
     * @return  double      result of sinus
     */
    public static double sin(double x)
    {
        x = x % 360;
        double radian = x * PI / 180;
        int i,sign;
        double res = 0,fact,power;

        for (i=1;i<=12;i++)
        {
            if(i%2==1)
            {
                sign=1;
            }
            else
            {
                sign=-1;
            }
            power = power(radian,2*i-1);
            fact = factorial(2*i-1);
            res += sign*power/fact;
        }
        return res;
    }
    /***
     * Calculate cosinus value of degree.
     * @param x     degree (double)
     * @return  double      result of cosinus
     */
    public static double cos(double x)
    {
        x = x % 360;
        double radian = x * PI / 180;
        int i,sign;
        double res = 0,fact,power;

        for (i=0;i<=11;i++)
        {
            if(i%2==1)
            {
                sign=-1;
            }
            else
            {
                sign=1;
            }
            power = power(radian,2*i);
            fact = factorial(2*i);
            res += sign*power/fact;
        }

        return res;
    }

    /***
     * Calculate absolute value of value.
     * @param x     value  (double)
     * @return double   absolute value
     */
    public static double abs(double x)
    {
        if(x < 0) return x*-1;
        else return x;
    }

    /***
     * Calculate value's powers.
     * @param x             value (double)
     * @param y             exponent (int)
     * @return  double      result of power.
     */
    public static double power(double x, int y)
    {
        double res = 1;
        for(int i=0; i<y; i++)
        {
           res *= x;
        }
        return res;
    }

    /***
     * Calculate value's factorial.
     * @param y         value (int)
     * @return  double  resutlt of factorial.
     */
    public static double factorial(int y)
    {
        double res = 1;
        while(y > 0)
        {
            res *= y;
            y--;
        }
        return res;
    }

}
