package org.myfm.team.controllers.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/user_delete")
@RequiredArgsConstructor
public class DeleteController {

    @GetMapping
    public String delete() {
        return "front/mypage/user_delete";
    }


}
