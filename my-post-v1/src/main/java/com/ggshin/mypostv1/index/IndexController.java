package com.ggshin.mypostv1.index;

import com.ggshin.mypostv1.member.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@NoArgsConstructor
@Controller
public class IndexController {

    private MemberService memberService;
    private MemberMapper memberMapper;

    @Autowired
    public IndexController( MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @GetMapping("/join")
    public String join() {
        return "joinForm";
    }


    @PostMapping("/join")
    public ResponseEntity join(@Valid MemberDto.Post memberPostDto) {
        System.out.println(memberPostDto);
        Member newMember = memberMapper.memberPostDtoToMember(memberPostDto);

        System.out.println(newMember);
        //MemberService가 작업을 수행하도록 함
        memberService.createMember(newMember);

        //Response로 return하기 위해 MemberResponseDto로 변환
        MemberDto.Response memberResponseDto = memberMapper.memberToMemberResponseDto(newMember);


        return new ResponseEntity<>("joined!", HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }


    //@PostMapping("/post") 만들기







}
