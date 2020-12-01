import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Menu extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	
	private final int width = 500, height = 300;
	private Rectangle startRect, settingRect, quitRect;
	private String hoveredRect = "";
	
	public Menu() {
		this.setPreferredSize(new Dimension(width, height));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		startRect = new Rectangle(width / 2 - 60, 100, 120, 35);
		settingRect = new Rectangle(width / 2 - 60, 160, 120, 35);
		quitRect = new Rectangle(width / 2 - 60, 220, 120, 35);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height);
		
		g2d.setFont(new Font("arial", Font.BOLD, 22));
		g2d.setColor(Color.WHITE);
		g2d.drawString("メインメニュー", width / 2 - g2d.getFontMetrics().stringWidth("メインメニュー") / 2, 50);
		
		g2d.setColor(Color.WHITE);
		g2d.fill(startRect);
		g2d.fill(settingRect);
		g2d.fill(quitRect);
		if(hoveredRect.equals("startRect")) {
			g2d.setColor(Color.CYAN);
			g2d.fill(startRect);
		}
		else if(hoveredRect.equals("settingRect")) {
			g2d.setColor(Color.CYAN);
			g2d.fill(settingRect);
		}
		else if(hoveredRect.equals("quitRect")) {
			g2d.setColor(Color.CYAN);
			g2d.fill(quitRect);
		}
		
		g2d.setColor(Color.BLACK);
		g2d.drawString("ゲーム開始", startRect.x + 5, startRect.y + g2d.getFontMetrics().getAscent() + 5);
		g2d.drawString("設定画面", settingRect.x + 15, settingRect.y + g2d.getFontMetrics().getAscent() + 5);
		g2d.drawString("ゲーム終了", quitRect.x + 5, quitRect.y + g2d.getFontMetrics().getAscent() + 5);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Rectangle mouseRect = new Rectangle(e.getX(), e.getY(),1, 1);
		if(mouseRect.intersects(startRect)) {
			Frame.changePanel("gamePanel");
		}
		else if(mouseRect.intersects(settingRect)) {
			Frame.changePanel("settingPanel");
		}
		else if(mouseRect.intersects(quitRect)) {
			System.exit(1);
		}
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

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Rectangle mouseRect = new Rectangle(e.getX(), e.getY(),1, 1);
		if(mouseRect.intersects(startRect)) {
			hoveredRect = "startRect";
		}
		else if(mouseRect.intersects(settingRect)) {
			hoveredRect = "settingRect";
		}
		else if(mouseRect.intersects(quitRect)) {
			hoveredRect = "quitRect";
		}
		else {
			hoveredRect = "";
		}
		repaint();
	}

}
