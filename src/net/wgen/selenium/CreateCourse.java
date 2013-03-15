package net.wgen.selenium;




import java.util.Collection;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.openqa.selenium.WebDriver;


import net.wgen.selenium.UserCreation;

@RunWith(Parameterized.class)

public class CreateCourse {
	private String name;
	private String code;
	private String URL;
	private WebDriver driver = UserCreation.driver;
	
	public CreateCourse (String name, String code) {
		this.name = name;
		this.code = code;
		this.URL = UserCreation.baseURL + "/admin/courses/new";
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		String[] labels = {"name", "code"};
		Collection<Object[]> data = XMLParser.parse("course", labels);
		return data;
	} 
	
	@Test
	public void create() {
	    driver.get(URL);
	    if (driver.getTitle().equals("Wireless Gen Server Login | Wireless Gen Server")) {
	    	driver = ErrorHandler.login(driver);
	    }
	    ActionTools.ImplicitWait(driver, 10);
	    ActionTools.typeinEditbox(driver, "id", "course_name", name);
	    ActionTools.typeinEditbox(driver, "id", "course_code", code);
	    
//	    driver.findElement(By.id("course_submit")).click();
	}
	
	
}