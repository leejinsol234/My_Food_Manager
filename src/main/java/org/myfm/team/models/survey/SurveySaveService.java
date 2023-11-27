package org.myfm.team.models.survey;

import lombok.RequiredArgsConstructor;
import org.myfm.team.commons.Utils;
import org.myfm.team.entities.SurveyItem;
import org.myfm.team.repositories.SurveyItemRepository;
import org.myfm.team.repositories.SurveyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



    private void removeItem(SurveyItem item) {
        item.setSurvey(null);
        SurveyItem _item = itemRepository.findById(item.getSeq()).orElse(null);
        if (_item != null) {
            itemRepository.delete(_item);
        }
        itemRepository.flush();
    }
}
