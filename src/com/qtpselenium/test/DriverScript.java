package com.qtpselenium.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;
import com.qtpselenium.test.Keywords;

import org.apache.log4j.Logger;

import com.qtpselenium.xls.read.Xls_Reader;

public class DriverScript {
	

		public static Logger APP_LOGS;
		
		//Suite.xlsx
		public Xls_Reader SuiteXLS;
		public int CurrentSuiteID;
		public String CurrentTestSuite; 
		
		//Single Test Suite
		public Xls_Reader CurrentTestSuiteXls;
		public int CurrentTestCaseID;	
		public String CurrnetTestCaseName;
		public int CurrentTestStepID;
		public String CurrentKeyword;
		public int CurrentTestDataSetID;
		public Method method[];
		public Keywords Keywords;
		public String Keyword_Execution_Result;
		public ArrayList<String> ResultSet;
		public String data;
		public String object;
		public static Method capturescreenShot_method;
		
		
		//properties file initilization
		public static Properties CONFIG=null;
		public static Properties OR=null;
		
		public DriverScript() throws IOException, NoSuchMethodException, RuntimeException 
		{
			
			Keywords =new Keywords();
			method = Keywords.getClass().getMethods();
			//capturescreenShot_method =Keywords.getClass().getMethod("captureScreenshot",String.class,String.class);
			
			//properties file initilization
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//com//qtpselenium//config//config.properties");
			CONFIG= new Properties();
			CONFIG.load(fs);
			
			fs = new FileInputStream(System.getProperty("user.dir")+"//src//com//qtpselenium//config//OR.poperties");
			OR= new Properties();
			OR.load(fs);
			
		}
		
		public static void main (String[] args) throws ReflectiveOperationException, Exception {
			DriverScript Test = new DriverScript();
			Test.start();
	}
				
