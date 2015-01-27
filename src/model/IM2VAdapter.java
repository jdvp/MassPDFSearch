package model;


import java.io.File;

/**
 * This is the adapter that the model uses to interact with the view
 *
 * @author JD Porterfield
 */
public interface IM2VAdapter {

    /**
     * Null adapter
     */
    IM2VAdapter NULL_ADAPTER = new IM2VAdapter(){

        public void displayText(String text){}

        public void clearDisplay(){}

        public void displayPDFButton(File file){}
    };

    /**
     * Calls on the View to display the given text
     *
     * @param text The text to display
     */
    public void displayText(String text);

    /**
     * Calls on the View to clear its display area
     */
    public void clearDisplay();

    /**
     * Calls on the view to display a hyperlink-like button to the given file
     *
     * @param file The file to display a button for
     */
    public void displayPDFButton(File file);

}