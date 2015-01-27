package model;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by JD on 1/26/2015.
 */
public class PDF {

    File myFile;
    String myText;
    ArrayList<String> sentences = new ArrayList<String>();

    public PDF(File file, String text){
        myFile = file;
        myText = text;
        StringTokenizer tk = new StringTokenizer(myText, ".;?!");
        while(tk.hasMoreTokens())
            sentences.add(tk.nextToken());
    }

    public ArrayList<String> search(String query){
        if(!myText.contains(query))
            return null;
        else {
            ArrayList<String> res = new ArrayList<String>();
            for(String s: sentences)
            {
                if(s.contains(query))
                    res.add(s);
            }
            return res;
        }
    }

    public File getFile(){
        return myFile;
    }
}
