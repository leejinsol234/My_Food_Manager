package org.myfm.team.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 영양소 비율 및 음식 조회용 엔티티
 *
 */
@Entity
@Table(name="PUBLIC_DB")
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class PublicDB extends Base {
    @Id
    @GeneratedValue
    private Long nuNo;
    private double ca;
    private double fe;
    private double mg;
    private double va;
    private double vb1;
    private double vb2;
    private double vc;
    private double zn;

    @Column(length = 65, nullable = false)
    private String foodNm;
}
