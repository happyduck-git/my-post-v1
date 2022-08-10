package com.ggshin.mypostv1.member;

import com.ggshin.mypostv1.post.Post;


import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/* 궁금증: Builder나 Setter가 없으면 왜 Constructor가 있어도 NoArgsConstructor 형태로 mapper가 생성되는 걸까? */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String role = "ROLE_USER";

    @Column
    private LocalDateTime joinedAt = LocalDateTime.now();

    @Column
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "member")
    List<Post> postList = new ArrayList<>();

    public void addPost(Post post) {
        postList.add(post);
    }

    @Builder
    public Member(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;

    }

    @Override
    public String toString() {
        return "Member: Name: " + name + ", Password: " + password;
    }

}
