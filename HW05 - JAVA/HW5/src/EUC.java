import java.util.Comparator;

/***
 * @author Furkan Ozev
 * @since 14-04-2019
 * Homework 5 - 161044036
 * Euclidean norm based comparison (EUC)
 */
public class EUC implements Comparator<Pixel>{

    /***
     * Compare method that compare 2 pixel.
     * whichever vector has the greater L2 norm is considered greater
     * @param o1    First pixel
     * @param o2    Second pixel
     * @return  (int)
     */
    public int compare(Pixel o1, Pixel o2)
    {
        double l1 = Math.sqrt(Math.pow(o1.get_r(),2) + Math.pow(o1.get_g(),2) + Math.pow(o1.get_b(),2));
        double l2 = Math.sqrt(Math.pow(o2.get_r(),2) + Math.pow(o2.get_g(),2) + Math.pow(o2.get_b(),2));

        if(l1 < l2) return 1;
        else if(l1 == l2) return 0;
        else return -1;
    }
}