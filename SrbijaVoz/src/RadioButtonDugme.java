import java.awt.Color;

import javax.swing.JRadioButton;

public class RadioButtonDugme extends JRadioButton {
	
	private static final long serialVersionUID = 5231744511098962859L;

	RadioButtonDugme(String tekst) {
		this.setText(tekst);
		this.setFocusable(false);
		this.setSize(140, 100);
		
		this.setOpaque(false);
		this.setForeground(Color.white);
	}
}
