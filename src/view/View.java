package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * This is the View of the MassPDFSearch system.
 * It is responsible for getting input from the user as to which PDFs to search and what phrase to query exactly.
 * It is also responsible for displaying the results to the user.
 */
public class View extends JFrame {

    /**
     * The adapter used to communicate with the model
     */
    private IV2MAdapter model;

    private JTextPane textArea;

    public View(IV2MAdapter adapter) {
        model = adapter;
        initGUI();
    }

    public void start(){
        setVisible(true);
    }

    public void initGUI(){
        setSize(1000,750);
        setTitle("Mass PDF Search");
        setLayout(new BorderLayout());
        setResizable(false);


        //make control panel
        final JPanel controlPanel = new JPanel(new FlowLayout());
        final JButton chooseDirectory = new JButton("Choose Directory");
        chooseDirectory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showOpenDialog(controlPanel);
                File retFile = fc.getSelectedFile();
                System.out.println("selected file "+ retFile.toString());
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    model.clear();
                    model.loadFiles(retFile);
                }
                textArea.setText("PDFs loaded successfully");
            }
        });
        final JTextField query = new JTextField(30);
        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(query.getText() != "")
                    model.searchFor(query.getText());
                query.setText("");
            }
        });
        controlPanel.add(chooseDirectory);
        controlPanel.add(query);
        controlPanel.add(search);
        add(controlPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea = new JTextPane();
        textArea.setEditable(false);
//        textArea.setLineWrap(true);
//        textArea.setWrapStyleWord(true);
        scrollPane.setViewportView(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void displayText(String text){
        try{
            Document doc = textArea.getDocument();
            doc.insertString(doc.getLength(), text, null);
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void displayPDFButton(final File file){
        JButton jumpToFile = new JButton(file.getName());
        jumpToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        jumpToFile.setBackground(Color.WHITE);
        jumpToFile.setBorder(new EmptyBorder(5,5,5,5));
        jumpToFile.setForeground(Color.BLUE);

        textArea.insertComponent(jumpToFile);
    }

    public void clearDisplay(){textArea.setText("");}
}
