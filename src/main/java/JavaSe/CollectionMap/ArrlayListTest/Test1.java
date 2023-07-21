package JavaSe.CollectionMap.ArrlayListTest;

import java.util.ArrayList;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        System.out.println("请录入学生信息");
        Scanner sc = new Scanner(System.in);
        ArrayList list = new ArrayList();
        while (true) {
            System.out.println("1:继续录入,  0:结束录入");
            int selection = sc.nextInt();
            if (selection == 0) {
                break;

            } else {
                System.out.print("请输入学生的姓名");
                String name = sc.next();
                System.out.print("请输入学生的年龄");
                int age = sc.nextInt();
                Student s = new Student(name, age);
                list.add(s);

            }

        }
        sc.close();
        System.out.println("遍历学生信息");
        for (Object obj : list) {
            System.out.println(obj.toString());
        }
    }
}
