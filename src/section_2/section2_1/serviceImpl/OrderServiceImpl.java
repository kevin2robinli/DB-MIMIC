package section_2.section2_1.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import section_2.section2_1.service.OrderService;
import section_2.util.DateConvertor;

public class OrderServiceImpl implements OrderService {

	/*Order the selected result by date. Algorithm as below steps: 
	 * 1. Fine the earliest date, get the index(line#) in SELECT result, then use the index to find all other columns in
	 * other SELECT results. Store the found record. 
	 * 2. remove all found results from the SELECTED lists. now the new Lists are without the earliest record.
	 * 3. recursively do step1, until all records are removed. 
	 * 4. Output
	 */
	public List<List<String>> orderByDate(List<List<String>> selectedResult, int columnDATE_Index) {
		
		List<Date> columnDate  = new ArrayList<Date>();
		List<String> columnDateString  = selectedResult.get(columnDATE_Index);
		
		//Convert all dates from String to Date, store the Date in a List.
		DateConvertor dc = new DateConvertor();
		for(String date : columnDateString) {
			columnDate.add(dc.StringToDate(date));
		}
		
		//Compare in list of Date record, find the earliest one, get it's index to find all associated record in other columns
	    //If more than one record found with same value and are the earliest, get all of the index to find all associated records in other columns
		Date minDate = Collections.min(columnDate);
         
		return null;
	}
	
	//Assist method "orderByDate" to order records by Date. It is recursively invoked by method "orderByDate"
	public List<List<String>> orderByDateAssister(List<List<String>> finalSelectResult,
			List<List<String>> selectedResult, int columnDATE_Index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> orderByLetter(List<List<String>> selectedResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> orderByDateThenLetter(List<List<String>> selectedResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> orderByLetterThenDate(List<List<String>> selectedResult) {
		// TODO Auto-generated method stub
		return null;
	}


}
