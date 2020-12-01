import javax.swing.JFrame;

public class Frame {
	
	private static JFrame frame;
	
	private static Menu menuPanel;
	private static Setting settingPanel;
	private static Game gamePanel;
	
	public Frame() {
		frame = new JFrame();
		frame.setTitle("○×ゲーム");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		menuPanel = new Menu();
		settingPanel = new Setting();
		gamePanel = new Game();
		frame.getContentPane().add(menuPanel);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void changePanel(String str) {
		frame.remove(menuPanel);
		frame.remove(settingPanel);
		frame.remove(gamePanel);
		
		if(str.equals("menuPanel")) {
			menuPanel = new Menu();
			frame.getContentPane().add(menuPanel);
		}
		else if(str.equals("settingPanel")) {
			settingPanel = new Setting();
			frame.getContentPane().add(settingPanel);
		}
		else if(str.equals("gamePanel")) {
			gamePanel = new Game();
			frame.getContentPane().add(gamePanel);
		}
		
		frame.pack();
	}

	public static void main(String[] args) {
		new Frame();
	}

}
