package org.myfm.team.models.survey;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.myfm.team.commons.ListData;
import org.myfm.team.commons.Pagination;
import org.myfm.team.commons.Utils;
import org.myfm.team.controllers.admins.dtos.SurveyForm;
import org.myfm.team.controllers.surveys.SearchSurvey;
import org.myfm.team.entities.Survey;
import org.myfm.team.entities.SurveyItem;
import org.myfm.team.repositories.SurveyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class SurveyInfoService {
    private final HttpServletRequest request;
    private final SurveyRepository repository;

    /**
     * 설문지 단일 조회
     *
     * @param seq
     * @return
     */
    public Survey get(Long seq) {
        Survey survey = repository.findById(seq).orElseThrow(SurveyNotFoundException::new);

        List<SurveyItem> _items = survey.getItems();
        Map<String, List<SurveyItem>> items =  _items == null ? null : _items.stream().collect(Collectors.groupingBy(SurveyItem::getIngredient));
        List<String> ingredients = _items.stream().map(SurveyItem::getIngredient).distinct().toList();

        survey.setSurveyItems(items);
        survey.setIngredients(ingredients);


        return survey;
    }

    /**
     * 설문지 단일 조회 후 SurveyForm 커맨드 객체로 변환
     *
     * @param seq
     * @return
     */
    public SurveyForm getForm(Long seq) {
        Survey survey = repository.findById(seq).orElseThrow(SurveyNotFoundException::new);

        List<SurveyItem> _items = survey.getItems();
        Map<String, List<SurveyItem>> items =  _items == null ? null : _items.stream().collect(Collectors.groupingBy(SurveyItem::getIngredient));
        List<String> ingredients = _items.stream().map(SurveyItem::getIngredient).distinct().toList();

        SurveyForm form = SurveyForm.builder()
                .seq(survey.getSeq())
                .mode("edit")
                .subject(survey.getSubject())
                .show(survey.isShow())
                .items(items)
                .ingredients(ingredients)
                .build();

        return form;
    }


    /**
     * 설문지 목록
     *
     * @param search
     * @return
     */
    public ListData<Survey> getList(SearchSurvey search) {
        int page = Utils.getNumber(search.getPage(), 1);
        int limit = Utils.getNumber(search.getLimit(), 20);

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt")));
        Page<Survey> data = repository.findAll(pageable);

        ListData<Survey> listData = new ListData<>();
        listData.setContent(data.getContent());

        Pagination pagination = new Pagination(page, (int)data.getTotalElements(), 10, limit, request);
        listData.setPagination(pagination);

        return listData;
    }
}
