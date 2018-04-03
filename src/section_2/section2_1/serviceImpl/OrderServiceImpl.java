package section_2.section2_1.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import section_2.section2_1.service.OrderService;
import section_2.util.DateConvertor;

public class OrderServiceImpl implements OrderService {

	/* Order the selected result by date.
	 * 
	 * Find the earliest date in DATA SELECT result, get the index(maybe more than one), then use the index to find all other columns in
	 * other SELECT results. Store the found record. 
	 */
	public List<List<String>> orderByDate(List<List<String>> selectedResult, int columnOfDATE_Index) {
		
		List<Date> columnDate  = new ArrayList<Date>();
		List<String> columnDateString  = selectedResult.get(columnOfDATE_Index);
		List<List<String>> queryOutput = new ArrayList<List<String>>();
		
		//Convert all dates from String to Date, store the Date in a List.
		DateConvertor dc = new DateConvertor();
		for(String date : columnDateString) {
			columnDate.add(dc.StringToDate(date));
		}
		
		/* Compare in list of Date record, find the earliest one, get it's index to find all associated record in other column
		 * If more than one record found with same value and are the earliest, get all of the index to find all associated records in other columns
		 * Add all record in a list as the ordered result
	     */
		Date minDate = Collections.min(columnDate);
		int index = 0;
		for(Date date: columnDate) {
			List<String> eachResultRow = new ArrayList<String>();
			if(date.equals(minDate)) {
				for(List<String> column: selectedResult) {
					eachResultRow.add(column.get(index));
				}
			}
			queryOutput.add(eachResultRow);
			index++;	
		}
         
		return queryOutput;
	}

	/* Order the SELECT result by Letter ascendent 
	 * 
	 * 1. Store ordered column in HashMap<index, columnContent>
	 * 2. Sort the HashMap by value: columnContent
	 * 3. Get the KeySet in HashMap as the index set.
	 * 4. Use the keyset to get all data from all columns
	 * 5. Get the ordered result.
	 */
	public List<List<String>> orderByLetter(List<List<String>> selectedResult, int columnOfString_Index) {

		List<String> columnOfString  = selectedResult.get(columnOfString_Index);
		List<List<String>> queryOutput = new ArrayList<List<String>>();

		int index = 0;
		HashMap<Integer, String> indexAndColumn = new HashMap<Integer, String>();
		
		for(String eachRecordInColumn : columnOfString) {
			indexAndColumn.put(index++, eachRecordInColumn);
		}

		//Java 8 can use below to sort HashMap<Integer, String> by value: String
	    HashMap<Integer, String> sortedMap = indexAndColumn.entrySet().stream().sorted(Entry.comparingByValue()).collect(Collectors.toMap(Entry::getKey, Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));
	
	    Set<Integer> sortedIndex = sortedMap.keySet();
	    
	    for(Integer indexInSet : sortedIndex ) {
			List<String> eachResultRow = new ArrayList<String>();
              for(List<String> column: selectedResult) {
            	  eachResultRow.add(column.get(indexInSet));
              }
              queryOutput.add(eachResultRow);
	    }
	
	    return queryOutput;
  }

	/*Order the selected result by date and then letter
	 * 
	 * 1.use method "orderByDate" to sort the SELECT result by date as initial output.
	 * 2.Loop the initial output, Gather all same date rows that next row(s) date is same as currently row's date. (Because order priority is Date first then Letter)
	 * 3.Use HashMap<Integer, String> to keep data. Key is index of row in initial output, value is the column value.
	 * Sort Map by value: specificColumn in letter ascendent. Get the keySet with the index for orderd "same date rows"
	 * 4.After sorting, use the index order in keyset to reorder initial output.
	 */
	 
	public List<List<String>> orderByDateThenLetter(List<List<String>> selectedResult, int columnOfDATE_Index, int columnOfString_Index) {
		
		List<List<String>> selectedResultbyDate =orderByDate(selectedResult, columnOfDATE_Index);
		List<List<String>> selectedResultbyDateAndLetter = new ArrayList<List<String>>();
		
		for(int i=0; i<selectedResultbyDate.size();i++)
		{
			int j=i+1;
			HashMap<Integer, String> temp = new HashMap<Integer, String>();
			List<String> currentRow = selectedResultbyDate.get(i);
			temp.put(i,currentRow.get(columnOfString_Index));
			
				//Check the situation index i reaches the end of the selectedResultbyDate
				if(i==selectedResultbyDate.size()-1) {
					break;
				}
				List<String> nextRow  = selectedResultbyDate.get(j);
				
				//If date is different, add current row in to lastest output
				if(!currentRow.get(columnOfDATE_Index).equals(nextRow.get(columnOfDATE_Index))) {
					selectedResultbyDateAndLetter.add(currentRow);
				}
				
				//Get index for all rows with same Date
				else {
				    while(currentRow.get(columnOfDATE_Index).equals(selectedResultbyDate.get(j).get(columnOfDATE_Index))) {
					temp.put(j, nextRow.get(columnOfDATE_Index));
					j++;
				  }
				}
				//move pointer i to pointer j position.
				i=j;
		
			//Java 8 can use below to sort HashMap<Integer, String> by value: String
		    HashMap<Integer, String> sortedMap = temp.entrySet().stream().sorted(Entry.comparingByValue()).collect(Collectors.toMap(Entry::getKey, Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));
			Set<Integer> sortedIndex = sortedMap.keySet();
			
			//add all sorted rows into latest output
			 for(Integer indexInSet : sortedIndex ) {
				 selectedResultbyDateAndLetter.add(selectedResultbyDate.get(indexInSet));
			    }
		}		
		return selectedResultbyDateAndLetter;
	}
	

	/*Order the selected result by letter ascendent and then date
	 * TO BE IMPLEMENTED. Don't implement it since it's similar as as method "orderByDateThenLetter"
	 * 
	 * 1. High level, first get a initial output sort by letter. 
	 * 2. Create a latest output.
	 * 3. Loop the initial output. 
	 *   (1)Gather all "same letter rows". Sort them and get their index in initial output. 
	 *   (2)Insert into latest output based on the index
	 * 4. When step 3 is loop over, the latest output is ready to be the query output.   
	 */
	public List<List<String>> orderByLetterThenDate(List<List<String>> selectedResult, int columnOfString_Index, int columnOfDATE_Index) {
		return null;
	}

}
