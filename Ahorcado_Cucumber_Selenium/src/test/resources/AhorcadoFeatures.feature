#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Testeo de UI para el test de Ahorcado

  @tag1
  Scenario Outline: Ingresar nombre de jugador
    Given Ingreso a la pagina de AhorcadoGame
    When Ingreso nombre jugador igual a <namePlayer>
    Then El texto de bienvenida debe ser <answer> 

    Examples: 
      | namePlayer | answer                            |
      | ""         | "Usted esta jugando como -"       |
      | "Nicolas"  | "Usted esta jugando como Nicolas" |
      
  @tag2
  Scenario: Errar todas las letras
  	Given Ingreso a la pagina de AhorcadoGame
  	When Ingreso todos caracteres asterisco
  	Then Pierdo todas las vidas
  	
  @tag3
  Scenario: Adivinar la palabra "perro"
  	Given Ingreso a la pagina de AhorcadoGame
  	When Ingreso la palabra "perro"
  	Then Adivino la palabra
