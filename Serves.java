package cs2030.simulator;

class Serves extends Event {
    
    //Server
    //Has time and customer
    
    private final Server server;
    
    //Constructor
    Serves(double time, Customer customer, Server server) {
        super(time,customer,4,"Serves");
        this.server = server;
                
    }

    //GetTime and GetCust and GetImportance inherited
    //Serves event will get time of customer arrival, 
    //and subtract from its own time to get time served. Needs a method for time in wait;
    //this method should be called by the Simulator when it realises its a serve event

    double getTimeInWait() { 
        return this.getTime() - this.getCust().getArrivalTime();
    }



    Server getServer() {
        return this.server;
    }
    
    @Override
    public String toString() {
        return String.format("%.3f %d serves by server %d",super.getTime(),
            super.getCust().getNum(), this.server.getNum());
    }

}
