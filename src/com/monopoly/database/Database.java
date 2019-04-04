package com.monopoly.database;

import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;

import com.monopoly.model.City;
import com.monopoly.model.Island;
import com.monopoly.model.Property;
import com.monopoly.utility.PolygonButton;
import com.monopoly.window.InfoWindow;

public class Database {
	private static Vector<JButton> buttonList = new Vector<JButton>();
	private static Vector<Property> propertyList = new Vector<Property>();
	
	public static Vector<JButton> getButtonsList() {
		return buttonList;
	}

	public static Vector<Property> getPropertyList() {
		return propertyList;
	}
	
	public static int getPropertyIndex(String name) {
		int idx = 0;
		for (Property property : propertyList) {
			if (property.getName().equals(name)) {
				return idx; 
			}
			idx++;
		}
		return -1;
	}

	public static void initButton() {
		 Polygon polygon;
		 JButton btn;
		  
		 // Start Button
		 polygon = new Polygon();
		 polygon.addPoint(509,556);
		 polygon.addPoint(437,598);
		 polygon.addPoint(509,642);
		 polygon.addPoint(580,598);
		 btn = new PolygonButton(polygon, "Start");
		 buttonList.add(btn);
		  
		 // Beijing Button
		 polygon = new Polygon();
		 polygon.addPoint(459,527);
		 polygon.addPoint(502,552);
		 polygon.addPoint(442,587);
		 polygon.addPoint(396,564);
		 btn = new PolygonButton(polygon, "Beijing");
		 buttonList.add(btn);
		 
		 // Chance1 Button
		 polygon = new Polygon();
		 polygon.addPoint(410,500);
		 polygon.addPoint(453,525);
		 polygon.addPoint(392,558);
		 polygon.addPoint(349,534);
		 btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Chance button
				}
			});
		 btn = new PolygonButton(polygon, "Chance");
		 
		 buttonList.add(btn);
		 
		 // Bangkok Button
		 polygon = new Polygon();
		 polygon.addPoint(359,471);
		 polygon.addPoint(405,495);
		 polygon.addPoint(342,530);
		 polygon.addPoint(297,506);
		 btn = new PolygonButton(polygon, "Bangkok");
		 buttonList.add(btn);
		 
		 // Phuket Button
		 polygon = new Polygon();
		 polygon.addPoint(309,442);
		 polygon.addPoint(352,465);
		 polygon.addPoint(292,499);
		 polygon.addPoint(249,476);
		 btn = new PolygonButton(polygon, "Phuket");
		 buttonList.add(btn);
		 
		 // Taipei Button
		 polygon = new Polygon();
		 polygon.addPoint(258,414);
		 polygon.addPoint(301,438);
		 polygon.addPoint(240,470);
		 polygon.addPoint(199,449);
		 btn = new PolygonButton(polygon, "Taipei");
		 buttonList.add(btn);
		 
		 // New Delhi Button
		 polygon = new Polygon();
		 polygon.addPoint(208,384);
		 polygon.addPoint(251,409);
		 polygon.addPoint(191,441);
		 polygon.addPoint(148,419);
		 btn = new PolygonButton(polygon, "New Delhi");
		 buttonList.add(btn);
		 
		 // Seoul Button
		 polygon = new Polygon();
		 polygon.addPoint(158,355);
		 polygon.addPoint(200,380);
		 polygon.addPoint(140,412);
		 polygon.addPoint(99,389);
		 btn = new PolygonButton(polygon, "Seoul");
		 buttonList.add(btn);
		 
		 // Jail Button
		 polygon = new Polygon();
		 polygon.addPoint(83,308);
		 polygon.addPoint(154,350);
		 polygon.addPoint(82,392);
		 polygon.addPoint(9,352);
		 btn = new PolygonButton(polygon, "Jail");
		 buttonList.add(btn);
		 
		 // Papua Button
		 polygon = new Polygon();
		 polygon.addPoint(98,312);
		 polygon.addPoint(142,288);
		 polygon.addPoint(201,323);
		 polygon.addPoint(161,348);
		 btn = new PolygonButton(polygon, "Papua");
		 buttonList.add(btn);
		 
		 // Tokyo Button
		 polygon = new Polygon();
		 polygon.addPoint(149,283);
		 polygon.addPoint(192,259);
		 polygon.addPoint(250,293);
		 polygon.addPoint(209,316);
		 btn = new PolygonButton(polygon, "Tokyo");
		 buttonList.add(btn);
		 
		 // Sydney Button
		 polygon = new Polygon();
		 polygon.addPoint(198,256);
		 polygon.addPoint(240,230);
		 polygon.addPoint(300,266);
		 polygon.addPoint(258,287);
		 btn = new PolygonButton(polygon, "Sydney");
		 buttonList.add(btn);
		 
		 // Chance2 Button
		 polygon = new Polygon();
		 polygon.addPoint(248,226);
		 polygon.addPoint(291,201);
		 polygon.addPoint(352,236);
		 polygon.addPoint(309,261);
		 btn = new PolygonButton(polygon, "Chance");
		 buttonList.add(btn);
		 
		 // Singapore Button
		 polygon = new Polygon();
		 polygon.addPoint(298,198);
		 polygon.addPoint(339,173);
		 polygon.addPoint(398,207);
		 polygon.addPoint(358,230);
		 btn = new PolygonButton(polygon, "Singapore");
		 buttonList.add(btn);
		 
		 // Bali Button
		 polygon = new Polygon();
		 polygon.addPoint(345,168);
		 polygon.addPoint(392,142);
		 polygon.addPoint(451,176);
		 polygon.addPoint(407,201);
		 btn = new PolygonButton(polygon, "Bali");
		 buttonList.add(btn);
		 
		 // Sao Paulo Button
		 polygon = new Polygon();
		 polygon.addPoint(396,137);
		 polygon.addPoint(441,113);
		 polygon.addPoint(498,146);
		 polygon.addPoint(458,169);
		 btn = new PolygonButton(polygon, "Sao Paulo");
		 buttonList.add(btn);
		 
		 // Chest Button
		 polygon = new Polygon();
		 polygon.addPoint(439,105);
		 polygon.addPoint(508,63);
		 polygon.addPoint(575,104);
		 polygon.addPoint(508,146);
		 btn = new PolygonButton(polygon, "Chest");
		 buttonList.add(btn);
		 
		 // Prague Button
		 polygon = new Polygon();
		 polygon.addPoint(515,148);
		 polygon.addPoint(573,114);
		 polygon.addPoint(619,137);
		 polygon.addPoint(558,172);
		 btn = new PolygonButton(polygon, "Prague");
		 buttonList.add(btn);
		 
		 // Hawaii Button
		 polygon = new Polygon();
		 polygon.addPoint(564,177);
		 polygon.addPoint(624,142);
		 polygon.addPoint(669,168);
		 polygon.addPoint(607,201);
		 btn = new PolygonButton(polygon, "Hawaii");
		 buttonList.add(btn);
		 
		 // Berlin Button
		 polygon = new Polygon();
		 polygon.addPoint(615,207);
		 polygon.addPoint(675,171);
		 polygon.addPoint(719,196);
		 polygon.addPoint(658,230);
		 btn = new PolygonButton(polygon, "Berlin");
		 buttonList.add(btn);
		 
		 // Chance3 Button
		 polygon = new Polygon();
		 polygon.addPoint(665,235);
		 polygon.addPoint(726,199);
		 polygon.addPoint(769,225);
		 polygon.addPoint(707,259);
		 btn = new PolygonButton(polygon, "Chance");
		 buttonList.add(btn);
		 
		 // Moscow Button
		 polygon = new Polygon();
		 polygon.addPoint(715,264);
		 polygon.addPoint(775,229);
		 polygon.addPoint(818,254);
		 polygon.addPoint(757,287);
		 btn = new PolygonButton(polygon, "Moscow");
		 buttonList.add(btn);
		 
		 // Geneva Button
		 polygon = new Polygon();
		 polygon.addPoint(765,293);
		 polygon.addPoint(825,259);
		 polygon.addPoint(869,283);
		 polygon.addPoint(806,316);
		 btn = new PolygonButton(polygon, "Geneva");
		 buttonList.add(btn);
		 
		 // Rome Button
		 polygon = new Polygon();
		 polygon.addPoint(815,322);
		 polygon.addPoint(875,287);
		 polygon.addPoint(918,311);
		 polygon.addPoint(856,344);
		 btn = new PolygonButton(polygon, "Rome");
		 buttonList.add(btn);
		 
		 // Loli Police Button
		 polygon = new Polygon();
		 polygon.addPoint(863,351);
		 polygon.addPoint(934,312);
		 polygon.addPoint(1003,352);
		 polygon.addPoint(932,390);
		 btn = new PolygonButton(polygon, "Loli Police");
		 buttonList.add(btn);
		 
		 // Bintan Button
		 polygon = new Polygon();
		 polygon.addPoint(815,382);
		 polygon.addPoint(858,356);
		 polygon.addPoint(918,391);
		 polygon.addPoint(875,414);
		 btn = new PolygonButton(polygon, "Bintan");
		 buttonList.add(btn);
		 
		 // London Button
		 polygon = new Polygon();
		 polygon.addPoint(765,410);
		 polygon.addPoint(808,385);
		 polygon.addPoint(867,419);
		 polygon.addPoint(826,441);
		 btn = new PolygonButton(polygon, "London");
		 buttonList.add(btn);
		 
		 // Paris Button
		 polygon = new Polygon();
		 polygon.addPoint(717,439);
		 polygon.addPoint(760,414);
		 polygon.addPoint(818,449);
		 polygon.addPoint(776,472);
		 btn = new PolygonButton(polygon, "Paris");
		 buttonList.add(btn);
		 
		 // Chance4 Button
		 polygon = new Polygon();
		 polygon.addPoint(666,468);
		 polygon.addPoint(709,442);
		 polygon.addPoint(770,476);
		 polygon.addPoint(727,499);
		 btn = new PolygonButton(polygon, "Chance");
		 buttonList.add(btn);
		 
		 // New York Button
		 polygon = new Polygon();
		 polygon.addPoint(617,496);
		 polygon.addPoint(660,471);
		 polygon.addPoint(718,505);
		 polygon.addPoint(676,529);
		 btn = new PolygonButton(polygon, "New York");
		 buttonList.add(btn);
		 
		 // Medical Bill Button
		 polygon = new Polygon();
		 polygon.addPoint(566,524);
		 polygon.addPoint(609,499);
		 polygon.addPoint(670,534);
		 polygon.addPoint(626,559);
		 btn = new PolygonButton(polygon, "Medical Bill");
		 buttonList.add(btn);
		 
		 // Jakarta Button
		 polygon = new Polygon();
		 polygon.addPoint(516,555);
		 polygon.addPoint(559,529);
		 polygon.addPoint(619,564);
		 polygon.addPoint(575,587);
		 btn = new PolygonButton(polygon, "Jakarta");
		 buttonList.add(btn);
		 
		 for (JButton jButton : buttonList) {
			 jButton.addActionListener( new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
					 int index = getPropertyIndex(jButton.getName());
					 if (index != -1) {
						 InfoWindow.view(propertyList.get(index));
					 }
				 }
			 }); 
		 }
	}
	
	public static void initProperty() {
		propertyList.add(new City(0, 0, "Beijing", 20000, 10000, 30000, 50000, 50000));
		propertyList.add(new City(0, 1, "Bangkok", 26000, 10000, 30000, 50000, 50000));
		
		propertyList.add(new City(0, 2, "Taipei", 48000, 20000, 60000, 100000, 100000));
		propertyList.add(new City(0, 3, "New Delhi", 54000, 20000, 60000, 100000, 100000));
		propertyList.add(new City(0, 4, "Seoul", 60000, 20000, 60000, 100000, 100000));
		
		propertyList.add(new City(0, 5, "Tokyo", 72000, 30000, 90000, 150000, 150000));
		propertyList.add(new City(1, 0, "Sydney", 72000, 30000, 90000, 150000, 150000));
		
		propertyList.add(new City(1, 1, "Singapore", 94000, 40000, 120000, 200000, 200000));
		propertyList.add(new City(1, 2, "Sao Paulo", 100000, 40000, 120000, 200000, 200000));
		
		propertyList.add(new City(1, 3, "Prague", 118000, 50000, 150000, 250000, 250000));
		propertyList.add(new City(1, 4, "Berlin", 124000, 50000, 150000, 250000, 250000));
		
		propertyList.add(new City(1, 5, "Moscow", 140000, 60000, 180000, 300000, 300000));
		propertyList.add(new City(2, 0, "Geneva", 146000, 60000, 180000, 300000, 300000));
		propertyList.add(new City(2, 1, "Rome", 146000, 60000, 180000, 300000, 300000));
		
		propertyList.add(new City(2, 2, "London", 164000, 70000, 210000, 350000, 350000));
		propertyList.add(new City(2, 3, "Paris", 170000, 70000, 210000, 350000, 350000));
		
		propertyList.add(new City(2, 4, "New York", 192000, 80000, 240000, 400000, 400000));
		propertyList.add(new City(2, 5, "Jakarta", 200000, 80000, 240000, 400000, 400000));
		
		propertyList.add(new Island(3, 0, "Phuket", 70500));
		propertyList.add(new Island(3, 1, "Papua", 70500));
		propertyList.add(new Island(3, 2, "Bali", 70500));
		propertyList.add(new Island(3, 3, "Hawaii", 70500));
		propertyList.add(new Island(3, 4, "Bintan", 70500));
	}
}