package org.sjsu.cmpe202;

public abstract class CCType {
    public CCType() {
        setSuccessor();
    }

    protected CCType next;

    public abstract void setSuccessor();

    public abstract CreditCard process(Record record);

    public CreditCard verifyCardAndProcess(Record record) {
        if(record.getCcNumberStr() == null) {
            record.setCcType("Invalid");
            record.setError("InvalidCardNumber");
            return null;
        } else
        if (record.getCcNumberLongStr() == null || record.getCcNumberLongStr().length() > 19) {
            record.setCcType("Invalid");
            record.setError("InvalidCardNumber");
            return null;
        } else  {
            return process(record);
        }
    }


}
