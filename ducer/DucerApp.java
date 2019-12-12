import java.net.*;
import java.applet.*;
import java.awt.*;
import java.util.*;
// gotta decode the cgi info,, maybe use cgiaccess?
public class DucerApp{
// URL burl = getCodeBase();
String baseurl = "www.budget.net/~theguy";
public boolean donestatus = false;
String stuffromcgi,stuftocgi,stuff,error,User,Job,Config,config;
int nodenumber;
boolean doneStatus;
// also run duceraction as a seperate thread.
public String appinfo ="appinfo.2001";

static TextField tf = new TextField("io/ca/pl/jav/ind");

public static String principalString, ExpOut;
public String script = "Ducerv.cgi";
public String host = "www.budget.net/~theguy";
public int port = 80;
public static DucerAction2 dact;
public static CGI cgi = new CGI();
public boolean neworders = false;
DucerMeth3 dmet= new DucerMeth3("000000000");

public static void main(String args[]){
 
 dact = new DucerAction2(principalString,ExpOut);

 tf.setText("system");
 dact.start();

} 
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















