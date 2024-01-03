package org.myfm.team.controllers.surveys;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.myfm.team.commons.MemberUtil;
import org.myfm.team.commons.ScriptExceptionProcess;
import org.myfm.team.commons.Utils;
import org.myfm.team.entities.Survey;
import org.myfm.team.models.survey.ResultInfoService;
import org.myfm.team.models.survey.SurveyApplyService;
import org.myfm.team.models.survey.SurveyInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController implements ScriptExceptionProcess {
    private final Utils utils;
    private final SurveyInfoService infoService;

    private final SurveyApplyService applyService;

    private final ResultInfoService resultInfoService;

    private final HttpServletRequest request;

    private final MemberUtil memberUtil;

    @GetMapping("/{seq}")
    public String apply(@PathVariable Long seq, Model model) {
        commonProcess("apply", model);

        Survey survey = infoService.get(seq);
        model.addAttribute("survey", survey);

        return utils.tpl("survey/view");
    }

    @PostMapping("/apply")
    public String applyPs(Long seq, @RequestParam(value = "itemSeq", required = false) List<Long> answers, Model model) {

        Long resultSeq = applyService.apply(seq, answers);

        String url = request.getContextPath() + "/survey/result/" + resultSeq;
        String script = String.format("parent.location.replace('%s');", url);
        model.addAttribute("script", script);
        return "common/_execute_script";
    }

    @GetMapping("/result/{seq}")
    public String result(@PathVariable Long seq, Model model) {

        String username = memberUtil.getMember().getUserNm();

        commonProcess("result", model);

        Map<String, String[]> data = resultInfoService.getResult(seq);
        String[] ingredients = data.keySet().toArray(String[]::new);

        model.addAttribute("data", data);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("username", username);


        return utils.tpl("survey/result");

    }

    private void commonProcess(String mode, Model model) {
        String pageTitle = "설문지 작성";
        List<String> addScript = new ArrayList<>();
        if (mode.equals("apply")) {
            addScript.add("survey/form");
        } else if (mode.equals("result")) {
            pageTitle = "결과확인";
        }

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("addScript", addScript);
    }


}
