package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;


public class Simulator {

    //properties - take in arraylist
    private final PriorityQueue pq = new PriorityQueue(); 
    private final int numOfServers;

    //Constructor
    public Simulator(List<Double> arrivalTime, int numOfServers) {
        this.numOfServers = numOfServers;

        //Creates arrival events and prints
        int lengthOfArrTime = arrivalTime.size(); //Also number of customers
        for (int i = 0; i < lengthOfArrTime; i++) {
            //create Arrive event and add event to the event array
            Event eventNew = new Arrive(arrivalTime.get(i),(i + 1));
            //System.out.println(eventNew);
            this.pq.add(eventNew);
            
        }

        

    }

    //Main Simulate
    
    public void simulate() {
        List<Object> summaryStats = new ArrayList<Object>();
        double totalWaitTime = 0;       
        int numberServed = 0;
        double aveWaitTime = 0;
        
        int numberLeft = 0;

        List<Server> availServers = new ArrayList<Server>(); //List of Available Servers

        //add server - here may need to add function for adding servers
        for (int i = 0; i < this.numOfServers; i++) {
            availServers.add(new Server(i + 1,false,false,0));
        }
     
        //List<Event> arrivalEvents = new ArrayList<Event>();

        //pick first event

        while (!pq.isEmpty()) {

            Event event = pq.poll(); //gets most impt event
            double time = event.getTime();
            Customer cust = event.getCust(); // to use cust or the num?
            double serviceTime = event.getCust().getServiceTime(); 
            
            System.out.println(event);
            
            //Check what event is at the front
            if (event.getEvent().equals("Arrive")) {

                boolean attended = false; //has the event been attended to
                
                for (int i = 0; i < availServers.size(); i++) { // i represents index of Server 
                    boolean isServing = availServers.get(i).getServing();
                    boolean hasWait = availServers.get(i).getInWait();

                    if (!isServing && !hasWait) { //if entirely free
                        Server avail = availServers.get(i); //get available server
                        //add to queue a serve event 
                        pq.add(new Serves(time,cust,availServers.get(i)));
                        attended = true;
                        pq.sortEvents();
                        break;
                    } 
                } // checked through all servers and none free
                 
                if (!attended) {
                    for (int j = 0; j < availServers.size(); j++) {
                        boolean isServing1 = availServers.get(j).getServing();
                        boolean hasWait1 = availServers.get(j).getInWait();
                        //System.out.println(isServing1);
                        //System.out.println(hasWait1);

                        if (isServing1 && !hasWait1) {
                            //Server can take a wait, changer server state to wait
                            Server avail = availServers.get(j);

                            //Add to queue wait event
                            //gets current server time done passes it to Wait
                            double nextAvail = availServers.get(j).getTimeDone(); 
                            pq.add(new Wait(time,cust,availServers.get(j),nextAvail));
                            attended = true;
                            pq.sortEvents();
                            break;
                        }                       
                    }                
                } //check through all servers and none have waiting slots 

                if (!attended) {
                    //no servers available, schedule leave event
                    pq.add(new Leave(time,cust));
                    attended = true;
                }
                                
                pq.sortEvents();
                //System.out.println(pq);

            } else if (event.getEvent().equals("Serves")) {
                
                double timeDone = time + serviceTime;
                
                Serves serveEvent = (Serves) event;
                
                totalWaitTime += serveEvent.getTimeInWait(); //TotalWaitTime added
                Server currentServer = serveEvent.getServer();
                boolean isServing1 = currentServer.getServing();
                boolean hasWait1 = currentServer.getInWait();
                int indexOfServer = 0;
                for (int i = 0; i < availServers.size(); i++) {
                    int serverNum = availServers.get(i).getNum();
                    if (serverNum == currentServer.getNum()) {
                        indexOfServer = i;
                    }
                }
                
                //Change server state to serving                         
                availServers.set(indexOfServer,new Server(currentServer.getNum(),
                    true,false,timeDone));    
                
                //Schedule Done Event
                pq.add(new Done(timeDone,cust,currentServer));
                pq.sortEvents();  
              
            } else if (event.getEvent().equals("Done")) {
                //N.o served counter
                numberServed += 1;
                
                Done doneEvent = (Done) event;
                Server currentServer = doneEvent.getServer();
                int currentServerNum = doneEvent.getServer().getNum();
                boolean isServing2 = currentServer.getServing();
                boolean hasWait2 = currentServer.getInWait();
                
                int indexOfServer = 0;
                for (int i = 0; i < availServers.size(); i++) {
                    int serverNum = availServers.get(i).getNum();
                    if (serverNum == currentServerNum) {
                        indexOfServer = i;
                    }
                }                
                if (hasWait2) {
                    availServers.set(indexOfServer, new Server(currentServer.getNum(),
                        true,false,time));
                } else {
                    availServers.set(indexOfServer, new Server(currentServer.getNum(),
                        false,false,time));
                }
                pq.sortEvents();

            } else if (event.getEvent().equals("Wait")) {
                Wait waitEvent = (Wait) event;
                Server currServer = waitEvent.getServer(); //current server is serving           

                int currServerNum = currServer.getNum();
                //Change state of server to serving and wait
                
                //double timeDone = time + serviceTime;
                int indexOfServer = 0;
                for (int i = 0; i < availServers.size(); i++) {
                    int serverNum = availServers.get(i).getNum();
                    if (serverNum == currServerNum) {
                        indexOfServer = i;
                    }
                }
                
                availServers.set(indexOfServer, new Server(currServer.getNum(),
                    true,true,currServer.getTimeDone()));
                //Schedule serve event with this Server once it is done
                pq.add(new Serves(waitEvent.getNextTime(),cust,currServer));
                pq.sortEvents();
                
                

                 
            } else if (event.getEvent().equals("Leave")) {
                numberLeft += 1;
            }                                     
            
        }
        
        if (totalWaitTime > 0) {
            aveWaitTime = totalWaitTime / (double) numberServed;
        }
        summaryStats.add(aveWaitTime);
        summaryStats.add(numberServed);
        summaryStats.add(numberLeft);
        
        
        String aveWaitTimeStr = String.format("%.3f", summaryStats.get(0));
        String numberServedStr = String.format("%d", numberServed);
        String numberLeftStr = String.format("%d", numberLeft);
        System.out.println("[" + aveWaitTimeStr + " " + numberServedStr +
            " " + numberLeftStr + "]");
    } //close simulate Mtd

} //close simulator
