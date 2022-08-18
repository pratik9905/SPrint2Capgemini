@ContactUs
Feature: Verifying Snapdeal COntact Us Functionality

  Scenario Outline: Verifying the Contact Us functionality
    Given User is on snapdeal Homepage
    When User should scrolls down
    When Uer should clicks on contact us
    When User enters "<FAQs>" on search menu
    When User can click on FAQs
    Then Display searched query results
    When User can click on any other query from FAQs List
    Then Display searched query results

    Examples: 
      | FAQs                                          |
      | How can I check my order status?              |
      | What are the different statuses in My Orders? |
