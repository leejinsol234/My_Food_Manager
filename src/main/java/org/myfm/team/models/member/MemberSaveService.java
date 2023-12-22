package org.myfm.team.models.member;


import lombok.RequiredArgsConstructor;
import org.myfm.team.commons.constants.Gender;
import org.myfm.team.commons.constants.MemberType;
import org.myfm.team.controllers.members.dtos.RequestJoin;
import org.myfm.team.controllers.members.validators.JoinValidator;
import org.myfm.team.entities.Member;
import org.myfm.team.repositories.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class MemberSaveService {
    private final MemberRepository repository;
    private final PasswordEncoder encoder;
    private final JoinValidator validator;

    /**
     * 회원가입 처리
     * @param form
     * @param errors
     */
    public Member join(RequestJoin form, Errors errors) {
        validator.validate(form, errors);

        if (errors.hasErrors()) {
            return null;
        }

        String hash = encoder.encode(form.getPassword());

        Member member = Member.builder()
                .email(form.getEmail())
                .userNm(form.getUserNm())
                .mobile(form.getMobile())
                .mtype(MemberType.USER)
                .gender(Gender.valueOf(form.getGender()))
                .password(hash)
                .build();

        save(member);

        member = repository.findByEmail(form.getEmail()).get();
        return member;
    }

    public void save(Member member) {
        String mobile = member.getMobile();
        if (mobile != null) {
            mobile = mobile.replaceAll("\\D", "");
            member.setMobile(mobile);
        }

        repository.saveAndFlush(member);
    }

}