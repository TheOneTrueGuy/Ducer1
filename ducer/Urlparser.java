import java.net.*;
import java.util.*;

public class Urlparser{
URL url2pars;
String urlstring;
Urlparser(String sturl) {
 try {
 url2pars = new URL(sturl);
 } catch (MalformedURLException e) { return "urlexception"; }

}
public String stringy(){
urlstring = url2pars.toString(); 
}
// if  parse_request = "tags" then extract the tags,
// "data" extract for data, "text" extract for text
// "img", "sound" or "links"/"url's"
public String parse(){
StringTokenizer stoken = new StringTokenizer(urlstring, "<");




