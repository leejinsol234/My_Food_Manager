package org.myfm.team.models.member;

import lombok.RequiredArgsConstructor;
import org.myfm.team.entities.Member;
import org.myfm.team.repositories.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDeleteService {

    private final MemberRepository memberRepository;

    public void delete(Long userNo) {

        Member member = memberRepository.findById(userNo).orElseThrow(MemberNotFoundException::new);

        memberRepository.delete(member);
        memberRepository.flush();

    }
}
