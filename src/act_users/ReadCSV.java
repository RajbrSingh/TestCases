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
		columnMapping.put("Top3news", "Top3news");
		beanStrategy.setColumnMapping(columnMapping);

		CsvToBean<Users> csvToBean = new CsvToBean<Users>();
		CSVReader reader = new CSVReader(new FileReader("users-top3news.csv"));
		List<Users> emps = csvToBean.parse(beanStrategy, reader);
		int noOfCol = 6;
		String[][] userValues = new String[emps.size()][noOfCol];
		for (int i = 0; i < emps.size(); i++) {
			userValues[i][0] = emps.get(i).getUsername();
			userValues[i][1] = emps.get(i).getPassword();
			userValues[i][2] = emps.get(i).getLoginType();
			userValues[i][3] = emps.get(i).getCountry();
			userValues[i][4] = emps.get(i).getLanguage();
			userValues[i][5] = emps.get(i).getTop3news();
		}
		return userValues;
	}
}