package JavaSe.CollectionMap.MapTest;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap map = new LinkedHashMap<>();
        map.put("张三", 10000.0);
        map.put("王五", 20000.0);
        //替换
        map.put("张三", 12000.0);
        map.put("李四", 10000.0);
        String name = null;
        Double salary = null;
        map.put(name, salary);
        /*通过entry的set属性遍历
        Set set = map.entrySet();
        for (Object obj:
             set) {
            Map.Entry entry= (Map.Entry) obj;
            System.out.println(obj);
        }
        */
        Set set = map.keySet();
        for (Object obj:
             set) {
            System.out.println(obj+"="+map.get(obj));
        }



    }
}