import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 6732424917803935806L;
	
	public static JPanel panelGornji, panelDonji, panelCentar;

	public static JPanel panelInformacije;
	
	public enum URGENTNOST {OBICNA_PORUKA, VAZNA_PORUKA, OBAVESTENJE};
	
	MainWindow(BazaPodataka.Korisnik korisnik) {
		this.setTitle("Srbija Voz");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setAlwaysOnTop(false);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("./slike//srbija.png").getImage());
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(900, 700));
		
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
		
		panelGornji = new JPanel();
		
		panelGornji.setBorder(BorderFactory.createLineBorder(Color.black));
		panelGornji.setBackground(Color.LIGHT_GRAY);
		panelGornji.setPreferredSize(new Dimension(300, 40));
		panelGornji.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelGornji.setName("panelGornji");
		
		panelDonji = new JPanel();
		
		panelDonji.setBorder(BorderFactory.createLineBorder(Color.black));
		panelDonji.setBackground(Color.LIGHT_GRAY);
		panelDonji.setPreferredSize(new Dimension(100, 30));
		panelDonji.setName("panelDonji");
		
		panelCentar = new JPanel();
		panelCentar.setBorder(BorderFactory.createLineBorder(Color.white));
		panelCentar.setBackground(Color.DARK_GRAY);
		panelCentar.setLayout(null);
		panelCentar.setSize(this.getSize().width, this.getSize().height - panelGornji.getPreferredSize().height - panelGornji.getPreferredSize().height - 10);
		panelCentar.setName("panelCentar");
		
		panelInformacije = new JPanel();
		panelInformacije.setBorder(BorderFactory.createLineBorder(Color.white));
		panelInformacije.setBackground(Color.decode("#5F6C6C"));
		panelInformacije.setLayout(new GridLayout(2,1));
		panelInformacije.setSize(panelCentar.getSize().width / 2, 60);
		panelInformacije.setName("panelInformacije");
		panelInformacije.setLocation(0, 0);
		
		panelInformacije.add(label3);
		panelInformacije.add(label4);
		panelCentar.add(panelInformacije);
		
		panelGornji.add(label1);
		panelDonji.add(label2);
		
		DODAJ_OSTALE_STVARI(panelCentar, korisnik);
		
		this.getContentPane().add(panelGornji, BorderLayout.NORTH);
		this.getContentPane().add(panelDonji, BorderLayout.SOUTH);
		this.getContentPane().add(panelCentar, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public void DODAJ_OSTALE_STVARI(JPanel panelCentar, BazaPodataka.Korisnik korisnik) {
		
		JLabel label1 = new JLabel("<html> - Odlukom Vlade Republike Srbije 05 Broj 023-7361/2015 od 2. jula 2015. godine, osnovano je Akcionarsko društvo za železnièki prevoz putnika “Srbija Voz” Beograd, kao društvo sticalac dela imovine “Železnice Srbije” a.d. <br><br> - Društvo je upisano u registar Agencije za privredne registre, 10. avgusta 2015. godine, kada je steklo svojstvo pravnog lica i poèelo da posluje.</html>");
		label1.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		label1.setForeground(Color.white);
		
		JLabel label2 = new JLabel("O NAMA");
		label2.setFont(new Font("Arial", Font.BOLD, 18));
		label2.setForeground(Color.white);
		label2.setBorder(BorderFactory.createLineBorder(Color.black));
		label2.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel label3 = new JLabel("AKTUELNA OBAVESTENJA");
		label3.setFont(new Font("Arial", Font.BOLD, 18));
		label3.setForeground(Color.white);
		label3.setBorder(BorderFactory.createLineBorder(Color.black));
		label3.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel panel1 = new JPanel();
		
		panel1.setLayout(new GridLayout(8, 1));
		panel1.setLocation(new Point(panelCentar.getSize().width / 2 - 1, 0));
		panel1.setSize(new Dimension(panelCentar.getSize().width / 2 - 15, panelCentar.getSize().height / 3 * 2 - 19));
		panel1.setBorder(BorderFactory.createLineBorder(Color.white));
		panel1.setBackground(Color.decode("#5F6C6C"));
		panel1.setName("obavestenja");
		
		panel1.add(label3);
		
		JPanel panel2 = new JPanel();
		
		panel2.setLayout(new BorderLayout());
		panel2.setLocation(new Point(panelCentar.getSize().width / 2 - 1, panelCentar.getSize().height / 3 * 2 - 19));
		panel2.setSize(new Dimension(panelCentar.getSize().width / 2 - 15, panelCentar.getSize().height - panel1.getSize().height - 19));
		panel2.setBorder(BorderFactory.createLineBorder(Color.white));
		panel2.setBackground(Color.decode("#5F6C6C"));
		
		panel2.add(label2, BorderLayout.NORTH);
		panel2.add(label1, BorderLayout.CENTER);
		
		panelCentar.add(panel1);
		panelCentar.add(panel2);
		
		DODAJ_OBAVESTENJE(panel1, "Srbija Voz krece sa radom dana 02.07.2022!", URGENTNOST.OBAVESTENJE);
		DODAJ_OBAVESTENJE(panel1, "Prvi voz koji krece je voz Sombor - Novi Sad!!", URGENTNOST.OBICNA_PORUKA);
		DODAJ_OBAVESTENJE(panel1, "Zastajanje voza Sombor - Novi Sad u Gajdobri!", URGENTNOST.VAZNA_PORUKA);
		DODAJ_OBAVESTENJE(panel1, "Voz ce krenuti dana 03.07.2022!", URGENTNOST.OBICNA_PORUKA);
		
		JButton kupiKarte = napraviDugme("Kupi Karte"), listajKarte = napraviDugme("Listaj Karte"), brisiKarte = napraviDugme("Brisi Karte"), listajRute = napraviDugme("Listaj Rute");
		
		JPanel panel3 = new JPanel();
		panel3.setLocation(new Point(0, panelInformacije.getSize().height));
		panel3.setSize(new Dimension(panelCentar.getSize().width / 2, panelCentar.getSize().height - panelGornji.getSize().height - panelDonji.getSize().height - panelInformacije.getSize().height - 19 - ((korisnik.pozicija.equals("admin")) ? 130 : 0)));
		panel3.setBorder(BorderFactory.createLineBorder(Color.white));
		panel3.setBackground(Color.decode("#5F6C6C"));
		panel3.setLayout(null);
		
		JPanel panel4 = new JPanel();
		panel4.setSize(new Dimension(220, 260));
		panel4.setLocation(new Point(panel3.getSize().width / 2 - panel4.getSize().width / 2, panel3.getSize().height / 2 - panel4.getSize().height / 2));
		panel4.setBackground(Color.blue);
		panel4.setLayout(new GridLayout(4, 1, 30, 30));
		panel4.setOpaque(false);
		
		panel4.add(kupiKarte);
		panel4.add(brisiKarte);
		panel4.add(listajKarte);
		panel4.add(listajRute);
		
		panel3.add(panel4);
		
		if(korisnik.pozicija.equals("admin")) {
			DODAJ_ADMIN_DUGMAD(panelCentar);
		}
		
		panelCentar.add(panel3);
	}

	public void DODAJ_OBAVESTENJE(JPanel panel1, String obavestenje, URGENTNOST urgentnost) {
		
		int brojac = 0;
		
		for(Component c : panel1.getComponents()) {
			if(c instanceof JLabel) {
				brojac++;
			}
		}
		
		JLabel label = new JLabel("<html>" + brojac + ". " + obavestenje + "</html>");
		label.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label.setBackground(Color.decode("#1B2C36"));
		label.setHorizontalAlignment(JLabel.LEFT);
		
		ImageIcon[] ikona = {new ImageIcon("./slike/megafon.png"), new ImageIcon("./slike/oblak.png"), new ImageIcon("./slike/uzvicnik.png")}; 
		
		Image image, newimage;
		
		switch(urgentnost) {
			case OBAVESTENJE:
				image = ikona[0].getImage();
				newimage = image.getScaledInstance(42, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				ikona[0] = new ImageIcon(newimage);  
				
				label.setIcon(ikona[0]);
				
				label.setForeground(Color.decode("#25B0FF"));
			break;
			
			case OBICNA_PORUKA:
				image = ikona[1].getImage();
				newimage = image.getScaledInstance(42, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				ikona[1] = new ImageIcon(newimage);  
				
				label.setIcon(ikona[1]);
				
				label.setForeground(Color.decode("#25B0FF"));
				
				label.setForeground(Color.black);
			break;
			
			case VAZNA_PORUKA:
				
				image = ikona[2].getImage();
				newimage = image.getScaledInstance(42, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				ikona[2] = new ImageIcon(newimage);  
				
				label.setIcon(ikona[2]);
				
				label.setForeground(Color.red);
			break;
		}
		
		panel1.add(label);
	}
	
	public static JButton napraviDugme(String tekst) {
		JButton dugme = new JButton(tekst);
		
		dugme.setPreferredSize(new Dimension(100, 30));
		dugme.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE, 18));
		dugme.setFocusable(false);
		dugme.setBackground(Color.white);
		dugme.setForeground(Color.black);
		dugme.setHorizontalAlignment(JButton.CENTER);
		dugme.setVerticalAlignment(JButton.CENTER);
		
		dugme.setName(tekst);
		
		dugme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String naziv = ((JButton)e.getSource()).getName();
				
				System.out.println(naziv);
			}
			
		});
		
		return dugme;
	}
	
	public static void DODAJ_ADMIN_DUGMAD(JPanel panel) {
		JPanel panel1 = new JPanel();
		
		panel1.setSize(new Dimension(220, 100));
		panel1.setLocation(panelCentar.getSize().width / 4 - panel1.getSize().width / 2, panelCentar.getSize().height - panelInformacije.getSize().height - panelGornji.getSize().height - panelDonji.getSize().height - 75);
		panel1.setBackground(Color.decode("#5F6C6C"));
		panel1.setLayout(new GridLayout(2, 1, 20, 20));
		panel1.setOpaque(false);
		
		JButton dodajObavestenja = napraviDugme("Dodaj Obavestenje");
		JButton dodajVoznuRutu = napraviDugme("Dodaj Voznu Rutu");
		
		panel1.add(dodajVoznuRutu);
		panel1.add(dodajObavestenja);
		
		panel.add(panel1);
	}
}
