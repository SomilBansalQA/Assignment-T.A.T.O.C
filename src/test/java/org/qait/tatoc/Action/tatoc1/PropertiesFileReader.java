package org.qait.tatoc.Action.tatoc1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {

	File configPropertiesFile;
	String fileName;
	 PropertiesFileReader(String fileName){
		 this.fileName=fileName;
	 }
	public Object getOptionValue(String optionKey) {

		configPropertiesFile = new File("C:" + File.separator + "Users" + File.separator + "somilbansal"
				+ File.separator + "Desktop" + File.separator + "Eclipse_Workspace" + File.separator + "tatoc2"
				+ File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator
				+ "testdata" + File.separator + fileName);
		
		FileReader reader = null;
		try {
			reader = new FileReader(configPropertiesFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Properties p = new Properties();
		try {
			p.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(optionKey);
	}
}
