@Search
Feature: Verifying Snapdeal Search Functionality

  @SearchPositive
  Scenario: Verifying the search functionality with valid keyword
    Given User is on snapdeal page
    When User enters "Headphones under 3000"
    Then User should click search button
    Then Verify and display the search results
    
  @SearchNegative
  Scenario: Verifying the search functionality with invalid keyword
    Given User is on snapdeal page
    When User enters "erejrelfefjedf"
    Then User should click search button
    Then Verify and display the search results
    
  @SearchOutline
  Scenario Outline: Check if keyword is valid or invalid
    Given User is on snapdeal page
    When User enters "<keyword>"
    Then User should click search button
    Then Verify and display the search results

    Examples: 
      | keyword              |
      | Bags under 2000      |
      | ghhghghghghghghghghg |
