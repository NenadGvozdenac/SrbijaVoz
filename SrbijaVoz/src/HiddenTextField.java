import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class HiddenTextField extends JTextField {
	
	private static final long serialVersionUID = 9070094544983563966L;

	Font pocetniFont = new Font("Times New Roman", Font.ITALIC, 24);
	Font unosFont = new Font("Times New Roman", Font.PLAIN, 24);
	
	HiddenTextField(String tekst) {
		
		this.setText(tekst);
		this.setFont(pocetniFont);
		this.setForeground(Color.LIGHT_GRAY);
		
		this.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField)e.getSource();

				if(field.getText().equals(tekst)) {
					field.setText("");
					field.setFont(pocetniFont);
					field.setForeground(Color.LIGHT_GRAY);
				} else {
					field.setText(field.getText());
					field.setFont(unosFont);
					field.setForeground(Color.BLACK);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField field = (JTextField)e.getSource();
				
				if (field.getText().equals(tekst)|| field.getText().length()==0) {  
					field.setText(tekst);  
					field.setFont(pocetniFont);  
					field.setForeground(Color.LIGHT_GRAY);
			    } else {  
			        field.setText(field.getText());  
			        field.setFont(unosFont);  
			        field.setForeground(Color.BLACK);
			    }  
				
			}
			
		});
		
	}
}
