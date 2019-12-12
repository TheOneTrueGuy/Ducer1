#!/usr/local/bin/perl

$size = $ENV{'CONTENT_LENGTH'};
read (STDIN, $stuff2, $size);
($field, $stuff) = split (/#/, $stuff2);
print "Content-type: text/html","\n\n";

print $stuff +1;




exit (0);

