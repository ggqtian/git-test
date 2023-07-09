package org.CollectionMap.MapTest;

import java.util.*;

public class Exercise {
    public static void main(String[] args) {
        String []nums={"3", "4", "5", "6", "7", "8", "9", "10", "J","Q", "K", "A", "2"};
        String []color={"方片", "梅花", "红桃", "黑桃"};
        HashMap map=new HashMap();
        ArrayList list=new ArrayList();
        int index=0;
        for(String s1:nums){
            for (String s2: color) {
                map.put(index,s2.concat(s1));
                list.add(index);
                index++;
            }
        }
        map.put(index,"小王");
        list.add(index);
        map.put(index,"大王");
        list.add(index);
        //洗牌
        Collections.shuffle(list);

        TreeSet tom=new TreeSet();
        TreeSet jerry=new TreeSet();
        TreeSet me=new TreeSet();
        TreeSet lastCards=new TreeSet();
        for (int i = 0; i < list.size(); i++) {
            if (i >= list.size() - 3) {
            lastCards.add(list.get(i));
            } else if (i%3==0) {
                tom.add(list.get(i));
            }
            else if (i%3==1) {
                jerry.add(list.get(i));
            }
            else {
                me.add(list.get(i));
            }
        }
        lookPoker("Tom", tom, map);
        lookPoker("Jerry", jerry, map);
        lookPoker("康师傅", me, map);
        lookPoker("底牌", lastCards, map);
    }
    public static void lookPoker(String name,TreeSet ts,HashMap map){
        System.out.println(name+"看的牌是");
        for (Object index:ts){
            System.out.print(map.get(index)+" ");
        }
        System.out.println();
    }
}
