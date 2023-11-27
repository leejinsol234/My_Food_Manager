package org.myfm.team.controllers.admins;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myfm.team.commons.ListData;
import org.myfm.team.commons.ScriptExceptionProcess;
import org.myfm.team.commons.constants.Ingredient;
import org.myfm.team.commons.exceptions.AlertException;
import org.myfm.team.commons.menus.Menu;
import org.myfm.team.controllers.admins.dtos.SurveyForm;
import org.myfm.team.controllers.surveys.SearchSurvey;
import org.myfm.team.entities.Survey;
import org.myfm.team.models.survey.SurveyDeleteService;
import org.myfm.team.models.survey.SurveyInfoService;
import org.myfm.team.models.survey.SurveySaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller("adminSurveyController")
@RequestMapping("/admin/survey")
@RequiredArgsConstructor
public class SurveyController implements ScriptExceptionProcess {
    private final HttpServletRequest request;

    private final SurveySaveService saveService;
    private final SurveyInfoService infoService;
    private final SurveyDeleteService deleteService;

    /**
     * 설문지 목록
     *
     * @return
     */
    @GetMapping
    private String list(@ModelAttribute SearchSurvey search, Model model) {
        commonProcess(model, "list");

        ListData<Survey> data = infoService.getList(search);

        model.addAttribute("items", data.getContent());
        model.addAttribute("pagination", data.getPagination());

        return "admin/survey/list";
    }

    @PostMapping
    private String listPs(@RequestParam(value = "seq", required = false) List<Long> seqs, Model model) {
        if (seqs == null || seqs.isEmpty()) {
            throw new AlertException("삭제할 설문지를 선택하세요.");
        }

        deleteService.delete(seqs);

        model.addAttribute("script", "parent.location.reload();");
        return "common/_execute_script";
    }

    /**
     * 설문지 등록
     * @return
     */
    @GetMapping("/add")
    private String register(@ModelAttribute SurveyForm form, Model model) {
        commonProcess(model, "add");

        return "admin/survey/add";
    }

    /**
     * 설문지 수정
     * @return
     */
    @GetMapping("/edit/{seq}")
    public String update(@PathVariable("seq") Long seq, Model model) {
        commonProcess(model, "edit");

        SurveyForm form = infoService.getForm(seq);
        model.addAttribute("surveyForm", form);

        return "admin/survey/edit";
    }

    @PostMapping("/save")
    public String save(@Valid SurveyForm form, Errors errors, Model model) {
        String mode = form.getMode();
        commonProcess(model, mode);

        if (errors.hasErrors()) {
            return "admin/survey/" + mode;
        }

        saveService.save(form);

        return "redirect:/admin/survey";
    }

    private void commonProcess(Model model, String mode) {
        mode = Objects.requireNonNullElse(mode, "list");

        String pageTitle = "설문지 목록";
        if (mode.equals("add")) pageTitle = "설문지 등록";
        else if (mode.equals("edit")) pageTitle = "설문지 수정";

        List<String> addScript = new ArrayList<>();

        if (mode.equals("add") || mode.equals("edit")) {
            addScript.add("survey/form");
            model.addAttribute("selections", Ingredient.getList());
        }

        model.addAttribute("addScript", addScript);

        model.addAttribute("menuCode", "survey");

        // 서브 메뉴 처리
        String subMenuCode = Menu.getSubMenuCode(request);
        model.addAttribute("subMenuCode", subMenuCode);

        model.addAttribute("submenus", Menu.gets("survey"));

        model.addAttribute("pageTitle", pageTitle);


    }
}
