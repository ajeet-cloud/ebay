Feature: Verify Book can be added to Cart

@ui
  Scenario: Successfull Add to cart first serched book
    Given Launch ebay portal
    When Search "Book" on the search bar
    And Click on the first book in the list
    And Click to added to cart
    Then Verify the cart has been updated and displays the number of items in the cart
