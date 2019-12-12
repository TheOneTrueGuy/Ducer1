#!/usr/local/bin/perl

print"content type: test/html", "\n\n";
print "<HTML>","\n";
print "<HEAD><TITLE>About this server</TITLE></HEAD>","\n";
print "<HR><PRE>";
print "server name:       ", $ENV{'SERVER_NAME'}, "<BR>", "\n";
print "<HR></PRE>", "\n";
print "</BODY></HTML>", "\n";

exit (0)

