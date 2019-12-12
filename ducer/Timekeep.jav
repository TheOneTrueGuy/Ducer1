

public class TimeKeeper extends Thread{
DucerApplet2 dap;
TimeKeeper(DucerApplet2 da){
dap = da;
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
