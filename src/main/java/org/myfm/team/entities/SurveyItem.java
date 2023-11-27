package org.myfm.team.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(indexes = @Index(name="idx_surveyitem_desc", columnList = "listOrder ASC, createdAt ASC"))
public class SurveyItem extends Base {
    @Id
    @GeneratedValue
    private Long seq;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    private Survey survey;

    @Column(length = 65, nullable = false)
    private String ingredient;
    @Column(length=150, nullable = false)
    private String question;
    private long listOrder;

    @Transient
    private boolean checked;
}
