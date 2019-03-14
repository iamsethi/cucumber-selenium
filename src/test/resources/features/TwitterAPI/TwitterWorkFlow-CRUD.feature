@api
Feature: API Testing - Post, retrieve and engage with Tweets
  The following API endpoints can be used to programmatically create, retrieve and delete Tweets

  @crud
  Scenario: POST statuses/update GET statuses/show/:id POST statuses/destroy/:id
    When a user post the tweet - "Love you @msdhoni!!"
    When user read the tweet
    And User delete the tweet

