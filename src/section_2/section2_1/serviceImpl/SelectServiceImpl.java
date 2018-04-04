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
	
	static final String IMPORTFILETYPE = "inputFileSampleOriginal";

	/* Execute select query
	 * 
	 * 1. Read from queried-column data store files. For each column, data is stored in List<Stirng>.
	 * 2. Handle data from step1. Generate select result. 
	 *    The result contains multiple rows. Each row is a combination of all queried columns.
	 */
	public List<List<String>> select(String[] columns) {
	
		List<List<String>> selectFromDataStoreResult = selectFromDateStore(columns);
		List<List<String>> rawOutputResult = new ArrayList<List<String>>();

		rawOutputResult = handleSelectedData(selectFromDataStoreResult);
		return rawOutputResult;		
	}

	/*Read data from data store file
	 * 
	 */
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

	/* Handle selected data
	 * 
	 * Handle selected date, generate output result. 
	 * The result contains multiple rows. Each row is a combination of all queried columns.
	 */
	public List<List<String>> handleSelectedData(List<List<String>> selectedData) {

		List<List<String>> result = new ArrayList<List<String>>();
		int index = 0;
		int maxIndex = selectedData.get(0).size();
		
		while(index < maxIndex) {
			List<String> eachRow = new ArrayList<String>();
			for(List<String> column : selectedData) {
				eachRow.add(column.get(index));
			}
			result.add(eachRow);
			index++;
		}
		
		return result;
	}

	/*SELECT data from data store files
	 * 
	 * Read from queried-column data store files. For each column, data is stored in List<Stirng>.
	 * */
	public List<List<String>> selectFromDateStore(String[] columns) {
		
		List<List<String>> selectFromDataStoreResult = new ArrayList<List<String>>();
		
		for(String column : columns) {
			String dbStoreFileLocation =  "resource/dataStore/" + IMPORTFILETYPE + "/" + column + ".txt";
			List<String> columnResult = new ArrayList<String>();
			columnResult = readDataFromDBStoreFile(dbStoreFileLocation);
			selectFromDataStoreResult.add(columnResult);
		}
		return selectFromDataStoreResult;
	}

}
