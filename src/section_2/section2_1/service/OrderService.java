package section_2.section2_1.service;

import java.util.List;

public interface OrderService {
	
	//Order the selected result by date
	public List<List<String>> orderByDate(List<List<String>> selectedResult, int columnOfDATE_Index);
	
	//Order the SELECT result by Letter ascendent 
	public List<List<String>> orderByLetter(List<List<String>> selectedResult, int columnOfString_Index);
	
	//Order the selected result by date and then letter
	public List<List<String>> orderByDateThenLetter(List<List<String>> selectedResult, int columnOfDATE_Index, int columnOfString_Index);
	
	//Order the selected result by letter and then letter
	public List<List<String>> orderByLetterThenDate(List<List<String>> selectedResult, int columnOfString_Index, int columnOfDATE_Index);

}
