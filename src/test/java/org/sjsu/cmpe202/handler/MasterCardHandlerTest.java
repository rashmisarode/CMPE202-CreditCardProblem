package org.sjsu.cmpe202.handler;

import org.junit.Assert;
import org.junit.Test;
import org.sjsu.cmpe202.CreditCard;
import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.RecordBuilder;
import org.sjsu.cmpe202.cards.MasterCreditCard;

public class MasterCardHandlerTest {

    @Test
    public void testNullEntry() {

        Record record = RecordBuilder.aRecord()
                .withRecordID(1)
                .withCcHolderName("")
                .withCcNumber("")
                .withExpDate("")
                .build();
        MasterCardHandler masterCardHandler = new MasterCardHandler();

        CreditCard creditCard =  masterCardHandler.verifyCardAndProcess(record);
        Assert.assertTrue(creditCard == null);
        Assert.assertTrue(record.getError() == "InvalidCardNumber");
        Assert.assertEquals(record.getCcType(), "Invalid");
    }
    @Test
    public void testInvalidCardNumberLength() {

        Record record = RecordBuilder.aRecord()
                .withRecordID(1)
                .withCcHolderName("test")
                .withCcNumber("541000000000000")
                .withExpDate("3/20/2030")
                .build();

        MasterCardHandler masterCardHandler = new MasterCardHandler();

        CreditCard creditCard =  masterCardHandler.verifyCardAndProcess(record);
        Assert.assertEquals(record.getExpDateObj().toString(), "Wed Mar 20 00:00:00 PDT 2030");
        Assert.assertTrue(creditCard == null);
        Assert.assertTrue(record.getError() == "InvalidCardNumber");
        Assert.assertEquals(record.getCcType(), "Invalid");
    }
    @Test
    public void testInvalidMasterCard() {

        Record record = RecordBuilder.aRecord()
                .withRecordID(1)
                .withCcHolderName("test")
                .withCcNumber("jkhkhkjhkjh")
                .withExpDate("")
                .build();
        MasterCardHandler masterCardHandler = new MasterCardHandler();

        CreditCard creditCard =  masterCardHandler.verifyCardAndProcess(record);
        Assert.assertTrue(creditCard == null);
        Assert.assertTrue(record.getError() == "InvalidCardNumber");
        Assert.assertEquals(record.getCcType(), "Invalid");
        //Assert.assertTrue(creditCard.getClass() == MasterCreditCard.class);
    }
    @Test
    public void testValidMasterCard() {

        Record record = RecordBuilder.aRecord()
                .withRecordID(1)
                .withCcHolderName("Alice")
                .withCcNumber("5410000000000000")
                .withExpDate("3/20/2030")
                .build();
        MasterCardHandler masterCardHandler = new MasterCardHandler();

        CreditCard creditCard =  masterCardHandler.verifyCardAndProcess(record);
        Assert.assertTrue(creditCard.getClass() == MasterCreditCard.class);
        Assert.assertTrue(record.getError() == "None");
        Assert.assertTrue(record.getCcType() == "MasterCard");
    }


}
