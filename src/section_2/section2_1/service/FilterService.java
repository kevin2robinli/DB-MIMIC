package section_2.section2_1.service;

import java.util.List;

public interface FilterService {

	//filter the selected result
	public List<List<String>> filter(List<List<String>> selectedRawOutputResult, String filterColumnName,int filterColumnIndex, String filterCriteria);
	
}
