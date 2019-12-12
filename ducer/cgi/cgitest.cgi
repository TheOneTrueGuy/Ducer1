#!/usr/bin/perl -w
$dat = $ENV{'QUERY_STRING'};
print "Content-type: text/plain", "\n\n";
print $dat;

exit (0);
#http://www.budget.net/~theguy/cgi-bin/cgitest.cgi?fyl=this&score=that