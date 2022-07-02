import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = -2962760868419309808L;

	LoginFrame(String naziv) {
		this.setTitle(naziv);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setAlwaysOnTop(false);
		this.setResizable(false);
		
		this.setIconImage(new ImageIcon("./slike//srbija.png").getImage());
		
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(700, 500));
		
		this.setLocationRelativeTo(null);
		
		JTextField area1 = new JTextField("Korisnicko ime");
		area1.setPreferredSize(new Dimension(350, 30));
		area1.setForeground(Color.black);
		area1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JTextField area2 = new JTextField("Korisnicka sifra");
		area2.setPreferredSize(new Dimension(350, 30));
		area2.setForeground(Color.black);
		area2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JButton dugme = new JButton("POTVRDA");
		dugme.setPreferredSize(new Dimension(100, 50));
		dugme.setBackground(Color.white);
		dugme.setForeground(Color.black);
		dugme.setHorizontalAlignment(JButton.CENTER);
		dugme.setVerticalAlignment(JButton.CENTER);
		dugme.setFont(new Font("Times New Roman", Font.BOLD, 12));
		dugme.setFocusable(false);
		
		dugme.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = area1.getText();
				String korisnickaSifra = area2.getText();
				try {
					PROVERI_LOGIN_INFORMACIJE(korisnickoIme, korisnickaSifra);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JLabel label1 = new JLabel("Dobrodosli u Srbija voz!");
		label1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel label2 = new JLabel("Hvala sto koristite Srbija voz!");
		label2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JPanel panelGornji = new JPanel();
		
		panelGornji.setBorder(BorderFactory.createLineBorder(Color.black));
		panelGornji.setBackground(Color.LIGHT_GRAY);
		panelGornji.setPreferredSize(new Dimension(100, 40));
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
		
		JPanel panelSredinaLogovanje = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setVgap(10);
		panelSredinaLogovanje.setLayout(layout);
		panelSredinaLogovanje.setSize(new Dimension(350, 150));
		panelSredinaLogovanje.setLocation(new Point(panelCentar.getSize().width / 2 - panelSredinaLogovanje.getSize().width / 2, panelCentar.getSize().height / 2 - panelSredinaLogovanje.getSize().height / 2 - 10));
		panelSredinaLogovanje.setName("panelSredinaLogovanje");
		
		panelSredinaLogovanje.add(area1);
		panelSredinaLogovanje.add(area2);
		panelSredinaLogovanje.add(dugme);
		
		panelSredinaLogovanje.setOpaque(false);
		
		JLabel labelBackground = new JLabel(new ImageIcon(new ImageIcon("./slike//slika1.jpg").getImage().getScaledInstance(panelCentar.getSize().width, panelCentar.getSize().height, Image.SCALE_SMOOTH)));
		labelBackground.setSize(panelCentar.getSize().width, panelCentar.getSize().height);
		
		panelCentar.add(panelSredinaLogovanje);
		panelCentar.add(labelBackground);
	
		panelGornji.add(label1);
		panelDonji.add(label2);
		
		this.getContentPane().add(panelGornji, BorderLayout.NORTH);
		this.getContentPane().add(panelDonji, BorderLayout.SOUTH);
		this.getContentPane().add(panelCentar, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public void PROVERI_LOGIN_INFORMACIJE(String korisnickoIme, String korisnickaSifra) throws SQLException {
		
		
		BazaPodataka.Korisnik korisnik = BazaPodataka.PROVERI_LOGIN(korisnickoIme, korisnickaSifra);
		
		// vraca false ukoliko nema greske, true ako ima
		
		this.setVisible(false);
		
		if(korisnickoIme.isEmpty() || korisnickoIme.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Greska! Upisite podatke!\n", "GRESKA", JOptionPane.WARNING_MESSAGE | JOptionPane.OK_OPTION);
			this.setVisible(true);
			return;
		}
		
		if(korisnik == null) {
			JOptionPane.showMessageDialog(null, "Greska! Pogresne informacije!\n", "GRESKA", JOptionPane.WARNING_MESSAGE | JOptionPane.OK_OPTION);
			this.setVisible(true);
			
			for(Component c : this.getContentPane().getComponents()) {
				if(c instanceof JPanel) {
					if(c.getName().equals("panelCentar")) {
						for(Component k : ((Container) c).getComponents()) {
							if(k instanceof JPanel) {
								if(k.getName().equals("panelSredinaLogovanje")) {
									for(Component j : ((Container) k).getComponents()) {
										if(j instanceof JTextField) {
											((JTextField) j).setText("");
										}
									}
								}
							}
						}
					}
				}
			}
			
			return;
		} else {
			JOptionPane.showMessageDialog(null, "Uspesno ste ulogovani!\n", "USPEH", JOptionPane.OK_OPTION | JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			
			new MainWindow(korisnik);
			
			return;
		}
	}
}
