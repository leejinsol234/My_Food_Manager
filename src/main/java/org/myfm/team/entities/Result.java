package org.myfm.team.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Result {
    @Id @GeneratedValue
    private Long seq;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="userNo")
    private Member member;

    private int ca;
    private int va;
    private int fe;

    private int mg;
    private int vb1;
    private int vb2;
    private int vc;
    private int zn;

    @Lob  // 설문지 시점 데이터 저장 필드
    @Column(length=8192)
    private String survey;
}
