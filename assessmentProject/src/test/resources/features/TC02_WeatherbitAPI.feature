Feature: TC02 Weather bit API feature
  As a registered user I should be able to do GET operation on Weather bit using API key

  @TC02_WeatherbitAPI
  Scenario Outline: A user should be  able to do GET operation on Weather bit based on data passed
    When user passes the <LAT> & <LON> values in weatherbit current weather get url
    Then verify statecode is <STATE_CODE>
    When user passes the <POSTAL_CODE> in weatherbit forcast get url
    Then verify TimeStamp and Weather

    Examples:
      | LAT | LON    | STATE_CODE | POSTAL_CODE |
      | 38  | -78.25 |  VA        |		28546    |
