package main;

import myUtil.Compare;
import myUtil.TextDiff;
import myUtil.TomMap;

import java.util.*;

public class TempTest {
    public static void main(String[] args){

        String text1 = "{\"SERVICE_CLASS_CODE\":\"50\",\"AREA_CODE\":\"0010\",\"SERIAL_NUMBER\":\"18515247997\",\"USER_ID\":\"1114112427570524\",\"ACCT_ID\":\"1114112438940105\",\"USED_MONEY\":\"0\",\"CANUSE_MONEY\":\"0\",\"DEPOSIT_NAME\":\"冻结赠款\",\"ACCT_BALANCE_ID\":\"1507021308093160\",\"DEPOSIT_CODE\":\"3001\",\"DEPOSIT_MONEY\":\"30010\",\"RETURN_TAG\":\"0\",\"DEPOSIT_TYPE_CODE\":\"3\",\"FEE_KIND\":\"1\",\"CAN_TRAN_TAG\":\"0\",\"PRIVATE_TAG\":\"1\",\"VALID_TAG\":\"0\",\"START_DATE\":\"20150701000000\",\"END_DATE\":\"20300131235959\",\"START_CYCLE_ID\":\"198001\",\"END_CYCLE_ID\":\"203001\",\"AMOUNT\":\"0\",\"FREEZE_FEE\":\"0\"}";
        String text2 = "{\"SERVICE_CLASS_CODE\":\"50\",\"AREA_CODE\":\"0010\",\"SERIAL_NUMBER\":\"18515247997\",\"USER_ID\":\"1114112427570524\",\"ACCT_ID\":\"1114112438940105\",\"USED_MONEY\":\"0\",\"CANUSE_MONEY\":\"0\",\"DEPOSIT_NAME\":\"预存转兑现金(预存总额)\",\"ACCT_BALANCE_ID\":\"1411241508904204\",\"DEPOSIT_CODE\":\"3\",\"DEPOSIT_MONEY\":\"3000\",\"RETURN_TAG\":\"0\",\"DEPOSIT_TYPE_CODE\":\"1\",\"FEE_KIND\":\"1\",\"CAN_TRAN_TAG\":\"0\",\"PRIVATE_TAG\":\"1\",\"VALID_TAG\":\"0\",\"START_DATE\":\"20141201000000\",\"END_DATE\":\"20301031235959\",\"START_CYCLE_ID\":\"201412\",\"END_CYCLE_ID\":\"203009\",\"AMOUNT\":\"0\",\"FREEZE_FEE\":\"0\"}";

        String text3 = "lalal----123tt" ;
        String text4 = "7alal909012344" ;
        Compare compare = new Compare();
        List<TextDiff> lists = compare.doCompare(text3,text4);
        Iterator lala = lists.iterator();
        while (lala.hasNext()){
            System.out.println(lala.next().toString());
        }



    }
}
