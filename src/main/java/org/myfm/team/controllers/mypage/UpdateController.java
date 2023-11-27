package org.myfm.team.controllers.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/user_update")
@RequiredArgsConstructor
public class UpdateController {

    @GetMapping
    public String update() {return "front/mypage/user_update";}
}
