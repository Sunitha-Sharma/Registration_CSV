package com.cg.registration;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.opencsv.CSVReader;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import jxl.Sheet;
import jxl.Workbook;

public class RegisterSteps {
	
	
	WebDriver driver;
	static String fName;
	static String lName;
	static String mail;
	static String cNum;
	static String address;
	static String city1;
	static String stateName;

	@Before
	public static void readerFunction() throws Exception {

		CSVReader reader = new CSVReader(new FileReader("D:\\CSVfile.csv"));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			fName = nextLine[0];
			lName = nextLine[1];
			mail = nextLine[2];
			cNum = nextLine[3];
			address = nextLine[4];
			city1 = nextLine[5];
			stateName = nextLine[6];
		}
		reader.close();
	}
	
	@Given("^Registration page is open$")
	public void registration_page_is_open() throws Throwable {
	   
		System.setProperty("webdriver.chrome.driver", "D:\\Users\\sunitsha\\Desktop\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.get("D:\\BDD_Workspace\\Registration\\src\\register.html");
		
		Thread.sleep(2000);
	}

	@Given("^User enters valid details$")
	public void user_enters_valid_details() throws Throwable {
		WebElement fname = driver.findElement(By.id("fname"));
		fname.sendKeys(fName);
		
		WebElement lname = driver.findElement(By.id("lname"));
		lname.sendKeys(lName);
		
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(mail);
		
		WebElement number = driver.findElement(By.id("number"));
		number.sendKeys(cNum);
		
		WebElement addr = driver.findElement(By.id("addr"));
		addr.sendKeys(address);
		
		WebElement city = driver.findElement(By.id("city"));
		city.sendKeys(city1);
		
		WebElement state = driver.findElement(By.xpath("/html/body/form/select"));
		state.sendKeys(stateName);
		
		Thread.sleep(3000);
	 
	}

	@Given("^page is submitted$")
	public void page_is_submitted() throws Throwable {
		WebElement submit = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
		submit.click();
		Thread.sleep(3000);
	}

	@Given("^Alert message for validation is displayed$")
	public void alert_message_for_validation_is_displayed() throws Throwable {
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		assertEquals("Details Validated",alert.getText());
		alert.accept();
		Thread.sleep(3000);
	    
	}

	@Given("^Next Page is redirected to enter project details$")
	public void next_Page_is_redirected_to_enter_project_details() throws Throwable {
		WebElement details = driver.findElement(By.id("details"));
		details.sendKeys("Automation Testing");
		
		WebElement Name = driver.findElement(By.id("ProjectName"));
		Name.sendKeys("Pothole detection testing");
		
		WebElement CName = driver.findElement(By.id("client Name"));
		CName.sendKeys("IOT");
	   
		WebElement size = driver.findElement(By.id("size"));
		size.sendKeys("10");
		Thread.sleep(3000);
	}
	
	@Given("^page is submitted for registeration$")
	public void page_is_submitted_for_registeration() throws Throwable {

		WebElement submit = driver.findElement(By.id("submit"));
		submit.click();
		Thread.sleep(3000);
	}

		
	@Then("^Alert message for succes is displayed$")
	public void alert_message_for_succes_is_displayed() throws Throwable {
	   
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		assertEquals("Registration Successful",alert.getText());
		Thread.sleep(3000);
		alert.accept();
		
	}




}
