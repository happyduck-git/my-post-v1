package com.ggshin.mypostv1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {


        return null;
    }

    //저장되어 있는 member는 아닌지 먼저 확인해보기
}
