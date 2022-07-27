package com.ggshin.mypostv1.member;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberPostDtoToMember(MemberDto.Post memberPostDto);
    MemberDto.Response memberToMemberResponseDto(Member member);


}
