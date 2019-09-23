package demo.Ahorcado_Cucumber_Selenium;

import static org.junit.Assert.*;

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

public class AhorcadoTest {

	private WebDriver driver;

	// Elementos pagina index.html
	By playerNameLocator = By.name("inputPlayerName");
	By btnPlayLocator = By.name("startGame");

	// Elementos pagina juego.jsp
	By gamePageLocator = By.xpath("//h1[@class='display-4 text-center']");
	By welcomeTextLocator = By.className("lead");
	By inputLetterLocator = By.name("letterInput");
	By btnGuessLocator = By.name("btnGuess");

	// Elementos pagina record.jsp
	By btnNewGameLocator = By.name("btnNewGame");
	By puntuationLocator = By.id("prgfPunctuation");

	// Otras variables
	String textoAssert;
	Boolean flagErrarTodas = false;
	Boolean flagGanarPerro = false;

	@Before
	public void chromeDriverConnection() {
		// Seteo el driver de chrome para utilizar dicho navegador en los tests
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();

	}

	@After()
	public void closeBrowser() {
		driver.quit();
	}

	/* TEST 1 */

	@Given("Ingreso a la pagina de AhorcadoGame")
	public void ingreso_a_la_pagina_de_AhorcadoGame() {
		driver.get("http://localhost:8080/AhorcadoGame/index.html");
	}

	@When("Ingreso nombre jugador igual a {string}")
	public void ingreso_nombre_jugador_igual_a(String nombre) throws InterruptedException {
		// Local variable
		WebElement welcomeText = null;
		// Ingreso nombre
		driver.findElement(playerNameLocator).sendKeys(nombre);
		// Comienzo el juego
		driver.findElement(btnPlayLocator).click();
		// Espero que cargue la pagina
		Thread.sleep(2000);
		// Si cambio de pagina pido el welcomeText
		if (driver.findElement(gamePageLocator).isDisplayed()) {
			welcomeText = driver.findElement(welcomeTextLocator);
		} else {
			System.out.print("No game location found");
		}

		textoAssert = welcomeText.getText();
	}

	@Then("El texto de bienvenida debe ser {string}")
	public void el_texto_de_bienvenida_debe_ser(String expectedAnswer) {
		assertEquals(expectedAnswer, textoAssert);
	}

	/* OTROS METODOS */
	public String validarName(String name) {
		return "Nicolas".equals(name) ? "success" : "fail";
	}

	/* TEST 2 */

	@When("Ingreso todos caracteres asterisco")
	public void ingreso_todos_caracteres_asterisco() throws InterruptedException {
		driver.findElement(btnPlayLocator).click();
		// Pido al test que espere 2 segundos
		Thread.sleep(2000);
		// Pregunto si cambio de pagina
		if (driver.findElement(gamePageLocator).isDisplayed()) {
			// jugar todas letras erroneas
			for (int i = 0; i < 5; i++) {
				driver.findElement(inputLetterLocator).clear();
				driver.findElement(inputLetterLocator).sendKeys("*");
				driver.findElement(btnGuessLocator).click();
				// Pido al test que espere 2 segundos
				Thread.sleep(1000);
			}
		} else
			System.out.print("No game location found");

		Thread.sleep(1000);

		if (driver.findElement(btnNewGameLocator).isDisplayed())
			flagErrarTodas = true;

	}

	@Then("Pierdo todas las vidas")
	public void pierdo_todas_las_vidas() {
		assertTrue(flagErrarTodas);
	}

	/* TEST 3 */

	@When("Ingreso la palabra {string}")
	public void ingreso_la_palabra(String palabra) throws InterruptedException {
		driver.findElement(btnPlayLocator).click();
		Thread.sleep(2000);

		if (driver.findElement(gamePageLocator).isDisplayed()) {
			// Ingreso p y espero 1 segundo
			driver.findElement(inputLetterLocator).sendKeys("p");
			driver.findElement(btnGuessLocator).click();
			Thread.sleep(1000);

			// Ingreso e
			driver.findElement(inputLetterLocator).sendKeys("e");
			driver.findElement(btnGuessLocator).click();
			Thread.sleep(1000);

			// Ingreso r
			driver.findElement(inputLetterLocator).sendKeys("r");
			driver.findElement(btnGuessLocator).click();
			Thread.sleep(1000);

			// Ingreso o
			driver.findElement(inputLetterLocator).sendKeys("o");
			driver.findElement(btnGuessLocator).click();
			Thread.sleep(1000);

		} else {
			System.out.print("No game location found");
		}

		if (driver.findElement(puntuationLocator).isDisplayed()) {
			flagGanarPerro = true;
		} else {
			System.out.print("You didn't win");
		}

	}

	@Then("Adivino la palabra")
	public void adivino_la_palabra() {
		assertTrue(flagGanarPerro);
	}

}
