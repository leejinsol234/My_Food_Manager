package org.myfm.team.controllers.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/result_list")
@RequiredArgsConstructor
public class ResultListController {

    @GetMapping
    public String resultList() {
        return "front/mypage/result_list";
    }
}
