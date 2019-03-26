@api
Feature: API Testing - Artist
  Get Spotify catalog information about an artist's albums. Optional parameters can be specified in the query string to filter and sort the response.

  @artist
  Scenario: Get an Artist's Albums
    When a user get an Artist Album for id "0TnOYISbd1XYRBk9myaseg"

  @several_artist
  Scenario: Get Several Artists
    When a user get an several Artist id "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6"
