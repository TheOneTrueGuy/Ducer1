#!/usr/bin/perl
# returns expected out to requesting node

for ($t=0;$t>150;$t++){
$ra=int(rand(255));
$expout=$expout.chr($ra);
}
#  open (FILE,"> exp$");
# read FILE, expout$;
# close FILE;
use Socket;
socket(SERVER, PF_INET, SOCK_STREAM,  getprotobyname('tcp'));
bind(SERVER, sockaddr_in(2012,  INADDR_ANY));
listen(SERVER, SOMAXCONN);
accept(CLIENT, SERVER);
print CLIENT $expout;
close CLIENT;

exit(0);
