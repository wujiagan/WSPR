package run;

import javax.swing.UIManager;
import view.WsprGUI;

public class Main {
	public static void main(String[] args) {
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			new WsprGUI();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}
