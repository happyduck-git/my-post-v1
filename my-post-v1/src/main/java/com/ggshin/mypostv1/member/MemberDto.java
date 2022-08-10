package com.ggshin.mypostv1.member;

import com.ggshin.mypostv1.validator.EmailFormat;
import com.ggshin.mypostv1.validator.Password;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


public class MemberDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @NotBlank
        private String name;

        @NotBlank
        @EmailFormat
        private String email;

        @NotBlank
        private String username;

        @NotBlank
        @Password
        private String password;

        @Override
        public String toString() {
            return "MemberPostDto: Name: " + name;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        @NotBlank
        private String name;

        @NotBlank
        @Password
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {

        private Long memberId;
        private String name;
        private String email;
        private String username;
        private LocalDateTime joinedAt;
        private LocalDateTime modifiedAt;


    }


}
