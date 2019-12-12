#!/usr/bin/perl

$r=rand(3)+1;
$nam='w'.$r.'.html';
open (FILE,"<$nam");
read(FILE,$dat,16000);
close FILE;
$nam='Welcome.html'
open(FILE,">$nam");
print FILE $dat;
close FILE
