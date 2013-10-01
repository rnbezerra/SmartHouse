package com.smarthouse.appdata.context.markup;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ContextMarkup extends ArrayList<ContextSide> {

	public static final int RANGE_SIZE = 16; 
	
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
}
