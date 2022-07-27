package com.ggshin.mypostv1.member;

import com.ggshin.mypostv1.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    private LocalDateTime joinedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "member")
    List<Post> postList = new ArrayList<>();



}
