package cs2030.simulator;

class Leave extends Event {
    
     
    //Has time and customer
    
    
    
    //Constructor
    Leave(double time, Customer customer) {
        super(time,customer,1,"Leave");
        
                
    }

    //GetTime and GetCust inherited


    @Override
    public String toString() {
        return String.format("%.3f %d leaves",super.getTime(),
            super.getCust().getNum());
    }

}
