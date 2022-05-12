package cs2030.simulator;

class Customer {

    //Properties

    private final int custNum;
    private final double serviceTime = 1.0;
    //arrivaltime
    private final double arrivalTime;

    //Constructor

    Customer(int custNum, double arrivalTime) {
        
        this.custNum = custNum;
        this.arrivalTime = arrivalTime;        

    }

    //Methods

    int getNum() {
        return this.custNum;
    }
    
    double getServiceTime() {
        return this.serviceTime;
    }

    double getArrivalTime() {
        return this.arrivalTime;
    }

    //Done event will get the arrival time of this customer and Subtract from it the current time.

    //Override to string
    
    @Override
    public String toString() {
        return String.format("%d",this.custNum);
    }
    
    


}
