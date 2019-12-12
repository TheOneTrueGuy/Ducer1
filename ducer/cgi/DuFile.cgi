#!/usr/bin/perl
# Full path to Perl
#
# Define upload directory
$dir = "/results"; 

# Set maximum size of upload directory in kilobytes
$quota = 3072;

# Start main program

$content_type = $ENV{'CONTENT_TYPE'};
$content_len = $ENV{'CONTENT_LENGTH'};
$request_method = $ENV{'REQUEST_METHOD'};

&CheckRequest;
&ParseForm;
&Verify;
&Dir;
&SaveFile;
exit;

# End main program

# Subroutines

sub ParseForm
 { # Begin ParseForm

	# Buffer the POST content
	binmode STDIN;
	read(STDIN, $buffer, $content_len);

	  # find boundary
	  # Eric Poulsen fixed the following to allow for quotes.
	  # ($boundary = $content_type) =~ s/^.*boundary=(.*)$/\1/;
	  ($boundary = $content_type) =~ s/^.*boundary="?(.*?)"?$/$1/;
          @pairs = split(/--$boundary/, $buffer);
          @pairs = splice(@pairs,1,$#pairs-1);
          for $part (@pairs) 
           {
            ($dump,$fline,$value) = split(/\r\n/,$part,3);
            next if $fline =~ /filename=\"\"/;
            $fline =~ s/^Content-Disposition: form-data; //;
            (@columns) = split(/;\s+/, $fline);
            ($name = $columns[0]) =~ s/^name="([^"]+)"$/$1/g;
            if ($#columns > 0) 
             {
              if ($value =~ /^Content-Type:/) 
               {
                ($dump,$dump,$value) = split(/\r\n/,$value,3);
               }
              else 
               {
                ($dump,$value) = split(/\r\n/,$value,2);
               }
             }
            else 
             {
              ($dump,$value) = split(/\r\n/,$value,2);
              if (grep(/^$name$/, keys(%CGI))) 
               {
                if (@{$FORM{$name}} > 0) 
                 {
                  push(@{$FORM{$name}}, $value);
                 }
                else 
                 {
                  $arrvalue = $FORM{$name};
                  undef $FORM{$name};
                  $FORM{$name}[0] = $arrvalue;
                  push(@{$FORM{$name}}, $value);
                 }
               }
              else 
               {
                next if $value =~ /^\s*$/;
                $FORM{$name} = $value;
               }
              next;
             }
            $FORM{$name} = $value;
           }
 } # End ParseForm
	
sub SaveFile
 { # Begin SaveFile

	$filename = "$dir/$upname";
	open(FILE, ">$filename") || &error("Cannot open file $filename for writing: $!");
	binmode FILE;

	print FILE $upfile;
	close FILE;

 } # End SaveFile

sub CheckRequest
 { # Start CheckForm

   if ($request_method ne "POST")
    {
     &error("Request method should be \"POST\".");
    }

   if ($content_type !~ m#^multipart/form-data#)
    {
     &error("Content-type should be multipart/form-data");
    }

 } # End CheckRequest


sub error
 { # Start error
  $Error = $_[0];
  print <<"(END ERROR HTML)";
Content-type: text/html

<HTML>
 <HEAD>
  <TITLE>Error</TITLE>
 </HEAD>
<BODY BGCOLOR=\"#8080ff\" TEXT=\"#000000\" LINK=\"#ffff00\" VLINK=\"#800000\">
<CENTER>
 <H1>Error</H1>
</CENTER>
<P>
$Error
<P>
Please click on the Back button to return.
</BODY>
</HTML>
(END ERROR HTML)
exit;     
 } # End error

sub Dir
 { # Start Dir

@sizes=(); # Define empty list with sizes of files in upload dir
opendir (DIR, $dir) || &error("Unable to open directory $dir: $!");
 while ($file = readdir (DIR))
  {
   if ($file eq ".")
   {
    # Do nothing!
   }
   elsif ($file eq "..")
   {
   # Do nothing!
   }
   else
   {
   $size = -s "$dir/$file";
   push (@sizes, $size);
   }
  } 

 $totalsize = 0;
 foreach $singlesize (@sizes)
  {
   $totalsize = $totalsize + $singlesize;
  }

 $newsize = ($totalsize + $content_len)/1024; # Size in kilobytes
 if ($newsize > $quota)
  {
   &error("Maximum directory size reached... Sorry");
  } 

closedir (DIR);
 } # End Dir

sub Verify
 { # Start Verify

  $upfile = $FORM{'upfile'};
  $upname = $FORM{'upname'};
  if (!($upfile) || !($upname))
   {
    &error("No upload file specified!");
   }

  if ($upname =~ /[;><&\*'\|\/\\]/ )
   {
    &error("The upload file name is invalid.");
   }

  $uplfile = "$dir/$upname";
  if (-s $uplfile)
   {
    &error("File $upname does already exist");
   }

 } # End Verify

