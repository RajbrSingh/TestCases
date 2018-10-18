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
		flag = Menu.adminMenu(driver, logger, adminMenu);

		// Test main menu
		flag1 = Menu.mainMenu(driver, logger, mainMenu, baseURL);

		// available content types
		flag2 = Content.types(driver, logger, contentTypes, baseURL);

		// available reports
		flag3 = Menu.availReports(driver, logger, reports, baseURL);

		// create a content and check its been created successfully
		flag4 = Content.create(driver, logger, baseURL);

		// view content with Diff scope
		flag5 = Content.contentView(driver, logger, baseURL, "Scope", contentOutOfScope,"");

		// view content with Diff Role
		flag6 = Content.contentView(driver, logger, baseURL, "Role", contentWithDiffRole, "");

		if (flag && flag1 && flag2 && flag3 && flag4 && flag5 && flag6) {
			logger.info("User role test: PASSED " + "\n");
		} else {
			logger.info("User role test: FAILED " + "\n");
		}

	}

	// check the role

	// create a content

	// edit some content

}
