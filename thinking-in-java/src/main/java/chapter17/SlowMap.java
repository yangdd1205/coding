package chapter17;

import java.util.*;

// A Map implemented with ArrayLists.
public class SlowMap<K, V> extends AbstractMap<K, V> {
    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();

    public V put(K key, V value) {
        V oldValue = get(key);// The old value or null
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else {
            values.set(keys.indexOf(key), value);
        }
        return oldValue;
    }

    public V get(Object key) { // key is type Object. not k
        if (!keys.contains(key)) {
            return null;
        }
        return values.get(keys.indexOf(key));

    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        Iterator<K> ki = keys.iterator();
        Iterator<V> vi = values.iterator();
        while (ki.hasNext()) {
            set.add(new MapEntry<K, V>(ki.next(), vi.next()));
        }
        return set;
    }

    public static void main(String[] args) {
        SlowMap<String, String> m = new SlowMap<>();
        m.put("北京", "北京");
        m.put("上海", "上海");
        m.put("天津", "天津");
        m.put("重庆", "重庆");
        m.put("四川", "成都");
        m.put("陕西", "西安");
        m.put("云南", "昆明");
        m.put("西藏", "拉萨");
        m.put("新疆", "乌鲁木齐");
        m.put("黑龙江", "哈尔滨");
        m.put("山东", "济南");
        m.put("安徽", "合肥");
        m.put("河南", "郑州");

        System.out.println(m);
        System.out.println(m.get("四川"));
        System.out.println(m.entrySet());
    }
}/* Output:
{北京=北京, 上海=上海, 天津=天津, 重庆=重庆, 陕西=西安, 山东=济南, 黑龙江=哈尔滨, 安徽=合肥, 四川=成都, 云南=昆明, 西藏=拉萨, 河南=郑州, 新疆=乌鲁木齐}
成都
[北京=北京, 上海=上海, 天津=天津, 重庆=重庆, 陕西=西安, 山东=济南, 黑龙江=哈尔滨, 安徽=合肥, 四川=成都, 云南=昆明, 西藏=拉萨, 河南=郑州, 新疆=乌鲁木齐]
*///:~