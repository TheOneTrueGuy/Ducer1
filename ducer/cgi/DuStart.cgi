#!/usr/local/bin/perl
require 'scheduler.pl';
require 'breeder.pl';
require 'password.pl';
require 'appletpage.pl';
require CGI;

## ducer start program
## constructs an <APPLET> page from users login and a new DucerApp,
## execution string, and required data (or a pointer to data?)
$name = param("name");
$pass = param("pass");
if (&password($name, $pass)){
## check the schedule and get a job name

@df = &scheduler();
$job = $df[0];
$config = $df[1];
$data = $df[2];
$user = $df[3];
$config = &config($config); # convert file to appropriate form
## breed a new program string for DucerApp and DucerAction, refer to batch
## job for configuration
$off = &breed($job);



$appinfo = $job + ','+ $config+ ','+ $data + ','+$user + ','+  $off ;

##

&appletpage($appinfo);

}
else{
print "hey you whacko! wrong name or password\n\n";
print "click on BACK and try again\n";
}
exit(0);

