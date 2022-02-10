Feature: Robobar cart
  Scenario: user add one cola
    Given user opens robobar webside
    When user adds a cola
    Then total should be €1.25

  Scenario: user add two cola
    Given user opens robobar webside
    When user adds a cola
    And user adds a cola
    Then total should be €2.50

  Scenario: user add one beer
    Given user opens robobar webside
    When user adds a beer
    Then total should be €2.00

  Scenario: user add two beers
    Given user opens robobar webside
    When user adds a beer
    And user adds a beer
    Then total should be €4.00

  Scenario: user add one wine
    Given user opens robobar webside
    When user adds a wine
    Then total should be €3.00

  Scenario: user add two wines
    Given user opens robobar webside
    When user adds a wine
    And user adds a wine
    Then total should be €6.00

  Scenario: user add one beer and age is 17
    Given user opens robobar webside
    When user adds a beer
    Then total should be €2.00
    When user press submit button
    And user enter his age is 17
    And user press order button
    Then alert is active

  Scenario: user add one beer and age is 20
    Given user opens robobar webside
    When user adds a beer
    Then total should be €2.00
    When user press submit button
    And user enter his age is 20
    And user press order button
    Then alert is not active
    And order is confirmed

  Scenario Outline: user buys several colas
    Given  user opens robobar webside
    When user adds <n> cola
    Then total should be €<total>
    Examples:
      | n | total |
      | 1 | 1.25  |
      | 2 | 2.50  |

  Scenario Outline: user buys several beers
    Given  user opens robobar webside
    When user adds <n> beer
    Then total should be €<total>
    Examples:
      | n | total |
      | 1 | 2.00  |
      | 2 | 4.00  |

  Scenario Outline: user buys several wines
    Given  user opens robobar webside
    When user adds <n> wine
    Then total should be €<total>
    Examples:
      | n | total |
      | 1 | 2.00  |
      | 2 | 4.00  |

  Scenario Outline: user buy several drinks
    Given user opens robobar webside
    When user adds <cola> cola
    And user adds <beer> beers
    Then total should be €<total>
    Examples:
      | cola | beer | total |
      | 1    | 0    | 1.25  |
      | 0    | 1    | 2.00  |
      | 1    | 1    | 3.25  |

  Scenario Outline: user buy several drinks
    Given user opens robobar webside
    When user adds <cola> cola <beer> beer <wine> wine
    Then total should be €<total>
    Examples:
      | cola | beer | wine | total |
      | 1    | 0    | 0    | 1.25  |

  Scenario Outline: underage user buys alcoholic drink
    Given user opens robobar webside
    When user adds <cola> cola <beer> beer <wine> wine
    And total should be €<total>
    When user press submit button
    And user enter his age is 17
    And user press order button
    Then alert is active
    Examples:
      | cola | beer | wine | total |
      | 1    | 1    | 0    | 3.25  |

  Scenario Outline: underage user buys alcoholic drink
    Given user opens robobar webside
    When user adds <cola> cola <beer> beer <wine> wine
    And total should be €<total>
    When user press submit button
    And user enter his age is 20
    And user press order button
    Then alert is not active
    Examples:
      | cola | beer | wine | total |
      | 1    | 1    | 0    | 3.25  |
