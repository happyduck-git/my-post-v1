package com.ggshin.mypostv1.post;

import com.ggshin.mypostv1.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    MemberService memberService;

    public Post createPost(Post post) {
        memberService.findVerifiedMember(post.getMember().getMemberId());
        return postRepository.save(post);
    }

    public Post findPost(long postId) {
        Post foundPost = findVerifiedPost(postId);

        return foundPost;
    }

    public void deletePost(long postId) {

        Post foundPost = findVerifiedPost(postId);

        postRepository.delete(foundPost);
    }


    public Post findVerifiedPost(long postId) {
        Post foundPost = postRepository.findById(postId).orElseThrow(() ->
                new RuntimeException("Post with postId " + postId + " not found!"));

        return foundPost;
    }
}
