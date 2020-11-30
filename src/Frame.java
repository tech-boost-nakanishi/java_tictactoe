import javax.swing.JFrame;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	Menu menuPanel;
	Setting settingPanel;
	
	public Frame() {
		this.setTitle("○×ゲーム");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		menuPanel = new Menu();
		settingPanel = new Setting();
		this.getContentPane().add(menuPanel);
		
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Frame();
	}

}
