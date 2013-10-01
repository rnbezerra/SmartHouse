package com.smarthouse.appdata.context.markup;

import java.awt.Point;

public class ContextSide{

	private Point startPoint;
	private Point endPoint;
	
	public ContextSide() {
		super();
	}
			
	public ContextSide(Point startPoint, Point endPoint) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Point StartPoint() {
		return startPoint;
	}
	
	public void StartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public Point EndPoint() {
		return endPoint;
	}
	
	public void EndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	
	
}
