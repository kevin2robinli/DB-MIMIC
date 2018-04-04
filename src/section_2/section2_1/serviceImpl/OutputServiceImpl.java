package section_2.section2_1.serviceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import section_1.util.ImporterPropertyLoader;
import section_2.section2_1.service.OutputService;

public class OutputServiceImpl implements OutputService{

	static final String QUERYOUTPUTPATH = "queryOutputPath";

	public void queryOutput(List<List<String>> rawQueryResult, String inputFileSampleName, String queryType) {
		 
		try {
			
		ImporterPropertyLoader importerProp = new ImporterPropertyLoader();
		String outputPath = importerProp.loadPropertyByName(QUERYOUTPUTPATH);
		
		String queryOutputFolderLocation = outputPath + inputFileSampleName;
		
		//Create query output folder if it doens't exist
		queryOutputFolderCreation(queryOutputFolderLocation);	
		
		//create query output files
		String filePath = queryOutputFileCreation(queryOutputFolderLocation, queryType);
		
		//write output into query output files
		BufferedWriter queryOutputBufferWriter = null;
		
	  for(List<String> oneRow :  rawQueryResult) {
		  
		  String handledRow = handleOneRowFromRawResult(oneRow);
		  queryOutputBufferWriter = new BufferedWriter(new FileWriter(filePath,true));
	     
		  queryOutputBufferWriter.write(handledRow);
		  queryOutputBufferWriter.newLine();
			
		  queryOutputBufferWriter.flush();
		  queryOutputBufferWriter.close();
		    	
		    }
		
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Handle one row of raw query result
	public String handleOneRowFromRawResult(List<String> rawQueryResultRow) {
		StringBuffer oneRowStringBuffer = new StringBuffer();
		for(String eachColumn : rawQueryResultRow) {
			oneRowStringBuffer.append(eachColumn + ",");
		}
		
		//remove character "," at the end of the line.
		oneRowStringBuffer.deleteCharAt(oneRowStringBuffer.length()-1);
		
		String result = oneRowStringBuffer.toString();
		
		return result;
	}

	//create query output folder
	public void queryOutputFolderCreation(String queryOutputFolderLocation) {
		  boolean directoryCreationStatus;  
		  String path = queryOutputFolderLocation;
		  File dir = new File(path);
		  if(dir.exists()) {
		      System.out.println("directory existed.");
		  }
		  else {
		      directoryCreationStatus  = dir.mkdir();
		      if (directoryCreationStatus){
		      System.out.println("directory created.");
		    }
		    else{
		      System.out.println("failed to create the directory");
		    }
		  }
		
	}

	//Create query output files
	public String queryOutputFileCreation(String queryOutputFolderLocation, String queryType) {
		
		    boolean directoryCreationStatus;
		    String outputFilePath = queryOutputFolderLocation + "/" + queryType + "_" + Long.toString(new Date().getTime()) +".txt";

			File outputFile = new File(outputFilePath);
			try {
				directoryCreationStatus = outputFile.createNewFile();
				if(directoryCreationStatus){
					System.out.println("File " + outputFile + " created." );
				 }
				else {
					System.out.println("failed to create file");
			     }
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return outputFilePath;
	}

}
