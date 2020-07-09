package thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableHelloWorld
public class EnableModuleDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnableModuleDemo.class);


        context.refresh();


        CharSequence helloWorld = context.getBean(CharSequence.class);
        System.out.println(helloWorld);

        context.close();

    }
}
