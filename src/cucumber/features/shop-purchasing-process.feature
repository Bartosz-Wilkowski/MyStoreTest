Feature: Shop purchasing process
  @purchase
  Scenario Outline: User purchase product from online shop
    Given User is on main page
    And User goes to login page
    And User logs in using "<email>" and "<password>"
    And User goes to main page
    When User chooses Hummingbird Printed Sweater on main page
    And User checks discount "SAVE 20%"
    And User selects size "<size>"
    And User selects <quantity> items
    And User adds products to cart
    And User goes to checkout
    And User confirms address on checkout "<alias>", "<address>", "<city>", "<zip/postal code>", "<country>", "<phone>", "<first name>", "<last name>"
    And User selects type of delivery
    And User selects type of payment
    And User confirms order
    Then User takes a screenshot
    And User goes to order history details
    And User can see status of the order
    Examples:
      | email                  | password               | size | quantity | alias      | address      | city   | zip/postal code | country        | phone     | first name | last name |
      | johncoal@test.tv| superpasswd | S    | 1        | My First Address | 12 Wide Street | York | 11222         | United Kingdom | 123456789 | John     | Coal    |
