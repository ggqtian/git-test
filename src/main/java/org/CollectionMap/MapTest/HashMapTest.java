package org.CollectionMap.MapTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap hashMap=new HashMap<>();
        String s1="周杰伦";
        ArrayList songs1=new ArrayList<>();
        songs1.add("双节棍");
        songs1.add("稻香");
        songs1.add("夜曲");
        songs1.add("可爱女人");
        String s2="陈奕迅";
        List songs2= Arrays.asList("十年","红玫瑰");
        hashMap.put(s1,songs1);
        hashMap.put(s2,songs2);
        for(Object obj:hashMap.keySet()){
            System.out.println(obj+"="+hashMap.get(obj));
        }

    }
}
