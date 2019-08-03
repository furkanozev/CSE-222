import java.util.Comparator;

/***
 * @author Furkan Ozev
 * @since 14-04-2019
 * Homework 5 - 161044036
 * Lexicographical comparison (LEX)
 */
public class LEX implements Comparator<Pixel>{

    /***
     * Compare method that compare 2 pixel.
     * Standard lexicographical comparison from discrete math.
     * @param o1    First pixel
     * @param o2    Second pixel
     * @return  (int)
     */
    public int compare(Pixel o1, Pixel o2)
    {
        if(o1.get_r() < o2.get_r()) return 1;
        else if(o1.get_r() > o2.get_r()) return -1;
        else
        {
            if(o1.get_g() < o2.get_g()) return 1;
            else if(o1.get_g() > o2.get_g()) return -1;
            else
            {
                if(o1.get_b() < o2.get_b()) return 1;
                else if(o1.get_b() > o2.get_b()) return -1;
                else
                {
                    return 0;
                }
            }
        }
    }
}