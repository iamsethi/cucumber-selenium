Feature: API Testing

  @tweet
  Scenario: Customer place an order by purchasing an item from search
   # Given user is on Home Page
    When a user post the tweet - "Love you @msdhoni!!"
    When user read the tweet
    And User delete the tweet
