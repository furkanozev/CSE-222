
/**
 * @author Furkan Ozev
 * @since 08-03-2019
 * The experiment class contains the information of the experiment.
 */
public class Experiment {
    /**
     * 	- setup (String): explains the experimental setup
     - day(integer): represents the day of start
     - time(String): represents the time of start
     - completed(boolean): indicates whether it is completed or not
     - accuracy(float): represents the output (not a valid value if the experiment is not completed)
     */
    private String setup;
    private int day;
    private String time;
    private boolean completed;
    public float accuracy;

    /**
     * Experiments constructor that take all information of experiment.
     * @param setup2 		experimental setup (String)
     * @param day2			day of experiment (int)
     * @param time2			time of experiment (String)
     * @param completed2	completion of the experiment (boolean)
     * @param accuracy2		accuracy of the experiment (float)
     */
    public Experiment(String setup2, int day2, String time2, boolean completed2, float accuracy2)
    {
        setup = setup2;
        day = day2;
        time = time2;
        completed = completed2;
        if(completed2 == true) {
            accuracy = accuracy2;
        } else {
            accuracy = -1;
        }
    }

    /**
     * Getter of information of experiments setup.
     * @return setup	experimental setup (String)
     */
    public String get_setup()
    {
        return setup;
    }

    /**
     * Getter of information of experiments day.
     * @return day		day of experiment (int)
     */
    public int get_day()
    {
        return day;
    }

    /**
     * Getter of information of experiments time.
     * @return time 	time of experiment (String)
     */
    public String get_time()
    {
        return time;
    }

    /**
     * Getter of information of experiments completed.
     * @return completed 	completion of the experiment (boolean)
     */
    public boolean get_completed()
    {
        return completed;
    }


    /**
     * Getter of information of experiments accuracy.
     * @return accuracy 	accuracy of the experiment (float)
     */
    public float get_accuracy()
    {
        return accuracy;
    }

    /*
     * @see java.lang.Object#toString()
     * Print information of experiment.
     */
    @Override
    public String toString() {
        return "Experiment{" +
                "setup='" + setup + '\'' +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", accuracy=" + accuracy +
                ", completed=" + completed +
                '}';
    }


}
