package com.slark.web;

import com.slark.target.InstrumentTarget;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liqianlong
 * 2021 2021/7/15 7:49 下午
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index(){

        InstrumentTarget target = new InstrumentTarget();
        target.sayHello();
        return "index";
    }

}
