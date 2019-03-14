@api

Feature: API Testing - Get Tweet timelines
  A timeline is simply a list, or an aggregated stream of Tweets.  The Twitter API has several endpoints that return a timeline of Tweet data - see the table below for more details:

  @timeline
  Scenario: GET statuses/user_timeline
    When a user read the user timeline
    When a user read the user timeline with count as "5"

  @timeline
  Scenario: GET statuses/mentions_timeline
    When a user read the mention timeline
    When a user read the mention timeline with count as "2"