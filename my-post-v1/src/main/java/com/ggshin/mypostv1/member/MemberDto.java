package com.ggshin.mypostv1.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


public class MemberDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        private String name;
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String username;
        @NotBlank
//        @Pattern(regexp = "") //비밀번호는 특수문자, 숫자, 알파벳 대,소문자를 각각 최소 한개씩 포함한 8~16자리여야 합니다.
        private String password;

        @Override
        public String toString() {
            return "MemberPostDto: Name: " + name;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Response {

        private Long memberId;
        private String name;
        private String email;
        private String username;
        private LocalDateTime createAt;


    }


}
