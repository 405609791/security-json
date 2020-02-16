package fun.codefarmer.securityjson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ @ClassName HelloController
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/16 17:23
 **/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello json";
    }
}
