package com.ggshin.mypostv1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Controller
@RequestMapping("/v1/member")
public class MemberController {

    private MemberMapper memberMapper;
    private MemberService memberService;

    public MemberController(MemberMapper memberMapper, MemberService memberService) {
        this.memberMapper = memberMapper;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post memberPostDto) {

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

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@Positive @PathVariable("member-id") long memberId) {
        Member member = memberService.findMember(memberId);
        //map to responseDto
        MemberDto.Response response = memberMapper.memberToMemberResponseDto(member);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        List<Member> foundMembers = memberService.findMembers();

        List<MemberDto.Response> responses = memberMapper.membersToMemberResponseDtos(foundMembers);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }


    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@Positive @PathVariable("member-id") long memberId,
                                      @RequestBody MemberDto.Patch memberPatchDto) {
        //Patch Dto 로 받은 내용을 Member entity로 변환하여 MemberService에서 처리할 수 있는 형태로 만들기
        Member reviseMember = memberMapper.memberPatchDtoToMember(memberPatchDto);
        reviseMember.setMemberId(memberId);

        //Service를 이용해서 업데이트하기
        memberService.updateMember(reviseMember);

        //map to responseDto
        MemberDto.Response response = memberMapper.memberToMemberResponseDto(reviseMember);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@Positive @PathVariable("member-id") long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
