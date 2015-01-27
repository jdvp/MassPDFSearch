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

    public void displayText(String text);

    public void clearDisplay();

    public void displayPDFButton(File file);

}