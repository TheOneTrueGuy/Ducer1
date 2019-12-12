import java.net.*;
import java.io.*;
import java.util.*;


/**  A small prototype spider-type search index generator
<BR>
     The method's so far allow you to give a method a URL, and
    return a list of all the URL's contained in it,
    but see note below.
<BR>
      I would like to extend this to return not only the URL's of
    links, but also the title's of the pages they refer to.
<BR>
<HR WIDTH=75%>
<BR>
<B>NOTE:-</B>
      This Spider will only consider a link if it starts with 
    'http://'  this means it is likely to be off site, hopefully
     this will stop the spider from landing on one web page, and
     bogging it down.  Hmm, hopefully.

<B>Version One</B>
<HR>
<CENTER>
Taken from Steve Kemp's <A HREF="http://www.tardis.ed.ac.uk/~skx/java">Java Pages</A>
</CENTER>
*/

public class Spider
{
   /** Used for reading from the URL - to find links */
   private InputStream in;

   /** Where we start looking from */
   private String baseurl;

   /** Set this variable to 'true' to see some diagnostic output */
   public static final boolean DEBUG = false;

  /** This method created a spider object that will start searching from
      a URL, the url is specified as a full, valid URL.  This may vary
      between servers, for example some servers expect homepages, etc, to
      end with an index.html.  Others on the other hand will happily except
      something ending in a '/'

      @param base The base URL
  */
  public Spider( String base )
  { this.baseurl = base;   
  }


  /** The main method of this class, this method will loop through the
     page of the base URL, and will extract a list of links contained in
     that page.  These will be added to a private vector, and that in 
     turn will be copied into an array to return to the caller.
       This method will ignore links that are not 'alive' (i.e. broken links)
     So the index that it returns will always be 'current'.
   
     @return An array containing links on the base url page
  */
  public String[] search()
  { try
    { // Create a URL from the base
      URL base = new URL( baseurl );
      // use this URL to read from
      this.in = base.openStream();
      
      if ( DEBUG )
        System.out.println( "Starting from " + baseurl );

      // now return an array of URLs that are found in that
      // input stream, (as calculated be another method ).
      return( Search( in ) );
    }
    catch( MalformedURLException e )
    { 
      if ( DEBUG )
        System.out.println( "Invalid url " + baseurl );

      // return a null array, to signify error
      return( null );
    }
    catch( IOException e )
    { 
      if ( DEBUG )
        System.out.println( "Error opening URL " + baseurl );

      // return a null array, to signify error
      return( null );
    }
  }


  /** This method reads that input stream, and returns an array
     of strings that match the string <A HREF="     .... "
   */
  private String[] Search( InputStream input )
  { 
    Vector v = new Vector();
    
    while ( true )
    { boolean ok = skipUntil( '<' );
      if ( !ok )
         break;

      String s = getUntil( '>' );
      if ( s.length() == 0 )
         break;

      if ( s.length() >=6 && s.substring( 0, 6 ).equalsIgnoreCase( "a href" ) )
      { int from = s.indexOf( "\"" );
        if ( from >= 0 )
        { int to = s.indexOf( "\"", from + 1 );
          if ( from > 0 )
          { to = s.indexOf( "\"", from + 1 );
            if ( to > 0 )
            { s = s.substring( from + 1, to );
              if ( s.startsWith( "http://" ) )
              { v.addElement( new String( "" + s ) );
                //System.out.println( "Found " + s );
              }
            }
          }
        }
      }
    }
    String foundurls[] = new String[ v.size() ];
    v.copyInto( foundurls );
    return( foundurls );
  }


  /** Read input from the URL until a specific character is reached
      @param ch The character to look for 
      @return true if ok, false on error
   */
  private boolean skipUntil( char ch )
  { try
    { while ( true )
      { int nextch = this.in.read();
        if ( nextch == -1 )
          return false;
        else if ( (char)nextch == ch )
          return true;
      }
    }
    catch ( IOException e )
    {  return false;
    }
  }



  /** Read from the URL until a specific character is reached
      @param ch The character to look for
      @return true if ok, false on error  ( e.g. end of url )
  */
  private String getUntil( char ch )
  { String s = "";
    try
    { while ( true )
      { int nextch = this.in.read();
        if ( nextch == -1 ||  (char) nextch == ch )
          return s;
        else
          s += (char) nextch;
      }
    }
    catch( IOException e )
    { return s;
    }
  }


  /* **********************************************************************
      A simple driver program that will allow this class to be tested from
     the command line
   */
  public static void main( String args[] )
  { if ( args.length < 1 )
    { System.out.println( "Usage: spider baseurl" );
      System.exit( 1 );
    }

   Spider s = new Spider( args[ 0 ] );
   String found_urls[] = s.search();

   for ( int i = 0; i < found_urls.length; i++ )
   { System.out.println( i + "th url is " + found_urls[ i ] );
   }

  }

}










