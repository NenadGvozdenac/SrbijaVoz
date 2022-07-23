import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

public class ListajKarteFrame extends JFrame implements ActionListener, ListSelectionListener {

    public static BazaPodataka.Korisnik KORISNIK;
    public static JPanel panelCentar;
    public static JButton dugme;
    public static BazaPodataka.Karta KARTA;
    public JList<String> list;
    public List<BazaPodataka.Karta> karte;

    ListajKarteFrame(BazaPodataka.Korisnik korisnik) {

        ListajRuteFrame.KORISNIK = korisnik;

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

        karte = BazaPodataka.ISPISI_KUPOVINE(korisnik);
        DefaultListModel<String> lista = new DefaultListModel<String>();

        for(BazaPodataka.Karta karta : karte) {
            lista.addElement(karta.ruta.pocetak + " - " + 
            karta.ruta.odrediste + ", " + 
            karta.ruta.danPocetka + ": " + 
            karta.ruta.vremePocetka + ", " + 
            karta.ruta.vremeDolaska + ". " + 
            karta.ruta.cenaKarte + "din");
        }

        list = new JList<String>();
        list.setModel(lista);
        list.setFont(new Font("Times New Roman", Font.BOLD, 18));
        list.setBackground(Color.white);
        list.setForeground(Color.black);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSize(500, 400);
        list.setLocation(this.getSize().width / 2 - list.getSize().width / 2, this.getSize().height / 2 - list.getSize().height / 2 - 100);
        list.addListSelectionListener(this);

        dugme = new JButton("BRISI RUTU");
		dugme.setSize(new Dimension(150, 30));
		dugme.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE, 18));
		dugme.setFocusable(false);
		dugme.setBackground(Color.white);
		dugme.setForeground(Color.black);
		dugme.setHorizontalAlignment(JButton.CENTER);
		dugme.setVerticalAlignment(JButton.CENTER);
        dugme.setLocation(list.getLocation().x + list.getSize().width / 2 - dugme.getSize().width / 2, list.getLocation().y + list.getSize().height + 15);
        dugme.addActionListener(this);

        panelCentar.add(list);
        panelCentar.add(dugme);

		this.getContentPane().add(panelGornji, BorderLayout.NORTH);
		this.getContentPane().add(panelDonji, BorderLayout.SOUTH);
		this.getContentPane().add(panelCentar, BorderLayout.CENTER);
		
		this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(dugme)) {
            try {

                if(list.getSelectedIndex() == -1) {
                    throw new Exception();
                }

                BazaPodataka.OBRISI_KUPLJENUKARTU(ListajKarteFrame.KARTA);

                karte = BazaPodataka.ISPISI_KUPOVINE(ListajKarteFrame.KARTA.korisnik);
                DefaultListModel<String> lista = new DefaultListModel<String>();
    
                for(BazaPodataka.Karta karta : karte) {
                    lista.addElement(karta.ruta.pocetak + " - " + 
                    karta.ruta.odrediste + ", " + 
                    karta.ruta.danPocetka + ": " + 
                    karta.ruta.vremePocetka + ", " + 
                    karta.ruta.vremeDolaska + ". " + 
                    karta.ruta.cenaKarte + "din");
                }
    
                list.setModel(lista);

                JOptionPane.showMessageDialog(null, "Uspesno obrisana karta!", "USPEH", JOptionPane.INFORMATION_MESSAGE | JOptionPane.OK_OPTION);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Greska pri brisanju!", "GRESKA!", JOptionPane.WARNING_MESSAGE);
            }

            panelCentar.revalidate();
            panelCentar.repaint();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
       
        if(e.getSource().equals(list) && list.getValueIsAdjusting() == false) {
            try {
                ListajKarteFrame.KARTA = karte.get(list.getSelectedIndex());
                // ListajKarteFrame.KARTA.ispisiKartu();
            } catch(IndexOutOfBoundsException ex) {
                list.setSelectedIndex(0);
            }
        }
    }
}
