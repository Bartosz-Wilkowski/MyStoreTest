Feature: Shop purchasing process
  @purchase
  Scenario Outline: User purchase product from online shop
    Given User is on main page
    And User go to login page
    And User log in using "<email>" and "<password>"
    And User go to main page
    When User choose Hummingbird Printed Sweater on main page
    And User check discount "SAVE 20%"
    And User select size "<size>"
    And User select <quantity> items
    And User add products to cart
    And User go to checkout
    And User confirm address on checkout "<alias>", "<address>", "<city>", "<zip/postal code>", "<country>", "<phone>", "<first name>", "<last name>"
    And User select type of delivery
    And User select type of payment
    And User confirm order
    Then User take a screenshot
    And User go to order history details
    And User can see status of the order
    Examples:
      | email                  | password               | size | quantity | alias      | address      | city   | zip/postal code | country        | phone     | first name | last name |
      | test@test.coderslab.pl | test@test.coderslab.pl | S    | 2        | My First Address | 12 Wide Street | York | 11222         | United Kingdom | 123456789 | Tester     | ISTQB     |
