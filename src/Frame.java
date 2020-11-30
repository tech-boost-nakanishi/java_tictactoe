import javax.swing.JFrame;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Frame() {
		this.setTitle("○×ゲーム");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Frame();
	}

}
