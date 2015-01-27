package model;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * The system representation of a PDF document.
 *
 * @author JD Porterfield
 */
public class PDF {

    /**
     * The actual file object that this PDF represents
     */
    File myFile;
    /**
     * The text stored in the PDF
     */
    String myText;
    /**
     * The text stored in the PDF broken down into its constituent sentences
     */
    ArrayList<String> sentences = new ArrayList<String>();

    /**
     * Creates a PDF object and splits the text into sentences.
     *
     * @param file The PDF document's file
     * @param text The txt of the PDF
     */
    public PDF(File file, String text){
        myFile = file;
        myText = text;
        StringTokenizer tk = new StringTokenizer(myText, ".;?!");
        while(tk.hasMoreTokens())
            sentences.add(tk.nextToken());
    }

    /**
     * Searches the PDF's sentences for any matches to the input query
     *
     * @param query The phrase to search for within the sentences
     * @return A list of sentences from this PDF that matches the query
     */
    public ArrayList<String> search(String query){
        if(!myText.toLowerCase().contains(query.toLowerCase()))
            return null;
        else {
            ArrayList<String> res = new ArrayList<String>();
            for(String s: sentences)
            {
                if(s.toLowerCase().contains(query.toLowerCase()))
                    res.add(s);
            }
            return res;
        }
    }

    /**
     * Returns the file associated with this PDF
     *
     * @return The file associated with this PDF
     */
    public File getFile(){
        return myFile;
    }
}
