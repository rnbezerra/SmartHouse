package com.smarthouse.simulation.screen.stage;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;

public class HouseMapImage extends JLabel {

	public static final String HOUSE_MAP_PATH = "Images/HouseMapImages/Shadow/planta-baixa.png";

	private int mouseCoordinatesX;
	private int mouseCoordinatesY;
	private ImageIcon image;

	public HouseMapImage() {
		super();

		buildComponent();
	}

	public int getMouseClickCoordinatesX() {
		return mouseCoordinatesX;
	}

	private void setMouseCoordinatesX(int mouseCoordinatesX) {
		this.mouseCoordinatesX = mouseCoordinatesX;
	}

	public int getMouseClickCoordinatesY() {
		return mouseCoordinatesY;
	}

	private void setMouseCoordinatesY(int mouseCoordinatesY) {
		this.mouseCoordinatesY = mouseCoordinatesY;
	}

	private void loadImage() {
		image = new ImageIcon(HOUSE_MAP_PATH);
	}

	private void buildComponent() {
		loadImage();

		this.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		this.setIcon(image);
		
//		this.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent evt) {
//				if(evt.getButton() == evt.BUTTON3){
//					mouseCoordinatesX = evt.getPoint().x;
//					mouseCoordinatesY = evt.getPoint().y;
//				}
//			}
//		});

	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}
	
	public void addPopup(final JPopupMenu popup) {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {				
				mouseCoordinatesX = e.getPoint().x;
				mouseCoordinatesY = e.getPoint().y;
 
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
