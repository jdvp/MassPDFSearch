package view;


import java.io.File;

/**
 * The adapter used by the view to communicate
 * with the model
 *
 * @author JD Porterfield
 */
public interface IV2MAdapter {

    /**
     * Null adapter
     */
    IV2MAdapter NULL_ADAPTER = new IV2MAdapter() {

        public void loadFiles(File file) {}

        public void searchFor(String query){}

        public void clear(){}
    };

    /**
     * Loads the PDF files from the specified directory into the model
     *
     * @param file The directory to search for PDFs
     */
    public void loadFiles(File file);

    /**
     * Calls on the model to search for a specified input query
     *
     * @param query The query that we are searching for
     */
    public void searchFor(String query);

    /**
     * Calls on the model to clear its memory of any loaded documents
     */
    public void clear();

}