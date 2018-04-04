package section_1.importer.serviceImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import exception.DataOverMaxLengthException;
import section_1.util.ImporterPropertyLoader;

public class ImporterServiceImpl implements section_1.importer.service.ImporterService{
	
	//For demo purpose, hard code the input file name. 
	static final String IMPORTFILETYPE = "inputFileSampleOriginal";
	
	
    //Read data input file 
	public void readAndHandleFileSample(){
		try {
		
		//Load input file location. The location is stored in property file		
	    ImporterPropertyLoader importerProp = new ImporterPropertyLoader();
	    String inputFileSampleOriginalPath = importerProp.loadPropertyByName(IMPORTFILETYPE);
	    	
		String line;
		int index = 0;
		String[] columnNames = null;
		
		
		File file = new File(inputFileSampleOriginalPath);
		FileReader fileSampleReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileSampleReader);
		
		//read input file line by line
		while ((line = bufferedReader.readLine()) != null) {
		String[] columns	= parseFileSample(line);
		
		//create folder and db storage files for db storage files
		if(index==0) {
			columnNames = columns;	
			dbStorgeFolderCreation(IMPORTFILETYPE);	
		    dbStorageFileCreation(IMPORTFILETYPE, columns);

		}
		//insert data into different db storage files by column.

	else
	{
	    String hashFileLocation = "resource/dataStore/" + IMPORTFILETYPE + "/" + "RecordHash" + ".txt";	
		String newRecordHash = generateRecordHash(columns);
		List isRecordExsitResult = isRecordExist(hashFileLocation, newRecordHash);
		
		//if this line is new, insert into all data store files and hash record file
	  if(!(boolean)isRecordExsitResult.get(0)){
		  
	    int nameIndex = 0;
	    BufferedWriter dataStorageBufferWriter = null;
	    
	    //
	    boolean isThisLineValid = isThisLineDataValid(columns);
	    
    	if(!isThisLineValid) {
    		throw new DataOverMaxLengthException("data [" + line + "] is over 64 chars. Invalid input.");
    	}
    	
    	else {  
        for(String column : columns) {
        	
        	//write data into DB storage files
    	    String dbStorageFile = "resource/dataStore/" + IMPORTFILETYPE + "/" + columnNames[nameIndex++] + ".txt";	
    	    dataStorageBufferWriter = new BufferedWriter(new FileWriter(dbStorageFile,true));
		dataStorageBufferWriter.write(column);
		dataStorageBufferWriter.newLine();
		
		dataStorageBufferWriter.flush();
		dataStorageBufferWriter.close();
       }
        //insert Hashcode
        insertHash(hashFileLocation, newRecordHash);
      }
       
   }
	    //if this line STB+TITLE+DATE is not unique, overwrite data in all files
		else {
		 int nameIndex = 0;

			int lineNumber = (int)isRecordExsitResult.get(1);	    
	        for(String column : columns) {
	        	//write data into DB storage files
	    	    String dbStorageFile = "resource/dataStore/" + IMPORTFILETYPE + "/" + columnNames[nameIndex++] + ".txt";	
	        	overwrite(dbStorageFile, lineNumber, column);		
		}
	}
	
	}
		index++;
}
		fileSampleReader.close();
		bufferedReader.close();				
	}catch (IOException e) {
		e.printStackTrace();
	}
		
}

	//parse input data, 
	public String[] parseFileSample(String line) {
		
		//split data by "|"
		String[] columns = line.split("\\|");
		return columns;
		
	}

    //create db storage folder for input data
	public void dbStorgeFolderCreation(String inputDataFileName) {
		
		  boolean directoryCreationStatus;  
		  String path = "resource/dataStore/" + inputDataFileName;
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

	//create db storage files for the inupt data, each file is for a "column" 
	public void dbStorageFileCreation(String inputDataFileName, String[] columns) {
		
		for(String column: columns) {
	    boolean directoryCreationStatus;

		File dbStoragefile = new File("resource/dataStore/" + inputDataFileName + "/" + column + ".txt");
		if ( dbStoragefile.exists()) {
			System.out.println("File " + column + ".txt already exsits.");
		
		}
		
		else {
		try {
			directoryCreationStatus = dbStoragefile.createNewFile();
			if(directoryCreationStatus){
				System.out.println("File " + column + ".txt created." );
			 }
			else {
				System.out.println("failed to create file");
		     }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	}
		
	//create data storage hash file.	
      File recordHash = new File("resource/dataStore/" + inputDataFileName + "/" + "RecordHash" + ".txt");			
      if ( recordHash.exists()) {
			System.out.println("File RecordHash.txt already exsits.");
		}
      else {
      try {
    	  boolean recordHashStatus;
		recordHashStatus = recordHash.createNewFile();
		if(recordHashStatus){
			System.out.println("File " + "RecordHash" + ".txt created." );
		 }
		else {
			System.out.println("failed to create file");
	     }
	} catch (IOException e) {
		e.printStackTrace();
	}
      }
  }

	
	//Generate hashcode for data record to make unique. 
	public String generateRecordHash(String[] columns) {

		//For Demo purpose, hard code the String array index postion.
		String columnCombination = columns[0] + columns[1] + columns[3];
		String hash = Integer.toString(columnCombination.hashCode());    
	   return hash;
	 
	}

	
	//check if new record is with unique STB+TITLE+DATE
	public List isRecordExist(String filePath, String recordHash) {
        
		List result = new ArrayList();
		String line;
		boolean isExist = false;
		int lineNumber = 1;
		
		try {
			
		  File file = new File(filePath);
		  FileReader hashFileReader;
		  hashFileReader = new FileReader(file);
		  BufferedReader bufferedReader = new BufferedReader(hashFileReader);
		  
		  while ((line = bufferedReader.readLine()) != null) {
				if(recordHash.equals(line)) {
					isExist = true;
					System.out.println("line " + lineNumber + " alreay has unique key [" + line + "]");
					
					break;
				}	
				lineNumber++;
			}
		  
		  result.add(isExist);
		  result.add(lineNumber);
		  
		  hashFileReader.close();
		  bufferedReader.close();
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		return result;
	}


	//insert hashcode into HashRecord data storage file
	public void insertHash(String hashFileLocation, String hash) {

		try {
			BufferedWriter dataStorageHashBufferWriter = new BufferedWriter(new FileWriter(hashFileLocation,true));
		    dataStorageHashBufferWriter.write(hash);
		    dataStorageHashBufferWriter.newLine();
		    
			dataStorageHashBufferWriter.flush();
			dataStorageHashBufferWriter.close();	
		}catch (IOException e) 
		   {
			 e.printStackTrace();
		   }
	}

	
	//Overwrite old record
	public void overwrite(String filename,int lineNumber, String newData) {
		Path path = Paths.get(filename);
	    List<String> lines;
		try {
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			lines.set(lineNumber - 1, newData);
		    Files.write(path, lines);
            
		} catch (IOException e) {
			e.printStackTrace();
		}    
	  }

    //check one line of input data is valid or not
	public boolean isThisLineDataValid(String[] columns) {
		boolean isValid = true;
		for(String column : columns) {
			if(column.length() > 64){
				isValid = false;
				System.out.println("data: " + column + "is over the max length of 64 char");
				return isValid;
			}
		}
	return isValid;		
	  }
	}
	
