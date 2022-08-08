package com.ggshin.mypostv1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {

        Member foundMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getName())
                .ifPresent(name -> foundMember.setName(name));
        Optional.ofNullable(member.getPassword())
                .ifPresent(pw -> foundMember.setPassword(pw));

        foundMember.setModifiedAt(LocalDateTime.now());
        return memberRepository.save(foundMember);
    }

    public Member findMember(Long memberId) {
        return findVerifiedMember(memberId);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(Long memberId) {
        Member memberToDelete = findVerifiedMember(memberId);
        memberRepository.delete(memberToDelete);
    }



    //저장되어 있는 member는 아닌지 먼저 확인해보기

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member foundMember = optionalMember.orElseThrow(() ->
                new RuntimeException("등록된 회원이 아닙니다."));
        return foundMember;
    }
}
