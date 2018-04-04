package section_2.section2_1.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import section_2.section2_1.service.FilterService;

public class FilterServiceImpl implements FilterService{

		
	/*Filter the selected result
	 * 
	 * 1. Parameters includes selectedResult, filter's ColumnName, filter's index in selectResult Row, and filter's criteria.
	 * 2. Search in each selected result's row with filter's index and criteria. if find record, put this row in a new list.
	 * 3. Search until the end of selected result. Generate the output.
	 */
	public List<List<String>> filter(List<List<String>> selectedRawOutputResult, String filterColumnName,int filterColumnIndex, String filterCriteria) {
		
		//create filter result
		List<List<String>> filterResult = new ArrayList<List<String>>();
		
		for(List<String> eachSelectReusltRow : selectedRawOutputResult) 
		{
			if(eachSelectReusltRow.get(filterColumnIndex).equals(filterCriteria)) {
				filterResult.add(eachSelectReusltRow);
			}
		}
       return filterResult;
	}

}
