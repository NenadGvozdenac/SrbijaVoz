import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DodajObavestenjeFrame extends JFrame {
	
	private static final long serialVersionUID = -4830151880025487047L;

	JPanel panelGornji, panelDonji, panelCentar;
	
	DodajObavestenjeFrame() {
		
		BazaPodataka.Korisnik KORISNIK = new BazaPodataka.Korisnik("admin", "admin", "admin");
		
		this.setTitle("Srbija Voz");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setAlwaysOnTop(false);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("./slike//srbija.png").getImage());
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(900, 700));
		
		this.setLocationRelativeTo(null);
		
		panelGornji = new JPanel();
		
		panelGornji.setBorder(BorderFactory.createLineBorder(Color.white));
		panelGornji.setBackground(Color.LIGHT_GRAY);
		panelGornji.setPreferredSize(new Dimension(300, 40));
		panelGornji.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelGornji.setName("panelGornji");
		
		panelDonji = new JPanel();
		
		panelDonji.setBorder(BorderFactory.createLineBorder(Color.white));
		panelDonji.setBackground(Color.LIGHT_GRAY);
		panelDonji.setPreferredSize(new Dimension(100, 30));
		panelDonji.setName("panelDonji");
		
		panelCentar = new JPanel();
		panelCentar.setBorder(BorderFactory.createLineBorder(Color.white));
		panelCentar.setBackground(Color.DARK_GRAY);
		panelCentar.setLayout(null);
		panelCentar.setSize(this.getSize().width, this.getSize().height - panelGornji.getPreferredSize().height - panelGornji.getPreferredSize().height - 10);
		panelCentar.setName("panelCentar");
		
		this.getContentPane().add(panelGornji, BorderLayout.NORTH);
		this.getContentPane().add(panelDonji, BorderLayout.SOUTH);
		this.getContentPane().add(panelCentar, BorderLayout.CENTER);
		
		
		this.setVisible(true);
	}
}
