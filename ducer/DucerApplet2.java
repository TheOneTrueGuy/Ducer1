import java.net.*;
import java.applet.*;
import java.awt.*;
import java.util.*;
// gotta decode the cgi info,, maybe use cgiaccess?
public class DucerApplet2 extends Applet{
URL burl = getCodeBase();
String baseurl = burl.toString();
public boolean donestatus = false;
String stuffromcgi,stuftocgi,stuff,error,User,Job,Config,config;
int nodenumber;
boolean doneStatus;
// also run duceraction as a seperate thread.

public String appinfo = getParameter("appinfo");

TextField tf = new TextField("io/ca/pl/jav/ind");

public String principalString, ExpOut;
public String script = "Ducerv.cgi";
public String host = "www.budget.net/~theguy";
public int port = 80;
public DucerAction2 dact;
public CGI cgi = new CGI();
// 
public boolean neworders = false;
DucerMeth3 dmet= new DucerMeth3("000000000"); 
 public void init(){
    
  dact = new DucerAction2(principalString,ExpOut);
   }

public void start(){
tf.setText("system");
dact.start();

}

public String gocgi(){  
// stuftocgi -- post format

// alternately could be script+appinfo when each user has own cgi
if (doneStatus){ script ="done.cgi";}
stuffromcgi = cgi.Post(host,port,script,appinfo +  stuftocgi);
// splice out appinfo in cgi for credit count

if (stuffromcgi=="restart") {
neworders();
}
// later add n-bit length measures starting at 1st n bits, length
// then n more bits, length and n bits final length for ExpOut

return stuffromcgi;

}

public void parser(String stuff){
  StringTokenizer tokey = new StringTokenizer(stuff,",");
   //     "job,config,data,user,ucca string"
 try{
  Job = (String)tokey.nextElement();
  Config =(String)tokey.nextElement();
  ExpOut =(String)tokey.nextElement();
  User = (String)tokey.nextElement();
  principalString =(String)tokey.nextElement();
  // sampler forced logic,rule, patient all in pS
 } 
 catch(NoSuchElementException e){ error = "NSEE"; }
}


public void neworders(){
dact.stop();
donestatus = true;
gocgi();
donestatus = false;
init();
start();
}

} // closing applet class brace














