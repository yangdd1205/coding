package chapter17;

import java.util.*;

// A demonstration hashed map
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
    // Choose a prime number for the hash table size,to achieve a uniform distribution:
    static final int SIZE = 997;

    // You can't have a physical array of generics,but you can upcast to one
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        MapEntry<K, V> pair = new MapEntry<>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair);//Replace old with new
                found = true;
                break;
            }
        }
        if (!found) {
            buckets[index].add(pair);
        }
        return oldValue;
    }

    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            return null;
        }
        for (MapEntry<K, V> iPair : buckets[index]) {
            if (iPair.getKey().equals(key)) {
                return iPair.getValue();
            }
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            bucket.forEach(me -> {
                set.add(me);
            });
        }
        return set;
    }

    public static void main(String[] args) {
        SimpleHashMap<String, String> m = new SimpleHashMap<>();
        m.put("北京", "北京");
        m.put("上海", "上海");
        m.put("天津", "天津");
        m.put("重庆", "重庆");
        m.put("四川", "成都市");
        m.put("四川", "成都");
        m.put("陕西", "西安");
        m.put("云南", "昆明");
        m.put("西藏", "拉萨");
        m.put("新疆", "乌鲁木齐");
        m.put("黑龙江", "哈尔滨");
        m.put("山东", "济南");
        m.put("安徽", "合肥");
        m.put("河南", "郑州");
        m.put("山西", "太原");
        m.put("吉林", "长春");
        m.put("辽林", "沈阳");
        m.put("内蒙古", "呼和浩特");
        m.put("河北", "石家庄");

        System.out.println(m);
        System.out.println(m.get("重庆"));
        System.out.println(m.entrySet());

    }
}/* Output:
{上海=上海, 内蒙古=呼和浩特, 北京=北京, 天津=天津, 重庆=重庆, 陕西=西安, 山东=济南, 黑龙江=哈尔滨, 河北=石家庄, 安徽=合肥, 四川=成都, 云南=昆明, 辽林=沈阳, 吉林=长春, 西藏=拉萨, 河南=郑州, 新疆=乌鲁木齐, 山西=太原}
重庆
[上海=上海, 内蒙古=呼和浩特, 北京=北京, 天津=天津, 重庆=重庆, 陕西=西安, 山东=济南, 黑龙江=哈尔滨, 河北=石家庄, 安徽=合肥, 四川=成都, 云南=昆明, 辽林=沈阳, 吉林=长春, 西藏=拉萨, 河南=郑州, 新疆=乌鲁木齐, 山西=太原]
*///:~
