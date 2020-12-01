import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;

public class Setting extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	
	private final int width = 500, height = 300;
	private Rectangle faRect, saRect, circleRect, xRect, backRect, changeRect;
	private String hoveredRect = "";
	public static String faorsa, circleorx;
	private boolean isChanged = false;
	
	
	public Setting() {
		this.setPreferredSize(new Dimension(width, height));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		faRect = new Rectangle(190, 98, 80, 30);
		saRect = new Rectangle(300, 98, 80, 30);
		circleRect = new Rectangle(190, 178, 80, 30);
		xRect = new Rectangle(300, 178, 80, 30);
		backRect = new Rectangle(190, 250, 80, 30);
		changeRect = new Rectangle(300, 250, 80, 30);
		
		changeSetting();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height);
		
		g2d.setFont(new Font("arial", Font.BOLD, 22));
		g2d.setColor(Color.WHITE);
		g2d.drawString("設定画面", width / 2 - g2d.getFontMetrics().stringWidth("設定画面") / 2, 50);
		
		g2d.drawString("先攻or後攻", 40, 120);
		g2d.setFont(new Font("arial", Font.BOLD, 24));
		g2d.drawString("○or×", 60, 200);
		
		g2d.fill(faRect);
		g2d.fill(saRect);
		g2d.fill(circleRect);
		g2d.fill(xRect);
		g2d.fill(backRect);
		g2d.fill(changeRect);
		if(faorsa.equals("fa")) {
			g2d.setColor(Color.YELLOW);
			g2d.fill(faRect);
		}
		else if(faorsa.equals("sa")) {
			g2d.setColor(Color.YELLOW);
			g2d.fill(saRect);
		}
		if(circleorx.equals("circle")) {
			g2d.setColor(Color.YELLOW);
			g2d.fill(circleRect);
		}
		else if(circleorx.equals("x")) {
			g2d.setColor(Color.YELLOW);
			g2d.fill(xRect);
		}
		if(hoveredRect.equals("backRect")) {
			g2d.setColor(Color.CYAN);
			g2d.fill(backRect);
		}
		else if(hoveredRect.equals("changeRect")) {
			g2d.setColor(Color.CYAN);
			g2d.fill(changeRect);
		}
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("arial", Font.BOLD, 20));
		g2d.drawString("先攻", faRect.x + 18, faRect.y + g2d.getFontMetrics().getAscent() + 3);
		g2d.drawString("後攻", saRect.x + 18, saRect.y + g2d.getFontMetrics().getAscent() + 3);
		g2d.drawString("メニュー", backRect.x, backRect.y + g2d.getFontMetrics().getAscent() + 3);
		g2d.drawString("変更", changeRect.x + 18, changeRect.y + g2d.getFontMetrics().getAscent() + 3);
		
		g2d.setFont(new Font("arial", Font.BOLD, 36));
		g2d.drawString("○", circleRect.x + 30, circleRect.y + g2d.getFontMetrics().getAscent() - 8);
		g2d.drawString("×", xRect.x + 30, xRect.y + g2d.getFontMetrics().getAscent() - 5);
	}
	
	public void changeSetting() {
		try {
			File file = new File("res/setting.txt");
			if(!file.exists()) {
				file.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				faorsa = "fa";
				circleorx = "circle";
				bw.write(faorsa + " " + circleorx);
				bw.close();
			}
			else {
				if(isChanged) {
					file.delete();
					file.createNewFile();
					BufferedWriter bw = new BufferedWriter(new FileWriter(file));
					bw.write(faorsa + " " + circleorx);
					bw.close();
				}
				else {
					BufferedReader br = new BufferedReader(new FileReader(file));
					String data = br.readLine();
					String[] datasplit = data.split(" ");
					faorsa = datasplit[0];
					circleorx = datasplit[1];
					br.close();
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		isChanged = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Rectangle mouseRect = new Rectangle(e.getX(), e.getY(),1, 1);
		if(mouseRect.intersects(backRect)) {
			hoveredRect = "backRect";
		}
		else if(mouseRect.intersects(changeRect)) {
			hoveredRect = "changeRect";
		}
		else {
			hoveredRect = "";
		}
		
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Rectangle mouseRect = new Rectangle(e.getX(), e.getY(),1, 1);
		if(mouseRect.intersects(faRect)) {
			faorsa = "fa";
		}
		else if(mouseRect.intersects(saRect)) {
			faorsa = "sa";
		}
		else if(mouseRect.intersects(circleRect)) {
			circleorx = "circle";
		}
		else if(mouseRect.intersects(xRect)) {
			circleorx = "x";
		}
		else if(mouseRect.intersects(backRect)) {
			Frame.changePanel("menuPanel");
		}
		else if(mouseRect.intersects(changeRect)) {
			isChanged = true;
			changeSetting();
			Frame.changePanel("menuPanel");
		}
		
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
