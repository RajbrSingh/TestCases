package act_users;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class ReadCSV {

	public String[][] readCSV() throws IOException {

		HashMap<String, String> usermap = new HashMap<String, String>();

		HeaderColumnNameTranslateMappingStrategy<Users> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<Users>();
		beanStrategy.setType(Users.class);

		Map<String, String> columnMapping = new HashMap<String, String>();
		columnMapping.put("Username", "Username");
		columnMapping.put("Password", "Password");
		columnMapping.put("LoginType", "LoginType");
		columnMapping.put("Country", "Country");
		columnMapping.put("Language", "Language");
		columnMapping.put("Role", "Role");
		columnMapping.put("ContentTypes", "ContentTypes");
		columnMapping.put("Reports", "Reports");
		columnMapping.put("Top3news", "Top3news");
		columnMapping.put("UserLanguage", "UserLanguage");
		columnMapping.put("LanguageTestPage", "LanguageTestPage");
		columnMapping.put("MainMenu", "MainMenu");
		columnMapping.put("AdminMenu", "AdminMenu");
		columnMapping.put("DefaultBU", "DefaultBU");
		columnMapping.put("BUNewsWithDefault", "BUNewsWithDefault");
		columnMapping.put("ChangeBU", "ChangeBU");
		columnMapping.put("ExpectedBUNews", "ExpectedBUNews");
		columnMapping.put("ContentOutOfScope", "ContentOutOfScope");
		columnMapping.put("ContentWithDiffRole", "ContentWithDiffRole");
		beanStrategy.setColumnMapping(columnMapping);

		CsvToBean<Users> csvToBean = new CsvToBean<Users>();
		CSVReader reader = new CSVReader(new FileReader("users-test.csv"));
		List<Users> emps = csvToBean.parse(beanStrategy, reader);
		int noOfCol = 19;
		String[][] userValues = new String[emps.size()][noOfCol];
		for (int i = 0; i < emps.size(); i++) {
			userValues[i][0] = emps.get(i).getUsername();
			userValues[i][1] = emps.get(i).getPassword();
			userValues[i][2] = emps.get(i).getLoginType();
			userValues[i][3] = emps.get(i).getCountry();
			userValues[i][4] = emps.get(i).getLanguage();
			userValues[i][5] = emps.get(i).getRole();
			userValues[i][6] = emps.get(i).getContentTypes();
			userValues[i][7] = emps.get(i).getReports();
			userValues[i][8] = emps.get(i).getTop3news();
			userValues[i][9] = emps.get(i).getUserLanguage();
			userValues[i][10] = emps.get(i).getLanguageTestPage();
			userValues[i][11] = emps.get(i).getMainMenu();
			userValues[i][12] = emps.get(i).getAdminMenu();
			userValues[i][13] = emps.get(i).getDefaultBU();
			userValues[i][14] = emps.get(i).getBUNewsWithDefault();
			userValues[i][15] = emps.get(i).getChangeBU();
			userValues[i][16] = emps.get(i).getExpectedBUNews();
			userValues[i][17] = emps.get(i).getContentOutOfScope();
			userValues[i][18] = emps.get(i).getContentWithDiffRole();
		}
		return userValues;
	}
}