package com.smarthouse.appdata.context.markup;

import java.awt.Graphics;

public class ContextMarkupDrawHelper extends Thread implements Runnable{	
	
	private Graphics graphics;
	private ContextMarkup markup;
	
	public ContextMarkupDrawHelper(Graphics graphics, ContextMarkup markup) {
		super();
		this.graphics = graphics;
		this.markup = markup;
	}

	@Override
	public void run() {
		super.run();
		drawMarkupIntoGraphics();
	}
		
	public void drawMarkupIntoGraphics() {		
		System.out.println("!");
		
		for (ContextSide side : markup) {
			graphics.drawLine(side.StartPoint().x, side.StartPoint().y, side.EndPoint().x, side.EndPoint().y);			
		}
		
		this.interrupt();
	}
	
	

}
