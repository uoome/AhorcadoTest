package demo.Ahorcado_Cucumber_Selenium;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SeleniumDemoUI {

	private WebDriver driver;
	
	@Before
	public void chromeDriverConnection() {
		// Seteo el driver de chrome para utilizar dicho navegador en los tests
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}	
	
	@Given("^I am on the Google search page$")
	public void I_visit_google() {
		driver.get("https:\\www.google.com");
	}

	@When("^I search for \"(.*)\"$")
	public void search_for(String query) {
		WebElement element = driver.findElement(By.name("q"));
		// Enter something to search for
		element.sendKeys(query);
		// Now submit the form. WebDriver will find the form for us from the element
		element.submit();
	}

	@Then("^the page title should start with \"(.*)\"$")
	public void checkTitle(final String titleStartsWith) {
		// Google's search is rendered dynamically with JavaScript
		// Wait for the page to load timeout after ten seconds
		new WebDriverWait(driver, 10L).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith(titleStartsWith);
			}
		});
	}

	@After()
	public void closeBrowser() {
		driver.quit();
	}
}
