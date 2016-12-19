package com.example.junit;

public class History {
    
    private int historyId;
    private int amt;
    private String event;
    private int total;

    public History(
            int i, int amt, String string, int total){
        this.historyId = i;
        this.amt = amt;
        this.event = string;
        this.total = total;
    }

}
