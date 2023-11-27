package org.myfm.team.models.survey;

import lombok.RequiredArgsConstructor;
import org.myfm.team.entities.Result;
import org.myfm.team.repositories.PublicDBRepository;
import org.myfm.team.repositories.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResultInfoService {
    private final ResultRepository resultRepository;
    private final PublicDBRepository publicDBRepository;

    private final int criteria = 50;

    /**
     * 결과에 따른 추천 음식 출력
     *
     * @param seq
     * @return
     */
    public Map<String, String[]> getResult(Long seq) {
        Result result = resultRepository.findById(seq).orElseThrow(ResultNotFoundException::new);
        List<String> ingredients = new ArrayList<>();
        if (result.getCa() >= criteria) {
            ingredients.add("CA");
        }

        if (result.getFe() >= criteria) {
            ingredients.add("FE");
        }

        if (result.getMg() >= criteria) {
            ingredients.add("MG");
        }

        if (result.getZn() >= criteria) {
            ingredients.add("ZN");
        }

        if (result.getVa() >= criteria) {
            ingredients.add("VA");
        }

        if (result.getVb1() >= criteria) {
            ingredients.add("VB1");
        }

        if (result.getVb2() >= criteria) {
            ingredients.add("VB2");
        }

        if (result.getVc() >= criteria) {
            ingredients.add("VC");
        }

        Map<String, String[]> data = publicDBRepository.getRecommendFoods(ingredients);

        return data;
    }
}
