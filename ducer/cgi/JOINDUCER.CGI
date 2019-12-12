#!/usr/bin/perl
$form = $ENV{'QUERY_STRING'};
($fst,$scnd) = split(/&/, $form);
($name, $data) = split(/=/, $fst);
($ps,$passwd) = split(/=/,$scnd);
$userf= 'users/' . $data;
$userc = $userf . '.cnt';
open (FILE, ">$userf") || die "cannot make user file.\n";
flock (FILE,2);
print FILE 'new'; 
flock (FILE,8);
close (FILE);

open (FILE, ">" . $userf) || die "cannot write to user file.\n";
flock (FILE,2);
print FILE $passwd;
flock (FILE, 8);
close (FILE);
 

open (FILEC, ">" . $userc) || die "cannot write to user credit file.\n";
flock (FILEC,2);
print FILEC '0';
flock (FILEC, 8);
close (FILEC);

$userd = $userf . '.dat';

open (FILED, ">" . $userd) || die "cannot write to user data file.\n";
flock (FILED,2);
print FILED "nojob";
## nojob indicator for scheduler

flock (FILED, 8);
close (FILED);

print "Location: http://www.chatlink.com/~guido5/Ducer/GoOn.html","\n\n";

exit (0);
