#!/usr/local/bin/zsh
cp chptr1.htm temp
cp chptr2.htm chptr1.htm
cp chptr3.htm chptr2.htm
cp chptr4.htm chptr3.htm
cp chptr5.htm chptr4.htm
cp chptr6.htm chptr5.htm
cp temp chptr6.htm
echo "Location:http://www.chatlink.com/~guido5/chptr6.htm"

exit 0


