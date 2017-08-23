package chapter17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// creating a gods hashCode()
public class CountedString {

    private static List<String> created = new ArrayList<>();
    private String s;
    private int id;

    public CountedString(String s) {
        this.s = s;
        created.add(s);
        // id is the total number of instances of this string in user by countedString
        for (String s2 : created) {
            if (s2.equals(s)) {
                id++;
            }
        }
    }

    @Override
    public String toString() {
        return "String: " + s + " id: " + id + " hashCode(): " + hashCode();
    }

    public int hashCode() {
        // The very simple approach:
        // return s.hashCode() * id
        // Using Joshua Bloch's recipe:
        int result = 17;
        result = 37 * result + s.hashCode();
        result = 37 + result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CountedString && s.equals(((CountedString) obj).s) && id == ((CountedString) obj).id;
    }

    public static void main(String[] args) {
        Map<CountedString, Integer> map = new HashMap<>();
        CountedString[] cs = new CountedString[5];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = new CountedString("hi");
            map.put(cs[i], i);
        }
        System.out.println(map);
        for (CountedString countedString : cs) {
            System.out.println("Looking up " + countedString);
            System.out.println(map.get(countedString));
        }

    }
}/* Output:
{String: hi id: 5 hashCode(): 4000=4, String: hi id: 1 hashCode(): 3996=0, String: hi id: 2 hashCode(): 3997=1, String: hi id: 3 hashCode(): 3998=2, String: hi id: 4 hashCode(): 3999=3}
Looking up String: hi id: 1 hashCode(): 3996
0
Looking up String: hi id: 2 hashCode(): 3997
1
Looking up String: hi id: 3 hashCode(): 3998
2
Looking up String: hi id: 4 hashCode(): 3999
3
Looking up String: hi id: 5 hashCode(): 4000
4
*///:~
