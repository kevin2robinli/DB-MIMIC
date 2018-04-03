package section_2.util;

import java.util.HashMap;

public class QueryCommandParser {
	
	/* Parse command line
	 * Use HashMap to store command. the key is the query type, value is the associated columns or columns with filter
	 * 
	 * Currently, my query tool supports:  
	 * (1)-s XXX 
	 * (2)-s XXX -f XXX
	 * (3)-s XXX -O XXX
	 * 
	 * -s XXX -f XXX -o XXX will be added as future feature
	 */
	public HashMap<String, String[]> pasreQueryCommand(String command) {
		
		HashMap<String, String[]> commandMap = new HashMap<String, String[]>();
		String[] splitCommand = command.split("-");
		
		//parse select columns
		String[] selectQueryColumns = splitCommand[0].substring(2).split(",");
		commandMap.put("select", selectQueryColumns);
		
		//parse order columns
		if(splitCommand.length >=1 &&splitCommand[1].charAt(0) == 'o') {
			String[] orderQueryColumns = splitCommand[1].substring(2).split(",");
			commandMap.put("order", orderQueryColumns);
		}
		
		//parse filter condition
		if(splitCommand.length >=1 &&splitCommand[1].charAt(0) == 'f') {
			String[] filterCondition = splitCommand[1].substring(2).split("=");
			commandMap.put("filter", filterCondition);
		}
		
       return commandMap;
 	}
	
	
	
	
	
	

}
