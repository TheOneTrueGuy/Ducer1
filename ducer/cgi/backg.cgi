#!/usr/local/bin/perl
print "Content type: text/plain","\n\n";
open (FILE, "< tiles.txt");
@phrases = <FILE>;
close (FILE);

 srand(time|$$);
 $r = int(rand(45));
 $todays_phrase ='<BODY BACKGROUND="' . $phrases[$r] . 
'"TEXT="#00FF00" LINK="#40E140" ALINK="#409933" VLINK="#4099FF">';
print  $todays_phrase, "\n\n";
exit(0);


