#!/usr/local/bin/perl
print "Content type: text/plain","\n\n";

@phrases = (
 '<P>Today is a good day for smiling.',
 '<P>Squishyblenderishywhooplisplooshyundiblapoohlabol',
 '<P>How come you did not notice that little bird this morning',
 '<P>Keep up the good work',
 '<P>Surf on fellow cyber-head!',
 '<P>How many cybernauts does it take to screw in a light bulb?',
 '<H1>Ahhh!!! my brain is tweaking!',
 '<P> There are fish swimming in this stream of thought',
 '<P> Don\'t throw your data out with the dishwater',
 '<H1>Help! I\'m trapped in a digital dream',
 '<P> Fire on the mountain reflects the sublime light of earth',
 '<P>dogs need only the comfort of a full belly'
 );
 srand(time|$$);
 $r = int(rand(11));
 $todays_phrase = $phrases[$r];
 print ": $todays_phrase","\n\n";
exit(0);


