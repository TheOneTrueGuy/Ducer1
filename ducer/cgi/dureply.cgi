#!/usr/local/bin/perl

$formsize = $ENV{'CONTENT_LENGTH'};
read (STDIN, $info, $formsize);
print "Content-type: text/plain","\n\n";
print "Process started";
exit (0)






