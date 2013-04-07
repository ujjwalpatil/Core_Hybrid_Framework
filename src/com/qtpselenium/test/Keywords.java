package com.qtpselenium.test;

import static com.qtpselenium.test.DriverScript.APP_LOGS;
import static com.qtpselenium.test.DriverScript.CurrentTestCaseID;
import static com.qtpselenium.test.DriverScript.CurrentTestSuiteXls;
import static com.qtpselenium.test.DriverScript.CurrentTestCaseID;
import static com.qtpselenium.test.DriverScript.CurrnetTestCaseName;
import static com.qtpselenium.test.DriverScript.CurrentTestDataSetID;
import static com.qtpselenium.test.DriverScript.CONFIG;
import static com.qtpselenium.test.DriverScript.OR;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
//import static com.qtpselenium.test.DriverScript.*;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.internal.selenesedriver.TakeScreenshot;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qtpselenium.xls.read.Xls_Reader;
import com.thoughtworks.selenium.Wait;
public class Keywords {
	
	public WebDriver driver;
		
	//WebDriver driver=null;
	
	public  String openBrowser(String object, String data){	
		
		APP_LOGS.debug("Opening browser");
		//APP_LOGS.debug(object);
		//APP_LOGS.debug(data);
		
		if (CONFIG.getProperty(data).equals("Mozilla"))
			driver =new FirefoxDriver();
			else if (CONFIG.getProperty(data).equals("IE"))
			driver =new InternetExplorerDriver();
			else if (CONFIG.getProperty(data).equals("Chrome"))
			driver =new ChromeDriver();
		
		/*if (DriverScript.data.equals("Mozilla"))
			driver =new FirefoxDriver();
			else if (CONFIG.getProperty(data).equals("IE"))
			driver =new InternetExplorerDriver();
			else if (CONFIG.getProperty(data).equals("Chrome"))
			driver =new ChromeDriver();
		*/
		long implicitWaitTime=Long.parseLong(CONFIG.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(implicitWaitTime,TimeUnit.SECONDS );
		
		return Constants.KEYWORD_PASS;
	}
	
		
		
	public  String navigate(String object, String data){	
		APP_LOGS.debug("Navigating to URL" + CONFIG.getProperty(data) );
		try{
		driver.navigate().to(CONFIG.getProperty(data));
		}catch(Exception e){		
		return Constants.KEYWORD_FAIL + " -- Can not navigate ";
	}
		return Constants.KEYWORD_PASS;
	}
	
	
	public  String validateTitle(String object, String data){	
		APP_LOGS.debug("Validating Title" + CONFIG.getProperty(data) );
		try{
		//driver.navigate().to(CONFIG.getProperty(data));
			
			String Actual=driver.getTitle();
			String Expected= driver.findElement(By.xpath(OR.getProperty(data))).getText();
			if (Actual.equals(Expected))
				return	Constants.KEYWORD_PASS;
				else
					return Constants.KEYWORD_FAIL+ "--- Not able to Verify link Text";
			
		}catch(Exception e){		
		return Constants.KEYWORD_FAIL + " -- Can not validate Title ";
	}
		//return Constants.KEYWORD_PASS;
	}
	
	
	
	
	
	public  String clickLink(String object, String data){
        APP_LOGS.debug("Clicking on link ");
        try{
     driver.findElement(By.xpath(OR.getProperty(object))).click();
      }catch(Exception e){
    	  return Constants.KEYWORD_FAIL+ "--- Not able to click the link";
      }
		return Constants.KEYWORD_PASS;
	}
	
	public  String verifyLinkText(String object, String data){
       
		APP_LOGS.debug("Verifying Link Text ");
		try{
			String Actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
			String Expected= driver.findElement(By.xpath(OR.getProperty(data))).getText();
			if (Actual.equals(Expected))
			return	Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL+ "--- Not able to Verify link Text";
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+ "--- Not able to Verify link Text";
		}
				
		
	}
	
	
	public  String clickButton(String object, String data){
        APP_LOGS.debug("Clicking on Button");
		
        try{
        	driver.findElement(By.xpath(OR.getProperty(object))).click();
        }catch (Exception e){
        	return Constants.KEYWORD_FAIL+ " Not able to click on button"+e.getMessage();
        }
        
		return Constants.KEYWORD_PASS;
	}
	
	public  String verifyButtonText(String object, String data){
		APP_LOGS.debug("Verifying the button text");
		
		try{
			String Actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
			String Expected= driver.findElement(By.xpath(OR.getProperty(data))).getText();
			if (Actual.equals(Expected))
			return	Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL+ "--- Not able to Verify Button Text";
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+ "--- Not able to Verify Button Text";
		}
			
		
	}
	
	/*@SuppressWarnings("unchecked")
	public  String selectList(String object, String data){
		APP_LOGS.debug("Selecting from list");
		
		try{
			
			if (!data.equals(Constants.RANDOM_VALUE)){
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
			}else{
				//logic to find random value in the list
				WebElement droplist = driver.findElement(By.xpath(OR.getProperty(object)));
				List<WebElement> droplist_contents = (List<WebElement>) droplist.findElement(By.xpath(OR.getProperty("option")));
				Random num =new Random();
				int index=num.nextInt(droplist_contents.size());
				String SelectedVal =droplist_contents.get(index).getText();
						
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(SelectedVal);
			}
			}catch (Exception e){
				return Constants.KEYWORD_FAIL + "--- Could not select any element from lsit";
			}
		
		
		return Constants.KEYWORD_PASS;	
	}
	*/
	
	public  String selectList(String object, String data){
		APP_LOGS.debug("Selecting from list");
       try{
			
			driver.findElement(By.xpath(OR.getProperty(object))).click();
			
			}catch (Exception e){
				return Constants.KEYWORD_FAIL + "--- Could not select any element from lsit";
			}
		
		
		return Constants.KEYWORD_PASS;	
	}
	
	
	public  String verifyListSelection(String object, String data){
		APP_LOGS.debug("Verifying the selection of the list");
		
		
		
		
		
		
		return Constants.KEYWORD_PASS;	
	}
	
	public  String verifyAllListElements(String object, String data){
		APP_LOGS.debug("Verifying all the list elements");
		try{
			WebElement droplist = driver.findElement(By.xpath(OR.getProperty(object)));
			List<WebElement> drop_contents = (List<WebElement>) droplist.findElement(By.tagName("Options"));
		
					//extract the expected value from OR.Properties.
			String temp =data;
			String allElements[]=temp.split(",");
			//check if size of array == size of list
			
			if (allElements.length != drop_contents.size())
				return Constants.KEYWORD_FAIL + " -- size of list does not match";
			
			for (int i=0;i<drop_contents.size();i++){
				
				if (!allElements[i].equals(drop_contents.get(i).getText()));
				return Constants.KEYWORD_FAIL + " -- Element not found" + allElements[i];
			}
			
		}catch(Exception e){
			
			return Constants.KEYWORD_FAIL + "Could not select from list" + e.getMessage();
			
		}
		
	
		
		
		return Constants.KEYWORD_PASS;	

	}
	
	public  String selectRadio(String object, String data){
		APP_LOGS.debug("Selecting a radio button");
		
		try{
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		}catch(Exception e){
			return Constants.KEYWORD_FAIL + " - Not able to find radio button" + e.getMessage();
		}
		
		  
		/*try{
			String temp[]=object.split(Constants.dataSeparator);
			driver.findElement(By.xpath(OR.getProperty(temp[0])+data+OR.get(data)));
		}catch(Exception e){
			return Constants.KEYWORD_FAIL + " - Not able to find radio button" + e.getMessage();
		}*/
		
		return Constants.KEYWORD_PASS;	

	}
	
	public  String verifyRadioSelected(String object, String data){
		APP_LOGS.debug("Verify Radio Selected");
		
		try {
		String checked = driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("checked");
		if (checked ==null)
        	return Constants.KEYWORD_FAIL + " Radio button not selected";
		}catch (Exception e){
			
			return Constants.KEYWORD_FAIL + " - Not able to find radio button" + e.getMessage();
		}
		/*try {
			String temp[] =object.split(Constants.dataSeparator);
			String checked = driver.findElement(By.xpath(OR.getProperty(temp[0])+data+OR.getProperty(temp[1]))).getAttribute("checked");
            if (checked ==null)
            	return Constants.KEYWORD_FAIL + " Radio button not selected";
			
		}catch (Exception e){
			
			return Constants.KEYWORD_FAIL + " - Not able to find radio button" + e.getMessage();
		}*/
		
		
		return Constants.KEYWORD_PASS;	

	}
	
	
	public  String checkCheckBox(String object, String data){
		APP_LOGS.debug("checking checkbox");
		try{
			//true or null
			String checked =driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("checked");
			if (checked==null) //if it is null, check box is unchecked
				driver.findElement(By.xpath(OR.getProperty(object))).click();
				
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+ "Could not find checkbox" + e.getMessage();
		}
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public  String uncheckCheckBox(String object, String data){
		APP_LOGS.debug("checking checkbox");
		try{
			//true or null
			String checked =driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("checked");
			if (checked!=null) //if it is null, check box is unchecked
				driver.findElement(By.xpath(OR.getProperty(object))).click();
				
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+ "Could not find checkbox" + e.getMessage();
		}
		
		return Constants.KEYWORD_PASS;
		
	}
	
	
	
	
	public  String verifyCheckBoxChecked(String object, String data){
		APP_LOGS.debug("Verifying checkbox selected");
		
		try{
			//true or null
			String checked =driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("checked");
			if (checked!=null) //if it is null, check box is unchecked
				return Constants.KEYWORD_PASS + "check box is checked";
			else 
				return Constants.KEYWORD_FAIL + "check box is not selected";
						
				
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+ "Could not find checkbox" + e.getMessage();
		}
		
		
	}
	
	
	public  String verifyText(String object, String data){
		APP_LOGS.debug("Verifying the text");
		/*System.out.print(OR.getProperty(object));
		System.out.print(OR.getProperty(data));
		
		String Expected =driver.findElement(By.xpath(DriverScript.data)).getText();
		String Actual= driver.findElement(By.xpath(DriverScript.object)).getText();
		System.out.print(Expected + "Expected value");
		System.out.print(Actual +"Actual value");*/
		
		/*try{
			String Actual=driver.findElement(By.xpath(DriverScript.object)).getText();
			String Expected= driver.findElement(By.xpath(DriverScript.data)).getText();
			if (Actual.equals(Expected))
			return	Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL+ "--- Not able to Verify Button Text"+ "Actual value is" + Actual +"Expected value is " + Expected ;
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+ "--- Exeption occured. Not able to Verify Button Text";
		}
		*/

		try{
			//String actual=driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("value");
			String actual=driver.findElement(By.xpath(DriverScript.OR.getProperty(object))).getText();
			//String actual = driver.findElement(By.name("id_loginForm:loginButton")).getAttribute("value");
			//String expected=DriverScript.OR.getProperty(data);
			String expected=OR.getProperty(data);
			System.out.print(actual + "This is actual text \n");
			System.out.print(expected + " this is OR.getProperty(data) value \n"); 
			if (actual.equals(expected))
			return	Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL+ "--- Not able to Verify Text" + "Actual value is" + actual + "Expected value is " + expected ;
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+ "--- Exception occured. Not able to Verify Button Text";
		}
			
		
		//return Constants.KEYWORD_PASS;
		
	}
	
	public  String verifyTextbyValue(String object, String data){
		APP_LOGS.debug("Verifying the text");
	
		try{
			String actual=driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("value");
			String expected=OR.getProperty(data);
			System.out.print(actual + "This is actual text \n");
			System.out.print(expected + " this is OR.getProperty(data) value \n"); 
			if (actual.equals(expected))
			return	Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL+ "--- Not able to Verify Text" + "Actual value is" + actual + "Expected value is " + expected ;
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+ "--- Exception occured. Not able to Verify Button Text";
		}
			
		
		//return Constants.KEYWORD_PASS;
		
	}
	
	
	
	
	
	
	
	
	
	
	public  String writeInInput(String object, String data){
		APP_LOGS.debug("Writing in text box");
		try{
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
			
		}catch (Exception e){
			return Constants.KEYWORD_FAIL+" Unable to write";
		}
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public  String verifyTextinInput(String object, String data){
       APP_LOGS.debug("Verifying the text in input box");
		
       try{
			String Actual = driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("value");
			String Expected = data;
			
			if (Actual.equals(Expected)){
				return Constants.KEYWORD_PASS;
			}else {
				return Constants.KEYWORD_FAIL+" Text does not match";
			}
       
		}catch (Exception e){
			return Constants.KEYWORD_FAIL+" Unable to find element" + e.getMessage();
		}
       
	}
	
	public  String clickImage(String object, String data){
	       APP_LOGS.debug("Clicking the image");
			
			return Constants.KEYWORD_PASS;
	}
	
	public  String verifyFileName(String object, String data){
	       APP_LOGS.debug("Verifying inage filename");
			
			return Constants.KEYWORD_PASS;
	}
	
	public  String pause(String object, String data) throws Exception{
	       	
	       long time =(long)Double.parseDouble(object);
	       Thread.sleep(time*1000L);
	       APP_LOGS.debug("Paused for" + time + "secs");
			return Constants.KEYWORD_PASS;
	}
	
	
	public  String store(String object, String data){
	       APP_LOGS.debug("Storing value");
			
			return Constants.KEYWORD_PASS;
	}
	
	public  String verifyTitle(String object, String data){
	       APP_LOGS.debug("Verifying title");
			
	       // Title can be retrived from two places, either from OR.properties or from the testcase xl file in Data col
	       // if Data is with the word "col", we need to read it from xl file. if not, read it from OR.properties.
	       
	       
			return Constants.KEYWORD_PASS;
	}
	
	public  String exist(String object, String data){
	       APP_LOGS.debug("Checking existance of element");
			try{
				driver.findElement(By.xpath(OR.getProperty(object)));
			}catch(Exception e){
				return Constants.KEYWORD_FAIL + "object does not exist" + e.getMessage();
			}
	       
			return Constants.KEYWORD_PASS;
	}
	
	public  String click(String object, String data){
	       APP_LOGS.debug("Clicking on any element");
	       try {
	    	   driver.findElement(By.xpath(OR.getProperty(object))).click();
	       }catch(Exception e){
	    	   return Constants.KEYWORD_FAIL + "Unalbe to click on element" + e.getMessage();
	    	   
	       }
	       
	       
			return Constants.KEYWORD_PASS;
	}
	
	public  String clickbyID(String object, String data){
	       APP_LOGS.debug("Clicking on any element");
	       try {
	    	   driver.findElement(By.id(OR.getProperty(object))).click();
	       }catch(Exception e){
	    	   return Constants.KEYWORD_FAIL + "Unalbe to click on element" + e.getMessage();
	    	   
	       }
	       
	       
			return Constants.KEYWORD_PASS;
	}
	
	
	public  String clickbyName(String object, String data){
	       APP_LOGS.debug("Clicking on any element");
	       try {
	    	   driver.findElement(By.name(OR.getProperty(object))).click();
	       }catch(Exception e){
	    	   return Constants.KEYWORD_FAIL + "Unalbe to click on element" + e.getMessage();
	    	   
	       }
	       
	       
			return Constants.KEYWORD_PASS;
	}
	
	
	
	
	
	public  String synchronize(String object, String data){
		APP_LOGS.debug("Waiting for page to load");
		((JavascriptExecutor) driver).executeAsyncScript(
				"Function pageLoadingtime()" +
		      "{"+
		      "return ' page has loaded' " +
		      "}"+
		      "return (window.onload=pageLoadingtime());");
			
		return Constants.KEYWORD_PASS;
	}
	
	public  String waitForElementVisibility(final String object, String data){
		APP_LOGS.debug("Waiting for an elelement to be visible");
	
		//WebElement element = fluentWait(By.xpath(OR.getProperty(object)));
		return Constants.KEYWORD_PASS;
	}
	
	public  String closeBrowser(String object, String data){
		APP_LOGS.debug("Closing the browser");
		try {
			driver.quit();
			
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+ "Unable to close browser" + e.getMessage();
		}
		
		return Constants.KEYWORD_PASS;

	}
	
	
	/************************APPLICATION SPECIFIC KEYWORDS********************************/
	
	// This is not a keyword
	
	public void captureScreenshot (String filename, String Keyword_Execution_Result) throws IOException {
		// take screenshots
		if (CONFIG.getProperty("screenshot_everystep").equalsIgnoreCase("Y")){
			
			//capture screenshot
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File (System.getProperty("user.dir")+"//screenshots//" +filename+".jpg"));	
		
		}else if (Keyword_Execution_Result.startsWith(Constants.KEYWORD_FAIL) && CONFIG.getProperty("screenshot_error").equalsIgnoreCase("Y")) {
			//capture screenshot
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile, new File (System.getProperty("user.dir")+"//screenshots//" +filename+".jpg"));	
		}
	}
	
	
	
	public  String verifySearchResults(String object, String data){
        APP_LOGS.debug("Verifying the Search results.");
        
       /* for this method,we have two different implementation we can use one is as below.
        we need xpath of search results in such a manner, that we can verify multiple search
        results.To do this, we first check the xpaths of results. In most cases, it has a fixed pattern.
        Patter here is increasing index of div or index of some other attributes.
        We get the xpath and split it in to start and end as below:
        Full x paths of results may be some thing like(amazon.com search for iphone),
        //*[@id='result_0']/h3/a/span, //*[@id='result_1']/h3/a/span,//*[@id='result_2']/h3/a/span,//*[@id='result_3']/h3/a/span
        In OR.properties, write the key/values as
        search_result_start=//*[@id='result_
        search_result_end=']/h3/a/span
        Also, in the get the search text from either col in sheet or from OR.properties.
        Note that, the only thing which is changing here, is index of resutls.*/
        
        data=data.toLowerCase();
		try{
			for (int i=2;i<=5;i++){
				String text=driver.findElement(By.xpath(OR.getProperty("search_result_start")+i+OR.getProperty("search_result_end"))).getText();
				if (text.indexOf(data) == -1){
					return Constants.KEYWORD_FAIL + "Got thre text results" + text;
				}
					
			}
			
		}catch(Exception e){
			return Constants.KEYWORD_FAIL + " Search result not found" + e.getMessage();
		}
        
		return Constants.KEYWORD_PASS;
		
	}
		/* This is second implementation, we will choose later on which on the use for our application*/
		
		public  String verifySearchResults2(String object, String data){
	        APP_LOGS.debug("Verifying the Search results.");
	        
	    /*Hers, we will look for only one search result and try to match with input string
	     * Provide the search text from OR.properties (data) with search_result key.
	     *  */    
	        
		data=data.toLowerCase();
		try{
			
				String text=driver.findElement(By.xpath(OR.getProperty("search_result"))).getText();
				if (text.indexOf(data) == -1){
					return Constants.KEYWORD_FAIL + "Got thre text results" + text;
				}
			
		}catch(Exception e){
			return Constants.KEYWORD_FAIL + " Search result not found" + e.getMessage();
		}
        
		return Constants.KEYWORD_PASS;
	}
	
	
	public String validateLogin(String object, String data){
		
		//get object of current test xls and name of current test case
        APP_LOGS.debug("Validating Login");
        String Data_correctnes = CurrentTestSuiteXls.getCellData(CurrnetTestCaseName,"Data_correctnes", CurrentTestDataSetID);
        while(driver.findElements(By.xpath(OR.getProperty("image_processing"))).size()!=0){
        	try{
        	String Visibility =	driver.findElement(By.xpath(OR.getProperty("image_processing"))).getAttribute("style");
        		APP_LOGS.debug("System Processing request -- " + "Visibility is -- " + Visibility);
        		
        		if (Visibility.indexOf("hidden")!= -1){
        			
        			//error msg is present and processing image is not present
        			String ActualErrMsg =driver.findElement(By.xpath(OR.getProperty("error_login"))).getText();
        			//String ExpectedErrMsg= driver.findElement(By.xpath(OR.getProperty("error_login"))).getText();
        			APP_LOGS.debug("Login failed ---- "+ActualErrMsg);
        			
        			if (Data_correctnes.equals("Y"))
        				return Constants.KEYWORD_FAIL;
        				else 
        				return Constants.KEYWORD_PASS;
        			
        			
        		}
        	}catch(Exception e){
        		
        	}
        	
        	if (Data_correctnes.equals("N"))
				return Constants.KEYWORD_FAIL;
				else 
				return Constants.KEYWORD_PASS;
        }
     
       ///may be check title of the page or any other validaton    
		return Constants.KEYWORD_PASS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
