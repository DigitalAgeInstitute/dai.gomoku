package dai.gomoku.client.swing;

import javax.swing.JButton;

public class MyPlayButton extends JButton {
	private boolean marked = false;
	
	public boolean isMarked ( ) {
		return marked;
	}
	
	public void setMarked( boolean marked) {
		this.marked = marked;
	}
	
	
}
