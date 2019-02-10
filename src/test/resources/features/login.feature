Feature: Login

  @regression
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
