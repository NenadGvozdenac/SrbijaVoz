import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		 new LoginFrame("Srbija Voz");
		
		// new MainWindow(new BazaPodataka.Korisnik("nenadgvozdenac40@gmail.com", "1234567", "admin"));

		// BazaPodataka.UPISI_KORISNIKA("testiranje123456@gmail.com", "12345", BazaPodataka.POZICIJA.ADMIN);
		
		 ispisi();
	}
	
	public static void ispisi() {
		for(BazaPodataka.Korisnik k : BazaPodataka.ISPISI_KORISNIKE()) {
			System.out.println(k.korisnicko_ime + " -> " + k.sifra + " --- " + k.pozicija);
		}
	}
}
