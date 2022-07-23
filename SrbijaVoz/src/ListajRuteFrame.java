import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListajRuteFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -3312634520169087758L;
	
	public static BazaPodataka.Korisnik KORISNIK;
	public static BazaPodataka.Ruta AKTIVNARUTA;
	public static JPanel panelCentar;
	public static JComboBox<String> komboBox, komboBox2, komboBox3;
	public static JLabel[] labele = new JLabel[6];
	public static JButton dugme;
	
	ListajRuteFrame(BazaPodataka.Korisnik KORISNIK) {
		ListajRuteFrame.KORISNIK = KORISNIK;
		this.setTitle("Srbija Voz");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setAlwaysOnTop(false);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("SrbijaVoz\\slike\\srbija.png").getImage());
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(900, 700));
		
		this.setLocationRelativeTo(null);
		
		JPanel panelGornji = new JPanel();
		panelGornji.setBorder(BorderFactory.createLineBorder(Color.white));
		panelGornji.setBackground(Color.LIGHT_GRAY);
		panelGornji.setPreferredSize(new Dimension(300, 40));
		panelGornji.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelGornji.setName("panelGornji");
		
		JPanel panelDonji = new JPanel();
		
		panelDonji.setBorder(BorderFactory.createLineBorder(Color.white));
		panelDonji.setBackground(Color.LIGHT_GRAY);
		panelDonji.setPreferredSize(new Dimension(100, 30));
		panelDonji.setName("panelDonji");
		
		panelCentar = new JPanel();
		panelCentar.setBorder(BorderFactory.createLineBorder(Color.white));
		panelCentar.setBackground(Color.DARK_GRAY);
		panelCentar.setLayout(null);
		panelCentar.setSize(this.getSize().width, this.getSize().height - panelGornji.getPreferredSize().height - panelDonji.getPreferredSize().height - 10);
		panelCentar.setName("panelCentar");
		
		JLabel label1 = new JLabel("Dobrodosli u Srbija voz!");
		label1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel label2 = new JLabel("Hvala sto koristite Srbija voz!");
		label2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		panelGornji.add(label1);
		panelDonji.add(label2);
		
		JPanel dropBoxPanel = new JPanel();
		dropBoxPanel.setSize(this.getSize().width / 3 * 2, 150);
		dropBoxPanel.setLocation(this.getSize().width / 2 - dropBoxPanel.getSize().width / 2, 50);
		dropBoxPanel.setOpaque(false);
		dropBoxPanel.setLayout(new FlowLayout());
		
		List<BazaPodataka.Ruta> rute = BazaPodataka.ISPISI_RUTE();
		List<BazaPodataka.Ruta> pocetneRute = new ArrayList<BazaPodataka.Ruta>();
		
		Boolean unikat = true;
		for(int i = 0; i < rute.size(); i++) {
			for(int j = 0; j < pocetneRute.size(); j++) {
				if(rute.get(i).pocetak.equals(pocetneRute.get(j).pocetak)) {
					unikat = false;
					break;
				}
			}
			
			if(unikat) {
				pocetneRute.add(rute.get(i));
			}
			
			unikat = true;
		}
		
		komboBox = new JComboBox<>();
		komboBox.addItem("<html><i>izaberite lokaciju</i></html>");
		
		for(int i = 0; i < pocetneRute.size(); i++) {
			komboBox.addItem(pocetneRute.get(i).pocetak);
		}
		
		komboBox.setPreferredSize(new Dimension(dropBoxPanel.getSize().width / 3 - 10, 35));
		komboBox.setFocusable(false);
		komboBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
		komboBox.setName("komboBox1");
		komboBox.addActionListener(this);
		komboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JLabel labela = new JLabel("→");
		labela.setHorizontalAlignment(JLabel.CENTER);
		labela.setForeground(Color.white);
		labela.setPreferredSize(new Dimension(dropBoxPanel.getSize().width / 3, 35));
		labela.setFocusable(false);
		labela.setFont(new Font("Times New Roman", Font.BOLD, 50));
		
		komboBox2 = new JComboBox<>();
		komboBox2.addItem("<html><i>izaberite lokaciju</i></html>");
		
		komboBox2.setPreferredSize(new Dimension(dropBoxPanel.getSize().width / 3 - 10, 35));
		komboBox2.setFocusable(false);
		komboBox2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		komboBox2.setEnabled(false);
		komboBox2.setName("komboBox2");
		komboBox2.addActionListener(this);
		komboBox2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		komboBox3 = new JComboBox<String>();
		
		komboBox3.setPreferredSize(new Dimension(dropBoxPanel.getSize().width / 3 - 10, 35));
		komboBox3.setFocusable(false);
		komboBox3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		komboBox3.setVisible(false);
		komboBox3.setName("komboBox3");
		komboBox3.addActionListener(this);
		komboBox3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		dropBoxPanel.add(komboBox);
		dropBoxPanel.add(labela);
		dropBoxPanel.add(komboBox2);
		dropBoxPanel.add(komboBox3);
		
		JPanel informacijePanel = new JPanel();
		informacijePanel.setSize(this.getSize().width / 3 * 2, 350);
		informacijePanel.setLocation(this.getSize().width / 2 - dropBoxPanel.getSize().width / 2, dropBoxPanel.getLocation().x);
		informacijePanel.setOpaque(false);
		informacijePanel.setLayout(new FlowLayout());
		informacijePanel.setBackground(Color.white);
		informacijePanel.setLayout(new FlowLayout());
		
		labele = new JLabel[6];
		
		for(int i = 0; i < labele.length; i++) {
			labele[i] = new JLabel();
			
			labele[i].setLocation(new Point(0, (informacijePanel.getSize().height / 6) * i));
			labele[i].setFont(new Font("Times New Roman", Font.BOLD, 18));
			labele[i].setPreferredSize(new Dimension(informacijePanel.getSize().width, informacijePanel.getSize().height / 6 - 5));
			labele[i].setForeground(Color.white);
			
			informacijePanel.add(labele[i]);
		}
		
		JPanel dugmePanel = new JPanel();
		dugmePanel.setSize(new Dimension(informacijePanel.getSize().width, 70));
		dugmePanel.setLocation(new Point(informacijePanel.getLocation().x, informacijePanel.getLocation().y + informacijePanel.getSize().height));
		dugmePanel.setLayout(new FlowLayout());
		dugmePanel.setOpaque(false);

		dugme = new JButton("KUPI KARTU");
		dugme.setPreferredSize(new Dimension(150, 30));
		dugme.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE, 18));
		dugme.setFocusable(false);
		dugme.setBackground(Color.white);
		dugme.setForeground(Color.black);
		dugme.setHorizontalAlignment(JButton.CENTER);
		dugme.setVerticalAlignment(JButton.CENTER);
		dugme.setVisible(false);

		dugme.addActionListener(this);

		dugmePanel.add(dugme);

		panelCentar.add(dropBoxPanel);
		panelCentar.add(informacijePanel);
		panelCentar.add(dugmePanel);

		this.getContentPane().add(panelGornji, BorderLayout.NORTH);
		this.getContentPane().add(panelDonji, BorderLayout.SOUTH);
		this.getContentPane().add(panelCentar, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(komboBox)) {
			if(komboBox.getSelectedIndex() != 0) {
				komboBox2.setEnabled(true);
				komboBox3.setVisible(true);
				komboBox2.removeAllItems();
				
				List<BazaPodataka.Ruta> rute = BazaPodataka.ISPISI_RUTE();
				List<BazaPodataka.Ruta> krajnjeRute = new ArrayList<BazaPodataka.Ruta>();
				
				for(int i = 0; i < rute.size(); i++) {
					if(rute.get(i).pocetak.equals(komboBox.getSelectedItem())) {
						krajnjeRute.add(rute.get(i));
					}
				}
				
				Boolean unikat = true;

				for(int i = 0; i < krajnjeRute.size(); i++) {
					for(int j = 0; j < i; j++) {
						if(krajnjeRute.get(i).odrediste.equals(krajnjeRute.get(j).odrediste)) {
							unikat = false;
							break;
						}
					}
					
					if(unikat) {
						komboBox2.addItem(krajnjeRute.get(i).odrediste);
					}
					
					unikat = true;
				}

				dugme.setVisible(true);

				return;
			} 
			
			if(komboBox.getSelectedIndex() == 0) {
				komboBox2.removeAllItems();
				komboBox2.setEnabled(false);
				komboBox3.setVisible(false);
				komboBox2.addItem("<html><i>izaberite lokaciju</i></html>");

				return;
			}
			
		} 
		
		if(e.getSource().equals(komboBox2)) {
			if(komboBox2.getItemAt(komboBox2.getSelectedIndex()) != null) {
				komboBox3.removeAllItems();
				DODAJ_RUTE_U_COMBOBOX(komboBox.getItemAt(komboBox.getSelectedIndex()), komboBox2.getItemAt(komboBox2.getSelectedIndex()));
			}	

			return; 
		}
		
		if(e.getSource().equals(komboBox3)) {
			if(komboBox3.getItemAt(komboBox3.getSelectedIndex()) != null) {
				ISPISI_INFORMACIJE_O_RUTI(komboBox.getItemAt(komboBox.getSelectedIndex()), komboBox2.getItemAt(komboBox2.getSelectedIndex()), komboBox3.getItemAt(komboBox3.getSelectedIndex()));
			}

			return;
		} 
		
		if(e.getSource().equals(dugme)) {
			BazaPodataka.KUPI_KARTU(ListajRuteFrame.AKTIVNARUTA, ListajRuteFrame.KORISNIK, this);
		}
	}

	private void ISPISI_INFORMACIJE_O_RUTI(String box1, String box2, String box3) {
		List<BazaPodataka.Ruta> rute = BazaPodataka.ISPISI_RUTE();
		BazaPodataka.Ruta ruta = null;
		
		String[] vreme = box3.split(" - ");
		
		for(int i = 0; i < rute.size(); i++) {
			if(rute.get(i).pocetak.equals(box1) && rute.get(i).odrediste.equals(box2) && rute.get(i).vremePocetka.toString().equals(vreme[0])) {
				ruta = rute.get(i);
				break;
			}
		}

		AKTIVNARUTA = ruta;
		
		if(ruta == null) {
			return;
		}
		
		labele[0].setText("Putovanje od " + ruta.pocetak.toUpperCase() + " do " + ruta.odrediste.toUpperCase());
		labele[1].setText("Dan: " + ruta.danDolaska);
		labele[2].setText("Vreme: " + ruta.vremePocetka + " - " + ruta.vremeDolaska);
		labele[3].setText("Trajanje putovanja: " + ruta.trajanje + " minuta");
		labele[4].setText("Cena karte: " + ruta.cenaKarte + " dinara");
		labele[5].setText("Dodatne informacije: nema dodatnih informacija.");
		
		for(JLabel l : labele) {
			l.setBorder(BorderFactory.createLineBorder(Color.white));
		}
	}

	private void DODAJ_RUTE_U_COMBOBOX(String pocetak, String kraj) {
		List<BazaPodataka.Ruta> rute = BazaPodataka.ISPISI_RUTE();
		
		List<BazaPodataka.Ruta> ispravneRute = new ArrayList<>();
		
		for(BazaPodataka.Ruta r : rute) {
			if(r.pocetak.equals(pocetak) && r.odrediste.equals(kraj)) {
				ispravneRute.add(r);
			}
		}
		
		for(BazaPodataka.Ruta r : ispravneRute) {
			komboBox3.addItem(r.vremePocetka + " - " + r.vremeDolaska);
		}
	}
}
