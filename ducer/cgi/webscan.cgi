#!/usr/bin/perl

# webscan cgi

$formsize = $ENV{'CONTENT_LENGTH'};
read (STDIN, $info, $formsize);
## jam everything into @stuf
@stuf = split(/&/, $info);

