package org.myfm.team.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myfm.team.commons.constants.Gender;
import org.myfm.team.commons.constants.MemberType;


@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Member extends Base {

    @Id @GeneratedValue
    private Long userNo;

    @Column(length=65, nullable = false, unique = true)
    private String email;

    @Column(length=65, nullable = false)
    private String password;

    @Column(length=30, nullable = false)
    private String userNm;

    @Column(length=11)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(length=20, nullable = false)
    private MemberType mtype = MemberType.USER;

}
