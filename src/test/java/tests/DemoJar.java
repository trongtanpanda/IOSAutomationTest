package tests;

import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;

public class DemoJar {

	public static void main(String[] args) {
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add(System.getProperty("user.dir") + System.getProperty("file.separator") + "resources"
				+ System.getProperty("file.separator") + "suites" + System.getProperty("file.separator")
				+ "test-jar.xml");
		testng.setTestSuites(suites);
		testng.run();
	}

}
