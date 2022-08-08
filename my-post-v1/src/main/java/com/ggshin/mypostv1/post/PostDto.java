package com.ggshin.mypostv1.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class PostDto {

    @Getter
    @Setter
    public static class Post {
        long memberId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        String title;
        @NotBlank(message = "내용을 작성하여 주세요!")
        String content;

        @Override
        public String toString() {
            return "Post{" +
                    "memberId=" + memberId +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    public static class Patch {

    }

    @Getter
    @Setter
//    @AllArgsConstructor
    public static class Response {

        long postId;
        String title;
        String content;
        LocalDateTime createdAt;
        LocalDateTime modifiedAt;

    }

}
