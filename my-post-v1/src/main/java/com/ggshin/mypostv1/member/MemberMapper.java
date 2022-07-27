package com.ggshin.mypostv1.member;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") //Spring Bean으로 등록됨
public interface MemberMapper {

    Member memberPostDtoToMember(MemberDto.Post memberPostDto);
    MemberDto.Response memberToMemberResponseDto(Member member);


}
