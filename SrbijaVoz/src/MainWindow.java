import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 6732424917803935806L;
	
	MainWindow() {
		
	}
	
	MainWindow(BazaPodataka.Korisnik korisnik) {
		this.setTitle("Srbija Voz");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(700, 500));
		
		this.setLocationRelativeTo(null);
		
		JLabel label1 = new JLabel("Dobrodosli u Srbija voz!");
		label1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel label2 = new JLabel("Hvala sto koristite Srbija voz!");
		label2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel label3 = new JLabel("KORISNIK: " + korisnik.korisnicko_ime.toUpperCase());
		label3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label3.setForeground(Color.white);
		label3.setSize(100, 30);
		
		JLabel label4 = new JLabel("POZICIJA: " + korisnik.pozicija.toUpperCase());
		label4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label4.setForeground(Color.white);
		
		label4.setSize(100, 30);
		
		JPanel panelGornji = new JPanel();
		
		panelGornji.setBorder(BorderFactory.createLineBorder(Color.black));
		panelGornji.setBackground(Color.LIGHT_GRAY);
		panelGornji.setPreferredSize(new Dimension(300, 40));
		panelGornji.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelGornji.setName("panelGornji");
		
		JPanel panelDonji = new JPanel();
		
		panelDonji.setBorder(BorderFactory.createLineBorder(Color.black));
		panelDonji.setBackground(Color.LIGHT_GRAY);
		panelDonji.setPreferredSize(new Dimension(100, 30));
		panelDonji.setName("panelDonji");
		
		JPanel panelCentar = new JPanel();
		panelCentar.setBorder(BorderFactory.createLineBorder(Color.black));
		panelCentar.setBackground(Color.DARK_GRAY);
		panelCentar.setLayout(null);
		panelCentar.setSize(this.getSize().width, this.getSize().height - panelGornji.getPreferredSize().height - panelGornji.getPreferredSize().height - 10);
		panelCentar.setName("panelCentar");
		
		JPanel panelInformacije = new JPanel();
		panelInformacije.setBorder(BorderFactory.createLineBorder(Color.black));
		panelInformacije.setBackground(Color.decode("#343B3B"));
		panelInformacije.setLayout(new GridLayout(2,1));
		panelInformacije.setSize(panelCentar.getSize().width / 2, 60);
		panelInformacije.setName("panelInformacije");
		panelInformacije.setLocation(0, 0);
		
		panelInformacije.add(label3);
		panelInformacije.add(label4);
		panelCentar.add(panelInformacije);
		
		panelGornji.add(label1);
		panelDonji.add(label2);
		
		this.getContentPane().add(panelGornji, BorderLayout.NORTH);
		this.getContentPane().add(panelDonji, BorderLayout.SOUTH);
		this.getContentPane().add(panelCentar, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public static void instanceFrame(MainWindow frame) {
		
	}
	
}
