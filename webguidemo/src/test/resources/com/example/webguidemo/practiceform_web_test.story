Scenario: User searches for a single step
 
Given user is on Home page
When user opens a link
Then another page is shown

When user types test on input
Then input has a value test

When user checks a checkBox
Then checkBox is clicked

When user clicks a radio button
Then radio button is clicked

When user clicks a button
Then alert is shown

When user clicks verification submit
Then validator is shown

When user types a in verification field
Then Please enter only digits. message is shown

When user types 123 in verification
Then following message is shown Please enter no more than 2 characters.