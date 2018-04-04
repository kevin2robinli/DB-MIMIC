package section_2.section2_1.service;

import java.util.List;

public interface SelectService {
	
	//execute select query
	public List<List<String>> select(String columns[]);
	
	//read data from dbStore file
	public List<String> readDataFromDBStoreFile(String dbStoreFileName);
	
	//handle selected data
	public List<List<String>> handleSelectedData(List<List<String>> selectedData);
	
	//get select data from date store file
	public List<List<String>> selectFromDateStore(String columns[]);

}
