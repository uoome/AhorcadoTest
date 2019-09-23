package demo.Ahorcado_Cucumber_Selenium;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

class IsItFriday {
	static String isItFriday(String today) {
		return "Friday".equals(today) ? "TGIF" : "Nope";
	}
}

public class StepsDemo {
	private String today;
	private String actualAnswer;

	@Given("^today is \"([^\"]*)\"$")
	public void today_is(String today) {
		this.today = today;
	}

	@When("^I ask whether it's Friday yet$")
	public void i_ask_whether_it_s_Friday_yet() {
		actualAnswer = IsItFriday.isItFriday(today);
	}

	@Then("^I should be told \"([^\"]*)\"$")
	public void i_should_be_told(String expectedAnswer) {
		assertEquals(expectedAnswer, actualAnswer);
	}
}
