Feature: Verify the list of BPIs

  @api
  Scenario: Verify the list of BPIs and GBP Description
    When User send Get request
    Then Verify the list of BPIs
      | USD |
      | GBP |
      | EUR |
    Then Verify GBP Description as "British Pound Sterling"
    
