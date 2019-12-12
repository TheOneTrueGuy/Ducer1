#!/usr/local/bin/zsh
cp chptr2.htm temp
cp chptr4.htm chptr2.htm
cp chptr3.htm chptr4.htm
cp chptr5.htm chptr3.htm
cp chptr1.htm chptr5.htm
cp chptr6.htm chptr1.htm
cp temp chptr6.htm
echo "Location:http://www.chatlink.com/~guido5/chptr2.htm"

exit 0


