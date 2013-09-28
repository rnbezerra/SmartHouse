package com.smarthouse.simulation.screen;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

import com.smarthouse.simulation.screen.stage.HouseMapImage;
import com.smarthouse.simulation.screen.stage.LinePanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame {

	public static final String HOUSE_PERSON_PATH = "C:\\GitHub\\SmartHouse\\Images\\person.png";
	public static final String HOUSE_CONTEXT_PATH = "Images/HouseMapImages/Lighted/area_social_partial.png";

	JPanel stagePanel;
	HouseMapImage houseMap;
	JLabel lblPerson;
	ArrayList<JLabel> contexts = new ArrayList<JLabel>();
	private JPopupMenu houseMapPopupMenu;

	private int mouseCoordinatesX;
	private int mouseCoordinatesY;

	public MainScreen() {

		// define o contentPane
		getContentPane().setLayout(new BorderLayout(0, 0));

		// cria o label do mapa da casa e adicona ele ao palco
		houseMap = new HouseMapImage();

		// cria o popupMenu do label do mapa da casa
		houseMapPopupMenu = new JPopupMenu();
		houseMap.addPopup(houseMapPopupMenu);
		JMenuItem mntmPosicionarPessoaAqui = new JMenuItem("Posicionar Pessoa Aqui");
		mntmPosicionarPessoaAqui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				houseMapPopupMenuActionPerformedHandler(evt);
			}
		});
		// mntmPosicionarPessoaAqui.setMnemonic('P');

		houseMapPopupMenu.add(mntmPosicionarPessoaAqui);
		
		//adiciona contextos
		for (int i = 0; i < 1; i++) {

			JLabel context = new JLabel();
			context.setBounds(0, 0, houseMap.getImage().getIconWidth(), houseMap.getImage().getIconHeight());
			context.setIcon(new ImageIcon(HOUSE_CONTEXT_PATH));
			
			contexts.add(context);
		}
		

		// carrega a imagem da pessoa e adiciona o label no palco
		ImageIcon personImage = new ImageIcon(HOUSE_PERSON_PATH);
		lblPerson = new JLabel();
		lblPerson.setBounds(0, 0, personImage.getIconWidth(),personImage.getIconHeight());
		lblPerson.setIcon(personImage);

		

		// muda tamanho da janela para comportar o mapa da casa
		this.setSize(houseMap.getImage().getIconWidth(), houseMap.getImage().getIconHeight());
		// cria stage
		stagePanel = new JPanel();
		stagePanel.setBounds(0, 0, houseMap.getImage().getIconWidth(), houseMap.getImage().getIconHeight());
		//getContentPane().add(stagePanel, BorderLayout.CENTER);
		getContentPane().add(new LinePanel(), BorderLayout.CENTER);
		stagePanel.setLayout(null);

		int zIndex = 0;
//		stagePanel.add(lblPerson, zIndex++);
//		//adicionando contextos no stage
//		for (JLabel lbl : contexts) {
//			stagePanel.add(lbl, zIndex++);
//		}
//		
//		stagePanel.add(houseMap, zIndex++);
		

	}

	private void houseMapPopupMenuActionPerformedHandler(ActionEvent evt) {

		int x = houseMap.getMouseClickCoordinatesX() - (lblPerson.getWidth()/2);
		int y = houseMap.getMouseClickCoordinatesY() - (lblPerson.getHeight()/2);
		
		lblPerson.setLocation(x, y);
//		System.out.println(String.format("houseMap x=%s y=%s", houseMap.getMouseClickCoordinatesX(), houseMap.getMouseClickCoordinatesY()));
//		System.out.println(String.format("lblPerson x=%s y=%s", lblPerson.getX(),lblPerson.getY()));
		System.out.println();

	}

}
