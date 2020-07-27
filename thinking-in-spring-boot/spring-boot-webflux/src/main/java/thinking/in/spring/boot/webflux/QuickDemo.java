package thinking.in.spring.boot.webflux;

import reactor.core.publisher.Flux;

public class QuickDemo {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        flux.subscribe(System.out::println);
        flux.subscribe(System.err::println);
    }
}
