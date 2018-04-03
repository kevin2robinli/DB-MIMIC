package section_2.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import section_2.section2_1.service.OutputService;
import section_2.section2_1.service.SelectService;
import section_2.section2_1.serviceImpl.OutputServiceImpl;
import section_2.section2_1.serviceImpl.SelectServiceImpl;

public class QueryServiceInvoker {

	static final String IMPORTFILETYPE = "inputFileSampleOriginal";

	public void selectServiceInvoker(HashMap<String, String[]> commandMap) {
		
		OutputService OutputService = new OutputServiceImpl();
		
		String[] queryColumns = commandMap.get("select");
		SelectService SelectService = new SelectServiceImpl();
		List<List<String>> selectResult = SelectService.select(queryColumns);
		
		List<List<String>> rawQueryResult = new ArrayList<List<String>>();
		
		int index = 0;
//TBD
		
		
		OutputService.queryOutput(rawQueryResult, IMPORTFILETYPE, "select");
		
	}
}
