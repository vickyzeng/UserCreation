package net.wgen.selenium;

import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import net.wgen.selenium.UserCreation;

@RunWith(Parameterized.class)

public class CreateSection {
	private String name, code, coursename;
	private String URL;
	private WebDriver driver = UserCreation.driver;
	private String startdate, sectionname, description, unitname, unitoverview, imagelink, school;
	
	public CreateSection (String name, String code, String coursename,
							String startdate, String sectionname,
							String description, String unitname, 
							String unitoverview, String imagelink,
							String school) {
		this.name = name; this.code = code; this.coursename = coursename;
		this.startdate = startdate; this.sectionname = sectionname;
		this.description = description; this.unitname = unitname;
		this.unitoverview = unitoverview; this.imagelink = imagelink;
		this.school = school;
		
		this.URL = UserCreation.baseURL + "/admin/sections/new";
	}
	
	@Parameters
	public static Collection<Object []> data() {
		String[] labels = {"name", "code", "coursename", "startdate", "sectionname", "description",
							"unitname","unitoverview","imagelink","school"};
		Collection<Object[]> data = XMLParser.parse("section", labels);
		return data;
	}
	
	@Test
	public void create() {
	    driver.get(URL);
	    if (driver.getTitle().equals("Wireless Gen Server Login | Wireless Gen Server")) {
	    	driver = ErrorHandler.login(driver);
	    }
	    ActionTools.ImplicitWait(driver, 10);
	    ActionTools.typeinEditbox(driver, "id", "section_name", name);
	    ActionTools.typeinEditbox(driver, "id", "section_code", code);
	    ActionTools.selectValue(driver, "section_course_id", coursename);
	    ActionTools.typeinEditbox(driver, "id", "section_start_date", startdate);
	    ActionTools.typeinEditbox(driver, "id", "section_section_friendly_name", sectionname);
	    ActionTools.typeinEditbox(driver, "id", "section_section_description", description);
	    ActionTools.typeinEditbox(driver, "id", "section_unit_name", unitname);
	    ActionTools.typeinEditbox(driver, "id", "section_unit_overview", unitoverview);
	    ActionTools.typeinEditbox(driver, "id", "section_unit_image_link", imagelink);
	    ActionTools.typeinEditbox(driver, "id", "section_school", school);
	    
//	    driver.findElement(By.id("section_name")).clear();
//	    driver.findElement(By.id("section_name")).sendKeys(name);
//	    driver.findElement(By.id("section_code")).clear();
//	    driver.findElement(By.id("section_code")).sendKeys(code);
//	    new Select(driver.findElement(By.id("section_course_id"))).selectByVisibleText(coursename);
//	    driver.findElement(By.id("section_start_date")).clear();
//	    driver.findElement(By.id("section_start_date")).sendKeys(startdate);
//	    driver.findElement(By.id("section_section_friendly_name")).clear();
//	    driver.findElement(By.id("section_section_friendly_name")).sendKeys(sectionname);
//	    driver.findElement(By.id("section_section_description")).clear();
//	    driver.findElement(By.id("section_section_description")).sendKeys(description);
//	    driver.findElement(By.id("section_unit_name")).clear();
//	    driver.findElement(By.id("section_unit_name")).sendKeys(unitname);
//	    driver.findElement(By.id("section_unit_overview")).clear();
//	    driver.findElement(By.id("section_unit_overview")).sendKeys(unitoverview);
//	    driver.findElement(By.id("section_unit_image_link")).clear();
//	    driver.findElement(By.id("section_unit_image_link")).sendKeys(imagelink);
//	    driver.findElement(By.id("section_school")).clear();
//	    driver.findElement(By.id("section_school")).sendKeys(school);

	    
//	    driver.findElement(By.id("section_submit")).click();
	}
	
}