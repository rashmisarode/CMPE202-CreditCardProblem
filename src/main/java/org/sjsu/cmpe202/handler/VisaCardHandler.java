package org.sjsu.cmpe202.handler;

import org.sjsu.cmpe202.CCType;
import org.sjsu.cmpe202.CreditCard;
import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.cards.MasterCreditCard;
import org.sjsu.cmpe202.cards.VisaCreditCard;

public class VisaCardHandler extends CCType {
    @Override
    public void setSuccessor() {
        this.next = new AmExCardHandler();
    }

    @Override
    public CreditCard process(Record record) {
        String ccNumber = record.getCcNumberLongStr();

        if ((ccNumber.charAt(0) == '4') && (( ccNumber.length()==13) || (ccNumber.length()==16))) {
            record.setCcType("Visa");
            return new VisaCreditCard(record);
        } else {
            return this.next.process(record);
        }
    }
}
