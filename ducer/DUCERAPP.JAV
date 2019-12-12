import java.applet.*;
import java.awt.*;

public class DucerApplet extends Applet{
String stuffromcgi,stuftocgi,stuff;
int nodenumber;
boolean doneStatus;
// also run duceraction as a seperate thread.
public String appinfo = getParameter("appinfo");
TimeKeeper tkp = new TimeKeeper(this);
public String principalString, ExpOut;
public String script = "ducer.cgi";
public String host = "www.chatlink.com/~guido5";
public int port = 80;
DucerAction dact;
CGI cgi = new CGI();
DucerMeth2 dmet = new DucerMeth2(dact,"000000000");
  public void init(){
	resize(200,150);
    tkp.start();
    startcom();
    dact = new DucerAction(this,principalString,ExpOut);

    dact.start();
  }
public void start(){

}

  public void startcom(){
   stuffromcgi = cgi.Post(host,port,"start.cgi","start"+appinfo);
   parser(stuffromcgi);// parse stuff for principal, ExpOut, datain etc.
}

public String gocgi(){  
// a different cgi call for timeupkeep, update duceroutput (combine these 2
// when appropriate) and jobdone

// alternately could be script+appinfo when each user has own cgi
if (doneStatus){ script ="done.cgi";}
stuffromcgi = cgi.Post(host,port,script,appinfo + "=" + stuftocgi);
// splice out appinfo in cgi for credit count
return stuffromcgi;
 }
public void parser(String stuff){
String config = stuff.substring(0,15);
String config2 = config.substring(16,31);
int conlen = dmet.fromBinary(config);
conlen = stuff.length()*(1/conlen);
principalString = stuff.substring(32,conlen);

ExpOut = stuff.substring(conlen+1);

// later add n-bit length measures starting at 1st n bits, length
// then n more bits, length and n bits final length for ExpOut

}




} // closing applet class brace














