package org.myfm.team.controllers;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.MemberUtils;
import org.myfm.team.commons.MemberUtil;
import org.myfm.team.commons.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final Utils utils;
    private final MemberUtil memberUtil;
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("addCss", new String[] {"main"});

        if (!memberUtil.isLogin()) {
            return "redirect:/member/login";

        }

        return utils.tpl("main/index");
    }
}
