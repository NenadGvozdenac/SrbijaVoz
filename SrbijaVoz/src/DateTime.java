import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTime {

	public java.sql.Date datum;
	public java.sql.Time vreme;
	
	public DateTime(String s) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		
		java.util.Date date = f.parse(s);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		java.sql.Time sqlTime = new java.sql.Time(date.getTime());
		
		this.datum = sqlDate;
		this.vreme = sqlTime;
	}
}