package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.gorest.frameworkexception.APIFrameworkException;

public class ConfigurationManager {
	
	Properties prop;
	FileInputStream fis;
	
	//mvn clean install -Denv="qa"
	
	public Properties initProp() {
		
		String envName = System.getProperty("env");
		prop = new Properties();
		
		try {	
		if(envName==null) {
			System.out.println("No env is given hence Running tests on default environment");
			fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
		}else {
			System.out.println("Running tests on environment::"+envName);
			
			switch (envName.toLowerCase()) {
			case "qa":
				fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev":
				fis = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stage":
				fis = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "prod":
				fis = new FileInputStream("./src/test/resources/config/config.properties");
				break;

			default:
				System.out.println("Please pass the right environment name "+ envName);
				throw new APIFrameworkException("NO ENV Is GIVEN");
			}
		}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	
		
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

}
