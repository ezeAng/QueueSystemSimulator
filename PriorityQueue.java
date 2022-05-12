package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

class PriorityQueue {
    
    protected final ArrayList<Event> events = new ArrayList<Event>();
    
    //Methods
    void add(Event e) {
        this.events.add(e);
    }

    void add(int index, Event e) {
        this.events.add(index,e);
    }

    void sortEvents() {
        events.sort(new EventComp());
        
    }

    boolean isEmpty() {
        return this.events.isEmpty();
    }

    Event poll() {       
        sortEvents();
        
        if (!this.isEmpty()) {
            Event eventOut = this.events.get(0);
            events.remove(0);
            return eventOut;
        } else {
            return null;
        }
    }

    Event getEvent(int index) {
        return this.events.get(index);
    }

    int getLength() {
        return this.events.size();
    }
    
    //
    
    @Override 
    public String toString() {
        String output = "";
        for (Event event: this.events) {
            output += event.toString() + "\n";
        }
        return output;
    }


        

    
    


}
