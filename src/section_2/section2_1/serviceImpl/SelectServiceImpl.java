package section_2.section2_1.serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import section_2.section2_1.service.SelectService;

public class SelectServiceImpl implements SelectService{
	
	//For demo purpose, hard code the data store folder name (This is created by section 1 when generate db store file) 
	static final String IMPORTFILETYPE = "inputFileSampleOriginal";

	//execute select query
	public List<List<String>> select(String[] columns) {
	
		List<List<String>> selectResult = new ArrayList<List<String>>();
		
		for(String column : columns) {
			String dbStoreFileLocation =  "resource/db/" + IMPORTFILETYPE + "/" + column + ".txt";
			List<String> columnResult = new ArrayList<String>();
			columnResult = readDataFromDBStoreFile(dbStoreFileLocation);
			selectResult.add(columnResult);
		}
		
		return selectResult;
		
	}

	//read data from a dbStore file
	public List<String> readDataFromDBStoreFile(String dbStoreFileLocation) {
		
		File file = new File(dbStoreFileLocation);
		String line;
		
		List<String> resultList = new ArrayList<String>();
		FileReader dbStoreFileReader;
		
		try {
			dbStoreFileReader = new FileReader(file);
			BufferedReader dbStoreFileBufferedReader = new BufferedReader(dbStoreFileReader);

			while ((line = dbStoreFileBufferedReader.readLine()) != null) {
				resultList.add(line);		
			}
			
			dbStoreFileReader.close();
			dbStoreFileBufferedReader.close();
			
		} catch (FileNotFoundException fnf_e) {
			fnf_e.printStackTrace();
		}catch (IOException io_e) {
			io_e.printStackTrace();
		}
		return resultList;	
	}

}
