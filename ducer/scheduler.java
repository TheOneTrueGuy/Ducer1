Scheduler.java




//
// This is the scheduler for the 4 processes in this simulation.
// Processes are scheduled in a round-robin way based on the
// currently running process faulting on a page (whence the next
// process (numerically) is scheduled. The page for which any
// process faults is stored in an array (corresponding to the
// location for that process). Then, the process() method is
// called with the particular array location value for the process
// being scheduled. The default value (if no page fault) is -1.
//
//  Author: Joyjeet Bhowmik, Avijit Chakraborty.
//
//  URL: http://earth.usc.edu/~bhowmik/vmsim/index.html#Source
//
import java.lang.*;
import java.awt.*;

public class Scheduler extends SimulatorDisplayUtils implements Runnable {
  private static int PageFaultTbl[] = new int[4];
  private static int pid;
  public static Thread schdThread;
  
  static MMU  theMMU;

  public Scheduler() {
    int i;

    for (i=0; i < 4; i++ )
      PageFaultTbl[i] = -1;
    pid = 0;
    theMMU = new MMU( 32, 16, 4 );
  }

  public void start() {
    if ( schdThread == null ) {
      schdThread = new Thread( this );
      schdThread.start();
    }
    schdThread.resume();
    //System.out.println( "schedular.start called" );
  }

  public void run() {
      pid = 0;
      setLastRunningProc( pid );
      setRunningProc( pid );
      while( schdThread != null ) {

	setPageState( false );
        theCanvas.updateDisplay();
	PageFaultTbl[pid] = Process( PageFaultTbl[pid] );
        setLastRunningProc( pid );
	pid = ( pid + 1 ) % 4;
	setRunningProc( pid );

	try { Thread.sleep( 1000 ); }
	catch( InterruptedException e ) {
	}
      }
      schdThread = null;

  }

  public void stop() {
    //System.out.println( "schedular.stop called" );
    if ( schdThread != null )
      schdThread.suspend();
  }
  public void resetSim() {
    //System.out.println( "schedular.resetSim called" );
    if ( schdThread != null )
      schdThread.stop();
    schdThread = null;
    VMS.restartSim();
  }

  //
  // This method generates a random page frame number and
  // requests for that page from the memory management unit.
  // If there is a page fault, it returns the page frame
  // number which faulted, else it just continues generating
  // requests for random page frame numbers.
  //
  public static int Process( int PageFrame ) {
    boolean status;
    boolean PageFault = false; 
    boolean FaultedLastTime = false;
    int physicalPageFrame;

    gimmePageNum = PageFrame;

    // Check if there was a page fault last time.
    if ( gimmePageNum != -1 )
      FaultedLastTime = true;

    // This loops runs till there is a pagefault
    while ( !PageFault && schdThread != null ) {

      if ( !FaultedLastTime ) { // If there was no page fault last time

        // If there was no page fault in the process history
        // then generate a random page frame number.
        gimmePageNum = Scheduler.RandomNumGenerate( 32 );
	//System.out.println( "Gimme " + gimmePageNum );
        //setVAddr( gimmePageNum );

        // Generate a request for the page from the memory manager
        status = theMMU.virtualToPhysical( gimmePageNum );


        // If the requested page is not in memory
        if ( !status ) {
          PageFault = true;
	  //System.out.println( "Oops ! Page Fault for " + gimmePageNum );
	  setPageState( false );
          return( gimmePageNum );
        }

      } else {

        // Get the last faulted page from disk
        theMMU.swapIn( gimmePageNum ); 

	//System.out.println( "Page "+gimmePageNum + " swapped in from disk" );

	// Set the boolean flag for faulted page to be fetched to false
	FaultedLastTime = false;
      }
      try { Thread.sleep( 500 ); }
      catch( InterruptedException e ) {
        }

    }
    return( 0 );
  }

  public static int RandomNumGenerate( int Range ) {
    double tmpNum;
    int    randomNum;
   
    tmpNum = Math.random();
    randomNum = (int)(tmpNum*1000.0);
    return( randomNum % Range );
  }
}





