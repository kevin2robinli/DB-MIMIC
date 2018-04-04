package dataEngine;

import java.util.HashMap;

import section_1.importer.service.ImporterService;
import section_1.importer.serviceImpl.ImporterServiceImpl;
import section_2.util.QueryCommandParser;
import section_2.util.QueryServiceInvoker;

public class Start {

	public static void main(String[] args) {
      
		//ImporterService importer = new ImporterServiceImpl();
		//importer.readAndHandleFileSample();
		
		String command1 = "$s TITLE,REV,DATE";
		String command2 = "$s TITLE,REV,DATE $f DATE=2014-04-01";
		String command3 = "$s TITLE,REV,DATE $o DATE";
		String command4 = "$s TITLE,REV,DATE $o TITLE";
		String command5 = "$s TITLE,REV,DATE $o DATE,TITLE";

		QueryCommandParser queryCommandParser = new QueryCommandParser();
		HashMap commandMap = queryCommandParser.pasreQueryCommand(command1);
		
		QueryServiceInvoker queryServiceInvoker = new QueryServiceInvoker();
		queryServiceInvoker.selectServiceInvoker(commandMap);
		
//		QueryCommandParser QueryCommandParse = new QueryCommandParser();
//		String command = "$s TITLE,REV,DATE $o DATE,TITLE";
//		String command = "$s TITLE,REV,DATE $f DATE=2014-04-01";
//		HashMap commandMap = QueryCommandParse.pasreQueryCommand(command);
//		System.out.println(commandMap);
		
	}

}
