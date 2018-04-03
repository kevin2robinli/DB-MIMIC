package section_1.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ImporterPropertyLoader {
	
	static final String IMPORTPROPPATH = "resource/configuration/importerConf.properties";

	public String loadPropertyByName(String propKey) throws IOException {
	
		Properties prop = new Properties();
		prop.load(new FileInputStream(IMPORTPROPPATH));	
		String propValue = prop.getProperty(propKey);
		
   return propValue;	
	}
}
