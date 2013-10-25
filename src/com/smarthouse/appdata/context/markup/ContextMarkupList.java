package com.smarthouse.appdata.context.markup;

import java.util.ArrayList;

public class ContextMarkupList extends ArrayList<ContextMarkup> {

	public ContextMarkup firstMarkup() {
		return this.get(0);
	}

	public ContextMarkup lastMarkup() {
		return this.get(this.size()-1);
	}
	
}
