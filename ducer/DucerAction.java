// Copyright Guy Giesbrecht 1997
import java.util.*;
import java.lang.Math;
public class DucerAction extends Thread{
// do a lot of stuff with DucerMethods
String Primary,OutSamp,bsf,eoString,config,ExpOut;
int gen, iteration;
DucerApplet duap;
Random rando = new Random();
boolean test,precedence,mutatespecification = true;
boolean dataleft = true;
boolean override = false;
DucerMeth2 dmeth;
int mspec;
DucerAction(DucerApplet dap,String prString, String ExpO){ 
Primary = prString;
ExpOut = ExpO;
config = Primary.substring(0,31);
String principal = Primary.substring(32);

dmeth = new DucerMeth2(this,principal); 
duap = dap;
precedence = (config.substring(31)=="1");// bit #31 of config is precedence 
mspec = dmeth.fromBinary(config.substring(29,30));
}
public void run(){
do{ 
 eoString = ExpOut.substring(iteration*8,8); // 8 bits for now.
  do{
  dmeth.stringParser();
  if (precedence) {
  // dmeth.inputPatient();
   dmeth.forcedLogic();}
  // resolve operator # conflict/gen/iteration
  dmeth.ruleOperator();

  if (!precedence) {
  //  dmeth.inputPatient();
    dmeth.forcedLogic();}

  OutSamp = dmeth.outputSampler();
   
  test = dmeth.EoComparator(OutSamp, eoString);
  gen++;
  } while (test); // make sure, bsf is always better than last bsf
 gen = 0; 

bsf = dmeth.reconData(); // reconstruct a 'best so far string'
  
  duap.stuftocgi = bsf; // and send it to applet
  // ***** test for newOrderOverride at this point
  // ##### change DucerAction to match
  override = duap.neworders;
if (override){
// stufffffffffffffffffffff
xxxerrormaker
}
  iteration++;
   if ((iteration*8)>ExpOut.length()) { dataleft = false; }

// now change either patient, operator, sampler or forced logic
// and test again. If change is better than original, substitue into
// temp array. all handled in DucerMethods
 if (dataleft){
   if (!mutatespecification){
   int rnd = (int)(5 * Math.random());
   dmeth.elementChanger(rnd); // assuming 1<=rnd<=4 changes 1)operator,2)patient
                            // 3)sampler or 4)forced logic.
   } else { dmeth.elementChanger(mspec); }
 }

} while (dataleft);
bsf = dmeth.reconData();
duap.stuftocgi = bsf;
duap.doneStatus = true;
duap.gocgi();

stop();
 }

}






