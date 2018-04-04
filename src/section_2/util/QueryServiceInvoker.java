package section_2.util;

import java.util.HashMap;
import java.util.List;

import section_2.section2_1.service.FilterService;
import section_2.section2_1.service.OrderService;
import section_2.section2_1.service.OutputService;
import section_2.section2_1.service.SelectService;
import section_2.section2_1.serviceImpl.FilterServiceImpl;
import section_2.section2_1.serviceImpl.OrderServiceImpl;
import section_2.section2_1.serviceImpl.OutputServiceImpl;
import section_2.section2_1.serviceImpl.SelectServiceImpl;

public class QueryServiceInvoker {

	static final String IMPORTFILETYPE = "inputFileSampleOriginal";
	static final String COLUMNNAME_DATE = "DATE";

	/*Invoke Select Query Service, output result
	 * 
	 */
	public void selectServiceInvoker(HashMap<String, String[]> commandMap) {
		
		OutputService outputService = new OutputServiceImpl();
		
		String[] queryColumns = commandMap.get("select");
		SelectService selectService = new SelectServiceImpl();
		List<List<String>> rawOutputResult = selectService.select(queryColumns);
			
		outputService.queryOutput(rawOutputResult, IMPORTFILETYPE, "select");
		
	}
	
	/*Invoke Filter Query Service, output result
	 * 
	 * 1.Invoke Select Service to get all queried columns
	 * 2.Invoke Filter Service to filter the result
	 */
	public void filterServiceInvoker(HashMap<String, String[]> commandMap) {
		
		OutputService outputService = new OutputServiceImpl();

		String[] queryColumns = commandMap.get("select");
		String[] filterColumnAndCriteria = commandMap.get("filter");
		
		//get filter column index in select columns
		int filterColumnIndex = 0;
		for(String column : queryColumns) {
			if(column.equals(filterColumnAndCriteria[0])) {
				break;
			}
			filterColumnIndex++;
		}
		
		String filterColumnName = filterColumnAndCriteria[0];
		String filterCriteria = filterColumnAndCriteria[1];
		
		//Invoke Select Service to get selected result. Then invoke Filter Service.
		SelectService selectService = new SelectServiceImpl();
		List<List<String>> selectedRawOutputResult = selectService.select(queryColumns);
		
		FilterService filterService = new FilterServiceImpl();
		List<List<String>> filterResult = filterService.filter(selectedRawOutputResult, filterColumnName, filterColumnIndex, filterCriteria);

		outputService.queryOutput(filterResult, IMPORTFILETYPE, "filterBy"+filterColumnName);		
	}
	
	
	/*Invoke Order Query Service, output result
	 * 
	 * Based on the query command, 3 kinds of order query:
	 * 1. Order by Date
	 * 2. Order by Letter
	 * 3. Order by Date and letter
	 * 
	 */
	public void orderServiceInvoker(HashMap<String, String[]> commandMap){
		
		OutputService outputService = new OutputServiceImpl();

		String[] queryColumns = commandMap.get("select");
		String[] orderColumns = commandMap.get("order");
		
		//Only one order. Either DATE or other column.
		if(orderColumns.length ==1) {
			
			//Invoke Select Service to get selected result.
			SelectService selectService = new SelectServiceImpl();
			List<List<String>> selectedResult = selectService.selectFromDateStore(queryColumns);
			OrderService orderService = new OrderServiceImpl();


			int orderColumnIndex = 0;
			for(String column : queryColumns) {
				if(column.equals(orderColumns[0])) {
					break;
				}
				orderColumnIndex++;
			}
			
		//Order by Date	
		if(orderColumns[0].equals(COLUMNNAME_DATE)) {
			List<List<String>> orderByDateResult = orderService.orderByDate(selectedResult, orderColumnIndex);
			outputService.queryOutput(orderByDateResult, IMPORTFILETYPE, "orderByDate");				
		}
		
		//Order by letter
		else {
			List<List<String>> orderByLetterResult = orderService.orderByLetter(selectedResult, orderColumnIndex);
			outputService.queryOutput(orderByLetterResult, IMPORTFILETYPE, "orderBy"+orderColumns[0]);		
		}
	}

		
		// Order by date then letter
		else {
			
			//Invoke Select Service to get selected result.
			SelectService selectService = new SelectServiceImpl();
			List<List<String>> selectedResult = selectService.selectFromDateStore(queryColumns);
			OrderService orderService = new OrderServiceImpl();
			
			int orderColumn_DateIndex = 0;
			for(String column : queryColumns) {
				if(column.equals(COLUMNNAME_DATE)) {
					break;
				}
				orderColumn_DateIndex++;
			}
			
			int orderColumn_OtherIndex = 0;
			for(String column : queryColumns) {
				if(column.equals(orderColumns[1])) {
					break;
				}
				orderColumn_OtherIndex++;
			}
			
	 List<List<String>> orderByDateThenLetterResult = orderService.orderByDateThenLetter(selectedResult, orderColumn_DateIndex, orderColumn_OtherIndex);
	 outputService.queryOutput(orderByDateThenLetterResult, IMPORTFILETYPE, "orderByDateThen"+orderColumns[1]);				
		
		}
		
     }

}
