package myUtil;

import java.io.IOException;
import java.util.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Convert {
    private Map<String,Object> WResult ;
    private Map<String,Object> TResult ;

    public Map<String, Object> getWResult() {
        return WResult;
    }

    public void setWResult(Map<String, Object> WResult) {
        this.WResult = WResult;
    }

    public Map<String, Object> getTResult() {
        return TResult;
    }

    public void setTResult(Map<String, Object> TResult) {
        this.TResult = TResult;
    }



    public Convert(Map<String,Object> WResult, Map<String,Object> TResult){
        this.WResult = WResult;
        this.TResult = TResult;
    }

    public Convert(){
        this.WResult = new TomMap<String,Object>() ;
        this.TResult = new TomMap<String,Object>() ;
    }

    //XML格式字符串转map
    public void xml2Map(String xml) throws DocumentException{
        Document mydoc = DocumentHelper.parseText(xml.trim());
        Element root = mydoc.getRootElement();
        this.TResult.clear();
        this.TResult.put(root.getName(),parseElement(root));
    }

    //XML格式字符串转map
    public void xml2Map(String xml,List nodes) throws DocumentException{
        Document mydoc = DocumentHelper.parseText(xml.trim());
        Element root = mydoc.getRootElement();
        this.TResult.clear();
        this.TResult.put(root.getName(),parseElement(root,nodes));
    }

    //XML节点处理
    private Object parseElement(Element root){
        Iterator<Element> rootItor = root.elementIterator();
        Map<String ,Object> result = new TomMap<String,Object>();
        if(!rootItor.hasNext()){
            return root.getData();
        }
        while (rootItor.hasNext())
        {
            Element tepElem = rootItor.next();
            result.put(tepElem.getName(),parseElement(tepElem)) ;
        }
        return result ;
    }

    //特殊XML节点处理
    private  Object parseElement(Element root,List<String> node){
        Iterator<Element> rootItor = root.elementIterator();
        Map<String ,Object> result = new TomMap<String,Object>();
        if(!rootItor.hasNext()){
            return root.getData();
        }
        //分支标识（是否找到该节点）
        boolean flag = false;
        while (rootItor.hasNext())
        {
            flag = false;
            Element tepElem = rootItor.next();
            for(int i = 0;i<node.size();i++){
                if(node.get(i).equals(tepElem.getName())){
                    flag = true ;
                    List list = new ArrayList<>();
                    list.add( parseElement(tepElem,node)) ;
                    result.put(tepElem.getName(),list);
                }
            }
            if(!flag) {
                result.put(tepElem.getName(), parseElement(tepElem, node));
            }
        }
        return result ;
    }

    //json转map
    public void json2Map(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //注意转为有序map
        Map map = mapper.readValue(json,TreeMap.class);
        this.WResult = map;
    }

    //根据节点名比较两个map(flag = 0 以XML为基准，1 以json为基准)
    public void commparaWithNode(String webNode,String tuxedoNode,boolean flag) throws Exception {
        //传入节点名为空 表示全量比较
        Object newT = getNodeValue1(tuxedoNode,this.TResult);
        Object newW = getNodeValue1(webNode,this.WResult);

        if(newT == null)
            throw new Exception("未找到对应的xml报文节点信息");
        if(newW == null)
            throw new Exception("未找到对应的json报文节点信息");
        //若返回map
        if (newT.getClass().isAssignableFrom(TomMap.class))
        {
            //进行两个map的比较
        }
        else {
            //进行list的比较
        }
    }

    public String comparaMapOrList(Object fox1,Object fox2){
        return "";
    }

    //获取map树中某个节点的值（近似深度优先遍历，待优化）---未找到节点返回null
    public Object getNodeValue1(String nodeName,Map map){
        Iterator keys = map.keySet().iterator();
        if(!keys.hasNext())
            return null;
        for(;keys.hasNext();){
            String key = (String) keys.next();
            if(nodeName.equals(key))
                return map.get(nodeName);
            //若为String值，则表示已遍历到底层，进入下一次循环
            if (map.get(key).getClass().isAssignableFrom(String.class))
                continue;
            //list处理
            if(map.get(key).getClass().isAssignableFrom(ArrayList.class)){
                ArrayList<Map> list = (ArrayList<Map>) map.get(key);
                Map tempMap = list.get(0);
                if(tempMap.get(nodeName)!=null)
                    return list;
                //该list不是要找的，跳过
                break;
            }
            Map temMap = (Map) map.get(key);
            return getNodeValue1(nodeName,temMap);
        }
        return null ;
    }

    //获取map树中的某个节点的值（广度优先遍历）  未找到节点返回null
    private Object getNodeValue2(String nodeName,Map map){

        return null;
    }
}