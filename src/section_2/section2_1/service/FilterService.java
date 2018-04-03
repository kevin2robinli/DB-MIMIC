package section_2.section2_1.service;

import java.util.List;

public interface FilterService {

	/*filter the selected result
	 *columnName and filterCriteria are splitted from filter condition
	 */
	public List<List<String>> filter(List<List<String>> selectedResult, String columnName, String filterCriteria);
	
	//find index of the filtered results. The index is used to filter the result of SELECT result.
	public List<Integer> findIndex(String columnName, String filterCriteria);
}
