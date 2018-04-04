package section_2.section2_1.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import section_2.section2_1.service.OrderService;
import section_2.section2_1.service.SelectService;
import section_2.util.DateConvertor;

public class OrderServiceImpl implements OrderService {

	/* Order the selected result by DATE.
	 * 
	 * 1. Get DATE column in selected result. Convert String -> Date
	 * 2. Handle selected result into rows of record. 
	 * 3. Create HashMap<index, date>. sort the hashmap by value. The keyset of index is the row order
	 * 4. Create a output, follow the index order to insert row into output.
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
				
		//handle selected data
		SelectService selectService = new SelectServiceImpl();
		List<List<String>> selectedRawOutputResult = selectService.handleSelectedData(selectedResult);
		
		//Create hashmap  
		int index = 0;
		HashMap<Integer, Date> indexOrderMap = new HashMap<Integer, Date>();

		for(Date date: columnDate) {
			indexOrderMap.put(index++, date);
		}
		
		//sort hashmap by value
	    HashMap<Integer, Date> sortedMap = indexOrderMap.entrySet().stream().sorted(Entry.comparingByValue()).collect(Collectors.toMap(Entry::getKey, Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));
         
	    //get the index order.
	    Set<Integer> sortedIndex = sortedMap.keySet();
	    
	    for(Integer indexInSet : sortedIndex ) {
			List<String> eachResultRow = new ArrayList<String>();
			eachResultRow = selectedRawOutputResult.get(indexInSet);
            queryOutput.add(eachResultRow);
	    }

		return queryOutput;
	}

	/* Order the selected result by letter ascendent 
	 * 
     * 1. Handle selected result into rows of record. 
	 * 2. Create HashMap<index, String>. sort the hashmap by value. The keyset of index is the row order
	 * 3. Create a output, follow the index order to insert row into output.
	 */
	public List<List<String>> orderByLetter(List<List<String>> selectedResult, int columnOfString_Index) {

		List<String> columnOfString  = selectedResult.get(columnOfString_Index);
		List<List<String>> queryOutput = new ArrayList<List<String>>();

		//handle selected data
		SelectService selectService = new SelectServiceImpl();
		List<List<String>> selectedRawOutputResult = selectService.handleSelectedData(selectedResult);
		
		int index = 0;
		HashMap<Integer, String> indexAndColumn = new HashMap<Integer, String>();
		
		for(String eachRecordInColumn : columnOfString) {
			indexAndColumn.put(index++, eachRecordInColumn);
		}
		
		//Sort hashmap
	    HashMap<Integer, String> sortedMap = indexAndColumn.entrySet().stream().sorted(Entry.comparingByValue()).collect(Collectors.toMap(Entry::getKey, Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));
	    Set<Integer> sortedIndex = sortedMap.keySet();
	    
	    
	    
	    for(Integer indexInSet : sortedIndex ) {
			List<String> eachResultRow = new ArrayList<String>();
			eachResultRow = selectedRawOutputResult.get(indexInSet);
             queryOutput.add(eachResultRow);
	    }
	
	    return queryOutput;
  }

	/*Order the selected result by date and then letter ascendent
	 * 
	 * 1. Use method "orderByDate" to sort the selected result by date as initial output.
	 * 2. Loop the initial output, get all rows that next row's date is same as currently row's date.
	 * 3. For all rows we get from step2, use HashMap<index, columnToBeOrderd> to keep temp data. 
	 *    Key is index of row in initial output, value is the column value.   
	 * 4. Sort hashmap by it's value.  Get the keySet with the index. The index is for the rows in initial output.
	 * 5. Create a new output. Insert all rows into it based on the index in keySet.
	 * 6. Repeat step2-step5 until the loop ends.
	 * 7. The new output is the results
	 * 
	 */
	 
	public List<List<String>> orderByDateThenLetter(List<List<String>> selectedResult, int columnOfDATE_Index, int columnOfString_Index) {
		
		List<List<String>> outputResultOrderbyDate =orderByDate(selectedResult, columnOfDATE_Index);
		List<List<String>> selectedResultbyDateAndLetter = new ArrayList<List<String>>();
		
		//loop to gather all rows with same Date
		for(int i=0; i<outputResultOrderbyDate.size();i++)
		{
			int j=i+1;
			HashMap<Integer, String> tempMap = new HashMap<Integer, String>();
			List<String> currentRow = outputResultOrderbyDate.get(i);
			
			//get current row's column value
			String currentRowColumnValue = currentRow.get(columnOfString_Index);
			String currentRowDate = currentRow.get(columnOfDATE_Index);
			tempMap.put(i,currentRowColumnValue);
			
			//Check the situation: index i reaches the end of the outputResultOrderbyDate
			if(i==outputResultOrderbyDate.size()-1) {
				selectedResultbyDateAndLetter.add(currentRow);
					break;
				}
			List<String> nextRow  = outputResultOrderbyDate.get(j);
			
			//get next row's date
			String nextRowDate = nextRow.get(columnOfDATE_Index);


				
			//If date is different, add current row into the new output
			if(!currentRowDate.equals(nextRowDate)) {
					selectedResultbyDateAndLetter.add(currentRow);
				}
			
			// put all same-date column in hashamp. Sort hashmap and get the keyset index of intial output
			else {
			
			//Get index for all rows with same Date
			while(currentRowDate.equals(outputResultOrderbyDate.get(j).get(columnOfDATE_Index))) 
			{
			   tempMap.put(j, outputResultOrderbyDate.get(j).get(columnOfString_Index));
					j++;
		    }
				
			//Sort hashmap
		    HashMap<Integer, String> sortedMap = tempMap.entrySet().stream().sorted(Entry.comparingByValue()).collect(Collectors.toMap(Entry::getKey, Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));
			Set<Integer> sortedIndex = sortedMap.keySet();
			
			for(Integer indexInSet : sortedIndex ) {
				 selectedResultbyDateAndLetter.add(outputResultOrderbyDate.get(indexInSet));
			    }
			
			//move pointer i to pointer j-1 position. Repeat the actions above until the loop ends. 
			//j-1 is because when new iteration starts, i will plus 1. 
			i=j-1;
		  }			 
	  }		
		return selectedResultbyDateAndLetter;
	}
	

	/* Order the selected result by letter ascendent and then date
	 * This is similar to method "orderByDateThenLetter", so far just leave the method here
	 */
	public List<List<String>> orderByLetterThenDate(List<List<String>> selectedResult, int columnOfString_Index, int columnOfDATE_Index) {
		return null;
	}

}
