package section_2.section2_1.service;

import java.util.List;

public interface SelectService {
	
	//execute select query
	public List<List<String>> select(String columns[]);
	
	//read data from dbStore file
	public List<String> readDataFromDBStoreFile(String dbStoreFileName);

}
