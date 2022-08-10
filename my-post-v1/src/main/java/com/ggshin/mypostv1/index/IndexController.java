package com.ggshin.mypostv1.index;

import com.ggshin.mypostv1.member.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/*postman branch*/
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

//    @PostMapping("/join")
//    public @ResponseBody
//    String join(Member member) {
//        member.setRole("ROLE_USER"); //처음에는 USER라는 권한 부여
//        member.setPassword(passwordEncoder.encode(member.getPassword())); //password 인코딩 해서 저장 (안하면 로그인 시 에러 발생!)
//
//        memberRepository.save(member);
//        return "joined!";
//    }

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

    @GetMapping("/post")
    public String post() {
        return "postForm";
    }

    //@PostMapping("/post") 만들기







}
