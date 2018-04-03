package section_2.section2_1.service;

import java.util.List;

public interface OrderService {
	
	/*Order the selected result by date. Algorithm as below steps: 
	 * 1. Fine the earliest date, get the index(line#) in SELECT result, then use the index to find all other columns in
	 * other SELECT results. Store the found record. 
	 * 2. remove all found results from the SELECTED lists. now the new Lists are without the earliest record.
	 * 3. recursively do step1, until all records are removed. 
	 * 4. Output
	 */
	public List<List<String>> orderByDate(List<List<String>> selectedResult, int columnDATE_Index);
	
	//Assist method "orderByDate" to order records by Date. It is recursively invoked by method "orderByDate"
	public List<List<String>> orderByDateAssister(List<List<String>> finalSelectResult, List<List<String>> selectedResult, int columnDATE_Index);

	
	//Order the selected result by letter
	public List<List<String>> orderByLetter(List<List<String>> selectedResult);
	
	//Order the selected result by date and then letter
	public List<List<String>> orderByDateThenLetter(List<List<String>> selectedResult);
	
	//Order the selected result by letter and then letter
	public List<List<String>> orderByLetterThenDate(List<List<String>> selectedResult);

}
