package com.monopoly.model;

import java.util.Random;

public class Dice {
	private int firstValue;
	private int secondValue;
	private Random rand = new Random();
	
	public int random(int min, int max) {
		return rand.nextInt(max-min+1) + min;
	}
	
	public void roll() {
		firstValue = random(1,6);
		secondValue = random(1,6);
	}
	
	public boolean isDouble() {
		return (firstValue == secondValue);
	}

	public int getFirstValue() {
		return firstValue;
	}

	public int getSecondValue() {
		return secondValue;
	}
	
	public int getSum() {
		return firstValue + secondValue;
	}
}
