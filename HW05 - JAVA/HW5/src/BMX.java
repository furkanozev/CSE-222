import java.util.Comparator;
/***
 * @author Furkan Ozev
 * @since 14-04-2019
 * Homework 5 - 161044036
 * Bitmix comparison (BMX)
 */
public class BMX implements Comparator<Pixel>{

    /***
     * Compare method that compare 2 pixel.
     * @param o1    First pixel
     * @param o2    Second pixel
     * @return  (int)
     */
    public int compare(Pixel o1, Pixel o2)
    {
        int[][] bin1 = new int[3][8];
        int[][] bin2 = new int[3][8];

        int temp1[] = {o1.get_r(), o1.get_g(), o1.get_b()};
        int temp2[] = {o2.get_r(), o2.get_g(), o2.get_b()};
        for(int j = 0; j < 3; j++)
        {
            for(int i = 7; i >= 0; i--)
            {
                bin1[j][i] = temp1[j] % 2;
                temp1[j] /= 2;

                bin2[j][i] = temp2[j] % 2;
                temp2[j] /= 2;
            }
        }

        double l1 = 0;
        double l2 = 0;
        double templ1 = 0, templ2 = 0;

        for(int j = 1 ; j <= 8 ; j++)
        {
            templ1 = 0;
            templ2 = 0;
            for(int i = 1 ; i <= 3 ; i++)
            {
                templ1 +=  Math.pow(2,3-i) * bin1[i-1][j-1];
                templ2 +=  Math.pow(2,3-i) * bin2[i-1][j-1];
            }
            l1 += Math.pow(2,3*(8-j)) * templ1;
            l2 += Math.pow(2,3*(8-j)) * templ2;
        }

        if(l1 < l2) return 1;
        else if(l1 == l2)   return 0;
        else return -1;
    }
}