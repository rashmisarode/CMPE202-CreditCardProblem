package org.sjsu.cmpe202.handler;

import org.sjsu.cmpe202.CCType;
import org.sjsu.cmpe202.CreditCard;
import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.cards.AmExCreditCard;
import org.sjsu.cmpe202.cards.MasterCreditCard;

public class AmExCardHandler extends CCType {
    @Override
    public void setSuccessor() {
        this.next = new DiscoverCardHandler();
    }

    @Override
    public CreditCard process(Record record) {
        String ccNumber = record.getCcNumberStr();
        char second = ccNumber.charAt(1);

        if ((ccNumber.charAt(0) == '3') && ( (second=='4') || (second=='7')) && (ccNumber.length()==15)) {
            record.setCcType("AmericanExpress");
            return new AmExCreditCard(record);
        } else {
            this.next.process(record);
        }
        return null;
    }
}
