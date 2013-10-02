package com.smarthouse.appdata.context.markup;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class ContextMarkup extends ArrayList<ContextSide> {

	public static final int RANGE_SIZE = 16; 
	public Color markupLineColor;
	
	public ContextSide firstSide() {
		return this.get(0);
	}

	public ContextSide lastSide() {
		return this.get(this.size()-1);
	}
	
	public boolean isNearOrigin() {
		if(this.size() > 1){
			Point current = lastSide().EndPoint();
			Point origin = firstSide().StartPoint();
			Point min = new Point(origin.x-(RANGE_SIZE/2), origin.y-(RANGE_SIZE/2));
			Point max = new Point(origin.x+(RANGE_SIZE/2), origin.y+(RANGE_SIZE/2));
			Rectangle r = new Rectangle(origin.x-(RANGE_SIZE/2), origin.y-(RANGE_SIZE/2), 50, 50);
						
			boolean pointInside = ((current.x > origin.x-(RANGE_SIZE/2) && current.x < origin.x+(RANGE_SIZE/2)) && 
								   (current.y > origin.y-(RANGE_SIZE/2) && current.y < origin.y+(RANGE_SIZE/2))); 
			
			return pointInside;
		}
		else return false;
	}

	public Color getMarkupLineColor() {
		
		if(markupLineColor == null){
			Random rand = new Random();
			int r = rand.nextInt(155) + 100;
			int g = rand.nextInt(155) + 100;
			int b = rand.nextInt(155) + 100;
//			markupLineColor = new Color(rand.nextInt()%255, rand.nextInt()%255, rand.nextInt()%255);
			markupLineColor = new Color(r,g,b);
		}
		
		return markupLineColor;
	}

}
