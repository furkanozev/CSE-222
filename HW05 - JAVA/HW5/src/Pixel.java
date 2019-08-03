/***
 * @author Furkan Ozev
 * @since 14-04-2019
 * Homework 5 - 161044036
 * It holds the rgb color values ​​of each pixel.
 */
public class Pixel {
    private int r,g,b;

    /***
     * Constructor of Pixel class.
     * @param r_    Red value of pixel. (int)
     * @param g_    Green value of pixel. (int)
     * @param b_    Blue value of pixel. (int)
     */
    public Pixel(int r_, int g_, int b_)
    {
        r = r_;
        g = g_;
        b = b_;
    }

    /***
     * Get red value of pixel
     * @return  r   (int)
     */
    public int get_r()
    {
        return r;
    }
    /***
     * Get green value of pixel
     * @return  g   (int)
     */
    public int get_g()
    {
        return g;
    }
    /***
     * Get blue value of pixel
     * @return  b   (int)
     */
    public int get_b()
    {
        return b;
    }
}
