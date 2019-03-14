Feature: API Testing - Post, retrieve and engage with Tweets

  @tweet
  Scenario: Post Tweet , Read Tweet and Delete Tweet
    When a user post the tweet - "Love you @msdhoni!!"
    When user read the tweet
    And User delete the tweet
