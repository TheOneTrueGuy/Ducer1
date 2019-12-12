#!/usr/bin/perl -w

use CGI;
$fyl = param("fyl");
$scor = param("scor");
$top = param("top");
$node = param("node");
$tym = param("tym");

open (FILE, "< $fyl");
read (FILE,$tdat,2000);
close FILE;

$scorfyl = $fyl.'sc';
open(FILE, "< $scorfyl");
read (FILE,$sdat,2000);
close FILE;

@tlist = split /"\n"/ $tdat;
@slist = split/"\n"/ $sdat;

for ($i=100;$i>0;$i--){
  if ($slist[$i] > $scor){
   $tlist[$i+1]=$top;
   $slist[$i+1]=$scor;
   $i=0; }
}
if ($slist[0] < $scor){
$tlist[$i+1]=$top;
   $slist[$i+1]=$scor;
} 
open (FILE,"<$fyl") or die("Error opening top file out");
print FILE join("\n",@tlist);
close FILE;
open (FILE,"<$scorfyl") or die("Error opening scor file out");
print FILE join("\n",@slist);
close FILE
# credit this node
open (FILE,">$node") or die(Error opening node in");
read (FILE, $nodtym,200);
close FILE
$nodtym = $nodtym+$tym;
open (FILE,">$node") or die(Error opening node out");
print FILE $nodtym;
close FILE


exit (0);