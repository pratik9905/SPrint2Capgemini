@Compare
Feature: Compare two Products

Scenario: Compare products in snapdeal
Given User is on Snapdeal home Page
When Enter search the "Boat Headphone under 3000"
And Click on search button
And select the product
When click on add to compare
And select another product
Then click on btn Lets compare