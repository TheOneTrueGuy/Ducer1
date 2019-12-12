#!/usr/bin/perl
print "Content type: text/plain","\n\n";

@phrases = (
'<H1><FONT COLOR=#00ff40>Welcome to the Virtual Mad Science Union Hall #128</FONT></H1>',
'<H1>Welcome to another Mad-Cap Adventure!</H1>',
'<H1>Welcome to the The Guy's Laboratory</H1>',
'<H1>Helloooo Nurse!!!</H1>',
'<H1>Spooooon!!</H1>',
'<H1>Welcome cyber-naught!</H1>',
'<H1>Welcome to the Guidosphere</H1>',
'<H1> Welcome to where you are not</H1>',
'<H1>Welcome, stranger</H1>',
'<H1>Hi Wiggers</H1>')

 );
 srand(time|$$);
 $r = int(rand(11));
 $todays_phrase = $phrases[$r];
 print ": $todays_phrase","\n\n";
exit(0);