		public void start() throws ReflectiveOperationException, IllegalArgumentException, Exception{
			// Initilize app logs
			APP_LOGS = Logger.getLogger("devpinoyLogger");
			APP_LOGS.debug("Hello");
			
			APP_LOGS.debug("Config loaded, stating test");
			
			// Check the RunMode of TestSuite
			// Check RunMode of TestCase in TestSuite
			// If Yes, Execute keywords of TestCase serially.
			//Iterate Keywords as many times as number of datasets (rows), which are set to yes.
			
			SuiteXLS  = new Xls_Reader(System.getProperty("user.dir")+"//src/com//qtpselenium//xls//Suite.xlsx");
			
			for (CurrentSuiteID=2; CurrentSuiteID<=SuiteXLS.getRowCount(Constants.TEST_SUIT_SHEET);CurrentSuiteID++ ){
				System.out.println(SuiteXLS.getCellData(Constants.TEST_SUIT_SHEET, Constants.TEST_SUIT_ID, CurrentSuiteID)+ "------"+ SuiteXLS.getCellData(Constants.TEST_SUIT_SHEET, Constants.TEST_SUIT_RUNMODE, CurrentSuiteID));
			 
				APP_LOGS.debug(SuiteXLS.getCellData(Constants.TEST_SUIT_SHEET, Constants.TEST_SUIT_ID, CurrentSuiteID)+ "------"+ SuiteXLS.getCellData(Constants.TEST_SUIT_SHEET, Constants.TEST_SUIT_RUNMODE, CurrentSuiteID));
				
				// Test suite name == test suite xls file having test cases 
				CurrentTestSuite=SuiteXLS.getCellData(Constants.TEST_SUIT_SHEET, Constants.TEST_SUIT_ID, CurrentSuiteID);
				
				
	          if (SuiteXLS.getCellData(Constants.TEST_SUIT_SHEET, Constants.TEST_SUIT_RUNMODE, CurrentSuiteID).equalsIgnoreCase("Y")){
	        	 
	        	  //Execute the Test Case from suite
	        	 APP_LOGS.debug("*****Excuting Suite ***" + "-----------"+ SuiteXLS.getCellData(Constants.TEST_SUIT_SHEET, Constants.TEST_SUIT_ID, CurrentSuiteID));
	        	 CurrentTestSuiteXls= new Xls_Reader(System.getProperty("user.dir")+"//src/com//qtpselenium//xls//"+CurrentTestSuite+".xlsx");
	        	 
	        	 ////Iterate through all the test cases in the sheet
	        	 for (CurrentTestCaseID=2; CurrentTestCaseID<=CurrentTestSuiteXls.getRowCount("Test Cases") ; CurrentTestCaseID++) {
	        		 APP_LOGS.debug(CurrentTestSuiteXls.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_ID, CurrentTestCaseID)+"--------" +CurrentTestSuiteXls.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_RUNMODE, CurrentTestCaseID));
	        		 
	        		 CurrnetTestCaseName=CurrentTestSuiteXls.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_ID, CurrentTestCaseID);
	        		 
	        		 ResultSet = new ArrayList<String>();
	        		 //Check if Runmode for Test Case is Yes
	        		 if (CurrentTestSuiteXls.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_RUNMODE, CurrentTestCaseID).equalsIgnoreCase("Y"))
	        		 {
	        			 APP_LOGS.debug("Excuting The Test Case "+  CurrentTestSuiteXls.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_ID, CurrentTestCaseID) );
	        			
	        			 
	        			 // check if Test Case has any Test Data set
	        			 if (CurrentTestSuiteXls.isSheetExist(CurrnetTestCaseName))
	        			 {
	        				//Run this as many times as number of test Datasets 
	        			 for(CurrentTestDataSetID=2;CurrentTestDataSetID<=CurrentTestSuiteXls.getRowCount(CurrnetTestCaseName); CurrentTestDataSetID++)
	        			 {
	        				  
	        				 APP_LOGS.debug("Iteration number" + "----"+(CurrentTestDataSetID-1) );
	        				 
	        				 //Checking the Runmode for Current Data Set.
	        				  if (CurrentTestSuiteXls.getCellData(CurrnetTestCaseName, Constants.TEST_CASE_RUNMODE, CurrentTestDataSetID).equalsIgnoreCase("Y"))
	        				   {
	        			     //Iterating through all keywords (steps) 
	        					  ExecuteKeyWords();
	        					  createXLSReport();
	        			      } 
	        		    }
	        		  }
	        			 else
	        			 {
	        			  // simply run keywords for the test case with not data sets.
	        				 ExecuteKeyWords();
	        				 createXLSReport();
	        				 
	        		     }
 
	        		  } 
	        	   }
	            }
			  }
		}


