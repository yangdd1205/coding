package blog.dddd.monitor.agent;

public class Test {

    public static void main(String[] args) {

        System.out.println("完成");



        hello();


        User user = new User();
        user.setId(100L);
        user.setName("杨冬冬");


        System.out.println(user);


    }



    public static void hello(){
        System.out.println("hello");
    }
}
