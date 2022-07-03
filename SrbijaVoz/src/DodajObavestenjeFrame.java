import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class DodajObavestenjeFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -4830151880025487047L;

	public JPanel panelGornji, panelDonji, panelCentar;
	public JTextArea area;
	public BazaPodataka.Korisnik KORISNIK;
	
	public JRadioButton obicnaPoruka, vaznaPoruka, obavestenje;
	
	DodajObavestenjeFrame(BazaPodataka.Korisnik KORISNIK) {
		this.KORISNIK = KORISNIK;
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
		panelCentar.setSize(this.getSize().width, this.getSize().height - panelGornji.getPreferredSize().height - panelDonji.getPreferredSize().height - 10);
		panelCentar.setName("panelCentar");
		
		JLabel label1 = new JLabel("Dobrodosli u Srbija voz!");
		label1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel label2 = new JLabel("Hvala sto koristite Srbija voz!");
		label2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		area = new JTextArea();
		area.setBackground(Color.white);
		area.setForeground(Color.black);
		area.setFont(new Font("Arial", Font.PLAIN, 18));
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		
		JButton dugme = new JButton("POTVRDI");
		
		dugme.setSize(new Dimension(150, 30));
		dugme.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE, 18));
		dugme.setFocusable(false);
		dugme.setBackground(Color.white);
		dugme.setForeground(Color.black);
		dugme.setHorizontalAlignment(JButton.CENTER);
		dugme.setVerticalAlignment(JButton.CENTER);
		
		dugme.addActionListener(this);
		
		JLabel label3 = new JLabel("KORISNIK: " + KORISNIK.korisnicko_ime.toUpperCase());
		label3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label3.setForeground(Color.white);
		label3.setSize(100, 30);
		
		JLabel label4 = new JLabel("POZICIJA: " + KORISNIK.pozicija.toUpperCase());
		label4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label4.setForeground(Color.white);
		
		JPanel panelInformacije = new JPanel();
		panelInformacije.setBorder(BorderFactory.createLineBorder(Color.white));
		panelInformacije.setBackground(Color.decode("#5F6C6C"));
		panelInformacije.setLayout(new GridLayout(2,1));
		panelInformacije.setSize(panelCentar.getSize().width / 2, 60);
		panelInformacije.setName("panelInformacije");
		panelInformacije.setLocation(0, 0);
		
		JPanel panelDodajObavestenje = new JPanel();
		
		panelDodajObavestenje.setSize(new Dimension(panelCentar.getSize().width / 3 * 2, panelCentar.getSize().height / 5 * 2));
		panelDodajObavestenje.setLocation(new Point(panelCentar.getSize().width / 2 - panelDodajObavestenje.getSize().width / 2, panelCentar.getSize().height / 2 - panelDodajObavestenje.getSize().height / 2 - 50));
		panelDodajObavestenje.setBorder(BorderFactory.createLineBorder(Color.blue));
		panelDodajObavestenje.setLayout(new BorderLayout());
		area.setSize(panelDodajObavestenje.getSize().width, panelDodajObavestenje.getSize().height);
		
		JPanel panelRadioDugme = new JPanel();
		panelRadioDugme.setLayout(null);
		panelRadioDugme.setOpaque(false);
		panelRadioDugme.setSize(new Dimension(panelCentar.getSize().width / 3 * 2, 50));
		panelRadioDugme.setLocation(new Point(panelCentar.getSize().width / 2 - panelDodajObavestenje.getSize().width / 2, panelDodajObavestenje.getLocation().y + panelDodajObavestenje.getSize().height + 15));
		
		obicnaPoruka = new RadioButtonDugme("OBICNA PORUKA");
		obavestenje = new RadioButtonDugme("OBAVESTENJE");
		vaznaPoruka = new RadioButtonDugme("VAZNA PORUKA");
		
		obicnaPoruka.setLocation(0, -35);
		obavestenje.setLocation(280, -35);
		vaznaPoruka.setLocation(140, -35);
		
		ButtonGroup buttonGrupa = new ButtonGroup();
		buttonGrupa.add(obicnaPoruka);
		buttonGrupa.add(obavestenje);
		buttonGrupa.add(vaznaPoruka);
		
		obicnaPoruka.setSelected(true);
		
		dugme.setLocation(new Point(panelRadioDugme.getLocation().x + panelRadioDugme.getSize().width - dugme.getSize().width * 2, 0));
		
		panelInformacije.add(label3);
		panelInformacije.add(label4);
		panelRadioDugme.add(obicnaPoruka);
		panelRadioDugme.add(vaznaPoruka);
		panelRadioDugme.add(obavestenje);
		panelRadioDugme.add(dugme);
		panelDodajObavestenje.add(area);
		
		panelCentar.add(panelRadioDugme);
		panelCentar.add(panelDodajObavestenje);
		panelCentar.add(panelInformacije);
		
		panelGornji.add(label1);
		panelDonji.add(label2);
		
		this.getContentPane().add(panelGornji, BorderLayout.NORTH);
		this.getContentPane().add(panelDonji, BorderLayout.SOUTH);
		this.getContentPane().add(panelCentar, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			
			if(this.obicnaPoruka.isSelected()) {
				BazaPodataka.UPISI_OBAVESTENJE(area.getText(), "obicnaporuka");
			} else if(this.vaznaPoruka.isSelected()) {
				BazaPodataka.UPISI_OBAVESTENJE(area.getText(), "vaznaporuka");
			} else {
				BazaPodataka.UPISI_OBAVESTENJE(area.getText(), "obavestenje");
			}
			this.dispose();
			
			JOptionPane.showMessageDialog(null, "Uspesno dodato obavestenje!", "USPEH", JOptionPane.OK_OPTION | JOptionPane.INFORMATION_MESSAGE);
			
			new MainWindow(this.KORISNIK);
		}
	}
}