public void ExecuteKeyWords() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
	
	 for (CurrentTestStepID=2;CurrentTestStepID<=CurrentTestSuiteXls.getRowCount(Constants.TEST_STEPS_SHEET);CurrentTestStepID++ )
     {
		
		
	  // Checking TCID if it has Runmode "Yes";
	  if (CurrnetTestCaseName.equals(CurrentTestSuiteXls.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_CASE_ID,CurrentTestStepID)))
	  {
		  data = CurrentTestSuiteXls.getCellData(Constants.TEST_STEPS_SHEET, Constants.Data ,CurrentTestStepID );
		  object = CurrentTestSuiteXls.getCellData(Constants.TEST_STEPS_SHEET, Constants.OBJECT ,CurrentTestStepID );
		 CurrentKeyword=CurrentTestSuiteXls.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_STEPS_KEYWORD, CurrentTestStepID ); 
	     APP_LOGS.debug(CurrentKeyword + "----- Excuting keyword" + CurrentKeyword );          				 
	     //Code to Execute the KEyword steps
	     
	     	     //System.out.println(method);
	     
	     
	     // Reflection API
	     for (int i=0;i<method.length;i++){
	    	   	 if (method[i].getName().equalsIgnoreCase(CurrentKeyword)){
	       		
	       		 APP_LOGS.debug("Method is " + method[i]);
	       		 APP_LOGS.debug("Current Keyword is "+ CurrentKeyword);
	       		
	       	/*	String dataCol = CurrentTestSuiteXls.getCellData(Constants.TEST_STEPS_SHEET, Constants.Data, CurrentTestStepID);	
				// config, OR, XLS
	       		if(dataCol.startsWith(Constants.CONFIG))
	       		{
					// data has to be read from config.prop
					String temp[] = dataCol.split(Constants.dataSeparator);
					String key=temp[1];
					data=CONFIG.getProperty(key);
				}
	       		else if(dataCol.startsWith(Constants.COL))
	       		{
					String temp[] = dataCol.split(Constants.dataSeparator);
					String colName=temp[1];
					
					data= CurrentTestSuiteXls.getCellData(CurrnetTestCaseName, colName, CurrentTestDataSetID);
				}
	       		else
	       		{
					data=OR.getProperty(dataCol);
				}*/
				
				// OBJECT
				object=CurrentTestSuiteXls.getCellData(CurrnetTestCaseName, Constants.OBJECT, CurrentTestStepID);
				
			    Keyword_Execution_Result=(String)method[i].invoke(Keywords,object,data); // pass on the object and data to all functions in Keywords.java
				APP_LOGS.debug(Keyword_Execution_Result);
				ResultSet.add(Keyword_Execution_Result);
				// report the result
	       		 
				// capture screenshot
				/*capturescreenShot_method.invoke(Keywords,
						CurrentTestSuite+"_"+CurrnetTestCaseName+"_TS"+CurrentTestStepID+"_"+(CurrentTestDataSetID-1),
						Keyword_Execution_Result);*/
                         
	    	 }
	     }
	     
	  }
    } 
	 
	
}
public void createXLSReport(){
	
	String colName=Constants.RESULT +(CurrentTestDataSetID-1);
	boolean isColExist=false;
	
	for(int c=0;c<CurrentTestSuiteXls.getColumnCount(Constants.TEST_STEPS_SHEET);c++){
		System.out.println(CurrentTestSuiteXls.getCellData(Constants.TEST_STEPS_SHEET,c , 2));
		if(CurrentTestSuiteXls.getCellData(Constants.TEST_STEPS_SHEET,c , 1).equals(colName)){
			isColExist=true;
			break;
		}
	}
	
	if(!isColExist)
		CurrentTestSuiteXls.addColumn(Constants.TEST_STEPS_SHEET, colName);
	int index=0;
	for(int i=2;i<=CurrentTestSuiteXls.getRowCount(Constants.TEST_STEPS_SHEET);i++){
		
		if(CurrnetTestCaseName.equals(CurrentTestSuiteXls.getCellData(Constants.TEST_STEPS_SHEET, Constants.TEST_CASE_ID, i))){
			if(ResultSet.size()==0)
				CurrentTestSuiteXls.setCellData(Constants.TEST_STEPS_SHEET, colName, i, Constants.KEYWORD_SKIP);
			else	
				CurrentTestSuiteXls.setCellData(Constants.TEST_STEPS_SHEET, colName, i, ResultSet.get(index));
			index++;
		}
		
		
	}
	
	if(ResultSet.size()==0){
		// skip
		CurrentTestSuiteXls.setCellData(CurrnetTestCaseName, Constants.RESULT, CurrentTestDataSetID, Constants.KEYWORD_SKIP);
		return;
	}else{
		for(int i=0;i<ResultSet.size();i++){
			if(!ResultSet.get(i).equals(Constants.KEYWORD_PASS)){
				CurrentTestSuiteXls.setCellData(CurrnetTestCaseName, Constants.RESULT, CurrentTestDataSetID, ResultSet.get(i));
				return;
			}
			
	}
	}
	CurrentTestSuiteXls.setCellData(CurrnetTestCaseName, Constants.RESULT, CurrentTestDataSetID, Constants.KEYWORD_PASS);
//	if(!currentTestSuiteXLS.getCellData(currentTestCaseName, "Runmode",currentTestDataSetID).equals("Y")){}
	
	
}

}


