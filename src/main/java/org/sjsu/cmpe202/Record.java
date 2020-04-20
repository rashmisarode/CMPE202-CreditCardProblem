package org.sjsu.cmpe202;

import java.math.BigDecimal;

public class Record {
   Integer recordID;
   String ccNumberStr;
   String expDate;
   String ccHolderName;

   Long ccNumberLong;

   CCType ccType = null;

   String error = "None";

   public Record(Integer recordID, String ccNumberStr, String expDate, String ccHolderName) {
      this.recordID = recordID;
      this.ccNumberStr = ccNumberStr;
      this.expDate = expDate;
      this.ccHolderName = ccHolderName;
      try{
         this.ccNumberLong = new BigDecimal(ccNumberStr).longValue();
      }
      catch(Exception e){
         setError("InvalidCardNumber");
      }


   }

   public Integer getRecordID() {
      return recordID;
   }

   public String getCcNumberStr() {
      return ccNumberStr;
   }

   public String getExpDate() {
      return expDate;
   }

   public String getCcHolderName() {
      return ccHolderName;
   }

   public CCType getCcType() {
      return ccType;
   }

   public void setCcType(CCType ccType) {
      this.ccType = ccType;
   }

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }

   @Override
   public String toString() {
      return "Record{" +
              "recordID=" + recordID +
              ", ccNumber=" + ccNumberStr +
              ", expDate='" + expDate + '\'' +
              ", ccHolderName='" + ccHolderName + '\'' +
              '}';
   }
}
