package myUtil;

import java.util.*;

public class TomMap<K,V> extends TreeMap {
    //重写put方法
    @Override
    public Object put(Object key, Object value) {
        Object tem = this.get(key);
        //若map中无改key，直接加入
        if(tem == null) {
            return super.put(key, value);
        }
        //若map中已有改key且值为list，则将新值加入list返回
        if(tem.getClass().isAssignableFrom(ArrayList.class)){
            ArrayList list = (ArrayList)tem;
            list.add( value);
            return  super.put(key,list);
        }
        //否则，map中已有该key且值为Map，则将新值和旧值封装成list返回
        List<Map> list = new ArrayList<Map>();
        list.add((Map) tem);
        list.add((Map) value);
        return super.put(key,list);
    }
}
