#!/usr/bin/perl -w
$formsize = $ENV{'CONTENT_LENGTH'};
read (STDIN, $info, $formsize);
($name, $data) = split(/=/, $info);
## fix this, so stuff goes in @array appinfo,job# etc.
$namedat = $name + '.dat';
$name = $name + '.cnt';
## file $name already made by JoinDucer.cgi set to 0
$counter = "/home2/theguy/WWW/Ducer/users/$name";
print "Content type: application/x-www-form-urlencoded", "\n\n";
&creditor();
$jobnum = &parse('job',$data);

## check to see if job is done with scheduler
@zd = &scheduler('check',$jobnum);

exit (0)







## stuff to do:::
## uhm.... continue from this point component, finish tester/requirement
## reassignment component, security, router 
