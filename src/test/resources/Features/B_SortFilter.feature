@SortFilter
Feature: Verifying Snapdeal Sort & Filter Functionality

  Background: 
    Given User is on Snapdeal page
    When User enters Ncert Textbook
    Then User should clicks on search button
    
  Scenario: Verifying the Sort & Filter functionality
    When User click on sort by
    Then Checks the counts of Options
    When User selects the option fresh arrivals
    Then verify the fresh arrival is selected
    When User clicks on apply filter options
    Then Dispaly the filtered results