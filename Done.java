package cs2030.simulator;

class Done extends Event {
    
    //Done has Server
    //Has time and customer
    
    private final Server server;
    
        
    //Constructor
    Done(double time, Customer customer, Server server) {
        super(time,customer,5,"Done");
        this.server = server;
                
    }

    //GetTime and GetCust inherited

    //

    Server getServer() {
        return this.server;
    }


    
    
    
    @Override
    public String toString() {
        return String.format("%.3f %d done serving by server %d",super.getTime(),
            super.getCust().getNum(), this.server.getNum());
    }

}
