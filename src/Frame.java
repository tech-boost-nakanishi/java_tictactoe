import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Frame implements ActionListener {
	
	private static JFrame frame;
	
	private static Menu menuPanel;
	private static Setting settingPanel;
	private static Game gamePanel;
	
	public Frame() {
		frame = new JFrame();
		frame.setTitle("○×ゲーム");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JMenuBar jmb = new JMenuBar();
		frame.setJMenuBar(jmb);
		
		JMenu menu = new JMenu("メニュー");
		jmb.add(menu);
		
		JMenuItem mainmenu = new JMenuItem("メインメニュー");
		mainmenu.addActionListener(this);
		mainmenu.setActionCommand("mainmenu");
		menu.add(mainmenu);
		
		JMenuItem start = new JMenuItem("ゲーム開始");
		start.addActionListener(this);
		start.setActionCommand("start");
		menu.add(start);
		
		JMenuItem setting = new JMenuItem("設定画面");
		setting.addActionListener(this);
		setting.setActionCommand("setting");
		menu.add(setting);
		
		JMenuItem quit = new JMenuItem("ゲーム終了");
		quit.addActionListener(this);
		quit.setActionCommand("quit");
		menu.add(quit);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("mainmenu")) {
			changePanel("menuPanel");
		}
		else if(e.getActionCommand().equals("start")) {
			changePanel("gamePanel");
		}
		else if(e.getActionCommand().equals("setting")) {
			changePanel("settingPanel");
		}
		else if(e.getActionCommand().equals("quit")) {
			System.exit(1);
		}
	}

}
