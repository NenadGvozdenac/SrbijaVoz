import java.sql.SQLException;
import java.text.ParseException;

public class Main {
	
	public static void main(String[] args) throws SQLException, ParseException {
		new LoginFrame("Srbija Voz");
		
		// new MainWindow(new BazaPodataka.Korisnik("nenadgvozdenac40@gmail.com", "1234567", "admin"));

		// BazaPodataka.UPISI_KORISNIKA("nenadgvozdenac40@gmail.com", "1234567", BazaPodataka.POZICIJA.ADMIN);
		
		// new ListajRuteFrame(new BazaPodataka.Korisnik("nenadgvozdenac40@gmail.com", "1234567", "admin"));
		
		// BazaPodataka.UPISI_RUTU(new BazaPodataka.Ruta("Subotica", "Beograd", 500, new DateTime("12-07-2022 12:25"), new DateTime("12-07-2022 15:00")));
		
		// new ListajKarteFrame(new BazaPodataka.Korisnik("nenadgvozdenac40@gmail.com", "1234567", "admin"));

		// BazaPodataka.ISPISI_RUTE().forEach(e -> {
		// 	System.out.println(e.pocetak + " -> " + e.odrediste + "\n" + e.cenaKarte + "din " + e.trajanje + "min\n" + e.vremePocetka + " -> " + e.vremeDolaska + "\n------\n");
		// });

		// BazaPodataka.ISPISI_KUPOVINE(new BazaPodataka.Korisnik("nenadgvozdenac40@gmail.com", "1234567", "admin"));
	}
}
