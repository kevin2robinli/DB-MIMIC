package section_2.util;

import java.util.HashMap;

public class QueryCommandParser {
	
	/* Parse command line
	 * Use HashMap to store command. the key is the query type, value is the associated columns or columns with filter
	 * 
	 * Currently, my query tool supports:  
	 * (1)$s XXX,XXX,XXX,....... 
	 * (2)$s XXX $f XXX
	 * (3)$s XXX $o XXX,XXX
	 * (4)$s XXX 
	 * 
	 * -s XXX -f XXX -o XXX will be added as future feature
	 */
	public HashMap<String, String[]> pasreQueryCommand(String command) {
		
		//-s TITLE,REV,DATE -o DATE,TITLE
		HashMap<String, String[]> commandMap = new HashMap<String, String[]>();
		String[] splitCommand = command.split("\\$"); 
		
		//parse select columns.
		//when previous step did the split, first element is a " ". So to read selected columns, the index goes from 1.
		String selectColumns = splitCommand[1].trim().substring(2);
		String[] selectQueryColumns = selectColumns.split(",");
		commandMap.put("select", selectQueryColumns);
		
		//parse order columns
		//Similarly, index goes from 2 
		if(splitCommand.length >=3 &&splitCommand[2].trim().charAt(0) == 'o') {
			String orderColumns = splitCommand[2].trim().substring(2);
			String[] orderQueryColumns = orderColumns.split(",");
			commandMap.put("order", orderQueryColumns);
		}
		
		//parse filter condition
		if(splitCommand.length >=3 &&splitCommand[2].trim().charAt(0) == 'f') {
			String filterSection = splitCommand[2].trim().substring(2);

			String[] filterCondition = filterSection.split("=");
			commandMap.put("filter", filterCondition);
		}
		
		//-s TITLE,REV,DATE -f DATE=2014-04-01 
       return commandMap;
 	}
	
	
	
	
	
	

}
