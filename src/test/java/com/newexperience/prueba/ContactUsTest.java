package com.newexperience.prueba;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactUsTest {
	
	private WebDriver driver;
	By btnSend = By.id("submitMessage");
	By listSubject = By.id("id_contact");
	By email = By.id("email");
	By orderRefence = By.id("id_order");
	By fileUpload = By.id("fileUpload");
	By mensaje = By.id("message");
	
	@Before
	public void setUp() {
		 System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("http://automationpractice.com/index.php?controller=contact");
	}
	
	@Test
	public void testCU001() throws InterruptedException {
		
		driver.findElement(this.btnSend).click();
		Thread.sleep(2000);
		
		String p = driver.findElement(By.cssSelector("div.alert-danger p")).getText();
		
		assertEquals("There is 1 error", p);
	}
	
	@Test
	public void testCU002() throws InterruptedException {
		driver.findElement(orderRefence).sendKeys("PRUEBA001");
		driver.findElement(fileUpload).sendKeys(System.getProperty("user.dir")+"\\test.jpg");
		driver.findElement(this.btnSend).click();
		Thread.sleep(2000);
		String p = driver.findElement(By.cssSelector("div.alert-danger p")).getText();
		assertEquals("There is 1 error", p);
	}
	
	
	@Test
	public void testCU003() throws InterruptedException {
		Select dropdown = new Select(driver.findElement(listSubject));
		dropdown.selectByIndex(1);
		
		driver.findElement(email).sendKeys("test@gmail.com");
		driver.findElement(mensaje).sendKeys("Hello World");
		driver.findElement(this.btnSend).click();
		Thread.sleep(2000);
		String p = driver.findElement(By.cssSelector("p.alert-success")).getText();
		assertEquals("Your message has been successfully sent to our team.", p);
	}
	
	
	@Test
	public void testCU004() throws InterruptedException {
		Select dropdown = new Select(driver.findElement(listSubject));
		dropdown.selectByIndex(1);
		
		driver.findElement(email).sendKeys("test@gmail.com");
		driver.findElement(orderRefence).sendKeys("PRUEBA-01");
		driver.findElement(fileUpload).sendKeys(System.getProperty("user.dir")+"\\test.jpg");
		driver.findElement(mensaje).sendKeys("Hello World");
		driver.findElement(this.btnSend).click();
		Thread.sleep(2000);
		String p = driver.findElement(By.cssSelector("p.alert-success")).getText();
		assertEquals("Your message has been successfully sent to our team.", p);
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}
