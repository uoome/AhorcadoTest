package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Controlador extends Base {

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

	public Controlador(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String setPlayerName(String playerName) throws InterruptedException {
		// Local variables
		WebElement welcomeText = null;

		// Ingreso nombre
		type(playerName, playerNameLocator);
		// Comienzo el juego
		click(btnPlayLocator);
		// Espero que cargue la pagina
		Thread.sleep(2000);
		// Si cambio de pagina pido el welcomeText
		if (isDisplayed(gamePageLocator)) {
			welcomeText = findElement(welcomeTextLocator);
		} else {
			System.out.print("No game location found");
		}

		return getText(welcomeText);
	}

	public Boolean guessWrong() throws InterruptedException {
		// Local variables
		Boolean flag = false;

		click(btnPlayLocator);
		// Pido al test que espere 2 segundos
		Thread.sleep(2000);
		// Pregunto si cambio de pagina
		if (isDisplayed(gamePageLocator)) {
			// jugar todas letras erroneas
			for (int i = 0; i < 5; i++) {
				clear(inputLetterLocator);
				type("*", inputLetterLocator);
				click(btnGuessLocator);
				// Pido al test que espere 2 segundos
				Thread.sleep(1000);
			}
		} else
			System.out.print("No game location found");

		if (isDisplayed(btnNewGameLocator))
			flag = true;

		return flag;

	}

	public Boolean guessPerro() throws InterruptedException {
		// Local variables
		Boolean flag = false;

		click(btnPlayLocator);
		Thread.sleep(2000);

		if (isDisplayed(gamePageLocator)) {
			// Ingreso p y espero 1 segundo
			type("p", inputLetterLocator);
			click(btnGuessLocator);
			Thread.sleep(1000);

			// Ingreso e
			type("e", inputLetterLocator);
			click(btnGuessLocator);
			Thread.sleep(1000);

			// Ingreso r
			type("r", inputLetterLocator);
			click(btnGuessLocator);
			Thread.sleep(1000);

			// Ingreso o
			type("o", inputLetterLocator);
			click(btnGuessLocator);
			Thread.sleep(1000);

		} else {
			System.out.print("No game location found");
		}

		if (isDisplayed(puntuationLocator)) {
			flag = true;
		} else {
			System.out.print("You didn't win");
		}
		
		return flag;
	}
}
