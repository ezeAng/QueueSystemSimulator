package cs2030.simulator;

import java.util.Comparator;

class EventComp implements Comparator<Event> {
    
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getTime() < e2.getTime()) {
            return -1;
        } else if (e1.getTime() > e2.getTime()) {
            return 1;
        } else {
            //System.out.println("sameTime");
            if (e1.getImportance() < e2.getImportance()) {
                return 1;
            } else if (e1.getImportance() > e2.getImportance()) {
                return -1;
            } else {
                return 0;
            }
            
        }
    }

}
