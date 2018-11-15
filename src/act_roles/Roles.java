package act_roles;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import act_content.Content;
import act_menu.Menu;

public class Roles {

	// check the role
	public static void role(WebDriver driver, Logger logger, String baseURL, String role, String contentTypes,
			String reports, String mainMenu, String adminMenu, String contentOutOfScope, String contentWithDiffRole) {
		boolean flag = false, flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false;
		// UserProfile.roles(driver, logger, role);

		// Test Admin menu
		try {
		flag = Menu.adminMenu(driver, logger, adminMenu);
		}catch(Exception e) {
			logger.info("No admin menu");
		}
		// Test main menu
		try {
		flag1 = Menu.mainMenu(driver, logger, mainMenu, baseURL);
	}catch(Exception e) {
		logger.info("No main menu");
	}

		// available content types
		try {
		flag2 = Content.types(driver, logger, contentTypes, baseURL);
		}catch(Exception e) {
			logger.info("No Content types");
		}

		// available reports
		try {
		flag3 = Menu.availReports(driver, logger, reports, baseURL);
	}catch(Exception e) {
		logger.info("No report available");
	}

		// create a content and check its been created successfully
		try {
		flag4 = Content.create(driver, logger, baseURL);
		}catch(Exception e) {
			logger.info("Can't create content");
		}

		// view content with Diff scope
		flag5 = Content.contentView(driver, logger, baseURL, "Scope", contentOutOfScope,"");

		// view content with Diff Role
		flag6 = Content.contentView(driver, logger, baseURL, "Role", contentWithDiffRole, "");

		if (flag && flag1 && flag2 && flag3 && flag4 && flag5 && flag6) {
	//		logger.info("User role test: PASSED " + "\n");
		} else {
	//		logger.info("User role test: FAILED " + "\n");
		}

	}

}
