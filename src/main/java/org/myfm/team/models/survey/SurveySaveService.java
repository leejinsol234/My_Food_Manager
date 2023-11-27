package org.myfm.team.models.survey;

import lombok.RequiredArgsConstructor;
import org.myfm.team.commons.Utils;
import org.myfm.team.controllers.admins.dtos.SurveyForm;
import org.myfm.team.entities.Survey;
import org.myfm.team.entities.SurveyItem;
import org.myfm.team.repositories.SurveyItemRepository;
import org.myfm.team.repositories.SurveyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 설문지 저장 처리
 *
 */
@Service
@Transactional
@RequiredArgsConstructor
public class SurveySaveService {
    private final SurveyRepository repository;
    private final SurveyItemRepository itemRepository;

    private final Utils utils;

    public void save(SurveyForm form) {
        Survey survey = null;
        Long seq = form.getSeq();
        if (seq != null) {
            survey = repository.findById(seq).orElseThrow(SurveyNotFoundException::new);

            /* 수정인 경우 기 입력된 설문 문항 삭제 후 업데이트 */
            survey.getItems().forEach(this::removeItem);
            itemRepository.flush();

        } else {
            survey = new Survey();
        }

        survey.setSubject(form.getSubject());
        survey.setShow(form.isShow());

        repository.saveAndFlush(survey);

        List<SurveyItem> items = new ArrayList<>();
        List<Integer> qNums = form.getQNum();
        long num = 0L;
        if (qNums != null && !qNums.isEmpty()) {
            for (int qNum : qNums) {
                String ingredient = utils.getParam("ingredient_" + qNum);
                String[] questions = utils.getParams("question_" + qNum);
                for (String question : questions) {
                    SurveyItem item = SurveyItem.builder()
                            .ingredient(ingredient)
                            .question(question)
                            .survey(survey)
                            .listOrder(num++)
                            .build();
                    items.add(item);
                }
            }

            itemRepository.saveAllAndFlush(items);
        }
    }

    private void removeItem(SurveyItem item) {
        item.setSurvey(null);
        SurveyItem _item = itemRepository.findById(item.getSeq()).orElse(null);
        if (_item != null) {
            itemRepository.delete(_item);
        }
        itemRepository.flush();
    }
}
