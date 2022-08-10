package com.ggshin.mypostv1.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/join")
    public String join() {
        return "joinForm";
    }

    @PostMapping("/join")
    public @ResponseBody
    String join(Member member) {
        member.setRole("ROLE_USER"); //처음에는 USER라는 권한 부여
        member.setPassword(passwordEncoder.encode(member.getPassword())); //password 인코딩 해서 저장 (안하면 로그인 시 에러 발생!)

        memberRepository.save(member);
        return "joined!";
    }





}
