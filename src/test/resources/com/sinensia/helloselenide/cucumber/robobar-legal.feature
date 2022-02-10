Feature: Robobar age checks

  Background: User has already placed alcoholic beverages in her cart

  Rule: Underage users can not buy alcohol

    Scenario: minor tries to buy alcohol
      Given user is ready to check out with alcohol
      When user is 15 years old
      Then robobar does not allow checkout

    Scenario: adult tries to buy alcohol
      Given user is ready to check out with alcohol
      When user is 19 years old
      Then robobar confirms order
