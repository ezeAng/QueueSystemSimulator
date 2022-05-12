package cs2030.simulator;

class Server {

    //Properties

    private final int serverNum;
    private final boolean isServing;
    private final boolean hasInWait;
    private final double timeDone;

    //Constructor

    Server(int serverNum, boolean serving, boolean inWait, double timeDone) {
        this.serverNum = serverNum;
        this.isServing = serving;
        this.hasInWait = inWait;
        this.timeDone = timeDone;        
        
    }

    //Methods

    int getNum() {
        return this.serverNum;
    }

    boolean getServing() {
        return this.isServing;
    }

    boolean getInWait() {
        return this.hasInWait;
    }

    double getTimeDone() {
        return this.timeDone;
    }

    //Override to string
    
    @Override
    public String toString() {
        return String.format("%d",this.serverNum);
    }
    
  

}
