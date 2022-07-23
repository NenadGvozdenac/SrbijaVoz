import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.BorderLayout;

public class IzdavanjeRacuna extends ListajRuteFrame {

    IzdavanjeRacuna(BazaPodataka.Korisnik KORISNIK) {
        super(KORISNIK);
    }

    public static void napraviIspis(String krajnjiString, JFrame frame) {

        JPanel dodavanjeStringa = null;

        for(Component panel : frame.getContentPane().getComponents()) {
			if(panel instanceof JPanel) {
				if(panel.getName().equals("panelCentar")) {
					JPanel panelCentar = (JPanel) panel;

					for(Component p : panelCentar.getComponents()) {
						panelCentar.remove(p);
					}

					panelCentar.revalidate();
					panelCentar.repaint();

					dodavanjeStringa = panelCentar;

					break;
				}
			}
		}

		JPanel panelCena = new JPanel();
		panelCena.setSize(frame.getSize().width / 3 * 2, 300);
		panelCena.setOpaque(false);
		panelCena.setBorder(BorderFactory.createLineBorder(Color.black));
		panelCena.setLocation(new Point(frame.getSize().width / 2 - panelCena.getSize().width / 2, frame.getSize().height / 2 - panelCena.getSize().height / 2 - 150));
        panelCena.setLayout(new BorderLayout());

        JTextPane tekst = new JTextPane();
        tekst.setEditable(false);
        tekst.setFont(new Font("Courier New", Font.PLAIN, 23));
        tekst.setText(krajnjiString);
        tekst.setSize(panelCena.getSize());
        tekst.setOpaque(false);
        tekst.setForeground(Color.WHITE);

        JButton dugme = new JButton("STAMPAJ KARTU");
        dugme.setSize(new Dimension(250, 30));
		dugme.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE, 18));
		dugme.setFocusable(false);
		dugme.setBackground(Color.white);
		dugme.setForeground(Color.black);
		dugme.setHorizontalAlignment(JButton.CENTER);
		dugme.setVerticalAlignment(JButton.CENTER);
        dugme.setLocation(new Point(frame.getSize().width / 2 - dugme.getSize().width / 2, frame.getSize().height / 2 - dugme.getSize().height / 2 + 100));

        dugme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tekst.print();
                    JOptionPane.showMessageDialog(null, "Uspesna Kupovina!", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    BazaPodataka.DODAJ_NOVU_KUPOVINU(ListajRuteFrame.KORISNIK, ListajRuteFrame.AKTIVNARUTA);
                } catch (PrinterException e1) {
                    e1.printStackTrace();
                }
            }
        });

        panelCena.add(tekst, BorderLayout.CENTER);
		dodavanjeStringa.add(panelCena);
        dodavanjeStringa.add(dugme);
    }
}