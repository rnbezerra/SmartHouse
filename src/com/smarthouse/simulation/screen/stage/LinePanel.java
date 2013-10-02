package com.smarthouse.simulation.screen.stage;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardEndHandler;

import com.smarthouse.appdata.context.Context;
import com.smarthouse.appdata.context.markup.ContextMarkup;
import com.smarthouse.appdata.context.markup.ContextMarkupDrawHelper;
import com.smarthouse.appdata.context.markup.ContextMarkupList;
import com.smarthouse.appdata.context.markup.ContextSide;

/**
 * @see http://stackoverflow.com/questions/6991648
 * @see http://stackoverflow.com/questions/6887296
 * @see http://stackoverflow.com/questions/5797965
 */
public class LinePanel extends JPanel {

    private MouseHandler mouseHandler = new MouseHandler();
    private KeyBoardHandler keyboardHandler = new KeyBoardHandler();
    private ContextMarkupList markupList = new ContextMarkupList();
    private boolean drawing;
    private Color defaultColor = Color.blue;

    public LinePanel() {
        this.setPreferredSize(new Dimension(640, 480));
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.addKeyListener(keyboardHandler);
        this.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        ImageIcon icon = new ImageIcon("Images/planta-baixa.jpg");
        
        g.drawImage(icon.getImage(), 0, 0, null);
        
        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(defaultColor);
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(4,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        
//        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        for (ContextMarkup markup : markupList) {
        	if(markup.isNearOrigin() && markup.equals(markupList.lastMarkup())){
        		Point origin = markup.firstSide().StartPoint();
        		g.setColor(Color.YELLOW);
        		g.fillOval(origin.x-(ContextMarkup.RANGE_SIZE/2), origin.y-(ContextMarkup.RANGE_SIZE/2), ContextMarkup.RANGE_SIZE, ContextMarkup.RANGE_SIZE);
        	}
        	
        	g.setColor(markup.getMarkupLineColor());
        	
        	for (ContextSide side : markup) {
        		g.drawLine(side.StartPoint().x, side.StartPoint().y, side.EndPoint().x, side.EndPoint().y);				
			}        	     	
		}
    }

    private class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
//            p1 = e.getPoint();
//            p2 = p1;
            if(markupList.isEmpty() || !drawing){
            	markupList.add(new ContextMarkup());
            	markupList.lastMarkup().add(new ContextSide(e.getPoint(),e.getPoint()));
                drawing = true;
            }
            
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
//            drawing = false;
//            p2 = e.getPoint();            
        	ContextMarkup markup = markupList.lastMarkup();
        	
        	if(markup.isNearOrigin() && markup.size() > 2){
        		drawing = false;
        		markup.lastSide().EndPoint(markup.firstSide().StartPoint());
        	}
        	else if(markup != null && markup.lastSide()!= null ){
            	markup.lastSide().EndPoint(e.getPoint());
            	markup.add(new ContextSide(e.getPoint(),e.getPoint()));
            }
                    	
        	
        	
            
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        	mouseMoved(e);
        }
        
        @Override
        public void mouseMoved(MouseEvent e) {        	
            if (drawing) {
//                p2 = e.getPoint();
            	if(markupList.lastMarkup() != null && markupList.lastMarkup().lastSide()!= null ){
            		markupList.lastMarkup().lastSide().EndPoint(e.getPoint());
            	}
                repaint();
            }
        }
        
//        @Override
//        public void mouseClicked(MouseEvent arg0) {
//        	super.mouseClicked(arg0);
//        	
//        	ContextMarkup markup = markupList.lastMarkup();
//        	
//        	if(markup.isNearOrigin() && markup.size() > 1){
//        		drawing = false;
//        		markup.lastSide().EndPoint(markup.firstSide().StartPoint());
//        	}
//        	
//        	repaint();
//        }
    }

    private class KeyBoardHandler extends KeyAdapter /*implements KeyListener*/{
    	private boolean deletePressed = false;
    	
    	@Override
		public void keyPressed(KeyEvent e) {
			//Sy1stem.out.println(e.getKeyChar() + " " + e.getKeyCode() + "   " + (e.getKeyCode() == KeyEvent.VK_DELETE));
			if(e.getKeyCode() == KeyEvent.VK_DELETE){
				deletePressed = true;
			}
		}
		
    	@Override
    	public void keyReleased(KeyEvent e) {
    		if(deletePressed && markupList.lastMarkup() != null && markupList.lastMarkup().lastSide()!= null ){
    			deletePressed = false;
    			
    			Point p = markupList.lastMarkup().lastSide().EndPoint();
    			markupList.remove(markupList.size()-1);
    			markupList.lastMarkup().lastSide().EndPoint(p);
    			repaint();
    		}
    	}
    }
    
    private class ControlPanel extends JPanel {

    	private static final int DELTA = 10;

        public ControlPanel() {
            this.add(new MoveButton("\u2190", KeyEvent.VK_LEFT, -DELTA, 0));
            this.add(new MoveButton("\u2191", KeyEvent.VK_UP, 0, -DELTA));
            this.add(new MoveButton("\u2192", KeyEvent.VK_RIGHT, DELTA, 0));
            this.add(new MoveButton("\u2193", KeyEvent.VK_DOWN, 0, DELTA));

        }

        private class MoveButton extends JButton {

            KeyStroke k;
            int dx, dy;

            public MoveButton(String name, int code,
                    final int dx, final int dy) {
                super(name);
                this.k = KeyStroke.getKeyStroke(code, 0);
                this.dx = dx;
                this.dy = dy;
                this.setAction(new AbstractAction(this.getText()) {

                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        LinePanel.this.p1.translate(dx, dy);
//                        LinePanel.this.p2.translate(dx, dy);
                        LinePanel.this.repaint();
                    }
                });
                ControlPanel.this.getInputMap(WHEN_IN_FOCUSED_WINDOW)
                    .put(k, k.toString());
                ControlPanel.this.getActionMap()
                    .put(k.toString(), new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MoveButton.this.doClick();
                    }
                });
            }
        }

    
    }

    private void display() {
        JFrame f = new JFrame("LinePanel");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.add(new ControlPanel(), BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new LinePanel().display();
            }
        });
    }
}