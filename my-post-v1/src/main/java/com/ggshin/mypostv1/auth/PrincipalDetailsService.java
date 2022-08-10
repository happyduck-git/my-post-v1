package com.ggshin.mypostv1.auth;

import com.ggshin.mypostv1.member.Member;
import com.ggshin.mypostv1.member.MemberMapper;
import com.ggshin.mypostv1.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    private MemberRepository memberRepository;


    @Autowired
    public PrincipalDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);

        if(member != null) {

            return new PrincipalDetails(member);
        }
        return null;
    }
}
