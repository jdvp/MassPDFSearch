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
 */
public class Model {

    /**
     * The adapter used to communicate with the view
     */
    IM2VAdapter view;

    ArrayList<PDF> pdfs = new ArrayList<PDF>();

    public Model(IM2VAdapter adapter)
    {
        view = adapter;
    }

    public void start(){

    }

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

    public void search(String query){
        view.clearDisplay();
        view.displayText("Results for search \'"+query+"\'");
        if(pdfs.size() > 0){
            for(PDF pdf : pdfs){
                ArrayList<String> results = pdf.search(query);
                if(results != null){
                    view.displayText("\n");
                    view.displayPDFButton(pdf.getFile());
                    for(String s: results)
                        view.displayText("\n\t" + s);
                }
            }
        }
    }
}
