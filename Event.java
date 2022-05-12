package cs2030.simulator;

class Event {

    //Properties
    private final double time;
    private final Customer customer;
    private final int imp;
    private final String eventName;

    //Constructor
    Event(double time, Customer customer, int imp, String eventName) {
        this.time = time;
        this.customer = customer; 
        this.imp = imp;
        this.eventName = eventName;
    }
    
    //Methods
    
    double getTime() {
        return this.time;
    }

    Customer getCust() {
        return this.customer;
    }

    int getImportance() {
        return this.imp;
    }

    String getEvent() {
        return this.eventName;
    }


}
