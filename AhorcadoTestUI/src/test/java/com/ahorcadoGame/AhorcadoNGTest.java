package com.ahorcadoGame;

import org.testng.annotations.Test;

import com.project.pom.Controlador;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public class AhorcadoNGTest {

	// Defino variables y locators
	private WebDriver driver;
	Controlador controlador;

	@BeforeMethod
	public void beforeMethod() {
		controlador = new Controlador(driver);
		driver = controlador.chromeDriverConnection();
		controlador.maximizeWindow();
		controlador.visit("http://localhost:8080/AhorcadoGame/index.html");
	}

	@AfterMethod
	public void afterMethod() {
		// Cierro el driver
		driver.quit();
	}

	@Test
	public void ingreso_nombre_jugador_vacio() throws InterruptedException {
		String mensaje = controlador.setPlayerName("");
		// Assert
		assertEquals("Usted esta jugando como -", mensaje);
	}

	@Test
	public void ingreso_nombre_jugador_Nicolas() throws InterruptedException {
		String mensaje = controlador.setPlayerName("Nicolas");
		// Assert
		assertEquals("Usted esta jugando como Nicolas", mensaje);
	}

	@Test
	public void perder_juego() throws InterruptedException {
		// Assert
		assertTrue(controlador.guessWrong());
	}

	@Test
	public void ganar_juego_palabra_perro() throws InterruptedException {
		// Assert 
		assertTrue(controlador.guessPerro());
	}

}
