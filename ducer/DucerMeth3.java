// Copyright Guy Giesbrecht 1997
import java.lang.Math;
import java.util.*;
public class DucerMeth3 {
// all methods to construct ducer 
// except cgi methods 
// 
public String principalString, Nsample,rtype;
public Random rando = new Random();
public int hours,mins,secs,nabors,status,sampleN,samplesize,floglen,flogtype;
int length1,length2,length3,ruletype,op_offset,flog_offset, offset =0;
int iteration =0;
int iter =0;
public boolean timecheck,eocom,ruleopout,samptype;
public boolean patient[] = new boolean[512];
public boolean temppat[] = new boolean[512];
public boolean forceLogic[] = new boolean[512];
public boolean operator[]; // = new boolean[512];
public boolean ruleWord[];

// input and expected output should come from DucerAction
DucerMeth3(String principal){

principalString = principal;
}
public void stringParser(){
// chop principal after each
// use proportional numbers at begin of string, prechop optype,nabors
// sample length and forced logic length: op 3b,nabors 3b, sampl 3b, fl 2b*2
// parses the 7 different strings: 
// sampleN,nabors,rule type, fl control, operator,patient, forced logic.

// *****************************************************
// sample length
String samp = principalString.substring(0,3);
sampleN= fromBinary(samp);
principalString = principalString.substring(4);
// neighborhood size: nabors
String nab = principalString.substring(0,4);
nabors = fromBinary(nab);
principalString= principalString.substring(5);
// rule types
String types = principalString.substring(0,11);
principalString = principalString.substring(12);
// forced logic control type
String flog = principalString.substring(0,4);
floglen = fromBinary(flog);
flogtype = fromBinary(principalString.substring(5,7));
principalString = principalString.substring(8);

// now extract 2 ratio numbers, 9 bits each
String ratnum1 = principalString.substring(0,8);
  principalString = principalString.substring(9);
String ratnum2 = principalString.substring(0,8);
  principalString = principalString.substring(9);
double rat1 = 1.0/(fromBinary(ratnum1)+1.1);
double rat2 = 1.0/(fromBinary(ratnum2)+2);
 int stlen = (int)(principalString.length()*rat1);
 String opString = principalString.substring(0,stlen);
operator = new boolean[stlen];
operator = toArray(opString);
// ** op offset ????????
principalString = principalString.substring(stlen+1);
 stlen =(int)(principalString.length()*rat2);
 String patString = principalString.substring(0,stlen);
 length2 = patString.length();
patient = new boolean[stlen];  
patient = toArray(patString);

 String flString= principalString.substring(stlen+1);
 forceLogic = new boolean[flString.length()];
 forceLogic = toArray(flString);

 }// parser method brace



public void ruleOperator(){
int n, n2;
// operates rules on patient, returns boolean for this bit
// requires neighbor() counter
temppat = patient;
int nk = 0; // op_offset;
iter = iter +nabors;
   for (int i=0;i<length2;i++){
       n = neighbors(i);
       n2 = n/2;
      
      switch (ruletype) {
      case 1:
        temppat[i] = (n2 * 2 == n);
      break;
      case 2:
      if (operator[iteration+op_offset]) { temppat[i] = (n2 * 2 ==n); }
      else { temppat[i] = (n2 * 2 != n); }
      break;
      case 3:
      temppat[i] = operator[n];
      break;
      case 4:
      if (nk>length1) {nk = 0;} else {}
      temppat[i] = operator[nk+n];
      break;
      case 5:
      offset = iter;
      temppat[i] = operator[n + offset];
      case 6:
      temppat[i] = operator[n+nk];
      break;
      case 7:
      temppat[i] = locality1(n, i);
      break;
      case 8:
        temppat[i] = locality2(n, i);
        break;
      
    } // close switch brace      
  if (ruletype ==6) {nk = nk+nabors;} else {nk++;}
  } // for brace
} // method brace

// fix this
public String outputSampler(){
String sample ="";
int sampndiv;
// sample patient string (1 of x where x = sampledef, 3 or 4 bits)
// returns 8 bits, adds any remaining bits to next sample
  for (int osl = 0;osl<length2;osl=osl+sampleN){
 
    // get bit sampleN every osl times
      if (temppat[osl]) { sample = sample +"1";}
      else {sample = sample+"0";}
   }// for brace os
return sample;
} // os method brace


public boolean EoComparator(String Exout, String outsample){
boolean eocom = false;
// compares outputsample to expected output, returns true if equal
// also counts number of step taken properly
// to see if new changes are better.
if (Exout == outsample){ eocom = true;} else {eocom = false;}
return eocom;
}

public void  forcedLogic(){

// uses patient string andforcedLogic bits (1 of x)
// to produce a new patient for next cycle of operations
// similar to inputPoints
int patndiv,fk=0;
 for (int pl = 0; pl<length2;pl++){
  patndiv = pl/floglen;
  if (pl == (patndiv*floglen)){
  temppat[pl] = forceLogic[fk];
   fk++;
  }
 }
}

public void elementChanger(int n){
int rnd;
// makes changes to initial string. outputs new patient string
// for use with EoComparator as an evolutionary component

switch (n){
 case 1: 
  rnd = rando.nextInt();
  while (rnd>length1){ rnd = rnd/10;}
  operator[rnd] = !operator[rnd];
 break;
 case 2:
  rnd = rando.nextInt();
  while (rnd>length2){ rnd = rnd/10;}
  temppat[rnd] = !temppat[rnd];
 case 3:
  rnd = rando.nextInt();
  while (rnd>length3){ rnd = rnd/10;}
  forceLogic[rnd] = ! forceLogic[rnd];
 break;
 case 4:
  Nsample ="";
  for (int sl = 0;sl<3;sl++){
  rnd = rando.nextInt();
  int rnddiv = rnd/2;
    if (rnd == (rnddiv*2)) {Nsample = Nsample +"0"; }
    else {Nsample = Nsample +"1"; }
  }
   sampleN = fromBinary(Nsample);
 break;
 }
 
}

public void inputPatient(String instring){
// uses 1 of x bit in patient string as input sights
// returns new temppat array.
}


public int neighbors(int sight){ 
// count neighbors, uses nabors variable for how many to check
// returns int number_neighbors_on
int botmhaf,tophaf,ks =0;
int nnon = 0;
boolean isiton = false;
tophaf = (nabors/2); botmhaf = -1 * tophaf;
  for (int i=botmhaf;i<tophaf;i++){
   if (i < 0 ) { ks = 512 + i; }
    else if (i>512) { ks = i - 512; }
   else { ks = i; }
    if (ks != sight) { isiton = temppat[ks]; }
    else {}
    if (isiton) { nnon++; } else {}
  }// forloop brace
return nnon;

}

public int fromBinary(String convert){
int converted;
String bt;
double dcon = 0;
 for (int i=0;i<convert.length();i++){
  bt = convert.substring(i,1); // use convert.substring(i,i)?
  if (bt == "1") { dcon = dcon + Math.pow(2.0,(double)i); } else { }
 }
converted = new Integer((int)dcon).intValue();
return converted;
}

public boolean[] toArray(String subject){
int lengthA = subject.length();
boolean retArray[] = new boolean[lengthA];

 for (int lc=0;lc<length1;lc++){
  if (principalString.substring(lc,1) =="1") {operator[lc] = true;} 
  else {operator[lc] = false;}
 }
return retArray;
}
public int intFromArray(int offsat,boolean arr[]){
double dcon = 0.00;
 for (int i=0;i<offsat;i++){
  if (arr[i]) { dcon = dcon + Math.pow(2.0,(double)i); } else { }
 }
return (int)dcon;
}

public String reconData(){
String data="";
int t;
// operator,patient,forceLogic,sampleN
  for (t = 0;t<length1;t++){
   if (operator[t]) {data = data +"1";} else {data = data + "0";}
  }
  for (t=0;t<length2;t++) {
   if (patient[t]) {data = data +"1";} else {data = data +"0"; }
  }
  for (t=0;t<length3;t++){
   if (forceLogic[t]) {data = data +"1";} else {data = data +"0";}
  }
  data = data + Nsample; 

return data;
}

public boolean locality1(int n,int i){
// extracts locality rule for cell neighbors at location i
int naborhood = i/nabors; // get which naborhood this index belongs to 
int nh = naborhood;
while (nh>operator.length) { nh = nh - operator.length; }
if (nh<0) {nh = -1 * nh; } 
boolean locality = operator[nh]; //with overlap catching
locality = locality ^ temppat[i];
return locality;
}


public boolean locality2(int n, int i){
// extracts another locality rule from neighbors,n, and index i
int naborhood = i/nabors; // get which naborhood this index belongs to 
int nh = naborhood;
while (nh>operator.length) { nh = nh - operator.length; }
if (nh<0) {nh = -1 * nh; } 
boolean locality = operator[nh]; //with overlap catching
locality = locality ^ temppat[i];
locality = ! locality;
return locality;

}



}























