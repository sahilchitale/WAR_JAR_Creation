package com.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HMTest {
	
	/** New addition hashmap. */
	@SuppressWarnings("rawtypes")
	public static Map<String, Map> hashMap = new HashMap<String, Map>();
	
	/** The rule service name. */
	private static String ruleServiceName;

	/** The entry point name for Health Check. */
	private String healthCheckEntryPoint;

	/** The entry point name for pricing method name. */
	private static String entryPoint;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createHashmaps();
		try {
			Map<String, String> mp = hashMap.get("COMMERCIAL_CLOS2");
			ruleServiceName = mp.get("serviceName");
			entryPoint = mp.get("decisionPoint");
			
			System.out.println(ruleServiceName);
			System.out.println(entryPoint);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//New addition
		public static void createHashmaps() {
			//String propFileLoc = System.getenv("BL_BNPL_Configurations");
			InputStream inputStream;
			Properties prop = new Properties();

			try {

				//File file = new File("D:\\Act21 Work\\lib\\application.properties");
				File file = new File("D:\\ConsumerClosWarDeployment\\WAR_JAR_Generation\\application.properties");
				
				inputStream = new FileInputStream(file);

				if (inputStream != null) {
					prop.load(inputStream);
				}
				int totalProd = Integer.parseInt(prop.getProperty("product.totalCount"));

				for (int i = 1; i <= totalProd; i++) {
					String IdentityName = prop.getProperty(i + ".identity");
					String ServerFileLocation = prop.getProperty(i + ".serverFile");
					String DecisionPointName = prop.getProperty(i + ".decisionPoint");
					String healthCheck = prop.getProperty(i + ".healthCheck");
					String serviceName = prop.getProperty(i + ".serviceName");
					Map<String, Object> mp = new HashMap<String, Object>();
					mp.put("identity", IdentityName);
					mp.put("serverFile", ServerFileLocation);
					mp.put("decisionPoint", DecisionPointName);
					mp.put("healthCheck", healthCheck);
					mp.put("serviceName", serviceName);
					/*
					 * System.out.println(i+"-> IdentityName -> "+IdentityName);
					 * System.out.println(i+"-> ServerFileLocation -> "+ServerFileLocation);
					 * System.out.println(i+"-> DecisionPointName -> "+DecisionPointName);
					 * System.out.println(i+"-> healthCheck -> "+healthCheck);
					 * System.out.println(i+"-> serviceName -> "+serviceName);
					 */
					hashMap.put(IdentityName, mp);
				}

			} catch (Exception e) {

			}

		}

}
