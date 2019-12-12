#!/usr/local/bin/perl

$size = $ENV{'CONTENT_LENGTH'};
read (STDIN, $stuff2, $size);
($field, $stuff) = split (/=/, $stuff2);

print "Content-type: text/html", "\n\n";
print "<HTML>", "\n";
print "<H1>", "\n\n";

print "HI: $stuff", "\n\n";

print "Hello: $stuff", "\n\n";
print "HHHHHHHHHHHHHHHHHHHHH", "\n\n";
print "</H1>";
print "</HTML>";
exit (0);

