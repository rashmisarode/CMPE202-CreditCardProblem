package org.sjsu.cmpe202.handler;

import org.sjsu.cmpe202.CCType;
import org.sjsu.cmpe202.CreditCard;
import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.cards.MasterCreditCard;

public class MasterCardHandler extends CCType {

    @Override
    public void setSuccessor() {
        this.next = new VisaCardHandler();
    }

    @Override
    public CreditCard process(Record record) {
        String ccNumber = record.getCcNumberLongStr();
        char second = ccNumber.charAt(1);
        int sec = Integer.parseInt(second+"");

        if ((ccNumber.charAt(0) == '5') && ((sec>=1) && (sec<=5)) && (ccNumber.length()==16)) {
            record.setCcType("MasterCard");
            return new MasterCreditCard(record);
        } else {
            return this.next.process(record);
        }
    }
}
