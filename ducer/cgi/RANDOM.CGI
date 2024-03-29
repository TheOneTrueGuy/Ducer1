#************************************************************
#** This Perl example is provided "as is".  This code is   **
#** not supported, but I will try to answer questions as   **
#** time allows.  Email: wizjd@panix.com                   **
#** Visit: <URL:http://www.panix.com/~wizjd/> for updated, **
#** and new examples.                                      **
#************************************************************
#!/usr/local/bin/perl
# Problem: The second time a user clicks on the URL that
# Calls your CGI script, their browser retrieves the 
# cached output of the last call.  It doesn't connect
# to the server, and cause the script to be run again,
# because it thinks it knows what the output will be.
# This can be a problem if your script is supposed to
# generate unique information each time its called.
# For instance- "Click here to get the current tempature".
# To keep this from happening you can have the CGI generate
# the page that calls it.  Each time it prints out its own
# URL it includes a bit of "extra path information", that is
# different each time.  Therefore when the user clicks on
# the URL for the CGI script it is not exactly the same,
# as last time, and the browser doesn't get it from the cache.



$number = $fields{'number'};

print("Content-Type: text/html\n\n");
print("<HEAD><TITLE>Random Number Page</TITLE></HEAD>\n");
print("<BODY>\n");

srand;
$number = rand(100);
$number = substr($number,0,2);

print "<H3>The random number is <B>", $number, "</B></H3>";
print "<HR>";
print "<A href=\x22/~wizjd/random.cgi/number=", $number, "\x22>";
print "<B>Get another Random number</A>\n";
print "<HR>";
print "<BR>See the Perl";
print "<A href=random_src.html> Source Code</A>";
print "(random.cgi) for this page";
print "<HR><A href=\x22/~wizjd/test.html\x22>Back to Examples Page</A>\n";


print("</BODY>");
print("</HTML>");
