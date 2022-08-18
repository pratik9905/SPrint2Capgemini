@Cart
Feature: Verifying Snapdeal Cart Functionality

  Background: 
    Given User is on snapdeal page.
    When User enters search keyword
    Then User should click on search button
    
  @CartPositive
  Scenario: Verifying the cart functionality if product is not already added to cart
    When User should clicks on product
    When User should click on add to cart button
    Then Verify the product is added to cart button
    When User can clicks on view cart button
    Then Display view cart details
    When User can clicks on remove from cart button
    Then Verify the product is removed from the cart
    
  @CartNegative
  Scenario: Verifying the cart functionality if same product is already added in cart
    When User should clicks on product
    When User should click on add to cart button
    Then Verify the product is added to cart button
    When User can clicks on same product again
    When User should click on add to cart button again
    Then Verify the product is already added to cart button
    When User can clicks on view cart button
    Then Display view cart details
    When User can clicks on remove from cart button
    Then Verify the product is removed from the cart
    
