import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Setting extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final int width = 500, height = 300;
	
	public Setting() {
		this.setPreferredSize(new Dimension(width, height));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height);
		
		g2d.setFont(new Font("arial", Font.BOLD, 26));
		g2d.setColor(Color.WHITE);
		g2d.drawString("設定画面", width / 2 - g2d.getFontMetrics().stringWidth("設定画面") / 2, 50);
	}

}
