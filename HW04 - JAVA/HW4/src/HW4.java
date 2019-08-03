/***
 *  @author Furkan Ozev
 *  @since 30-03-2019
 *  Homework 4 - 161044036
 */
public class HW4 {

    /**
     * All functions and codes successfully tested.
     * @param args String
     */
    public static void main(String args[])
    {
        Integer[][] arr = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        ArrayList mal = new ArrayList<Integer>(arr);

        for(Object x: mal)
        {
            System.out.printf("%d ",x);
        }


    }
}
