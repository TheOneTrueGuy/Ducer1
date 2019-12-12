#!/usr/local/bin/perl

$formsize = $ENV{'CONTENT_LENGTH'};
read (STDIN, $info, $formsize);
@data= split/&/$info;
open (FILE,"<" @data[1]);
print FILE @data[2];
close FILE;

exit (0)






