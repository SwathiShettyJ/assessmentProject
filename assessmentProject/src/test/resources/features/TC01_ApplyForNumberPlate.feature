Feature: TC01 Apply For Number Plates
  As a user I should be apply for Number plates in Service NSW Gov Page

  @TC01_ApplyForNumberPlates
  Scenario Outline: A user should be apply for Number plates based on the suburb data passed
    Given user is in 'Service NSW Gov' Page
    Then verify page title
    When user clicks on 'Driving and transport'
    Then verify user is in 'Driving and transport' page
    When user clicks on 'Number Plates'
    Then verify user is in 'Number plates' page
    When user clicks on 'Apply for a number plate'
	Then verify user is in 'Apply for a number plate' page
	When user clicks on 'Find Locations' tab
	Then verify user is in 'Find Locations' page
	When user searches for suburb <SUBURB>
	Then verify and click on service centre - <SERVICE_CENTRE>

    Examples:
      | SUBURB       | SERVICE_CENTRE              |
      | Sydney 2000  | Marrickville Service Centre |
