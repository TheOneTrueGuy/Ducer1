#!/usr/bin/perl
$here = 'http://www.budget.net/~theguy';
# establish files, names and types
$scorfyl = 'job1.sc';
open(FILE, "> $scorfyl");
for ($i=100;$i>0;$i--){
$slist = $slist+"0,";
}
print FILE $slist;
close FILE;
# 
$jobs='request.job';
open(FILE, "> $jobs");
# engine type, exp.out source, requester id., percentile usage, priority 
# priority: system 1-10, registered 1-10, common 1-10

print FILE 'Evoca&expout.htm&ducer0000&.75&s1!';
close FILE;

open(FILE, ">>$jobs");
print FILE 'reDucer7b&expout.txt&ducer0000&.75&s2!';
print FILE 'complex&'.$here.'&ducer0000&.75&s3!';
close FILE;

