package net.wgen.selenium;


import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.openqa.selenium.WebDriver;
import net.wgen.selenium.UserCreation;

@RunWith(Parameterized.class)

public class CreateLessonlist {
	private String section;
	private String data;
	private String URL;
	private WebDriver driver = UserCreation.driver;
	
	public CreateLessonlist (String section, String data) {
		this.section = section;
		this.data = data;
		this.URL = UserCreation.baseURL + "/admin/lesson_lists/new";
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		String[] labels = {"sectionname", "data"};
		Collection<Object[]> data = XMLParser.parse("lessonlist", labels);
		return data;
	} 
	
	@Test
	public void create() {
	    driver.get(URL);
	    if (driver.getTitle().equals("Wireless Gen Server Login | Wireless Gen Server")) {
	    	driver = ErrorHandler.login(driver);
	    }
//	    new Select (driver.findElement(By.id("lesson_list_section_id"))).selectByVisibleText(section);
	    ActionTools.selectValue(driver, "lesson_list_section_id", section);
	    ActionTools.typeinEditbox(driver, "id", "lesson_list_data", data);
//	    driver.findElement(By.id("lesson_list_data")).clear();
//	    driver.findElement(By.id("lesson_list_data")).sendKeys(data);
//	    driver.findElement(By.id("lesson_list_submit")).click();
	}
	
	
}