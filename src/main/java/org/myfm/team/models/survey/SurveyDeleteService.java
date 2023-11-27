package org.myfm.team.models.survey;

import lombok.RequiredArgsConstructor;
import org.myfm.team.entities.Survey;
import org.myfm.team.repositories.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 설문지 삭제 처리
 *
 */
@Service
@RequiredArgsConstructor
public class SurveyDeleteService {
    private final SurveyRepository repository;

    public void delete(Long seq) {
        Survey survey = repository.findById(seq).orElseThrow(SurveyNotFoundException::new);

        repository.delete(survey);
        repository.flush();
    }

    public void delete(List<Long> seqs) {
        for (Long seq : seqs) {
            delete(seq);
        }
    }
}
