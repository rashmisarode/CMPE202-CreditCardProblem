package org.sjsu.cmpe202.handler;

import org.junit.Assert;
import org.junit.Test;
import org.sjsu.cmpe202.CreditCard;
import org.sjsu.cmpe202.Record;
import org.sjsu.cmpe202.RecordBuilder;
import org.sjsu.cmpe202.cards.AmExCreditCard;

public class AmExCardHandlerTest {
    @Test
    public void testNullEntry() {

        Record record = RecordBuilder.aRecord()
                .withRecordID(1)
                .withCcHolderName("")
                .withCcNumber("")
                .withExpDate("")
                .build();
        AmExCardHandler amExCardHandler = new AmExCardHandler();

        CreditCard creditCard =  amExCardHandler.verifyCardAndProcess(record);
        Assert.assertTrue(creditCard == null);
        Assert.assertTrue(record.getError() == "InvalidCardNumber");
        Assert.assertEquals(record.getCcType(), "Invalid");
    }
    @Test
    public void testInvalidCardNumberLength() {

        Record record = RecordBuilder.aRecord()
                .withRecordID(1)
                .withCcHolderName("test")
                .withCcNumber("34100000000000")
                .withExpDate("3/20/2030")
                .build();
        AmExCardHandler amExCardHandler = new AmExCardHandler();

        CreditCard creditCard =  amExCardHandler.verifyCardAndProcess(record);
        Assert.assertTrue(creditCard == null);
        Assert.assertTrue(record.getError() == "InvalidCardNumber");
        Assert.assertEquals(record.getCcType(), "Invalid");
    }
    @Test
    public void testInvalidVisaCard() {

        Record record = RecordBuilder.aRecord()
                .withRecordID(1)
                .withCcHolderName("test")
                .withCcNumber("jkhkhkjhkjh")
                .withExpDate("")
                .build();
        AmExCardHandler amExCardHandler = new AmExCardHandler();

        CreditCard creditCard =  amExCardHandler.verifyCardAndProcess(record);
        Assert.assertTrue(creditCard == null);
        Assert.assertTrue(record.getError() == "InvalidCardNumber");
        Assert.assertEquals(record.getCcType(), "Invalid");
    }
    @Test
    public void testValidVisaCard() {

        Record record = RecordBuilder.aRecord()
                .withRecordID(1)
                .withCcHolderName("Eve")
                .withCcNumber("341000000000000")
                .withExpDate("3/20/2030")
                .build();
        AmExCardHandler amExCardHandler = new AmExCardHandler();

        CreditCard creditCard =  amExCardHandler.verifyCardAndProcess(record);
        Assert.assertTrue(creditCard.getClass() == AmExCreditCard.class);
        Assert.assertTrue(record.getError() == "None");
        Assert.assertTrue(record.getCcType() == "AmericanExpress");
    }

}
