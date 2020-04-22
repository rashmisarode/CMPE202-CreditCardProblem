package org.sjsu.cmpe202.handler;

import org.sjsu.cmpe202.CCType;
import org.sjsu.cmpe202.CreditCard;
import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.cards.DiscoverCreditCard;
import org.sjsu.cmpe202.cards.MasterCreditCard;

public class DiscoverCardHandler extends CCType {
    @Override
    public void setSuccessor() {

    }

    @Override
    public CreditCard process(Record record) {
        String ccNumber = record.getCcNumberStr();

        if (ccNumber.startsWith("6011") && ccNumber.length() == 16) {
            record.setCcType("Discover");
            return new DiscoverCreditCard(record);
        } else {
            record.setCcType("Invalid");
            record.setError("InvalidCardNumber");
            return null;
        }
    }
}
