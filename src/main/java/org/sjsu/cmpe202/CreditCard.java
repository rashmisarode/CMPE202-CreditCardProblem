package org.sjsu.cmpe202;

public abstract class CreditCard {
    //CCType ccType;
    Record record;
    public CreditCard(Record record) {
       // this.ccType = ccType;
        this.record = record;
    }
}
