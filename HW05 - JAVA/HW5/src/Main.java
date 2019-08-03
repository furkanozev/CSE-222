import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/***
 * @author Furkan Ozev
 * @since 14-04-2019
 * Homework 5 - 161044036
 *
 * From the image file, the process of reading and transferring the pixels is done here.
 */
public class Main {

    /**
     * Program successfully tested.
     * @param args String
     */
    public static void main(String args[])
    {
        Pixel[][] pixels;
        BufferedImage img = null;
        File f = null;
        int width = 0;
        int height = 0;

        //read image
        try{
            f = new File(args[0]);
            img = ImageIO.read(f);

            width = img.getWidth();
            height = img.getHeight();
        }catch(IOException e){
            System.out.println(e);
        }
        pixels = new Pixel[height][width];
        Color color;
        for(int r=0; r<height; r++)
        {
            for(int c=0; c<width; c++)
            {
                color = new Color(img.getRGB(c,r));
                pixels[r][c] = new Pixel(color.getRed(),color.getGreen(),color.getBlue());
            }
        }


        Threads thr = new Threads(pixels, height, width);
        thr.start();

    }
}
