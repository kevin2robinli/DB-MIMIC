package dataEngine;

import java.util.HashMap;

import section_1.importer.service.ImporterService;
import section_1.importer.serviceImpl.ImporterServiceImpl;
import section_2.util.QueryCommandParser;
import section_2.util.QueryServiceInvoker;

public class Start {

	public static void main(String[] args) {
      
//	    ImporterService importer = new ImporterServiceImpl();
//      importer.readAndHandleFileSample();
		
//		String command1 = "$s TITLE,REV,DATE";
//		QueryCommandParser queryCommandParser = new QueryCommandParser();
//		HashMap<String, String[]> commandMap = queryCommandParser.pasreQueryCommand(command1);		
//		QueryServiceInvoker queryServiceInvoker = new QueryServiceInvoker();
//		queryServiceInvoker.selectServiceInvoker(commandMap);
//		
//		String command2 = "$s TITLE,REV,DATE $f DATE=2014-04-01";
//		QueryCommandParser queryCommandParser = new QueryCommandParser();
//		HashMap<String, String[]> commandMap = queryCommandParser.pasreQueryCommand(command2);		
//		QueryServiceInvoker queryServiceInvoker = new QueryServiceInvoker();
//		queryServiceInvoker.filterServiceInvoker(commandMap);
//		
//		String command3 = "$s TITLE,REV,DATE $o DATE";
//		QueryCommandParser queryCommandParser = new QueryCommandParser();
//		HashMap<String, String[]> commandMap = queryCommandParser.pasreQueryCommand(command3);		
//		QueryServiceInvoker queryServiceInvoker = new QueryServiceInvoker();
//		queryServiceInvoker.orderServiceInvoker(commandMap);
//		
//		String command4 = "$s TITLE,REV,DATE $o TITLE";
//		QueryCommandParser queryCommandParser = new QueryCommandParser();
//		HashMap<String, String[]> commandMap = queryCommandParser.pasreQueryCommand(command4);		
//		QueryServiceInvoker queryServiceInvoker = new QueryServiceInvoker();
//		queryServiceInvoker.orderServiceInvoker(commandMap);
		
		String command5 = "$s TITLE,REV,DATE $o DATE,TITLE";
		QueryCommandParser queryCommandParser = new QueryCommandParser();
		HashMap<String, String[]> commandMap = queryCommandParser.pasreQueryCommand(command5);		
		QueryServiceInvoker queryServiceInvoker = new QueryServiceInvoker();
		queryServiceInvoker.orderServiceInvoker(commandMap);
		

		
	}

}
