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

    public void searchFor(String query);

    public void clear();

}