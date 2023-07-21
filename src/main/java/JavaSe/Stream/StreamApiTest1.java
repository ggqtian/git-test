package JavaSe.Stream;

import JavaSe.CollectionMap.ArrlayListTest.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class StreamApiTest1 {
    public static void main(String[] args) {
      //创建stream实例
        //通过集合
        List<Student> list=new ArrayList<>();
        Stream<Student> stream = list.stream();
        System.out.println(stream);
        //通过数组
        Integer[]arr=new Integer[]{1,2,3,4,5};
        Stream<Integer> stream1 = Arrays.stream(arr);
        //通过stream的of（）方法
        Stream<String> stream2 = Stream.of("Aa", "bb", "cc");

        //转换为大写
        List<String> list1 = Arrays.asList("aa", "bb", "cc");
        list1.stream().map(str->str.toUpperCase()).forEach(System.out::println);
        list1.stream().map(String::toUpperCase).forEach(System.out::println);

        //排序
        Integer[]arr1=new Integer[]{6,1,2,3,4,5};
        Arrays.stream(arr1).sorted().forEach(System.out::println);




    }
}
