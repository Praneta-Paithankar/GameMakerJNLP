package com.component;

import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JComponent;

import org.apache.log4j.Logger;

import com.infrastruture.Element;

public class Clock extends JComponent implements Element,Serializable{

	protected static Logger log = Logger.getLogger(Clock.class);
	private long milisecondsElapsed;
	public Clock() {
		milisecondsElapsed = 0;
	}

	public Clock(Clock c) {
		this.milisecondsElapsed = c.milisecondsElapsed;
	}
	
	public String getTime() {
		if (getSeconds() >= 10) {
			return Integer.toString(getMinutes()) + ":" + Integer.toString(getSeconds());
		} else {
			return Integer.toString(getMinutes()) + ":0" + Integer.toString(getSeconds());
		}
	}
	
	public void draw(Graphics g) {
		g.drawRect(0, 150, 250, 100);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		String time = getTime();
		g.drawString(time, 25, 80);
	}

	@Override
	public void reset() {
		milisecondsElapsed = 0;	
	}
	public void reset(Clock c) {
		milisecondsElapsed = c.milisecondsElapsed;	
	}
	public long getMilisecondsElapsed() {
		return milisecondsElapsed;
	}

	public void setMilisecondsElapsed(long milisecondsElapsed) {
		this.milisecondsElapsed = milisecondsElapsed;
	}

	public int getMinutes() {
		return (int) (milisecondsElapsed / 60000);
	}

	public int getSeconds() {
		return (int) ((milisecondsElapsed / 1000) % 60);
	}

	@Override
	public void addComponent(Element e) {
		//throw new UnsupportedOperationException();
	}

	@Override
	public void removeComponent(Element e) {
		//throw new UnsupportedOperationException();
	}
	@Override
	public void save(ObjectOutputStream op) {
		try {
			op.writeObject(this);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
		}
	}

	@Override
	public Element load(ObjectInputStream ip){
		try {
			return (Clock)ip.readObject();
		} catch (ClassNotFoundException | IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}
}