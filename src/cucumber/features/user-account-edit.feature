Feature: User account edit

@newAddress
  Scenario Outline: User add new address to existing one
    Given User is on main page
    And User goes to login page
    And User logs in using "<email>" and "<password>"
    And User goes to addresses page by addresses tile
    And User can see there is already one address
    When User creates new address
    And User enters new address "<alias>", "<address>", "<city>", "<zip/postal code>", "<country>", "<phone>"
    Then User can see new address
    And User verifies created address "<alias>", "<address>", "<city>", "<zip/postal code>", "<country>", "<phone>", "<first name>", "<last name>"
    And User removes new address
    And User can see the address was removed
    And User logs out
    And User closes the browser

    Examples:
      | email                  | password               | alias      | address      | city   | zip/postal code | country        | phone     |first name|last name|
      | johncoal@test.tv | superpasswd | NewAddress | 13 Narrow St | London | E14 8BP         | United Kingdom | 123456789 |John    |Coal    |


@firstAddress
  Scenario Outline: User add first address
    Given User is on main page
    And User goes to login page
    And User logs in using "<email>" and "<password>"
    And User goes to addresses page by footer
    When User creates new address
    And User enters new address "<alias>", "<address>", "<city>", "<zip/postal code>", "<country>", "<phone>"
    Then User can see first address
    And User verifies first address "<alias>", "<address>", "<city>", "<zip/postal code>", "<country>", "<phone>", "<first name>", "<last name>"
    And User logs out
    And User closes the browser

    Examples:
      | email                  | password               | alias      | address      | city   | zip/postal code | country        | phone     |first name|last name|
      | mauriceking@test.ru | mauricepass | First Address | 213 Steel St | Cork | N56 82P         | United Kingdom | 123456789 |Maurice    |King    |

