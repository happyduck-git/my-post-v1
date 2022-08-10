package com.ggshin.mypostv1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/member")
public class MemberController {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberService memberService;

    public MemberController(MemberMapper memberMapper, MemberService memberService) {
        this.memberMapper = memberMapper;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberDto.Post memberPostDto) {

        System.out.println(memberPostDto);
        //MemberPostDto로 받은 request 내용을 Member entity 형태로 변환 (MemberService에서 처리할 수 있는 형태로 변환)
        Member newMember = memberMapper.memberPostDtoToMember(memberPostDto);

        System.out.println(newMember);
        //MemberService가 작업을 수행하도록 함
        memberService.createMember(newMember);

        //Response로 return하기 위해 MemberResponseDto로 변환
        MemberDto.Response memberResponseDto = memberMapper.memberToMemberResponseDto(newMember);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.CREATED);
    }

}
