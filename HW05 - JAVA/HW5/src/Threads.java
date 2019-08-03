/***
 * @author Furkan Ozev
 * @since 14-04-2019
 * Homework 5 - 161044036
 * This class have 4 threads. It is responsible for the operation of these threads.
 */
public class Threads {

    private PriorityQueue<Pixel> PQLEX = new PriorityQueue<Pixel>(1000, new LEX());
    private PriorityQueue<Pixel> PQEUC = new PriorityQueue<Pixel>(1000, new EUC());
    private PriorityQueue<Pixel> PQBMX = new PriorityQueue<Pixel>(1000, new BMX());

    private int height;
    private int width;
    private int size;
    Pixel[][] pixels;

    /***
     * Constructor of  Threads class.
     * @param arr2  Pixel array that include pixels of image.
     * @param h     height of image or row amount of array. (int)
     * @param w     width of image or column amount of array. (int)
     */
    public Threads(Pixel[][] arr2, int h, int w)
    {
        pixels = arr2;
        height = h;
        width = w;
        size = height*width;

    }

    /***
     * It will remove from PQLEX the color pixels and print them on screen one after the other,
            up until a total of WxH pixels are printed.
     */
    private Thread thread2 = new Thread(new Runnable() {
        public void run() {
            Pixel temp = null;
            int counter = 0;
            try {
                while(counter < size) {
                    synchronized (PQLEX) {
                        temp = PQLEX.poll();
                        System.out.printf("Thread2-PQLEX: [%d,%d,%d]\n", temp.get_r(), temp.get_g(), temp.get_b());
                    }
                    counter++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    });

    /***
     * It will remove from PQEUC the color pixels and print them on screen one after the other,
     up until a total of WxH pixels are printed.
     */
    private Thread thread3 = new Thread(new Runnable() {
        public void run() {
            Pixel temp = null;
            int counter = 0;
            try {
                while(counter < size)
                {
                    synchronized (PQEUC) {
                        temp = PQEUC.poll();
                        System.out.printf("Thread3-PQEUC: [%d,%d,%d]\n", temp.get_r(), temp.get_g(), temp.get_b());
                    }
                    counter++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    });

    /***
     * It will remove from PQBMX the color pixels and print them on screen one after the other,
     up until a total of WxH pixels are printed.
     */
    private  Thread thread4 = new Thread(new Runnable() {
        public void run() {
            Pixel temp = null;
            int counter = 0;
            try {
                while(counter < size) {
                    synchronized (PQBMX) {
                        temp = PQBMX.poll();
                        System.out.printf("Thread4-PQBMX: [%d,%d,%d]\n", temp.get_r(), temp.get_g(), temp.get_b());
                    }
                    counter++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    });

    /***
     * Every color pixel read, will be inserted to each of the 3 priority queues.
     */
    private Thread thread1 = new Thread(new Runnable() {
        public void run() {
            int counter = 0;
            for(int r = 0; r< height; r++)
            {
                for(int c = 0; c< width; c++)
                {

                    synchronized (PQLEX)
                    {
                        synchronized (PQEUC)
                        {
                            synchronized (PQBMX)
                            {
                                System.out.printf("Thread 1: [%d, %d, %d]\n", pixels[r][c].get_r(), pixels[r][c].get_g(), pixels[r][c].get_b());
                                PQLEX.offer(pixels[r][c]);
                                PQEUC.offer(pixels[r][c]);
                                PQBMX.offer(pixels[r][c]);
                            }
                        }
                    }

                    counter++;

                    if(counter == 100)
                    {
                        thread2.start();
                        thread3.start();
                        thread4.start();
                    }
                }
            }
        }
    });

    /***
     * Start method of class, so, program. Thread 1 will work.
     */
    public void start()
    {
        thread1.start();
    }

}
