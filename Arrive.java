package cs2030.simulator;

class Arrive extends Event {
    
    //Has time and customer and importance 
    //Customer (custNum ,arr Time)
    
    /*
    Creation of Arrive event creates a new customer with arrivalTime and custNum
    
    */

    //constructor
    Arrive(double arrivalTime, int custNum) {
        super(arrivalTime, new Customer(custNum,arrivalTime), 2, "Arrive");
        
    }

    
    
    

    //Methods
    
    //GetTime and GetCust and GetImportance Inherited

    //toString

    @Override
    public String toString() {
        return String.format("%.3f %d arrives",this.getTime(),
            this.getCust().getNum());
    }


}
