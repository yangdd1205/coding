package chapter17;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class SpringDetector {
    public static <T extends Groundhog> void detectSpring(Class<T> type) throws Exception {

        Constructor<T> ghog = type.getConstructor(int.class);
        Map<Groundhog, Prediction> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(ghog.newInstance(i), new Prediction());
        }
        System.out.println("map = " + map);
        Groundhog gh = ghog.newInstance(3);
        System.out.println("Looking up prediction for " + gh);

        if (map.containsKey(gh)) {
            System.out.println(map.get(gh));
        } else {
            System.out.println("Key not found " + gh);
        }
    }

    public static void main(String[] args) throws Exception {
        detectSpring(Groundhog.class);
    }
} /* Output:
map = {Groundhog #4=Six more weeks of Winter!, Groundhog #5=Early Spring!, Groundhog #1=Six more weeks of Winter!, Groundhog #7=Early Spring!, Groundhog #9=Six more weeks of Winter!, Groundhog #2=Early Spring!, Groundhog #8=Six more weeks of Winter!, Groundhog #3=Early Spring!, Groundhog #0=Six more weeks of Winter!, Groundhog #6=Early Spring!}
Looking up prediction for Groundhog #3
Key not found Groundhog #3
*///:~
