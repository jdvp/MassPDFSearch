package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This is the View of the MassPDFSearch system.
 * It is responsible for getting input from the user as to which PDFs to search and what phrase to query exactly.
 * It is also responsible for displaying the results to the user.
 */
public class View extends JFrame {

    /**
     * The adapter used to communicate with the model
     */
    IV2MAdapter model;

    public View(IV2MAdapter adapter) {
        model = adapter;
        initGUI();
    }

    public void start(){
        setVisible(true);
    }

    public void initGUI(){
        setSize(600,500);
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
                if (returnVal == JFileChooser.APPROVE_OPTION)
                    model.loadFiles(retFile);

            }
        });
        JTextField query = new JTextField(30);
        JButton search = new JButton("Search");
        controlPanel.add(chooseDirectory);
        controlPanel.add(query);
        controlPanel.add(search);
        add(controlPanel, BorderLayout.NORTH);
    }
}
