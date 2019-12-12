#!/usr/local/bin/perl
print "Content type: text/plain","\n\n";

 srand(time|$$);
 $r = int(rand(13))+1;
 $todays_phrase ='<IMG SRC ="pics/'. $r.'.gif"  >';
 print ": $todays_phrase","\n\n";
exit(0);


