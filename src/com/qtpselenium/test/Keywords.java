package com.qtpselenium.test;

import static com.qtpselenium.test.DriverScript.APP_LOGS;
import static com.qtpselenium.test.DriverScript.CurrentTestCaseID;
import static com.qtpselenium.test.DriverScript.CurrentTestSuiteXls;
import static com.qtpselenium.test.DriverScript.CurrentTestCaseID;
import static com.qtpselenium.test.DriverScript.CurrnetTestCaseName;
import static com.qtpselenium.test.DriverScript.CurrentTestDataSetID;
import static com.qtpselenium.test.DriverScript.CONFIG;
import static com.qtpselenium.test.DriverScript.OR;

import java.util.List;
import java.util.Random;
//import static com.qtpselenium.test.DriverScript.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.qtpselenium.xls.read.Xls_Reader;
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
		return Constants.KEYWORD_PASS;
	}
	
		
		
	public  String navigate(String object, String data){	
		APP_LOGS.debug("Navigating to URL");
		try{
		driver.navigate().to(CONFIG.getProperty(data));
		}catch(Exception e){		
		return Constants.KEYWORD_FAIL + " -- Can not navigate ";
	}
		return Constants.KEYWORD_PASS;
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
	
	@SuppressWarnings("unchecked")
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
	
	public  String verifyListSelection(){
		APP_LOGS.debug("Verifying the selection of the list");
		
		return Constants.KEYWORD_PASS;	
	}
	
	public  String verifyAllListElements(){
		APP_LOGS.debug("Verifying all the list elements");
		
		return Constants.KEYWORD_PASS;	

	}
	
	public  String selectRadio(){
		APP_LOGS.debug("Selecting a radio button");
		
		return Constants.KEYWORD_PASS;	

	}
	
	public  String verifyRadioSelected(){
		APP_LOGS.debug("Verify Radio Selected");
		
		return Constants.KEYWORD_PASS;	

	}
	
	public  String verifyCheckBoxSelected(){
		APP_LOGS.debug("Verifying checkbox selected");
		
		return Constants.KEYWORD_PASS;
		
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
			String Actual1=driver.findElement(By.xpath(DriverScript.object)).getText();
			String Expected1= driver.findElement(By.xpath(DriverScript.data)).getText();
			if (Actual1.equals(Expected1))
			return	Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL+ "--- Not able to Verify Button Text"+ "Actual value is" + Actual +"Expected value is " + Expected ;
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+ "--- Exeption occured. Not able to Verify Button Text";
		}
		*/

		try{
			String Actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
			String Expected=DriverScript.OR.getProperty(data);
			System.out.print(Expected + " this is OR.getProperty(data) value \n"); 
			if (Actual.equals(Expected))
			return	Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL+ "--- Not able to Verify Button Text"+ "Actual value is" + Actual +"Expected value is " + Expected ;
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
	
	public  String clickImage(){
	       APP_LOGS.debug("Clicking the image");
			
			return Constants.KEYWORD_PASS;
	}
	
	public  String verifyFileName(){
	       APP_LOGS.debug("Verifying inage filename");
			
			return Constants.KEYWORD_PASS;
	}
	
	public  String pause(){
	       APP_LOGS.debug("Waiting");
			
			return Constants.KEYWORD_PASS;
	}
	
	
	public  String store(){
	       APP_LOGS.debug("Storing value");
			
			return Constants.KEYWORD_PASS;
	}
	
	public  String verifyTitle(){
	       APP_LOGS.debug("Verifying title");
			
			return Constants.KEYWORD_PASS;
	}
	
	public  String exist(){
	       APP_LOGS.debug("Checking existance of element");
			
			return Constants.KEYWORD_PASS;
	}
	
	public  String click(){
	       APP_LOGS.debug("Clicking on any element");
			
			return Constants.KEYWORD_PASS;
	}
	
	public  String synchronize(){
		APP_LOGS.debug("Waiting for page to load");
		
		return Constants.KEYWORD_PASS;
	}
	
	public  String waitForElementVisibility(){
		APP_LOGS.debug("Waiting for an elelement to be visible");
		
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
	
	public  String verifyLaptops(){
        APP_LOGS.debug("Verifying the laptops in app");
		
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
