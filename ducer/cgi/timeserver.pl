#!/usr/bin/perl -Tw

# simple time server

require 5.002;
use strict;
BEGIN { $ENV{PATH} = '/usr/ucb:/bin' }
use Socket;
use Carp;
use FileHandle;

sub spawn;
sub logmsg { print "$0 $$: @_ at ", scalar localtime, "\n" }
my $port = shift || 2345;
my $proto = getprotobyname('tcp');
socket(Server, PF_INET, SOCK_STREAM, $proto) or die "socket: $!";
