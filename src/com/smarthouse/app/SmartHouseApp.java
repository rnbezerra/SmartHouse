package com.smarthouse.app;

import javax.swing.JFrame;

import com.smarthouse.appdata.ApplicationData;
import com.smarthouse.simulation.screen.MainScreen;

public class SmartHouseApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationData.LoadXml();
		
		// TODO Auto-generated method stub
		MainScreen screen = new MainScreen();
		screen.setTitle("Tela");
		screen.setVisible(true);
		screen.setLocationRelativeTo(null);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
