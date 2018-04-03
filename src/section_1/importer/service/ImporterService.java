package section_1.importer.service;

import java.util.List;

public interface ImporterService {
	
	//Read file sample 
	public void readAndHandleFileSample();
	
	//Parse file sample
    public String[] parseFileSample(String line);
    
    //Create db storage folder
    public void dbStorgeFolderCreation(String inputDataFileName);
    
    //Create db storage files for the inupt data
    public void dbStorageFileCreation(String inputDataFileName, String[] columns);
    
	//Generate hashcode for data record to make unique. 
    public String generateRecordHash(String[] columns);
    
    //Check if record already exits
    public List isRecordExist(String filePath, String recordHash);
    
    //insert hash into HashRecord table, 
    public void insertHash(String hashFileLocation, String hash);
    
    //overwrite old record(if already exists)
    public void overwrite(String filename,int lineNumber, String newData);
    
    //check one line of input data is valid or not
    public boolean isThisLineDataValid(String[] columns);
 
}
