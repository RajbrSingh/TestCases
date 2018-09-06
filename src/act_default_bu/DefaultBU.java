package act_default_bu;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import act_news.News;

public class DefaultBU {
	
    public static void defaultBU(WebDriver driver, Logger logger, String defaultBU, String BUNewsWithDefault, String changeBU,String expectedBUNews) {
    	driver.findElement(By.cssSelector(".site-logo")).click();
    	boolean status1=false, status2=false, status3=false;
    	
    	status1=checkBU(driver,logger,defaultBU,BUNewsWithDefault,false);
    	String buID=changeBU(driver,logger,changeBU); 
    	String url = driver.getCurrentUrl();
    	logger.info("BU dropdown changed to: "+ changeBU + "\n");
    	if(url.endsWith(buID)) {
    		logger.info("URL has changed BU's ID");
    		status2=true;
    	}else {
    		logger.info("URL doesnot have changed BU's ID");
    	}
    	status3=checkBU(driver,logger,changeBU,expectedBUNews, true);
    	 
    	 if(status1 && status2 && status3) {
			   logger.info("BU news test: PASSED " + "\n");
		   }else {
			   logger.info("BU news test: FAILED " + "\n");
		   }
	}
	public static boolean checkBU(WebDriver driver, Logger logger, String defaultBU, String buNews,boolean changedBU ) {
		
		WebElement ulElement = driver.findElement(By.cssSelector("select.ViewsJumpMenu"));
		List<WebElement> buValues = ulElement.findElements(By.xpath("//*[@selected='selected']"));
		String buValue=buValues.get(0).getText();
		boolean status=false;
		if(!changedBU) {
		if(buValue.trim().equals(defaultBU)) {
			logger.info("BU selected is same as User's default BU " + "\n");
			status=true;
		}else{
			logger.info("BU selected is not same as User's default BU " + "\n");
		}
		}
	
		   status=News.buNews(driver, logger, buNews);
		  
		return status;
	}
	
   public static String changeBU(WebDriver driver, Logger logger, String changeBU) {
	    String buID="";
		WebElement ulElement = driver.findElement(By.cssSelector("select.ViewsJumpMenu"));
		List<WebElement> buValues = ulElement.findElements(By.tagName("option"));
		for (int i = 0; i < buValues.size(); i++) {
			String bu = buValues.get(i).getText();
			if(bu.trim().equals(changeBU)) {
				buID=buValues.get(i).getAttribute("data-url");
				buValues.get(i).click();
			}
		}
		return buID;
	}
}
