import java.util.Iterator;

/**
 * @author Furkan Ozev
 * @since 08-03-2019
 * Homework 2 - 161044036
 */
public class Main {


    /**
     * All functions and codes successfully tested.
     * @param args String
     */
    public static void main(String[] args) {

        System.out.printf("\n------------------------------- DRIVER -------------------------------\n");

        ExperimentList ExpList = new ExperimentList();

        ExpList.addExp(new Experiment("Exp1", 4, "05:45:29", true, 44));
        ExpList.addExp(new Experiment("Exp1", 10, "09:52:55", true, 84));
        ExpList.addExp(new Experiment("Exp1", 2, "07:49:25", false, -1));
        ExpList.addExp(new Experiment("Exp1", 8, "01:36:17", true, 59));
        ExpList.addExp(new Experiment("Exp1", 9, "03:44:38", true, 63));
        ExpList.addExp(new Experiment("Exp2", 4, "09:37:19", true, 56));
        ExpList.addExp(new Experiment("Exp1", 7, "16:33:47", false, -1));
        ExpList.addExp(new Experiment("Exp1", 1, "02:42:53", true, 65));
        ExpList.addExp(new Experiment("Exp1", 3, "12:02:58", true, 83));
        ExpList.addExp(new Experiment("Exp2", 2, "10:22:53", true, 68));
        ExpList.addExp(new Experiment("Exp1", 6, "08:20:47", false, -1));
        ExpList.addExp(new Experiment("Exp2", 9, "09:12:51", false, -1));
        ExpList.addExp(new Experiment("Exp2", 1, "08:12:23", false, -1));
        ExpList.addExp(new Experiment("Exp2", 6, "18:32:41", true, 77));
        ExpList.addExp(new Experiment("Exp3", 9, "14:42:01", true, 49));
        ExpList.addExp(new Experiment("Exp3", 1, "19:18:13", true, 27));
        ExpList.addExp(new Experiment("Exp3", 4, "13:22:54", false, -1));
        ExpList.addExp(new Experiment("Exp3", 6, "21:23:45", true, 52));
        ExpList.addExp(new Experiment("Exp2", 8, "09:08:39", false, -1));

        ExpList.listAll();

        System.out.printf("\n* All tests shall be carried out on the above draft. You need to check the steps in sequence. *\n");

        System.out.printf("\n/* ------------------------  ADD EXPERIMENT TEST ------------------------ *\\ \n");
        System.out.printf("\n/* STEP 1 : addExp(Experiment) method : insert experiment to the end of the day *\\ \n");
        System.out.printf("\nSome experiments will be added using the addExp(...) method. Experiments to be added are as follows:\n");
        Experiment a1 = new Experiment("Exp1", 5, "11:39:27", true, 73);
        Experiment a2 = new Experiment("Exp2", 10, "19:05:08", false, -1);
        Experiment a3 = new Experiment("Exp4", 1, "23:02:05", true, 97);
        Experiment a4 = new Experiment("Exp4", 9, "19:37:05", true, 73);
        Experiment a5 = new Experiment("Exp4", 4, "16:02:05", true, 76);
        Experiment a6 = new Experiment("Exp3", 2, "16:17:44", false, -1);
        Experiment a7 = new Experiment("Exp2", 5, "21:32:15", true, 54);
        Experiment a8 = new Experiment("Exp5", 4, "20:20:53", true, 25);
        System.out.printf(" <***1***> \n%s \n\n<***2***> \n\n%s \n<***3***> \n\n%s \n<***4***> \n\n%s \n<***5***> \n\n%s \n<***6***> \n\n%s \n<***7***> \n\n%s \n<***8***> \n%s \n\n", a1, a2, a3, a4, a5, a6, a7, a8);
        ExpList.addExp(a1);
        ExpList.addExp(a2);
        ExpList.addExp(a3);
        ExpList.addExp(a4);
        ExpList.addExp(a5);
        ExpList.addExp(a6);
        ExpList.addExp(a7);
        ExpList.addExp(a8);
        System.out.printf("/* ------------------------ AFTER ADD EXPERIMENT STEP ------------------------ *\\ \n");
        ExpList.listAll();
        System.out.printf("\n Add Experiment Test Successfully Completed! \n");

        System.out.printf("\n/* ------------------------  GET EXPERIMENT TEST ------------------------ *\\ \n");
        System.out.printf("\n/* STEP 2 : getExp(day,index) method : get the experiment with the given day and position *\\ \n\n");
        System.out.printf("\nSome experiments will be get using the getExp(...) method. The experiments gotten are as follows:\n");
        try {
            System.out.printf("<***1***> day = 6, index = 1 : \n%s\n\n", ExpList.getExp(6, 1));
            System.out.printf("<***2***> day = 4 , index = 3 : \n%s\n\n", ExpList.getExp(4, 3));
            System.out.printf("<***3***> day = 1 , index = 0 : \n%s\n\n", ExpList.getExp(1, 0));
            System.out.printf("<***4***> day = 9 , index = 3 : \n%s\n\n", ExpList.getExp(9, 3));
            System.out.printf("<***5***> day = 3 , index = 0 : \n%s\n\n", ExpList.getExp(3, 0));
            System.out.printf("<***6***> day = 10 , index = 1 : \n%s\n\n", ExpList.getExp(10, 1));
            System.out.printf("<***7***> day = 3 , index = 1 : \n%s \t => Because there is no experiment for index 1. \n\n", ExpList.getExp(3, 1));
            System.out.printf("<***8***> day = 15 , index = 0 : \n%s \t => Because there is no experiment for day 15. \n\n", ExpList.getExp(15, 0));
        } catch (NullPointerException e) {
            System.out.printf("Invalid day or index for getExp\n");
        }
        System.out.printf("\n Get Experiment Test Successfully Completed! \n");

        System.out.printf("\n/* ------------------------  SET EXPERIMENT TEST ------------------------ *\\ \n");
        System.out.printf("\n/* STEP 3 : setExp(day,index, Experiment) method : set the experiment with the given day and position *\\ \n\n");
        System.out.printf("\nSome experiments will be set using the setExp(...) method. The experiments to be set are as follows:\n");

        Experiment b1 = new Experiment("Exp1", 3, "14:23:07", false, -1);
        Experiment b2 = new Experiment("Exp4", 9, "21:08:34", true, 54);
        Experiment b3 = new Experiment("Exp3", 1, "17:35:23", true, 44);
        Experiment b4 = new Experiment("Exp1", 7, "18:57:38", true, 79);
        Experiment b5 = new Experiment("Exp3", 2, "17:25:08", true, 37);

        System.out.printf("<***1***> day = 3 , index = 0 Experiment to be set: \n%s\n\n", b1);
        System.out.printf("<***3***> day = 9 , index = 3 Experiment to be set: \n%s\n\n", b2);
        System.out.printf("<***4***> day = 1 , index = 2 Experiment to be set: \n%s\n\n", b3);
        System.out.printf("<***4***> day = 7 , index = 0 Experiment to be set: \n%s\n\n", b4);
        System.out.printf("<***5***> day = 2 , index = 2 Experiment to be set: \n%s\n\n", b5);
        ExpList.setExp(3, 0, b1);
        ExpList.setExp(9, 3, b2);
        ExpList.setExp(1, 2, b3);
        ExpList.setExp(7, 0, b4);
        ExpList.setExp(2, 2, b5);
        System.out.printf("/* ------------------------ AFTER SET EXPERIMENT STEP ------------------------ *\\ \n");
        ExpList.listAll();
        System.out.printf("\n Set Experiment Test Successfully Completed! \n");

        System.out.printf("\n/* ------------------------  REMOVE EXPERIMENT TEST ------------------------ *\\ \n");
        System.out.printf("\n/* STEP 4 : removeExp(day,index) method : remove the experiment specified as index from given day. *\\ \n");
        System.out.printf("\nSome experiments will be remove using the removeExp(...) method. The experiments to be remove are as follows:\n");
        System.out.printf("<***1***> Experiment to be remove: day = 5 , index = 1 \n");
        System.out.printf("<***2***> Experiment to be remove: day = 3 , index = 0 \n");
        System.out.printf("<***3***> Experiment to be remove: day = 10 , index = 0 \n");
        System.out.printf("<***4***> Experiment to be remove: day = 4 , index = 2 \n");
        System.out.printf("<***5***> Experiment to be remove: day = 1 , index = 0 \n");
        System.out.printf("<***6***> Experiment to be remove: day = 9 , index = 1 \n");
        System.out.printf("<***7***> Experiment to be remove: day = 6 , index = 0 \n");
        ExpList.removeExp(5, 1);
        ExpList.removeExp(3, 0);
        ExpList.removeExp(10, 0);
        ExpList.removeExp(4, 2);
        ExpList.removeExp(1, 0);
        ExpList.removeExp(9, 1);
        ExpList.removeExp(6, 0);
        System.out.printf("/* ------------------------ AFTER REMOVE EXPERIMENT STEP ------------------------ *\\ \n");
        ExpList.listAll();
        System.out.printf("\n Remove Experiment Test Successfully Completed! \n");

        System.out.printf("\n/* ------------------------  LIST EXPERIMENT TEST ------------------------ *\\ \n");
        System.out.printf("\n/* STEP 5 : listExp(day) method : list all completed experiments in a given day. *\\ \n");
        System.out.printf("\nExperiments that are on some days will be list using the listExp(...) method. The days to be list are as follows:\n");
        System.out.printf("\n*** DAY 4: *** \n");
        ExpList.listExp(4);
        System.out.printf("\n*** DAY 10: *** \n");
        ExpList.listExp(10);
        System.out.printf("\n\n*** DAY 2: *** \n");
        ExpList.listExp(2);
        System.out.printf("\n*** DAY 8: *** \n");
        ExpList.listExp(8);
        System.out.printf("\n*** DAY 1: *** \n");
        ExpList.listExp(1);

        System.out.printf("\n List Experiment Test Successfully Completed! \n");

        System.out.printf("\n/* ------------------------  REMOVE DAY TEST ------------------------ *\\ \n");
        System.out.printf("\n/* STEP 6 : removeDay(day) method : remove all experiments in a given day. *\\ \n");
        System.out.printf("\nExperiments that are on some days will be remove using the removeDay(...) method. The days to be remove are as follows:\n");
        System.out.printf("\n*** REMOVE 7. DAY *** \n");
        ExpList.removeDay(7);
        System.out.printf("\n*** REMOVE 1. DAY *** \n");
        ExpList.removeDay(1);
        System.out.printf("\n*** REMOVE 10. DAY *** \n");
        ExpList.removeDay(10);
        System.out.printf("/* ------------------------ AFTER REMOVE DAY STEP ------------------------ *\\ \n");
        ExpList.listAll();
        System.out.printf("\n Remove Day Test Successfully Completed! \n");

        System.out.printf("\n/* ------------------------  ORDER DAY TEST ------------------------ *\\ \n");
        System.out.printf("\n/* STEP 7 : orderDay(day) method : sorts the experiments in a given day according to the accuracy, the changes will be done on the list. *\\ \n");
        System.out.printf("\nExperiments that are on some days will be order using the orderDay(...) method. The days to be order are as follows:\n");
        System.out.printf("\n*** ORDER 4. DAY *** \n");
        ExpList.orderDay(4);
        System.out.printf("\n*** ORDER 9. DAY *** \n");
        ExpList.orderDay(9);
        System.out.printf("\n*** ORDER 2. DAY *** \n");
        ExpList.orderDay(2);
        System.out.printf("/* ------------------------ AFTER ORDER DAY STEP ------------------------ *\\ \n");
        ExpList.listAll();
        System.out.printf("\n Order Day Test Successfully Completed! \n");

        System.out.printf("\n/* ------------------------  ORDER EXPERIMENTS TEST ------------------------ *\\ \n");
        System.out.printf("\n/* STEP 8 : orderExperiments(day) method : sorts all the experiments in the list according to the accuracy, the original list should not be changed since the day list may be damage. *\\ \n");
        System.out.printf("\nAll Experiments will be order using the orderExperiments(...) method. The experiments to be order are as follows:\n");
        System.out.printf("\n*** ORDER ALL EXPERIMENTS *** \n");
        ExperimentList orderedlist = ExpList.orderExperiments();
        System.out.printf("/* ------------------------ AFTER ORDER EXPERIMENTS STEP ------------------------ *\\ \n");
        orderedlist.printallexp();
        System.out.printf("\n Order Experiments Test Successfully Completed! \n");

        System.out.printf("\n/* ------------------------  ITERATOR TEST ------------------------ *\\ \n");
        System.out.printf("\n=> * Test hasNext() and next() methods. All experiments will be printed using iterator. *\n");
        Iterator<Experiment> iter = ExpList.iterator();
        while (iter.hasNext()) {
            System.out.printf("%s\n", iter.next());
        }
        System.out.printf("\n ITERATOR hasNext() and next() methods Test Successfully Completed! \n");

        System.out.printf("\n=> * Test remove() method. Some experiments will be removed using iterator. *\n");
        iter = ExpList.iterator();

        System.out.printf("\n/* ------------------------ BEFORE ITERATOR REMOVE STEP ------------------------ *\\ \n");
        ExpList.listAll();

        System.out.printf("\nRemoved experiment: %s\n", iter.next());
        iter.remove();
        iter.next();
        iter.next();
        System.out.printf("\nRemoved experiment: %s\n", iter.next());
        System.out.printf("\nRemoved experiment: %s\n", iter.next());
        iter.remove();
        iter.remove();
        iter.next();
        System.out.printf("\nRemoved experiment: %s\n", iter.next());
        System.out.printf("\nRemoved experiment: %s\n", iter.next());
        iter.remove();
        iter.remove();
        System.out.printf("\nRemoved experiment: %s\n", iter.next());
        iter.remove();
        iter.next();
        iter.next();
        iter.next();
        System.out.printf("\nRemoved experiment: %s\n", iter.next());
        iter.remove();
        iter.next();
        System.out.printf("\nRemoved experiment: %s\n", iter.next());
        iter.remove();
        System.out.printf("\n/* ------------------------ AFTER ITERATOR REMOVE STEP ------------------------ *\\ \n");
        ExpList.listAll();
        System.out.printf("\n ITERATOR remove methods Test Successfully Completed! \n");
        System.out.printf("\n");

        System.out.printf("\n************** ALL METHODS HAVE BEEN SUCCESSFULLY TESTED. ************** \n");

    }
}
