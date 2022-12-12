
package PriorityScheduling;


import java.util.Comparator;

public class Process {
                public int processID;
	public int burstTime;
	public int tempburstTime;
	public int arrivalTime;//time the proccess has come(can wait or not)
	public int priority;
	public int outtime;
	public int intime=-1;//time that the process started excution
                public int waitingTime;
                public int TurnaroundTime;

        
        public Process(){}
        
        public Process(int ID, int at, int bt, int pr){
            this.processID=ID;
            this.arrivalTime=at;
            this.burstTime=bt;
            this.priority=pr;
        }
        
                
        public void setArrivalTime(int t){
            this.arrivalTime=t;
        }

        public void setBurstTime(int t){
            this.arrivalTime=t;
        }
         public void setPriority(int p){
            this.priority=p;
        }
         public void setID(int id){
            this.processID=id;
        }
         public void setWt(int wt){
             this.waitingTime=wt;
         }
       
         public void setTAT(int tat){
             this.TurnaroundTime=tat;
         }
         
       
        public int getArrivalTime(){
            return arrivalTime;
        }
        public int getProcessID(){
            return processID;
        }
        public int getburstTime(){
            return burstTime;
        }
       public int getPriority(){
            return priority;
        }
        public int getWt(){
        return waitingTime;
        }
        public int getTAT(){
        return TurnaroundTime;
        }
        
}


