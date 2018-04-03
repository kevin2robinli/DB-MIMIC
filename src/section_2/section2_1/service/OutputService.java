package section_2.section2_1.service;

import java.util.List;

public interface OutputService {

	//output query result
	public void queryOutput(List<List<String>> rawQueryResult, String inputFileSampleName, String queryType);
	
	//process one row of raw query result
	public String handleOneRowFromRawResult(List<String> rawQueryResultRow);
	
	//create query output folder
	public void queryOutputFolderCreation(String outputFolderLocation);

	//create query output files in output folder
	public String queryOutputFileCreation(String outputFolderLocation, String queryType);

}