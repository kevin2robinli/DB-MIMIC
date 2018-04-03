package dataEngine;

import section_1.importer.service.ImporterService;
import section_1.importer.serviceImpl.ImporterServiceImpl;

public class Start {

	public static void main(String[] args) {
      
		ImporterService importer = new ImporterServiceImpl();
		importer.readAndHandleFileSample();
	}

}
