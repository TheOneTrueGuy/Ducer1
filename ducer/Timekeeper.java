

public class Timekeeper extends Thread{
DucerApplet dap;
Timekeeper(DucerApplet da){
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
