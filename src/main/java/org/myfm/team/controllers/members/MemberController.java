package org.myfm.team.controllers.members;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myfm.team.commons.CommonProcess;
import org.myfm.team.commons.MemberUtil;
import org.myfm.team.commons.Utils;
import org.myfm.team.controllers.members.dtos.RequestJoin;
import org.myfm.team.models.member.MemberSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements CommonProcess {

    private final Utils utils;
    private final MemberUtil memberUtil;
    private final MemberSaveService saveService;

    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form, Model model) {
        commonProcess(model, "회원가입");

        return utils.tpl("member/join");
    }

    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors, Model model) {
        commonProcess(model, "회원가입");

        saveService.join(form, errors);

        if (errors.hasErrors()) {
            return utils.tpl("member/join");
        }

        return "redirect:/member/login";
    }


    @GetMapping("/login")
    public String login(String redirectURL, Model model) {
        commonProcess(model, "로그인");

        model.addAttribute("redirectURL", redirectURL);

        return utils.tpl("member/login");
    }
}