package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public Base(WebDriver driver) {
		this.setDriver(driver);
	}

	public WebDriver chromeDriverConnection() {
		// Seteo el driver de chrome para utilizar dicho navegador en los tests
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		this.driver = new ChromeDriver();
		return driver;
	}
	
	public void visit(String url) {
		this.getDriver().get(url);
	}
	
	public WebElement findElement(By locator) {
		return this.getDriver().findElement(locator);
	}
	
	public void clear(WebElement element) {
		element.clear();
	}
	
	public void clear(By locator) {
		this.getDriver().findElement(locator).clear();
	}
	
	public String getText(WebElement element) {
		return element.getText();
	}
	
	public String getText(By locator) {
		return this.getDriver().findElement(locator).getText();
	}
	
	public void type(String inputText, By locator) {
		this.getDriver().findElement(locator).sendKeys(inputText);
	}
	
	public void click(By locator) {
		this.getDriver().findElement(locator).click();
	}
	
	public Boolean isDisplayed(By locator) {
		try {
			return this.getDriver().findElement(locator).isDisplayed();
		} catch(org.openqa.selenium.NoSuchElementException e) {
			// Si el elemento no esta atrapo la excepcion y devuelvo false
			return false; 
		}
	}
	
	public void maximizeWindow() {
		this.getDriver().manage().window().maximize();
	}
	
}
