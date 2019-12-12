#!/usr/bin/perl -w

# duRestart.cgi

require 'scheduler.pl';
require 'breeder.pl';
require 'password.pl';
require 'appletpage.pl';
use CGI;

$name = param("name");
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

$appinfo = $job + ','+ $config+ ','+ $data + ','+$name + ','+  $off ;

##

&appletpage($appinfo);





