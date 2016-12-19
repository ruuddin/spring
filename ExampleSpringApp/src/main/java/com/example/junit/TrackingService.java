package com.example.junit;

import java.util.ArrayList;
import java.util.List;

public class TrackingService {

    public class InvalidInputException extends RuntimeException {
        public InvalidInputException(
                String string){
            super(string);
        }

        private static final long serialVersionUID = -8870981051715329047L;
    }

    private int total;
    private int goal;
    private List<History> history = new ArrayList<>();
    private int historyId = 0;
    
    public void addProtein(int amt){
        if(amt < 0)
            throw new InvalidInputException("Input protein amount is invalid");
        
        total += amt;
        history.add(new History(historyId++, amt, "add", total));
    }
    
    public void removeProtein(int amt){
        total -= amt;
        if(total < 0)
            total = 0;
        
        history.add(new History(historyId++, amt, "remove", total));
    }
    
    public int getTotal(){
        return total;
    }
    
}
