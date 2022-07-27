package com.ggshin.mypostv1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberMapper memberMapper;

    public MemberController(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    @PostMapping("/v1/member")
    public ResponseEntity postMember(MemberDto.Post memberPostDto) {

        memberPostDto.





    }

}
