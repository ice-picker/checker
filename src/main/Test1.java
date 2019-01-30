package main;

import myUtil.Convert;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) throws DocumentException {
        Convert myTrans = new Convert();
        String XML = "<UNI_BSS_BODY>\n" +
                "  <QRY_FEE_INFO_REQ>\n" +
                "    <QUERY_TYPE>1</QUERY_TYPE>\n" +
                "    <ACCT_ID>1114112438940105</ACCT_ID>\n" +
                "    <SERVICE_CLASS_CODE>50</SERVICE_CLASS_CODE>\n" +
                "    <SERIAL_NUMBER>18515247879</SERIAL_NUMBER>\n" +
                "    <CHARGE_TYPE>1</CHARGE_TYPE>\n" +
                "    <PARA>\n" +
                "      <PARA_ID>lala</PARA_ID>\n" +
                "      <PARA_VALUE>111</PARA_VALUE>\n" +
                "    </PARA>\n" +
                "\t<PARA>\n" +
                "      <PARA_ID>sasa</PARA_ID>\n" +
                "      <PARA_VALUE>222</PARA_VALUE>\n" +
                "    </PARA>\n" +
                "  </QRY_FEE_INFO_REQ>\n" +
                "</UNI_BSS_BODY>" ;
//        try {
//            myTrans.xml2Map(XML);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        System.out.println(myTrans.getTResult().toString());

        String json = "{\n" +
                "    \"STATUS\": \"0000\",\n" +
                "    \"MSG\": \"查询成功!\",\n" +
                "    \"TXID\": \"a265^1528279648212^2584\",\n" +
                "    \"RSP\": {\n" +
                "        \"RSP_CODE\": \"0000\",\n" +
                "        \"RSP_DESC\": \"查询成功!\",\n" +
                "        \"DATA\": [\n" +
                "            {\n" +
                "                \"ACCT_MONEY_INFO\": {\n" +
                "                    \"BALANCE\": \"117412\",\n" +
                "                    \"ACCT_BALANCE\": \"84402\",\n" +
                "                    \"ACCT_CURNT_BALANCE\": \"83552\",\n" +
                "                    \"SPECIAL_FEE\": \"0\",\n" +
                "                    \"ACCT_BALANCE_UNAVAILABLE\": \"33010\",\n" +
                "                    \"PREPAY_AVAILABLE\": \"84402\",\n" +
                "                    \"PROM_AVAILABLE\": \"0\",\n" +
                "                    \"PREPAY_UNAVAILABLE\": \"3000\",\n" +
                "                    \"PROM_UNAVAILABLE\": \"30010\",\n" +
                "                    \"ACTION_TYPE_FLAG\": \"0\"\n" +
                "                },\n" +
                "                \"FEE_INFO\": [\n" +
                "                    {\n" +
                "                        \"SERVICE_CLASS_CODE\": \"50\",\n" +
                "                        \"AREA_CODE\": \"0010\",\n" +
                "                        \"SERIAL_NUMBER\": \"18515247997\",\n" +
                "                        \"USER_ID\": \"1114112427570524\",\n" +
                "                        \"ACCT_ID\": \"1114112438940105\",\n" +
                "                        \"USED_MONEY\": \"2600\",\n" +
                "                        \"CANUSE_MONEY\": \"0\",\n" +
                "                        \"DEPOSIT_NAME\": \"普通赠款\",\n" +
                "                        \"ACCT_BALANCE_ID\": \"1411241508904205\",\n" +
                "                        \"DEPOSIT_CODE\": \"26\",\n" +
                "                        \"DEPOSIT_MONEY\": \"2600\",\n" +
                "                        \"RETURN_TAG\": \"0\",\n" +
                "                        \"DEPOSIT_TYPE_CODE\": \"2\",\n" +
                "                        \"FEE_KIND\": \"1\",\n" +
                "                        \"CAN_TRAN_TAG\": \"0\",\n" +
                "                        \"PRIVATE_TAG\": \"1\",\n" +
                "                        \"VALID_TAG\": \"0\",\n" +
                "                        \"START_DATE\": \"20141201000000\",\n" +
                "                        \"END_DATE\": \"20301031235959\",\n" +
                "                        \"START_CYCLE_ID\": \"201412\",\n" +
                "                        \"END_CYCLE_ID\": \"203009\",\n" +
                "                        \"AMOUNT\": \"0\",\n" +
                "                        \"FREEZE_FEE\": \"0\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"SERVICE_CLASS_CODE\": \"50\",\n" +
                "                        \"AREA_CODE\": \"0010\",\n" +
                "                        \"SERIAL_NUMBER\": \"18515247997\",\n" +
                "                        \"USER_ID\": \"1114112427570524\",\n" +
                "                        \"ACCT_ID\": \"1114112438940105\",\n" +
                "                        \"USED_MONEY\": \"0\",\n" +
                "                        \"CANUSE_MONEY\": \"0\",\n" +
                "                        \"DEPOSIT_NAME\": \"冻结赠款\",\n" +
                "                        \"ACCT_BALANCE_ID\": \"1507021308093160\",\n" +
                "                        \"DEPOSIT_CODE\": \"3001\",\n" +
                "                        \"DEPOSIT_MONEY\": \"30010\",\n" +
                "                        \"RETURN_TAG\": \"0\",\n" +
                "                        \"DEPOSIT_TYPE_CODE\": \"3\",\n" +
                "                        \"FEE_KIND\": \"1\",\n" +
                "                        \"CAN_TRAN_TAG\": \"0\",\n" +
                "                        \"PRIVATE_TAG\": \"1\",\n" +
                "                        \"VALID_TAG\": \"0\",\n" +
                "                        \"START_DATE\": \"20150701000000\",\n" +
                "                        \"END_DATE\": \"20300131235959\",\n" +
                "                        \"START_CYCLE_ID\": \"198001\",\n" +
                "                        \"END_CYCLE_ID\": \"203001\",\n" +
                "                        \"AMOUNT\": \"0\",\n" +
                "                        \"FREEZE_FEE\": \"0\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"SERVICE_CLASS_CODE\": \"50\",\n" +
                "                        \"AREA_CODE\": \"0010\",\n" +
                "                        \"SERIAL_NUMBER\": \"18515247997\",\n" +
                "                        \"USER_ID\": \"1114112427570524\",\n" +
                "                        \"ACCT_ID\": \"1114112438940105\",\n" +
                "                        \"USED_MONEY\": \"0\",\n" +
                "                        \"CANUSE_MONEY\": \"0\",\n" +
                "                        \"DEPOSIT_NAME\": \"预存转兑现金(预存总额)\",\n" +
                "                        \"ACCT_BALANCE_ID\": \"1411241508904204\",\n" +
                "                        \"DEPOSIT_CODE\": \"3\",\n" +
                "                        \"DEPOSIT_MONEY\": \"3000\",\n" +
                "                        \"RETURN_TAG\": \"0\",\n" +
                "                        \"DEPOSIT_TYPE_CODE\": \"1\",\n" +
                "                        \"FEE_KIND\": \"1\",\n" +
                "                        \"CAN_TRAN_TAG\": \"0\",\n" +
                "                        \"PRIVATE_TAG\": \"1\",\n" +
                "                        \"VALID_TAG\": \"0\",\n" +
                "                        \"START_DATE\": \"20141201000000\",\n" +
                "                        \"END_DATE\": \"20301031235959\",\n" +
                "                        \"START_CYCLE_ID\": \"201412\",\n" +
                "                        \"END_CYCLE_ID\": \"203009\",\n" +
                "                        \"AMOUNT\": \"0\",\n" +
                "                        \"FREEZE_FEE\": \"0\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"SERVICE_CLASS_CODE\": \"50\",\n" +
                "                        \"AREA_CODE\": \"0010\",\n" +
                "                        \"SERIAL_NUMBER\": \"18515247997\",\n" +
                "                        \"USER_ID\": \"1114112427570524\",\n" +
                "                        \"ACCT_ID\": \"1114112438940105\",\n" +
                "                        \"USED_MONEY\": \"16448\",\n" +
                "                        \"CANUSE_MONEY\": \"83552\",\n" +
                "                        \"DEPOSIT_NAME\": \"普通现金\",\n" +
                "                        \"ACCT_BALANCE_ID\": \"1411241508904203\",\n" +
                "                        \"DEPOSIT_CODE\": \"0\",\n" +
                "                        \"DEPOSIT_MONEY\": \"100000\",\n" +
                "                        \"RETURN_TAG\": \"1\",\n" +
                "                        \"DEPOSIT_TYPE_CODE\": \"0\",\n" +
                "                        \"FEE_KIND\": \"0\",\n" +
                "                        \"CAN_TRAN_TAG\": \"1\",\n" +
                "                        \"PRIVATE_TAG\": \"0\",\n" +
                "                        \"VALID_TAG\": \"0\",\n" +
                "                        \"START_DATE\": \"20141124153828\",\n" +
                "                        \"END_DATE\": \"20300131235959\",\n" +
                "                        \"START_CYCLE_ID\": \"198001\",\n" +
                "                        \"END_CYCLE_ID\": \"203001\",\n" +
                "                        \"AMOUNT\": \"0\",\n" +
                "                        \"FREEZE_FEE\": \"0\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"ATTACH\": {}\n" +
                "    }\n" +
                "}" ;
        try {
            myTrans.json2Map(json.trim());

            List list = new ArrayList<String>();
            list.add("CHARGE_TYPE") ;
            myTrans.xml2Map(XML,list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(myTrans.getTResult().toString());
        System.out.println(myTrans.getWResult().toString());
        System.out.println(myTrans.getWResult().keySet().toString());
//        Object finalNode1 = myTrans.getNodeValue1("DATA",myTrans.getWResult());
//        ArrayList list  = (ArrayList) finalNode1;
//        System.out.println(list.toString());
//        Object finalNode2 = myTrans.getNodeValue1("PARA",myTrans.getTResult());
//        System.out.println(((ArrayList)finalNode2).toString());
    }
}
