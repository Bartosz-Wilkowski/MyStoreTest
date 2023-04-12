Feature: User account edit

@newAddress
  Scenario Outline: User add new address to existing one
    Given User is on main page
    And User go to login page
    And User log in using "<email>" and "<password>"
    And User go to addresses page by addresses tile
    And User can see there is already one address
    When User create new address
    And User enter new address "<alias>", "<address>", "<city>", "<zip/postal code>", "<country>", "<phone>"
    Then User can see new address
    And User verify created address "<alias>", "<address>", "<city>", "<zip/postal code>", "<country>", "<phone>", "<first name>", "<last name>"
    And User remove new address
    And User can see the address was removed
    And User log out
    And User close the browser

    Examples:
      | email                  | password               | alias      | address      | city   | zip/postal code | country        | phone     |first name|last name|
      | test@test.coderslab.pl | test@test.coderslab.pl | NewAddress | 13 Narrow St | London | E14 8BP         | United Kingdom | 123456789 |Tester    |ISTQB    |


#@firstAddress
#  Scenario Outline: User add first address
#    Given User is on main page
#    And User go to login page
#    And User log in using "<email>" and "<password>"
#    And User go to addresses page by footer
#    When User create new address
#    And User enter new address "<alias>", "<address>", "<city>", "<zip/postal code>", "<country>", "<phone>"
#    Then User can see new address
#    And User verify created address "<alias>", "<address>", "<city>", "<zip/postal code>", "<country>", "<phone>", "<first name>", "<last name>"
#    And User log out
#    And User close the browser
#
#    Examples:
#      | email                  | password               | alias      | address      | city   | zip/postal code | country        | phone     |first name|last name|
#      | test@test.coderslab.pl | test@test.coderslab.pl | NewAddress | 13 Narrow St | London | E14 8BP         | United Kingdom | 123456789 |Tester    |ISTQB    |
#
