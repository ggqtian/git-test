package JavaSe.Lambda;

public class LambdaTest {
    public static void main(String[] args) {

            Object obj = new String("hello,Java14");

            if(obj instanceof String){
                String str = (String) obj;
                System.out.println(str);
            }else{
                System.out.println("非 String 类型");
            }
            //举例 1：
            if(obj instanceof String str){ //新特性：省去了强制类型转换的过程
                System.out.println(str);
            }else{
                System.out.println("非 String 类型");
            }
        }


}
