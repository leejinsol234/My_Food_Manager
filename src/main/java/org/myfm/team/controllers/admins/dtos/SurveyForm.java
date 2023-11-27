package org.myfm.team.controllers.admins.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myfm.team.entities.SurveyItem;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class SurveyForm {

    private Long seq;

    private String mode;

    // 설문 제목
    @NotBlank
    private String subject;

    // 노출 여부
    private boolean show;

    private List<Integer> qNum;

    private Map<String, List<SurveyItem>> items;

    private List<String> ingredients;
}
