package JavaSe.CollectionMap.HandlerTest;

public class Director extends Handler {
    @Override
    public void handle(int num) {
        if(num<=10000){
            System.out.println("主任处理了"+num+"的购物单");
        }
        else{
            this.next.handle(num);
        }
    }
}
