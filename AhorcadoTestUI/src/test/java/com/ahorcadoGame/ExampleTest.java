package com.ahorcadoGame;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class ExampleTest {

	public WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		// Seteo el driver de chrome para utilizar dicho navegador en los tests
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();

		// maximizo la ventana del navegador
		driver.manage().window().maximize();

		// abro google en el navegador
		driver.get("https://www.google.com");
	}

	@AfterMethod
	public void afterMethod() {
		// Cierro el driver
		driver.quit();
	}

	@Test
	public void exampleTest() {

		// Asigno string en buscador y clickeo el boton de busqueda
		String searchFor = "cambridge dictionary";
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.clear();
		searchBox.sendKeys(searchFor);
		searchBox.submit();

		// Pido al test que espere 2 segundos
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		assertEquals("cambridge dictionary - Buscar con Google", driver.getTitle());
	}

}
