package thinking.in.spring.bean.scope.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thinking.in.spring.ioc.overview.domain.User;

@Controller
public class IndexController {
    @Autowired
    private User user; // CGLIB 代理后对象（不变的 ）

    @GetMapping("/index.html")
    public String index(Model model){
        // 搜索范围：page --> request --> session --> application(servletContext)
        model.addAttribute("user",user);
        return "index";
    }
}
