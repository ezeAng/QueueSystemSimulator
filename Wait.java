package cs2030.simulator;

class Wait extends Event {
    
    //Server 
    //Has time and customer
    
    private final Server server;
    private final double nextAvailTime;
    
    //Constructor
    Wait(double time, Customer customer, Server server,double nextAvail) {
        super(time,customer,3,"Wait");
        this.server = server;
        this.nextAvailTime = nextAvail;
                
    }

    //GetTime and GetCust inherited
    
    double getNextTime() {
        return this.nextAvailTime;
    }

    Server getServer() {
        return this.server;
    }

    
    @Override
    public String toString() {
        return String.format("%.3f %d waits at server %d",super.getTime(),
            super.getCust().getNum(), this.server.getNum());
    }

} 
