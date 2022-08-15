package com.ggshin.mypostv1.post;

import com.ggshin.mypostv1.auth.PrincipalDetails;
import com.ggshin.mypostv1.member.Member;
import com.ggshin.mypostv1.member.MemberService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@Slf4j
@Controller
@Validated
@RequestMapping("/v1/post")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final MemberService memberService;

    @Autowired
    public PostController(PostService postService, PostMapper postMapper, MemberService memberService) {
        this.postService = postService;
        this.postMapper  = postMapper;
        this.memberService = memberService;
    }

    @GetMapping
    public String post() {
        return "postForm";
    }

    @PostMapping
    public ResponseEntity postPost(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                   @Valid PostDto.Post postDto) {
        System.out.println("Post triggered");

        System.out.println(principalDetails.getUserId());

        //postDto to Post so that Service can do its job
        Post post = postMapper.postDtoToPost(postDto);

        long memberId = principalDetails.getUserId();
        System.out.println("PostController memberId: " + memberId);
        Member foundMember = memberService.findVerifiedMember(memberId);
        System.out.println("FoundMember from PostController: " + foundMember);
        post.setMember(foundMember);

        //service
        Post createdPost = postService.createPost(post);

        //post entity to response dto
        PostDto.Response response = postMapper.postToPostResponse(createdPost);

        log.info(post.getMember().getName() + " post Success");

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/{post-id}")
    public ResponseEntity getPost(@Positive @PathVariable("post-id") long postId)
    {
        Post foundPost = postService.findPost(postId);
        PostDto.Response response = postMapper.postToPostResponse(foundPost);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/member/{member-id}")
    public ResponseEntity getPosts(@Positive @PathVariable("member-id") long memberId) {
        Member foundMember = memberService.findMember(memberId);
        List<Post> postList = foundMember.getPostList();
        System.out.println(postList);

        List<PostDto.Response> responses = postMapper.postsToPostResponses(postList);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }


}
