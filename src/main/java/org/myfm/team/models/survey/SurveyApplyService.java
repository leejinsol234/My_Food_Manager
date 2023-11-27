package org.myfm.team.models.survey;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.myfm.team.commons.MemberUtil;
import org.myfm.team.commons.constants.Ingredient;
import org.myfm.team.commons.exceptions.AlertException;
import org.myfm.team.entities.Result;
import org.myfm.team.entities.Survey;
import org.myfm.team.entities.SurveyItem;
import org.myfm.team.repositories.ResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class SurveyApplyService {

    private final SurveyInfoService infoService;

    private final ResultRepository resultRepository;

    private final MemberUtil memberUtil;

    /**
     *
     * @param seq 설문지 번호
     * @param answers 설문 답변
     */
    public Long apply(Long seq, List<Long> answers) {
        if (answers == null || answers.isEmpty()) {
            throw new AlertException("설문 항목에 답변을 해주세요.");
        }

        Survey survey = infoService.get(seq);
        // 설문지가 현재 미노출 상태인 경우
        if (!survey.isShow()) {
            throw new SurveyNotFoundException();
        }

        List<SurveyItem> items = survey.getItems();
        items.stream().forEach(item -> updateChecked(item, answers));
        Map<String, Integer[]> ratio = calculate(items);

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        try {
            String json = om.writeValueAsString(survey);
            Result result = Result.builder()
                    .survey(json)
                    .ca(checkNull(ratio.get("CA"), 0))
                    .fe(checkNull(ratio.get("FE"), 0))
                    .mg(checkNull(ratio.get("MG"), 0))
                    .va(checkNull(ratio.get("VA"), 0))
                    .vb1(checkNull(ratio.get("VB1"), 0))
                    .vb2(checkNull(ratio.get("VB2"), 0))
                    .vc(checkNull(ratio.get("VC"), 0))
                    .zn(checkNull(ratio.get("ZN"), 0))
                    .member(memberUtil.getMember())
                    .build();

            resultRepository.saveAndFlush(result);

            return result.getSeq();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 체크 상태 업데이트
     *
     * @param item
     * @param answers
     */
    private void updateChecked(SurveyItem item, List<Long> answers) {
        Long seq = item.getSeq();
        boolean checked = answers.stream().anyMatch(s -> s == seq);
        item.setChecked(checked);
    }

    private Map<String, Integer[]> calculate(List<SurveyItem> items) {
        Map<String, Integer[]> data = new HashMap<>();
        for (String[] item : Ingredient.getList()) {
            data.put(item[0], new Integer[]{0,0,0});
        }

        for (SurveyItem item : items) {
            String key = item.getIngredient();
            Integer[] value = Objects.requireNonNullElse(data.get(key), new Integer[] { 0, 0, 0});

            if (item.isChecked()) value[0]++; // 체크된 문항 갯수
            value[1]++; // 전체 문항 갯수
            value[2] = (int) Math.round((double)value[0] / value[1] * 100); // 선택비율
            System.out.println(Arrays.toString(value));
            data.put(key, value);
        }

        return data;
    }

    private Integer checkNull(Integer[] nums, Integer value) {
       return nums == null ? 0 : nums[2];
    }
}
