package net.wgen.selenium;

import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorHandler {
	
	public static WebDriver login(WebDriver driver) {
	    driver.findElement(By.id("admin_user_email")).clear();
	    driver.findElement(By.id("admin_user_email")).sendKeys(UserCreation.user);
	    driver.findElement(By.id("admin_user_password")).clear();
	    driver.findElement(By.id("admin_user_password")).sendKeys(UserCreation.password);
	    driver.findElement(By.id("admin_user_submit")).click();
		return driver;
	}
	
	public static void checkStatus(WebDriver driver) throws IOException {
		
		/* Validate Input Password */
		if (driver.findElement(By.className("inline-errors")) != null) {
			String id = driver.findElement(By.className("inline-errors")).findElement(By.xpath(".."))
					.getAttribute("id");
			if (id.equals("teacher_password_input")) {
				System.out.println("Invalid Password");
				System.out.println("Please offer a valid password:");
				String value = ActionTools.readFromSystemInput();
				ActionTools.typeinEditbox(driver, "id", "teacher_password", value);
			} else {
				System.out.println("Invalid Input Data: " + id);
			}
		}
		
		/* Validate Create Action Success or not */
		if (driver.findElement(By.cssSelector("BODY")).getText().contains("RecordNotUnique"))
		{
			System.out.println("Error: User already exist on current server");
			driver.navigate().back();
			driver.navigate().refresh();
		}
		else
		{
			System.out.println("Finish Creating");
		}
	}
	
	public static void createPopup() {
	        final JFrame parent = new JFrame();
	        JButton button = new JButton();

	         button.setText("Click me to show dialog!");
	         parent.add(button);
	         parent.pack();
	         parent.setVisible(true);

	         button.addActionListener(new java.awt.event.ActionListener() {

	         @Override
	         public void actionPerformed(java.awt.event.ActionEvent evt) {

	         String name = JOptionPane.showInputDialog(parent,"What is your name?",null);

	         }

	         });
	}
	
}