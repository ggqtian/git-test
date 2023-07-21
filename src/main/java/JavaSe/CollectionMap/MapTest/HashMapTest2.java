package JavaSe.CollectionMap.MapTest;

import java.util.*;

//将省份和城市的名称保存在集合中，当用户选择省份以后，二级联动，显示对应省份的地级市供用户选择
public class HashMapTest2 {

    public static void main(String[] args) {
        Set keySet=CityMap.model.keySet();
        /*  迭代器遍历set
        Iterator iterator=keySet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        */
        //增强for
        for(Object obj:keySet){
            System.out.print(obj+"\t");
            System.out.println();}
            System.out.println("请输入您想选择的省份");
            Scanner scanner=new Scanner(System.in);
            String province= scanner.next();
            String []citys= (String[]) CityMap.model.get(province);
            for(String str:citys){
                System.out.print(str+"\t");
            }
            System.out.println("");
            System.out.println("请输入您想选择的城市");
            String city=scanner.next();
            System.out.println("信息登记完毕");


    }
}
class CityMap {
    public static Map model = new HashMap();

    static {
        model.put("北京", new String[]{"北京"});
        model.put("上海", new String[]{"上海"});
        model.put("天津", new String[]{"天津"});
        model.put("重庆", new String[]{"重庆"});
        model.put("黑龙江", new String[]{"哈尔滨", "齐齐哈尔", "牡丹江", " 大庆", "伊春", "双鸭山", "绥化"});
        model.put("吉林", new String[]{"长春", "延边", "吉林", "白山", "白 城", "四平", "松原"});
        model.put("河北", new String[]{"石家庄", "张家口", "邯郸", "邢台 ", "唐山", "保定", "秦皇岛"});
    }
}