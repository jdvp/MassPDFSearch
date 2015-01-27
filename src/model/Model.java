package model;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * This is the model of the MassPDFSearch System.
 * It is responsible for parsing the data from all of the PDF documents in order to make the document searchable.
 *
 * @author JD Porterfield
 */
public class Model {

    /**
     * The adapter used to communicate with the view
     */
    IM2VAdapter view;

    /**
     * The list of PDF documents loaded into the system
     */
    ArrayList<PDF> pdfs = new ArrayList<PDF>();

    /**
     * The Constructor for the Model object.
     * Sets the adapter to the view
     *
     * @param adapter The adapter used to communicate with the view
     */
    public Model(IM2VAdapter adapter)
    {
        view = adapter;
    }

    /**
     * A no-op for this model
     */
    public void start(){

    }

    /**
     * Clears all of the stored PDFs from the model
     */
    public void clear(){
        pdfs = new ArrayList<PDF>();
    }

    /**
     * Loads any PDFs found in the input directory to the model
     *
     * @param directory The directory to search for PDF files
     */
    public void loadFiles(File directory){
        File[] files = directory.listFiles();
        if(files != null){
            for(File f: files) {
                if(f.isDirectory())
                    loadFiles(f);
                else if(f.getName().contains(".pdf")) {
                    System.out.println("pdf = " + f);
                    PDFParser parser = null;
                    PDDocument pdDoc = null;
                    COSDocument cosDoc = null;
                    PDFTextStripper pdfStripper;

                    String parsedText;
                    try {
                        parser = new PDFParser(new FileInputStream(f));
                        parser.parse();
                        cosDoc = parser.getDocument();
                        pdfStripper = new PDFTextStripper();
                        pdDoc = new PDDocument(cosDoc);
                        parsedText = pdfStripper.getText(pdDoc);
                        parsedText = parsedText.replaceAll("[^A-Za-z0-9. ]+", "");
                        pdDoc.close();
                        cosDoc.close();
                        PDF newPDF = new PDF(f, parsedText);
                        pdfs.add(newPDF);
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            if (cosDoc != null)
                                cosDoc.close();
                            if (pdDoc != null)
                                pdDoc.close();
                        } catch (Exception e1) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        }
    }

    /**
     * Searches all of the PDF documents for sentences relevant to the input query.
     * If there are results, a link to the document and all of the sentences that contain the query are displayed
     * by making a call to the view.
     *
     * @param query The phrase to search for
     */
    public void search(String query){
        view.clearDisplay();
        view.displayText("Results for search \'"+query+"\'");
        if(pdfs.size() > 0){
            for(PDF pdf : pdfs){
                ArrayList<String> results = pdf.search(query);
                if(results != null){
                    view.displayText("\n");
                    File file = pdf.getFile();
                    //File searchFile = new File(file.getPath()+"#search=\""+query+"\"");
                    view.displayPDFButton(file);
                    for(String s: results)
                        view.displayText("\n\t" + s);
                }
            }
        }
    }
}
