package JavaSe.CollectionMap.ListTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class methodTest {
    public static void main(String[] args) {
        //    add(Object obj)
        List list = new ArrayList<>();
        list.add("AA");
        list.add(123);
        list.add(new person("Tom", 23));
        list.add("BB");
        System.out.println(list.toString());

/*
        //add(int Index,Object ele)
        list.add(2, "cc");
        System.out.println(list.toString());


        // addAll(Collection coll)
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
        System.out.println(list.toString());
        list.addAll(1,list1);
        System.out.println(list.toString());

        //将list看成整体插入
        list.add(1,list1);
        System.out.println(list.toString());


        //删除索引
        list.remove(1);
        System.out.println(list.toString());

        //删除元素
        list.remove(Integer.valueOf(2));
        System.out.println(list.toString());
        list.remove(Integer.valueOf(2));
        System.out.println(list.toString());
        list.remove(Integer.valueOf(2));
        System.out.println(list.toString());
        
 */

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        for (Object obj:
             list) {
            System.out.println(obj);
            
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }


    }
}
