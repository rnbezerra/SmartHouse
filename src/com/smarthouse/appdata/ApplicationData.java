package com.smarthouse.appdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.smarthouse.appdata.context.Block;
import com.smarthouse.appdata.context.Context;
import com.smarthouse.appdata.context.ContextArray;
import com.smarthouse.appdata.context.Info;
import com.smarthouse.appdata.context.Url;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ApplicationData {

	public static void LoadXml() {
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		
		
		ContextArray array = new ContextArray();
		array.contextList = new Context[1];
		Context c = new Context();
		c.info = new Info();
		c.info.name = "area_social";
		c.info.friendlyName = "Área Social";
		
		c.url = new Url();
		c.url.LightedImage = "/SmartHouse/Images/HouseMapImages/Lighted/area_social_partial.png";
		
		c.rangeBlocks = new Block[4];
		for (int i = 0; i < 4; i++) {
			Block b = new Block();
			b.positionX = 0;
			b.positionY = 1;
			b.width = 2;
			b.height = 3;
			c.rangeBlocks[i] = b; 
		}
		
		array.contextList[0] = c;

		xstream.alias("contexts", ContextArray.class);
		xstream.alias("context", Context.class);
		xstream.alias("block", Block.class);
		xstream.alias("url", Url.class);
		xstream.alias("info", Info.class);
		String xml = xstream.toXML(array);
		xml = new String();
//		try {
//			new java.io.PrintStream("XML/teste.xml").print(xml);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		
		try {
			ContextArray readFromFile = (ContextArray)xstream.fromXML((InputStream)new FileInputStream("XML/teste.xml"));
			System.out.println(readFromFile.contextList[0].info.friendlyName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
