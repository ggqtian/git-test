package JavaSe.CollectionMap.ListTest;

public class person {
    String name;
    int age;

    public person() {
    }

    public person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String
    toString() {
        return "person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
