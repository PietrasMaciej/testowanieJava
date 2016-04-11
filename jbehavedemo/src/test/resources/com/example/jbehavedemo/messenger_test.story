Given a messenger
When set server to gdansk.pjwstk.edu.eu and message to "some message"
Then sending message should return 1

When server is set to gdansk.pjwstk.edu.pl and message to ab
Then sendingMessage method should return exception with code status 2

When server is set to gdansk.pjwstk.edu.pl and message is set to some message
Then sending message method should return 0 or 1

When server's set to gdansk.pjwstk.edu.pl
Then test connection should return 0

!--When server is gdansk.pjwstk.edu.eu
!--Then test connection method should return 1

