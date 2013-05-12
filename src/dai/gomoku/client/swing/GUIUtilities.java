package dai.gomoku.client.swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GUIUtilities {
	public static JMenu setJMenu(JMenu menu) {
		menu.setFont(new Font("Dialog", Font.BOLD, 12));
		menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menu.setForeground(new Color(0, 0, 0));
		return menu;
	}

	public static JMenuItem setJMenuItem(JMenuItem menuItem, String sCaption, ActionListener handler) {
		menuItem.setText(sCaption);
		menuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menuItem.setFont(new Font("Dialog", Font.PLAIN, 12));
		menuItem.setForeground(new Color(0, 0, 0));
		menuItem.addActionListener(handler);
		return menuItem;
	}
	
	public static void switchPanels(CardLayout card, JPanel cardpanel, String toPanel) {
		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, toPanel);
	}
	
	/*public static boolean checkValidCharacters(List<String> xtersList, String [] xtersArray) {
		boolean invalid = false;
		for (int i = 0; i < xtersArray.length; i++) {
			System.out.println((xtersArray[i])+" "+xtersList.indexOf(xtersArray[i]));
			if(xtersList.indexOf(xtersArray[i]) == -1) {
				invalid = true;
				//break;
			}
			else {
				invalid = false;
				//break;
			}
			//break;
		}
		return invalid;	
	}*/
	
	public static void center( Window window ) {

        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = window.getSize().width;
        int h = window.getSize().height;
        int x = (dim.width - w) / 5;
        int y = ((dim.height - h) / (dim.height - h)) + 35;

        // Move the window
        window.setLocation(x, y);
    }
}
