package model;


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

        public void displayResults(String text){}
    };

    public void displayResults(String text);

}