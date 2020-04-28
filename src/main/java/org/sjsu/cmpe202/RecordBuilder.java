package org.sjsu.cmpe202;

public final class RecordBuilder {
    Integer recordID;
    String ccNumber;
    String expDate;
    String ccHolderName;

    private RecordBuilder() {
    }

    public static RecordBuilder aRecord() {
        return new RecordBuilder();
    }

    public RecordBuilder withRecordID(Integer recordID) {
        this.recordID = recordID;
        return this;
    }

    public RecordBuilder withCcNumber(String ccNumber) {
        if(ccNumber!=null) {
            this.ccNumber = ccNumber.trim();
        }
        return this;
    }

    public RecordBuilder withExpDate(String expDate) {
        this.expDate = expDate;
        return this;
    }

    public RecordBuilder withCcHolderName(String ccHolderName) {
        this.ccHolderName = ccHolderName;
        return this;
    }

    public Record build() {
        return new Record(recordID, ccNumber, expDate, ccHolderName);
    }
}
