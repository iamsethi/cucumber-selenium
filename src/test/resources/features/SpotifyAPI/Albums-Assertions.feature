@api

Feature: API Testing - Albums
  Get Spotify catalog information about an artist’s albums. Optional parameters can be specified in the query string to filter and sort the response

  @album
  Scenario: Get an Album's Tracks
    Get Spotify catalog information about an album’s tracks. Optional parameters can be used to limit the number of tracks returned

    When a user read the album track
