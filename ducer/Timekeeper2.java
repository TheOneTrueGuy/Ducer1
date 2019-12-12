

public class Timekeeper2 extends Thread{
DucerApplet2 dap;
DucerApp duapp;
Timekeeper2(DucerApplet2 da){
dap = da;
}
Timekeeper2(DucerApp da){
duapp = da;
}


public void run(){
while (true){
try{
sleep(220000);
} catch(InterruptedException e){ }
dap.gocgi();
  }
 }// method brace
}
