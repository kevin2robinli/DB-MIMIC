package section_2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertor {

	//convert string "YYYY-MM-DD" to Date
	public Date StringToDate(String stringDate) {
		Date date = new Date();
		SimpleDateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = dateFormatter.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return date;
	}
}
