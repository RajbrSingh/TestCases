package act_logout;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import act_users.ReadCSV;
import act_users.UserLogin;

public class Master_Logout {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//
		//Creating new file for Logs 
		//
        FileHandler handler = new FileHandler("Automated-tests.log");
        SimpleFormatter formatterTxt = new SimpleFormatter();
        handler.setFormatter(formatterTxt);
        Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        logger.addHandler(handler);
        logger.info("Begining Test Script\n");
        
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
		String env="dev";
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
		boolean loginStatus=false;
		
		for (int i = 0; i<userValues.length; i++){
						username=userValues[i][0];
						password=userValues[i][1];
						loginType=userValues[i][2];
						country=userValues[i][3];
						language=userValues[i][4];
						logger.info("-----------------------------------\n");
						loginStatus=userLogin.userLogin(driver, logger, username, password,loginType,country,language,baseURL);
						try {
							//logout 
							if(loginStatus) {
						driver.findElement(By.cssSelector("li.userlogout")).click();
							}
				            //check if user is logged out of Drupal and/or Okta 
						String titleText="";
						if(loginType.equalsIgnoreCase("Drupal")) {
							try {
								titleText=driver.findElement(By.cssSelector("div#middle_part h2")).getText().toString();	
							}
							catch(Exception e) {
							}
							try {
								titleText=driver.findElement(By.cssSelector("div#na-login-card h2")).getText().toString();
							}catch(Exception e) {
								
							}
							
							if(titleText.equalsIgnoreCase("Global Intranet") || titleText.equalsIgnoreCase("Intranet global")) {
								logger.info("Drupal logout passed: " + username + "\n");
							}else {
								logger.info("Drupal logout failed: " + username + "\n");
							}		
						}
						else if(loginType.equalsIgnoreCase("Okta")) {
							try {
								titleText=driver.findElement(By.cssSelector("div#middle_part h2")).getText().toString();	
							}
							catch(Exception e) {
							}
							try { 
								titleText=driver.findElement(By.cssSelector("div#na-login-card h2")).getText().toString();
							}catch(Exception e) {
								
							}
						
							if(titleText.equalsIgnoreCase("Global Intranet") || titleText.equalsIgnoreCase("Intranet global")) {
								logger.info("Drupal logout passed: " + username + "\n");
								// implies user has logged out of Drupal
								//then check for Okta logout
								if (country.equalsIgnoreCase("CA") || country.equalsIgnoreCase("NA")) {
									String url ="";
                                        if(language.toLowerCase().equals("en")) {
                                        	 url = baseURL + "/login-na.php";	
                                        }else {
                                        	 url = baseURL + "/fr-login-na.php";	
                                        }
										driver.get(url);
										driver.findElement(By.cssSelector("button#okta-button")).click();
										try {
					                    assertEquals(driver.findElement(By.cssSelector("input#okta-signin-username")).getText().toString(),"");
					                    logger.info("Okta logout passed: " + username + "\n");
										}catch(Exception e) {
											logger.info("Okta logout failed: " + username + "\n");
										}
								}		
							}else {
								logger.info("Drupal logout failed: " + username + "\n");
							}
							
						}
					}catch(Exception e){
							logger.warning("\n**Could not find logout button**\n\n");
						}
		}
		}finally {
			driver.quit();
		}
	}

}
