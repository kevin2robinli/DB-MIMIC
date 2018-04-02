package dataEngine;

import service.ImporterService;
import serviceImpl.ImporterServiceImpl;

public class Start {

	public static void main(String[] args) {
      
		ImporterService importer = new ImporterServiceImpl();
		importer.readAndHandleFileSample();
	}

}
