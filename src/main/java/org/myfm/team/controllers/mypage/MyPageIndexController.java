package org.myfm.team.controllers.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageIndexController {

    @GetMapping
    public String index() {
        return "front/mypage/index";
    }

}
