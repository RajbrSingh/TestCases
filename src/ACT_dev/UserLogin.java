package ACT_dev;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserLogin {

	// function called from Master class with all the user parameters
	public void userLogin(WebDriver driver, Logger logger, String username, String password, String loginType,
			String country, String language, String baseURL) {

		String url = baseURL;

		// select login screen url as per the user location
		if (country.equalsIgnoreCase("EU")) {
			if (loginType.equalsIgnoreCase("Drupal")) {
				logger.info("-----Login type: Drupal-----");
				url = baseURL + "/user/login?visitor=EU&destination=/en/node/86";
				drupalLogin(logger, driver, username, password, url);
			}
		} else if (country.equalsIgnoreCase("CA")) {
			if (loginType.equalsIgnoreCase("Okta")) {
				logger.info("-----Login type: Okta-----");
				url = baseURL + "/login-na-select.html";
				oktaLogin_CA(logger, driver, username, password, url, language);
			}
		} else if (country.equalsIgnoreCase("NA")) {
			if (loginType.equalsIgnoreCase("Okta")) {
				logger.info("-----Login type: Okta-----");
				url = baseURL + "/login-na.html";
			}
			oktaLogin_NA(logger, driver, username, password, url);
		}

	}

	// Drupal login - EU
	public void drupalLogin(Logger logger, WebDriver driver, String username, String password, String url) {
		driver.get(url);
		driver.findElement(By.cssSelector("input#edit-name")).sendKeys(username);
		driver.findElement(By.cssSelector("input#edit-pass")).sendKeys(password);
		driver.findElement(By.cssSelector("input#edit-submit")).click();
		try {
			assertEquals(driver.findElement(By.cssSelector("div.messages--error div h2")).getText().toString(),
					"Error message");
			logger.warning("****Error from Drupal on Login:  "
					+ driver.findElement(By.cssSelector("div.messages--error div")).getText()
					+ " Error from the following username:" + username + "****");

		} catch (Exception e) {
			logger.info("User passed : " + username + "\n");
		}
	}

	// Okta login CA
	public void oktaLogin_CA(Logger logger, WebDriver driver, String username, String password, String url,
			String language) {
		driver.get(url);
		driver.findElement(By.cssSelector("button#button-" + language.toLowerCase())).click();
		driver.findElement(By.cssSelector("button#okta-button")).click();
		driver.findElement(By.cssSelector("input#okta-signin-username")).sendKeys(username);
		driver.findElement(By.cssSelector("input#okta-signin-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#okta-signin-submit")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		boolean status = true;

		try {
			if (driver.findElement(By.cssSelector("div.okta-form-infobox-error p")).getText().toString()
					.equalsIgnoreCase("Sign in failed!")) {
				assertEquals(driver.findElement(By.cssSelector("div.okta-form-infobox-error p")).getText().toString(),
						"Sign in failed!");
				logger.warning("****Error From Okta on Login: "
						+ driver.findElement(By.cssSelector("div.okta-form-infobox-error p")).getText()
						+ " Error from the following username:" + username + "****");
				status = false;
			}
		} catch (Exception e) {
			logger.info("User passed Okta : " + username + "\n");
		}
		if (status) {
			try {
				if (driver.findElement(By.cssSelector("div#errorMessage p")).getText().toString().equalsIgnoreCase(
						"There is a problem with your Inner Circle user account. Contact the help desk for your area for assistance.")) {
					assertEquals(driver.findElement(By.cssSelector("div#errorMessage p")).getText().toString(),
							"There is a problem with your Inner Circle user account. Contact the help desk for your area for assistance.");
					logger.warning("****Error From Drupal on Login: "
							+ driver.findElement(By.cssSelector("div#errorMessage p")).getText()
							+ " Error from the following username:" + username + "****");
				}
			} catch (Exception e) {
				logger.info("User passed Drupal: " + username + "\n");
			}
		}

	}

	// Okta login NA
	public void oktaLogin_NA(Logger logger, WebDriver driver, String username, String password, String url) {
		driver.get(url);
		// driver.findElement(By.cssSelector("button#button-en")).click();
		driver.findElement(By.cssSelector("button#okta-button")).click();
		driver.findElement(By.cssSelector("input#okta-signin-username")).sendKeys(username);
		driver.findElement(By.cssSelector("input#okta-signin-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#okta-signin-submit")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		boolean status = true;

		try {
			if (driver.findElement(By.cssSelector("div.okta-form-infobox-error p")).getText().toString()
					.equalsIgnoreCase("Sign in failed!")) {
				assertEquals(driver.findElement(By.cssSelector("div.okta-form-infobox-error p")).getText().toString(),
						"Sign in failed!");
				logger.warning("\n****Error From Okta on Login: "
						+ driver.findElement(By.cssSelector("div.okta-form-infobox-error p")).getText()
						+ " Error from the following username:" + username + "****");
				status = false;
			}
		} catch (Exception e) {
			logger.info("User passed Okta : " + username + "\n");
		}
		if (status) {
			try {
				if (driver.findElement(By.cssSelector("div#errorMessage p")).getText().toString().equalsIgnoreCase(
						"There is a problem with your Inner Circle user account. Contact the help desk for your area for assistance.")) {
					assertEquals(driver.findElement(By.cssSelector("div#errorMessage p")).getText().toString(),
							"There is a problem with your Inner Circle user account. Contact the help desk for your area for assistance.");
					logger.warning("\n****Error From Drupal on Login: "
							+ driver.findElement(By.cssSelector("div#errorMessage p")).getText()
							+ " Error from the following username:" + username + "****");
				}
			} catch (Exception e) {
				logger.info("User passed Drupal: " + username + "\n");
			}
		}
	}

}