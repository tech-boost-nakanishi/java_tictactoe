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

public class Game extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	private final int width = 500, height = 600;
	private Rectangle menuRect, againRect, settingRect;
	private String hoveredRect = "";
	public static String[] board = new String[9];
	public static String playerState;
	public static String playerWinner;
	
	public Game() {
		this.setPreferredSize(new Dimension(width, height));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		menuRect = new Rectangle(50, 530, 80, 30);
		againRect = new Rectangle(210, 530, 80, 30);
		settingRect = new Rectangle(370, 530, 80, 30);
		
		for(int i = 0; i < board.length; i++) {
			board[i] = State.BLANK.toString();
		}
		
		playerWinner = State.BLANK.toString();
		
		if(Setting.faorsa.equals("fa")) {
			playerState = State.USER.toString();
		}
		else {
			playerState = State.COMPUTER.toString();
			GameLogic.computerLogic();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, width, width);
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, width, width, height - width);
		
		g2d.setColor(Color.BLACK);
		g2d.drawLine(167, 0, 167, 500);
		g2d.drawLine(334, 0, 334, 500);
		g2d.drawLine(0, 167, 500, 167);
		g2d.drawLine(0, 334, 500, 334);
		
		g2d.setColor(Color.WHITE);
		g2d.fill(menuRect);
		g2d.fill(againRect);
		g2d.fill(settingRect);
		if(hoveredRect.equals("menuRect")) {
			g2d.setColor(Color.CYAN);
			g2d.fill(menuRect);
		}
		else if(hoveredRect.equals("againRect")) {
			g2d.setColor(Color.CYAN);
			g2d.fill(againRect);
		}
		else if(hoveredRect.equals("settingRect")) {
			g2d.setColor(Color.CYAN);
			g2d.fill(settingRect);
		}
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("arial", Font.BOLD, 18));
		g2d.drawString("メニュー", menuRect.x + 4, menuRect.y + g2d.getFontMetrics().getAscent() + 4);
		g2d.drawString("最初から", againRect.x + 4, againRect.y + g2d.getFontMetrics().getAscent() + 4);
		g2d.drawString("設定画面", settingRect.x + 4, settingRect.y + g2d.getFontMetrics().getAscent() + 4);
		
		for(int i = 0; i < board.length; i++) {
			if(board[i].equals(State.USER.toString())) {
				if(Setting.circleorx.equals("circle")) {
					drawCircle(g, i);
				}
				else {
					drawX(g, i);
				}
			}
			else if(board[i].equals(State.COMPUTER.toString())) {
				if(Setting.circleorx.equals("circle")) {
					drawX(g, i);
				}
				else {
					drawCircle(g, i);
				}
			}
		}
		
		if(!playerWinner.equals(State.BLANK.toString())) {
			g2d.setColor(new Color(0, 0, 0, 200));
			g2d.fillRect(0, 0, width, width);
			
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("arial", Font.BOLD, 26));
			if(playerWinner.equals(State.USER.toString())) {
				g2d.drawString("あなたの勝ちです", width / 2 - g2d.getFontMetrics().stringWidth("あなたの勝ちです") / 2, width / 2 + g2d.getFontMetrics().getAscent());
			}
			else if(playerWinner.equals(State.COMPUTER.toString())) {
				g2d.drawString("あなたの負けです", width / 2 - g2d.getFontMetrics().stringWidth("あなたの負けです") / 2, width / 2 + g2d.getFontMetrics().getAscent());
			}
			else if(playerWinner.equals(State.DRAW.toString())) {
				g2d.drawString("引き分けです", width / 2 - g2d.getFontMetrics().stringWidth("引き分けです") / 2, width / 2 + g2d.getFontMetrics().getAscent());
			}
		}
	}
	
	public void drawX(Graphics g, int index) {
		Graphics2D g2d = (Graphics2D)g;
		
		int diff = 5;
		int xPos = getX(index) * 167;
		int yPos = getY(index) * 167;
		
		g2d.setColor(Color.BLUE);
		g2d.drawLine(xPos + diff, yPos + diff, xPos + 167 - diff, yPos + 167 - diff);
		g2d.drawLine(xPos + 167 - diff, yPos + diff, xPos + diff, yPos + 167 - diff);
	}
	
	public void drawCircle(Graphics g, int index) {
		Graphics2D g2d = (Graphics2D)g;
		
		int diff = 5;
		int xPos = getX(index) * 167;
		int yPos = getY(index) * 167;
		
		g2d.setColor(Color.RED);
		g2d.drawOval(xPos + diff, yPos + diff, 167 - diff * 2, 167 - diff * 2);
	}
	
	public int getIndex(int mx, int my) {
		if(mx >= 0 && mx <= 167 && my >= 0 && my <= 167) {
			return 0;
		}
		else if(mx >= 167 && mx <= 334 && my >= 0 && my <= 167) {
			return 1;
		}
		else if(mx >= 334 && mx <= 500 && my >= 0 && my <= 167) {
			return 2;
		}
		else if(mx >= 0 && mx <= 167 && my >= 167 && my <= 334) {
			return 3;
		}
		else if(mx >= 167 && mx <= 334 && my >= 167 && my <= 334) {
			return 4;
		}
		else if(mx >= 334 && mx <= 500 && my >= 167 && my <= 334) {
			return 5;
		}
		else if(mx >= 0 && mx <= 167 && my >= 334 && my <= 500) {
			return 6;
		}
		else if(mx >= 167 && mx <= 334 && my >= 334 && my <= 500) {
			return 7;
		}
		else if(mx >= 334 && mx <= 500 && my >= 334 && my <= 500) {
			return 8;
		}
		else return mx;
	}
	
	public int getX(int index) {
		if(index == 0) return 0;
		else if(index == 1) return 1;
		else if(index == 2) return 2;
		else if(index == 3) return 0;
		else if(index == 4) return 1;
		else if(index == 5) return 2;
		else if(index == 6) return 0;
		else if(index == 7) return 1;
		else if(index == 8) return 2;
		else return index;
	}
	
	public int getY(int index) {
		if(index == 0) return 0;
		else if(index == 1) return 0;
		else if(index == 2) return 0;
		else if(index == 3) return 1;
		else if(index == 4) return 1;
		else if(index == 5) return 1;
		else if(index == 6) return 2;
		else if(index == 7) return 2;
		else if(index == 8) return 2;
		else return index;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Rectangle mouseRect = new Rectangle(e.getX(), e.getY(),1, 1);
		if(mouseRect.intersects(menuRect)) {
			hoveredRect = "menuRect";
		}
		else if(mouseRect.intersects(againRect)) {
			hoveredRect = "againRect";
		}
		else if(mouseRect.intersects(settingRect)) {
			hoveredRect = "settingRect";
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
		int mx = e.getX();
		int my = e.getY();
		Rectangle mouseRect = new Rectangle(mx, my,1, 1);
		if(mouseRect.intersects(menuRect)) {
			Frame.changePanel("menuPanel");
		}
		else if(mouseRect.intersects(againRect)) {
			Frame.changePanel("gamePanel");
		}
		else if(mouseRect.intersects(settingRect)) {
			Frame.changePanel("settingPanel");
		}
		
		if(mx >= 0 && mx <= width && my >= 0 && my <= width) {
			if(playerState.equals(State.USER.toString())) {
				if(board[getIndex(mx, my)].equals(State.BLANK.toString())) {
					board[getIndex(mx, my)] = State.USER.toString();
					if(!GameLogic.checkWinner()) {
						GameLogic.computerLogic();
					}
				}
			}
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
