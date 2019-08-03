
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/***
 * @author Furkan Ozev
 * @since 21-04-2019
 * Homework 6 - 161044036
 */

public class NLP
{
    public Word_Map wmap;
    private ArrayList<Integer> Totalnumber = new ArrayList<Integer>();
    private ArrayList<String>  filenames = new ArrayList<String>();

    /*You should not use File_Map class in this file since only word hash map is aware of it.
    In fact, you can define the File_Map class as a nested class in Word_Map,
     but for easy evaluation we defined it separately.
     If you need to access the File_Map instances, write wrapper methods in Word_Map class.
    * */

    /*Reads the dataset from the given dir and created a word map */

    /***
     * Reads the dataset from the given dir and created a word map
     * @param dir   String  -   directory of file.
     */
    public void readDataset(String dir)
    {
        wmap = new Word_Map();
        Scanner fs;
        String word;
        File maindir = new File(dir);
        if(!maindir.exists())
        {
            System.out.println("Dataset is not found.! Please check dir");
        }
        if(maindir.exists() && maindir.isDirectory())
        {
            int i=0;
            File arr[] = maindir.listFiles();
            for(File file: arr)
            {
                i = 0;
                try {
                    fs = new Scanner(file);
                    while (fs.hasNext()) {
                        word = fs.next();
                        word = word.trim().replaceAll("\\p{Punct}", "");
                        if(word.length() != 0)
                        {
                            wmap.put(word,wmap.wrapperFileMap(file.getName(),i));
                            i++;
                        }
                        //System.out.println("word:" + word + " filename:" + file.getName());
                    }
                    filenames.add(file.getName());
                    Totalnumber.add(i);
                } catch (IOException e) {
                    System.out.println("\nError accessing input file!");
                }
            }
        }
        else
        {
            System.out.println("\nFile not found or there is an error.!");
        }
    }


    /*Finds all the bigrams starting with the given word*/

    /***
     * Finds all the bigrams starting with the given word
     * @param word  -   String  -   word to find bigrams
     * @return  List    all biagram object as list
     */
    public List<String> bigrams(String word){

        List<String> list = new ArrayList<>();
        File_Map fmap, temp;
        String name, keep;
        int val, index;
        Iterator iter;
        Word_Map.Node node;
        ArrayList<Integer>  cursors;

        if((fmap = (File_Map) wmap.get(word)) == null)  return null;
        for(int i=0; i < fmap.size(); i++)
        {
            name = fmap.fnames.get(i);
            cursors = (ArrayList<Integer>) fmap.occurances.get(i);

            iter = wmap.iterator();
            while(iter.hasNext())
            {
                node = (Word_Map.Node) iter.next();
                temp = (File_Map)node.getValue();
                if(temp.fnames.contains(name))
                {
                    index = temp.fnames.indexOf(name);
                    for(int j = 0 ; j < cursors.size() ; j++)
                    {
                        val = cursors.get(j);
                        if(temp.occurances.get(index).contains(val+1))
                        {
                            keep = word + " " + node.getKey();
                            if(!list.contains(keep)) list.add(keep);
                        }
                    }
                }
            }

        }

        return list;
    }


    /*Calculates the tfIDF value of the given word for the given file */

    /***
     * Calculates the tfIDF value of the given word for the given file.
     * @param word  -   String  -   word to calculate
     * @param fileName  - String  - file name to calculate
     * @return  float   -   calculated tfIDF value
     */
    public float tfIDF(String word, String fileName)
    {
        float TF, IDF, TFDIF;
        int amountTerm = 0, amountTermInFile = 0;
        int amountDoc = 0, amountTermDoc = 0;
        int index;
        File_Map fmap = (File_Map) wmap.get(word);
        if(fmap != null && fmap.containsKey(fileName))
        {
            index = fmap.fnames.indexOf(fileName);
            amountTerm = fmap.occurances.get(index).size();
        }
        else
        {
            return 0;
        }
        index = filenames.indexOf(fileName);
        amountTermInFile = Totalnumber.get(index);
        TF = (float) amountTerm / amountTermInFile;


        amountDoc = filenames.size();
        amountTermDoc = fmap.fnames.size();
        IDF = (float) Math.log((float)amountDoc / amountTermDoc);

        TFDIF = TF * IDF;
        return TFDIF;
    }

    /*Print the WordMap by using its iterator*/

    /***
     * Print the WordMap by using its iterator.
     */
    public  void printWordMap()
    {
        Word_Map.Node node;
        File_Map fmap;
        Iterator iter = wmap.iterator();
        System.out.println();
        while (iter.hasNext())
        {
            node = (Word_Map.Node) iter.next();
            fmap = (File_Map) node.getValue();
            System.out.println("Word:" + node.getKey());
            for(int i = 0; i < fmap.size(); i++)
            {
                System.out.printf("\t\t File name: %s, Positions: %s\n", fmap.fnames.get(i), fmap.occurances.get(i));
            }
            System.out.println();
        }
    }

    // TO TEST WORD (If you want, you can will use. )
    /*public void print()
    {
        File_Map fmap = (File_Map) wmap.get("April");
        String name;
        for(int i = 0; i < fmap.size(); i++)
        {
            name = fmap.fnames.get(i);
            int index = fmap.fnames.indexOf(name);
            System.out.printf("File name: %s , Positions: %s\n", name, fmap.occurances.get(index));
        }

    }*/
}
