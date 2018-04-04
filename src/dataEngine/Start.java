package dataEngine;

import section_1.importer.service.ImporterService;
import section_1.importer.serviceImpl.ImporterServiceImpl;

public class Start {

	public static void main(String[] args) {
      
		ImporterService importer = new ImporterServiceImpl();
		importer.readAndHandleFileSample();
		
		
//		QueryCommandParser QueryCommandParse = new QueryCommandParser();
//		String command = "$s TITLE,REV,DATE $o DATE,TITLE";
//		String command = "$s TITLE,REV,DATE $f DATE=2014-04-01";
//		HashMap commandMap = QueryCommandParse.pasreQueryCommand(command);
//		System.out.println(commandMap);
		
	}

}
