package com.shpp.cs.aokhotnikov.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private ArrayList<NameSurferEntry> listEntrys = new ArrayList<>();
    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        // You fill in the rest //
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        listEntrys.clear();
        update();
    }

	
	/* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        listEntrys.add(entry);
        update();
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawGridOfCharts();
        drawCharts();
    }

    /**
     *  Draws the charts of names using Gline and GLable on the screen
     */
    private void drawCharts() {

        ArrayList<Color> colorsList = setColorsOfCharts(listEntrys.size());

        for (int i = 0; i < listEntrys.size(); i++) {

            int rank = listEntrys.get(i).getRank(1);
            //calculating the coordinates x1 and y1
            double x1 = 0;
            double y1 = calculateY(rank);

            //label of rank
            String rankForLabel = (rank == 0) ? "*" : rank + "";
            drawLabel(listEntrys.get(i).getName() + " " + rankForLabel, x1, y1, colorsList.get(i), "SansSerif-10");

            for (int j = 2; j <= NDECADES; j++) {

                rank = listEntrys.get(i).getRank(j);
                //calculating the coordinates x2 and y2
                double x2 = x1 + getWidth() * 1.0 / NDECADES;
                double y2 = calculateY(rank);

                //label of rank
                rankForLabel = (rank == 0) ? "*" : rank + "";
                drawLabel(listEntrys.get(i).getName() + " " + rankForLabel, x2, y2, colorsList.get(i), "SansSerif-10");

                GLine line = new GLine(x1, y1, x2, y2);
                line.setColor(colorsList.get(i));
                add(line);

                x1 = x2;
                y1 = y2;
            }
        }
    }

    /**
     * Calculates the coordinate of the y-axis
     * @param rank The rank associated with an entry for a particular decade
     * @return coordinate Y
     */
    private double calculateY(int rank) {
        double numPixelsY = getHeight() - GRAPH_MARGIN_SIZE * 2;
        double y = rank * 1.0 / MAX_RANK * numPixelsY;
        y = ((y == 0) || (rank >= MAX_RANK)) ? (numPixelsY + GRAPH_MARGIN_SIZE) : (y + GRAPH_MARGIN_SIZE);
        return y;
    }
    /**
     * Displays the text
     * @param str The text
     * @param x The x coordinate of the lower-left corner of the text
     * @param y The y coordinate of the lower-left corner of the text
     * @param color The text color
     * @param font The text font
     */
    private void drawLabel(String str, double x, double y, Color color, String font){
        GLabel label = new GLabel(str);
        label.setColor(color);
        label.setFont(font);
        add(label, x, y);
    }

    /**
     * Draws a grid on the background, which consists of vertical lines (decades),
     * and horizontal lines that separate the upper and lower borders and names decades
     */
    private void drawGridOfCharts(){
        double x = 0;
        double widthBetweenVerticals = getWidth() * 1.0 / NDECADES;
        int year = START_DECADE;
        for (int i = 0; i < NDECADES; i++) {
            //vertical line
            add(new GLine(x, 0, x, getHeight()));
            //label of decade
            drawLabel(year + "", x, getHeight(), Color.BLACK, "SansSerif-18");

            year += 10;
            x += widthBetweenVerticals;
        }
        //horizontal lines
        add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
        add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
    }

    /**
     * Sets the color of each chart in the sequence: blue, red, magenta, black, blue, red ...
     * @param numCharts Number of charts
     * @return Color sequence
     */
    private ArrayList<Color> setColorsOfCharts(int numCharts){
        Color[] list = {Color.blue, Color.red, Color.magenta, Color.black};
        ArrayList<Color> colors = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < numCharts; i++) {
            colors.add(list[k]);
            k = (k == 3) ? 0 : k + 1;
        }
        return colors;
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
