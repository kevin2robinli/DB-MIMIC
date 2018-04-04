package section_2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertor {

	//convert string "yyyy-mm-dd" to Date
	public Date StringToDate(String stringDate) {
		Date date = new Date();
		SimpleDateFormat dateFormatter=new SimpleDateFormat("yyyy-mm-dd");
		try {
			date = dateFormatter.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return date;
	}
	
	public String DateToString(Date date) {
		String dateString;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-mmM-dd");
		dateString = dateFormatter.format(date);
		
		return dateString;
	}
}
