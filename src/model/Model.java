package model;

import java.io.File;

/**
 * This is the model of the MassPDFSearch System.
 * It is responsible for parsing the data from all of the PDF documents in order to make the document searchable.
 */
public class Model {

    /**
     * The adapter used to communicate with the view
     */
    IM2VAdapter view;

    public Model(IM2VAdapter adapter)
    {
        view = adapter;
    }

    public void start(){

    }

    /**
     * Loads any PDFs found in the input directory to the model
     *
     * @param file The directory to search for PDF files
     */
    public void loadFiles(File file){

    }
}
