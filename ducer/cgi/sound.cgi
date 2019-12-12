#!/usr/local/bin/perl
print "Content type: text/plain","\n\n";

@phrases = (
'2001.mid',
'airwolf.mid',
'arabian.mid',
'batlstar.mid',
'bevrlhl.mid',
'bud.mid',
'chariots.mid',
'chopstick.mid',
'colympic.mid',
'danger.mid',
'drwho.mid',
'elephant.mid',
'getsmart.mid',
'grnacres.mid',
'hawaii50.mid',
'hogans.mid',
'jbond.mid',
'jquest.mid',
'knghtrdr.mid',
'limeli.mid',
'mission.mid',
'morphin.mid',
'muppets.mid',
'mx76.mid',
'popeye.mid',
'rvalkyr.mid',
'startrek.mid',
'superman.mid',
'twilight.mid',
'voyager.mid',
'wmtell.mid'

);
srand(time|$$);
$r = int(rand(10));
$todays_phrase = '<BGSOUND SRC="' . $phrases[$r] . '">';
print "$todays_phrase","\n\n";
exit(0);


