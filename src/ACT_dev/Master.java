package ACT_dev;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class Master {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//
		//Creating new file for Logs 
		//
        FileHandler handler = new FileHandler("logs.log");
        SimpleFormatter formatterTxt = new SimpleFormatter();
        handler.setFormatter(formatterTxt);
        Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        logger.addHandler(handler);
        logger.info("Begining Test Script");
        
        //
		// Initilizing Chromedriver
		//
        System.setProperty("webdriver.chrome.driver", "chromedriver");
		WebDriver driver = new ChromeDriver();
        try {
	    //
	    // Creating Variables for Username and Password then retriving the values from the CSV
	    //
		String username="";
		String password="";	
		String loginType="";
		String country="";
		String language="";
		String env="stg";
		String baseURL="";
		
        if(env.equals("dev")) {
        	baseURL="https://innercircleglobal-dev.circlek.com";
		}else if(env.equals("stg")) {
			baseURL="https://innercircleglobal-stg.circlek.com";
		}else if(env.equals("prod")) {
			baseURL="https://innercircleglobal.circlek.com";
		}
		
		ReadCSV readCSV=new ReadCSV();
		String[][] userValues=readCSV.readCSV();
		UserLogin userLogin=new UserLogin();
		
		for (int i = 0; i<userValues.length; i++){
						username=userValues[i][0];
						password=userValues[i][1];
						loginType=userValues[i][2];
						country=userValues[i][3];
						language=userValues[i][4];
						logger.info("-----------------------------------");
						userLogin.userLogin(driver, logger, username, password,loginType,country,language,baseURL);
						try {
						driver.findElement(By.cssSelector("li.userlogout")).click();
						}catch(Exception e){
							logger.warning("**Could not find logout button**");
						}
		}
		}finally {
			driver.quit();
		}
	}

}
