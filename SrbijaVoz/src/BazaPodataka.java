import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BazaPodataka {
	
	// vraca false ukoliko nema greske, true ako ima
	
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
	}
	
	public static Korisnik PROVERI_LOGIN(String korisnicko_ime, String korisnicka_sifra) throws SQLException {
	
		Connection connection = null;
		
		List<Korisnik> lista_korisnika = new ArrayList<Korisnik>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "gvozdenacnenad");
		Statement statement = connection.createStatement();
			
		ResultSet select_query = statement.executeQuery("SELECT * FROM srbijavoz.korisnik");
			
		while(select_query.next()) {
			lista_korisnika.add(new Korisnik(select_query.getString(2), select_query.getString(3), select_query.getString(4)));
		}
		
		if(lista_korisnika.isEmpty()) {
			return null;
		}
		
		connection.close();
		
		for(Korisnik p : lista_korisnika) {
			if(korisnicko_ime.equals(p.korisnicko_ime) && korisnicka_sifra.equals(p.sifra)) {
				return p;
			}
		}
		
		return null;
	}
}
