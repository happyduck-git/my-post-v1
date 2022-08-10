package com.ggshin.mypostv1.member;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") //Spring Bean으로 등록됨
public interface MemberMapper {

    Member memberPostDtoToMember(MemberDto.Post memberPostDto);

    Member memberPatchDtoToMember(MemberDto.Patch memberPatchDto);

    MemberDto.Response memberToMemberResponseDto(Member member);

    List<MemberDto.Response> membersToMemberResponseDtos(List<Member> members);


}
