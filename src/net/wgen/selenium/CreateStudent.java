package net.wgen.selenium;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import net.wgen.selenium.UserCreation;
import net.wgen.selenium.ErrorHandler;

@RunWith(Parameterized.class)

public class CreateStudent {
	private String name, uid, email, password;
	private String sections;
	private String URL;
	private WebDriver driver = UserCreation.driver;
	
	public CreateStudent (String name, String uid, String email, String password, String sections) {
		this.name = name;
		this.uid = uid;
		this.email = email;
		this.password = password;
		this.sections = sections;
		this.URL = UserCreation.baseURL + "/admin/students/new";
	}
	
	@Parameters
	public static Collection<Object[]> getParameters() {
		String[] labels = {"name", "uid", "email","password","sectionname"};
		Collection<Object[]> data = XMLParser.parse("student", labels);
		System.out.println("DATA size:" + data.size());
		return data;
	}
	
	
	@Test
	public void create() throws IOException {
	    driver.get(URL);
	    if (driver.getTitle().equals("Wireless Gen Server Login | Wireless Gen Server")) {
	    	driver = ErrorHandler.login(driver);
	    }
	    ActionTools.ImplicitWait(driver, 10);
	    ActionTools.typeinEditbox(driver, "id", "student_name", name);
	    ActionTools.typeinEditbox(driver, "id", "student_uid", uid);
	    ActionTools.typeinEditbox(driver, "id", "student_email", email);
	    ActionTools.typeinEditbox(driver, "id", "student_password", password);
//	    driver.findElement(By.id("student_name")).clear();
//	    driver.findElement(By.id("student_name")).sendKeys(name);
//	    driver.findElement(By.id("student_uid")).clear();
//	    driver.findElement(By.id("student_uid")).sendKeys(uid);
//	    driver.findElement(By.id("student_email")).clear();
//	    driver.findElement(By.id("student_email")).sendKeys(email);
//	    driver.findElement(By.id("student_password")).clear();
//	    driver.findElement(By.id("student_password")).sendKeys(password);
	    
	    String[] section = toArray(sections);
	    	    
	    for (int i = 0; i < section.length; i++) {
	    	driver.findElement(By.id("student_section_ids_chzn")).click();
	    	driver.findElement(By.xpath(".//*[@id='student_section_ids_chzn']/ul/li/input")).sendKeys(section[i]);
	    	ActionTools.forcetoWait(1);
	    	driver.findElement(By.className("highlighted")).click();
	    	System.out.println(section[i]);
	    }
	    
	    ErrorHandler.checkStatus(driver);
	}
		
	public static String[] toArray(String src) {
		String[] section = src.split(",");
		return section;
	}
	

	
}