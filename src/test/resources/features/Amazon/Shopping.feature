@regression
Feature: Login

  @toy
  Scenario Outline: Customer place an order by purchasing an item from search
    Given user is on Home Page
    When he search for "Toys"
    And choose to buy the first item
    And moves to checkout from mini cart
    And I sign in on application
    And user select new delivery address
    And enter "<customer>" personal details on checkout page
    And select payment method as "check" payment
    And place the order

    Examples: 
      | customer |
      | Brian    |

  @dress
  Scenario Outline: Customer place an order by purchasing an item from search
    Given user is on Home Page
    When he search for "dress"
    And choose to buy the first item
    And moves to checkout from mini cart
    And I sign in on application
    And user select new delivery address
    And enter "<customer>" personal details on checkout page
    And select payment method as "check" payment
    And place the order

    Examples: 
      | customer |
      | Brian    |
      
      
   @magazine
  Scenario Outline: Customer place an order by purchasing an item from search
    Given user is on Home Page
    When he search for "magazine"
    And choose to buy the first item
    And moves to checkout from mini cart
    And I sign in on application
    And user select new delivery address
    And enter "<customer>" personal details on checkout page
    And select payment method as "check" payment
    And place the order

    Examples: 
      | customer |
      | Brian    |
      
      
    @chair
  Scenario Outline: Customer place an order by purchasing an item from search
    Given user is on Home Page
    When he search for "chair"
    And choose to buy the first item
    And moves to checkout from mini cart
    And I sign in on application
    And user select new delivery address
    And enter "<customer>" personal details on checkout page
    And select payment method as "check" payment
    And place the order

    Examples: 
      | customer |
      | Brian    |
      
      
  @table
  Scenario Outline: Customer place an order by purchasing an item from search
    Given user is on Home Page
    When he search for "table"
    And choose to buy the first item
    And moves to checkout from mini cart
    And I sign in on application
    And user select new delivery address
    And enter "<customer>" personal details on checkout page
    And select payment method as "check" payment
    And place the order

    Examples: 
      | customer |
      | Brian    |
      
