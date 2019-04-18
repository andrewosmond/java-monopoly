package com.monopoly.utility;

import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class PolygonButton extends JButton {
	private static final long serialVersionUID = 1L;
	private String name;
    private Shape polygon;
    
    public PolygonButton(Polygon polygon, String name) {
    	this.name = name;
    	this.polygon = polygon;
	}

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void paintBorder( Graphics g ) {
        ((Graphics2D)g).draw(polygon);
    }
    
    public void paintComponent( Graphics g ) {
        ((Graphics2D)g).fill(polygon);
    }
    
    public Dimension getPreferredSize() {
    	 return new Dimension(polygon.getBounds().width, polygon.getBounds().height);
    }
    
    public boolean contains(int x, int y) {
        return polygon.contains(x, y);
    }
}