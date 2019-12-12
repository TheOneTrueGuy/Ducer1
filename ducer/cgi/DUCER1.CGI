#!/usr/local/bin/perl

$size = $ENV{'CONTENT_LENGTH'};
read (STDIN, $stuff2, $size);
($field, $stuff) = split (/=/, $stuff2);

print "Content-type: text/html", "\n\n";
print "<HTML><H1>", "\n";

print "test loop: $stuff", "\n\n";

print "</H1></HTML>", "\n";

exit (0);

