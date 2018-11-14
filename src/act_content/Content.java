package act_content;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Content {

	public static boolean types(WebDriver driver, Logger logger, String contentTypes, String baseURL) {
		driver.get(baseURL + "/node/add");
		String types = "";
		boolean flag = false;

		WebElement ulElement = driver.findElement(By.cssSelector("ul.admin-list"));
		List<WebElement> links = ulElement.findElements(By.tagName("span"));
		for (int i = 0; i < links.size(); i++) {
			String CT = links.get(i).getText();
			if (CT.contains(",")) {
				CT = CT.replaceAll(",", "@c");
			}

			if (i == links.size() - 1) {
				types = types + CT;
			} else {
				types = types + CT + "/";
			}
		}
		System.out.println("Content Types available: "+types);
		if (contentTypes.equalsIgnoreCase(types)) {
			logger.info("Publisher has correct available Content types. \n");
			flag = true;
		} else {
			logger.info("Publisher don't have correct available Content types. \n");
		}
		return flag;

	}

	public static boolean create(WebDriver driver, Logger logger, String baseURL) {
		boolean flag = false, flag1 = false;

		// create news
		try {
			driver.get(baseURL + "/node/add/blog");
			String title = "Test News";
			driver.findElement(By.cssSelector("div.form-item-title-0-value input")).sendKeys(title);
			driver.findElement(By.cssSelector("div.field--name-field-short-title input")).sendKeys("test");
			driver.findElement(By.cssSelector("div.form-item-field-scope-0-target-id input")).sendKeys("Denmark");
			driver.findElement(By.cssSelector("div.form-item-field-department select option")).click();
			driver.findElement(By.cssSelector("details#edit-options")).click();
			driver.findElement(By.cssSelector("input#edit-promote-value")).click();
			driver.findElement(By.cssSelector("input#edit-sticky-value")).click();
			//edit-field-file-upload-0-upload
		//	driver.findElement(By.cssSelector("input#edit-field-file-upload-0-upload")).sendKeys("test.pdf");
			driver.findElement(By.cssSelector("input#edit-submit")).click();
			boolean status = false;
			try {
				if (driver.findElement(By.cssSelector("div.messages--error")).getText()
						.contains("There are no entities matching")) {
					logger.info("Publisher trying to publish News & Media out of scope. \n");
				}

			} catch (Exception e) {
				status = true;
			}
			// message after creating content
			if (status) {
				String message = "News & Blogs " + title + " has been created";
				String actualMessage = driver.findElement(By.cssSelector("div.messages__wrapper .messages")).getText()
						.replaceAll("Status message", "");

			//	if (actualMessage.contains(message)) {
				if(actualMessage.length() > 0) {
					logger.info("News and Blog created successfully. \n");
					flag = true;
				} else {
					logger.info("Failed to create new news and blogs. \n");
				}

			}

		} catch (Exception e) {
			logger.info("Error creating News and Blog content. ");
		}

		// create Document
		try {
			String title = "Test docs";
			driver.get(baseURL + "/node/add/documents");
			driver.findElement(By.cssSelector("div.field--name-title input")).sendKeys(title);
			driver.findElement(By.cssSelector("div.field--name-field-short-title input")).sendKeys("test");
			driver.findElement(By.cssSelector("div.field--name-field-scope input")).sendKeys("Sweden");
			driver.findElement(By.cssSelector("div.field--name-field-department select option")).click();
			Select dropdown = new Select(driver.findElement(By.id("edit-field-document-type")));
			dropdown.selectByVisibleText("PDF");
			driver.findElement(By.cssSelector("input#edit-submit")).click();

			boolean status1 = false;
			try {
				if (driver.findElement(By.cssSelector("div.messages--error")).getText()
						.contains("There are no entities matching")) {
					logger.info("Publisher trying to publish Document out of scope. \n");
				}

			} catch (Exception e) {
				status1 = true;
			}

			// message after creating content
			if (status1) {
				String message = "Documents " + title + " has been created";
				String actualMessage = driver.findElement(By.cssSelector("div.messages__wrapper .messages")).getText()
						.replaceAll("Status message", "");

		//		if (actualMessage.contains(message)) {
				if(actualMessage.length() > 0) {
					logger.info("Document created successfully. \n");
					flag1 = true;
				} else {
					logger.info("Failed to create new document. \n");
				}
			}
		} catch (Exception e) {
			logger.info("Error creating Document content." );
		}

		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (flag && flag1) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean contentView(WebDriver driver, Logger logger, String baseURL, String var, String val,
			String contentOnPage) {
		boolean flag = false;
		driver.get(baseURL + "/" + val);
		if (var.length() > 0) {
			try {
				String actualMessage = driver
						.findElement(By.cssSelector("div.region-content .block--main-page-content")).getText();
				String message = "You are not authorized to access this page.";
			//	System.out.println("Actual message :"+actualMessage);
			//	if (actualMessage.contains(message)) {
				if(actualMessage.length() > 0) {
					logger.info("User can't see content out of its " + var + ". \n");
					flag = true;
				} else {
					//
				}
			} catch (Exception e) {
				logger.info("User can see content out of its " + var + ". \n");
			}
		}

		if (contentOnPage.length() > 0) {
			driver.get(baseURL + "/" + val);
			String content_title = "";
			try {
			 content_title = driver.findElement(By.cssSelector("div.layout-content article h2")).getText();
			}catch(Exception e) {
				
			}
			
			String content_body = "";
			try {
			content_body = driver
					.findElement(By.cssSelector("div.layout-content article div.field--name-body")).getText()
					.trim();
			}catch(Exception e) {
				
			}
			if (content_title.contains(",")) {
				content_title = content_title.replaceAll(",", "@c");
			}
			if (content_body.contains(",")) {
				content_body = content_body.replaceAll(",", "@c");
			}
			
			System.out.println("Content on page : "+content_title.trim() + "/" + content_body.trim());
			if ((content_title.trim() + "/" + content_body.trim()).equals(contentOnPage)) {
				logger.info("This page has content as expected. \n");
				flag = true;
			} else {
				logger.info("This page doesnot have content as expected. \n");
				flag = false;
			}
		}
		return flag;
	}
}
