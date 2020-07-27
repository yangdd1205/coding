package thinking.in.spring.boot.webflux;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FluxDemo {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3);


        flux.map(num -> num * 5)//放大5倍
                .filter(num -> num > 10) // 只过滤出值中大于10的数
                .map(String::valueOf)//转成 String
                .publishOn(Schedulers.elastic())// 使用弹性线程池来处理数据
                .subscribe(System.out::println);//消费数据
    }
}
