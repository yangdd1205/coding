package thinking.in.spring.boot.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webmvc")
public class WebMvcDemoController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @GetMapping("/list")
    public String list() {
        return "list";
    }
}
