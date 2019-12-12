#!/usr/local/bin/perl

 $size_form_info = $ENV{'CONTENT_LENGTH'};

 read (STDIN, $form_info, $size_form_info);
 
  $form_info =~ s/%([\dA-Fa-f][\dA-Fa-f])/pack ("C", hex ($1))/eg;

 ($field_name, $bday) = split (/=/, $form_info);

print "Content-type: text/plain", "\n\n";
print "your bday: $bday", "\n";


exit (0)

