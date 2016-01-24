package com.shpp.cs.aokhotnikov.namesurfer;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    private NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
    private NameSurferGraph graph;
    private JTextField textField;

    /* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {

        add(new JLabel("Name:"), "North");

        textField = new JTextField(20);
        add(textField, "North");
        textField.setActionCommand("EnterPressed");
        textField.addActionListener(this);

        add(new JButton("Graph"), "North");
        add(new JButton("Clear"), "North");

        graph = new NameSurferGraph();
        add(graph);

        addActionListeners();

    }

	/* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand().equals("Graph")) || (e.getActionCommand().equals("EnterPressed"))){
            NameSurferEntry entry = dataBase.findEntry(textField.getText());
            if (entry != null) {
                graph.addEntry(entry);
            }
            textField.setText("");
        } else if (e.getActionCommand().equals("Clear")) {
            graph.clear();
        }
    }
}
