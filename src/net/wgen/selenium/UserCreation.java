package net.wgen.selenium;


import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Suite.class)
@SuiteClasses({
	CreateCourse.class,
	CreateSection.class,
	CreateLessonlist.class,
	CreateTeacher.class,
	CreateStudent.class
})
public class UserCreation {
	public static WebDriver driver;
	public static String baseURL;
	public static String user;
	public static String password;
	
	@BeforeClass
	public static void setup() {
		String[] labels = {"baseURL", "user", "password"};
		Collection<Object[]> data = XMLParser.parse("serverinfo", labels);
		Object[] item = data.iterator().next();
		baseURL =  (String) item[0];
		user = (String) item[1];
		password = (String) item[2];
		driver = new FirefoxDriver();
	}
	
	@AfterClass
	public static void teardown() {
		driver.quit();
	}
	
	public static void main(String[] args) throws Exception {                    
	       JUnitCore.main("net.wgen.selenium.UserCreation");            
	}
}