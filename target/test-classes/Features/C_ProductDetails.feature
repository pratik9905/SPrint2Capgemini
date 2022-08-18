@ProductDetails
Feature: Verifying Snapdeal Product Details Functionality

  Scenario Outline: Viewing product details of a particular product
    Given user is on snapdeal home page
    When user enters "<search keyword>"
    And hover over the desired product and click on QUICK VIEW and then on VIEW DETAILS
    And expand all offers and hover over the image
    Then clicks on View all item details

    Examples: 
      | search keyword          |
      | rd sharma maths class10 |
