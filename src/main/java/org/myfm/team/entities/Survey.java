package org.myfm.team.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Survey extends BaseMember {

    @Id @GeneratedValue
    private Long seq;

    // 설문 주제
    @Column(length=150, nullable = false)
    private String subject;

    // 노출 여부
    private boolean show;

    @ToString.Exclude
    @OneToMany(mappedBy = "survey", fetch=FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<SurveyItem> items;

    @Transient
    @ToString.Exclude
    private List<String> ingredients;

    @Transient
    @ToString.Exclude
    private Map<String, List<SurveyItem>> surveyItems;
}
