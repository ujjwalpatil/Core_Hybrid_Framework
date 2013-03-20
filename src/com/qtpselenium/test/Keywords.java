package com.qtpselenium.test;

import static com.qtpselenium.test.DriverScript.APP_LOGS;
import static com.qtpselenium.test.DriverScript.CONFIG;
import static com.qtpselenium.test.DriverScript.OR;
//import static com.qtpselenium.test.DriverScript.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
public class Keywords {
	
	public WebDriver driver;
	
	//WebDriver driver=null;
	
	public  String openBrowser(String object, String data){	
		
		APP_LOGS.debug("Opening browser");
		APP_LOGS.debug(object);
		APP_LOGS.debug(data);
		System.out.print(object);
		System.out.print(data);
		
		if (CONFIG.getProperty(data).equals("Mozilla"))
			driver =new FirefoxDriver();
			else if (CONFIG.getProperty(data).equals("IE"))
			driver =new InternetExplorerDriver();
			else if (CONFIG.getProperty(data).equals("Chrome"))
			driver =new ChromeDriver();
		
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
	
	public  String verifyButtonText(){
		APP_LOGS.debug("Verifying the button text");
		
		return Constants.KEYWORD_PASS;	
	}
	
	public  String selectList(){
		APP_LOGS.debug("Selecting from list");
		
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
	
	
	public  String verifyText(){
		APP_LOGS.debug("Verifying the text");
		
		return Constants.KEYWORD_PASS;
		
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
	
	public  String closeBrowser(){
		APP_LOGS.debug("Closing the browser");
		
		return Constants.KEYWORD_PASS;

	}
	
	
	/************************APPLICATION SPECIFIC KEYWORDS********************************/
	
	public  String verifyLaptops(){
        APP_LOGS.debug("Verifying the laptops in app");
		
		return Constants.KEYWORD_PASS;
	}
	
	public String validateLogin(){
        APP_LOGS.debug("Validating Login");
		
		return Constants.KEYWORD_PASS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
