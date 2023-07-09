package org.CollectionMap.SetTest;

import org.CollectionMap.ArrlayListTest.Student;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest1 {
    public static void main(String[] args) {


         Set set = new HashSet();
        set.add("aa");
        set.add(new Student("张三",25));
        set.add(new Student("张三",25));
        set.add("bb");
        set.add(123);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();
        System.out.println(set.contains(new Student("张三",25)));
    }
}
