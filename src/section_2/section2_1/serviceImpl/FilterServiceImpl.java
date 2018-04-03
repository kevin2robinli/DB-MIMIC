package section_2.section2_1.serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import section_2.section2_1.service.FilterService;

public class FilterServiceImpl implements FilterService{

	//For demo purpose, hard code the data store folder name (This is created by section 1 when generate db store file) 
     static final String IMPORTFILETYPE = "inputFileSampleOriginal";
		
	//filter the selected result
	public List<List<String>> filter(List<List<String>> selectedResult, String columnName,String filterCriteria) {
		
		String dbStoreFileLocation =  "resource/db/" + IMPORTFILETYPE + "/" + columnName + ".txt";
		List<List<String>> filteredSelectionResult = new ArrayList<List<String>>();
		List<Integer> resultIndex = findIndex(dbStoreFileLocation, filterCriteria);
		
		for(List<String> selectedEachcolumn : selectedResult) 
		{
			List<String> filteredEachColumn = new ArrayList<String>();
			for(int a : resultIndex) 
			{
				filteredEachColumn.add(selectedEachcolumn.get(a));
			}
			filteredSelectionResult.add(filteredEachColumn);
		}
       return filteredSelectionResult;
	}

	//find index of the filtered results. The index is used to filter the result of SELECT result.
	public List<Integer> findIndex(String dbStoreFileLocation, String filterCriteria) {
		File file = new File(dbStoreFileLocation);
		String line;
		int count = 1;
		
		List<Integer> indexResult = new ArrayList<Integer>();
		FileReader dbStoreFileReader;
		
		try {
			dbStoreFileReader = new FileReader(file);
			BufferedReader dbStoreFileBufferedReader = new BufferedReader(dbStoreFileReader);

			while ((line = dbStoreFileBufferedReader.readLine()) != null) {
				if(line.equals(filterCriteria)) {
					indexResult.add(count);
				}	
				count++;
			}
			
			dbStoreFileReader.close();
			dbStoreFileBufferedReader.close();		
		} catch (FileNotFoundException fnf_e) {
			fnf_e.printStackTrace();
		}catch (IOException io_e) {
			io_e.printStackTrace();
		}
		
		return indexResult;
	}

}
