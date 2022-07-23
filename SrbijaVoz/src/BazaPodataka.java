import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BazaPodataka {
	
	public static Connection connection;
	public static Statement statement;
	
	public static class Korisnik {
		public String korisnicko_ime;
		public String sifra;
		public String pozicija;
		
		Korisnik() {}
		
		Korisnik(String a, String b, String c) {
			this.korisnicko_ime = a;
			this.sifra = b;
			this.pozicija = c;
		}

		public void ispisiKorisnika() {
			System.out.println("KORISNIK: " + this.korisnicko_ime);
		}
	}
	
	public static class Obavestenje {
		public String obavestenje;
		public String urgentnost;
		
		Obavestenje() {}
		
		Obavestenje(String a, String b) {
			this.obavestenje = a;
			this.urgentnost = b;
		}
	}
	
	public static class Ruta {
		public String pocetak;
		public String odrediste;
		
		Time vremePocetka, vremeDolaska;
		Date danPocetka, danDolaska;
		
		public Integer cenaKarte;
		public Integer trajanje;
		
		Ruta() {}
		
		Ruta(String pocetak, String kraj, 
				Integer cenaKarte, Integer trajanje, 
				Time vremePocetka, Time vremeDolaska,
				Date danPocetka, Date danDolaska) {
			
			this.pocetak = pocetak;
			this.odrediste = kraj;
			this.cenaKarte = cenaKarte;
			this.trajanje = trajanje;
			
			this.vremePocetka = vremePocetka;
			this.vremeDolaska = vremeDolaska;
			this.danPocetka = danPocetka;
			this.danDolaska = danDolaska;
		}
		
		Ruta(String pocetak, String kraj, 
			Integer cenaKarte, Integer trajanje, 
			DateTime pocetnoVreme, DateTime krajnjeVreme) {
			
			this.pocetak = pocetak;
			this.odrediste = kraj;
			this.cenaKarte = cenaKarte;
			this.trajanje = trajanje;
			
			this.vremePocetka = pocetnoVreme.vreme;
			this.vremeDolaska = krajnjeVreme.vreme;
			this.danPocetka = pocetnoVreme.datum;
			this.danDolaska = krajnjeVreme.datum;
		}
		
		Ruta(String pocetak, String kraj, 
				Integer cenaKarte, 
				DateTime pocetnoVreme, DateTime krajnjeVreme) {
				
			this.pocetak = pocetak;
			this.odrediste = kraj;
			this.cenaKarte = cenaKarte;
				
			this.vremePocetka = pocetnoVreme.vreme;
			this.vremeDolaska = krajnjeVreme.vreme;
			this.danPocetka = pocetnoVreme.datum;
			this.danDolaska = krajnjeVreme.datum;
			this.trajanje = (int) TimeUnit.MILLISECONDS.toMinutes(this.vremeDolaska.getTime() - this.vremePocetka.getTime());
		}

		public void ispisiRutu() {
			System.out.println("RUTA: " + 
			this.pocetak + " -> " + 
			this.odrediste + "\n" + "VREME: " + 
			this.vremePocetka + " - " + 
			this.vremeDolaska + "\n" + "DATUM: " + 
			this.danPocetka + " - " + this.danDolaska + "\n" + "CENA: " + 
			this.cenaKarte + ", TRAJANJE: " + this.trajanje + "\n");
		}
	}

	public static class Karta {
		public Ruta ruta;
		public Korisnik korisnik;

		Karta(Ruta ruta, Korisnik korisnik) {
			this.ruta = ruta;
			this.korisnik = korisnik;
		}

		Karta() {
			this.ruta = null;
			this.korisnik = null;
		}

		public void dodajRutu(Ruta ruta) {
			this.ruta = ruta;
		}

		public void dodajKorisnika(Korisnik korisnik) {
			this.korisnik = korisnik;
		}

		public void ispisiKartu() {
			ruta.ispisiRutu();
			korisnik.ispisiKorisnika();
		}
	}
	
	public enum POZICIJA {ADMIN, KORISNIK};
	
	public static Boolean UPISI_KORISNIKA(String korisnicko_ime, String korisnicka_sifra, POZICIJA pozicija) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "gvozdenacnenad");
			
			statement = connection.createStatement();
			
			String poz;
			
			if(pozicija == POZICIJA.ADMIN) {
				poz = "admin";
			} else if(pozicija == POZICIJA.KORISNIK) {
				poz = "korisnik";
			} else {
				return false;
			}
			
			if(korisnicko_ime.equals("Korisnicko ime") || korisnicko_ime.equals("")) {
				statement.close();
				connection.close();
				return false;
			} 
			
			if(korisnicka_sifra.equals("Korisnicka sifra") || korisnicka_sifra.equals("")) {
				statement.close();
				connection.close();
				return false;
			}
			
			statement.execute("INSERT INTO srbijavoz.korisnik (korisnik_naziv, korisnik_sifra, pozicija) VALUES (\"" + korisnicko_ime + "\", \"" + korisnicka_sifra +"\", \"" + poz  + "\")");
		
			statement.close();
			connection.close();
			
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Greska pri unosu korisnika!", "GRESKA", JOptionPane.WARNING_MESSAGE | JOptionPane.OK_CANCEL_OPTION);
			return false;
		}
	}
	
	public static Korisnik PROVERI_LOGIN(String korisnicko_ime, String korisnicka_sifra) throws SQLException {
	
		List<Korisnik> lista_korisnika = new ArrayList<Korisnik>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "gvozdenacnenad");
		statement = connection.createStatement();
			
		ResultSet select_query = statement.executeQuery("SELECT * FROM srbijavoz.korisnik");
			
		while(select_query.next()) {
			lista_korisnika.add(new Korisnik(select_query.getString(2), select_query.getString(3), select_query.getString(4)));
		}
		
		if(lista_korisnika.isEmpty()) {
			return null;
		}
		
		statement.close();
		connection.close();
		
		for(Korisnik p : lista_korisnika) {
			if(korisnicko_ime.equals(p.korisnicko_ime) && korisnicka_sifra.equals(p.sifra)) {
				return p;
			}
		}
		
		return null;
	}
	
	public static List<Korisnik> ISPISI_KORISNIKE() {
		
		List<Korisnik> lista_korisnika = new ArrayList<Korisnik>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "gvozdenacnenad");
			
			statement = connection.createStatement();
			
			ResultSet select_query = statement.executeQuery("SELECT * FROM srbijavoz.korisnik");
			
			while(select_query.next()) {
				lista_korisnika.add(new Korisnik(select_query.getString(2), select_query.getString(3), select_query.getString(4)));
			}
			
			statement.close();
			connection.close();
			
			return lista_korisnika;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void UPISI_OBAVESTENJE(String obavestenje, String urgentnost) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "gvozdenacnenad");
			
			statement = connection.createStatement();
			
			statement.execute("INSERT INTO srbijavoz.obavestenja (obavestenje, urgentnost) VALUES (\"" + obavestenje + "\", \"" + urgentnost + "\")");
		
			statement.close();
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Greska pri unosu obavestenja!", "GRESKA", JOptionPane.WARNING_MESSAGE | JOptionPane.OK_CANCEL_OPTION);
		}
	}
	
	public static List<Obavestenje> ISPISI_OBAVESTENJA() {
		
		List<Obavestenje> obavestenja = new ArrayList<Obavestenje>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "gvozdenacnenad");
			
			statement = connection.createStatement();
			
			ResultSet select_query = statement.executeQuery("SELECT * FROM srbijavoz.obavestenja");
			
			while(select_query.next()) {
				obavestenja.add(new Obavestenje(select_query.getString(2), select_query.getString(3)));
			}
			
			statement.close();
			connection.close();
			
			return obavestenja;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void UPISI_RUTU(Ruta ruta) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "gvozdenacnenad");
			
			statement = connection.createStatement();
			
			String upis = "INSERT INTO srbijavoz.rute (putovanje_pocetnalokacija, putovanje_krajnjalokacija, putovanje_vremekretanja, putovanje_vremedolaska, putovanje_cena, putovanje_trajanje) VALUES (\"" + 
					ruta.pocetak + "\", \"" + ruta.odrediste + "\", \"" + 
					ruta.danPocetka.toString() + " " + ruta.vremePocetka.toString() + "\", \"" + 
					ruta.danDolaska.toString() + " " + ruta.vremeDolaska.toString() + "\", " + 
					ruta.cenaKarte + ", " + ruta.trajanje + ");";
			
			statement.execute(upis);
		
			statement.close();
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Greska pri unosu rute!", "GRESKA", JOptionPane.WARNING_MESSAGE | JOptionPane.OK_CANCEL_OPTION);
		}
		
	}
	
	public static List<Ruta> ISPISI_RUTE() {
		
		List<Ruta> rute = new ArrayList<Ruta>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "gvozdenacnenad");
			
			statement = connection.createStatement();
			
			ResultSet select_query = statement.executeQuery("SELECT * FROM srbijavoz.rute");
			
			while(select_query.next()) {
				rute.add(new Ruta(
						select_query.getString(2), 
						select_query.getString(3), 
						select_query.getInt(6),
						select_query.getInt(7),
						new Time(select_query.getTimestamp(4).getTime()),
						new Time(select_query.getTimestamp(5).getTime()),
						select_query.getDate(4),
						select_query.getDate(5)));
			}
			
			statement.close();
			connection.close();

			return rute;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected static void KUPI_KARTU(BazaPodataka.Ruta ruta, BazaPodataka.Korisnik korisnik, JFrame frame) {
		String prvaLinija = String.format("%-15s %-15s %-5s\n", "POCETAK", "KRAJ", "CENA");
		String prviRed = String.format("%-15s %-15s %-5s\n", "---------", "---------", "---------");
		String prviIspis = String.format("%-15s %-15s %-5s\n", ruta.pocetak, ruta.odrediste, ruta.cenaKarte.toString() + "RSD");

		String drugaLinija = String.format("%-15s %-15s %-5s\n", "POCETNO VREME", "KRAJNJE VREME", "TRAJANJE");
		String drugiIspis = String.format("%-15s %-15s %-5s\n", ruta.vremePocetka, ruta.vremeDolaska, ruta.trajanje.toString() + " minuta");
		String treciIspis = String.format("%-15s %-15s\n", ruta.danPocetka, ruta.danDolaska);
		
		String cetvrtaLinije = String.format("%-20s\n", "-----------------------------------------");
		String cetvrtiRed = String.format("%-20s\n", "KUPAC: " + korisnik.korisnicko_ime);

		String krajnjiString = cetvrtaLinije + prvaLinija + prviIspis + prviRed + drugaLinija + drugiIspis + treciIspis + cetvrtaLinije + cetvrtiRed + cetvrtaLinije;

		IzdavanjeRacuna.napraviIspis(krajnjiString, frame);
	}

	protected static void DODAJ_NOVU_KUPOVINU(BazaPodataka.Korisnik korisnik, BazaPodataka.Ruta ruta) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "gvozdenacnenad");
			
			statement = connection.createStatement();
			
			String upis = "INSERT INTO srbijavoz.kupljenekarte (pocetak, odrediste, cena, datum, vreme, trajanje, korisnik) VALUES (\"" + 
				ruta.pocetak + "\", \"" + ruta.odrediste + "\", " + ruta.cenaKarte + 
				", \"" + ruta.danPocetka.toString() + "\", \"" + ruta.vremePocetka.toString() + 
				"\", " + ruta.trajanje + ", \"" + korisnik.korisnicko_ime + "\");";

			statement.execute(upis);
		
			statement.close();
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Greska pri unosu rute!", "GRESKA", JOptionPane.WARNING_MESSAGE | JOptionPane.OK_CANCEL_OPTION);
		}
	}

	protected static List<Karta> ISPISI_KUPOVINE(BazaPodataka.Korisnik korisnik) {

		List<Karta> karte = new ArrayList<Karta>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "gvozdenacnenad");
			
			statement = connection.createStatement();
			
			ResultSet select_query = statement.executeQuery("SELECT * FROM srbijavoz.kupljenekarte WHERE korisnik = \"" + korisnik.korisnicko_ime + "\";");
			
				while(select_query.next()) {
					
					Ruta ruta = new Ruta(
						select_query.getString(2), 
						select_query.getString(3), 
						select_query.getInt(4), 
						select_query.getInt(5), 
						new Time(select_query.getTimestamp(8).getTime()),
						new Time(select_query.getTimestamp(8).getTime() + TimeUnit.MINUTES.toMillis(select_query.getInt(5))),
						select_query.getDate(7),
						select_query.getDate(7));

					Karta karta = new Karta(ruta, korisnik);

					karte.add(karta);

				}
			
			return karte;

			} catch(SQLException e) {
				e.printStackTrace();
			}

		return null;
	}

    protected static void OBRISI_KUPLJENUKARTU(BazaPodataka.Karta karta) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "gvozdenacnenad");
			
			statement = connection.createStatement();
			
			String s = "DELETE FROM srbijavoz.kupljenekarte WHERE korisnik = \"" + 
			karta.korisnik.korisnicko_ime + "\" AND pocetak = \"" + 
			karta.ruta.pocetak + "\" AND odrediste = \"" + karta.ruta.odrediste + "\" LIMIT 1;";
			
			statement.executeUpdate(s);

		} catch(SQLException e) {
			e.printStackTrace();
		}
    }
}