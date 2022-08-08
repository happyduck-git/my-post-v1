package com.ggshin.mypostv1.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post postDtoToPost(PostDto.Post postDto);
    @Mapping(source = "post.lastModifiedAt", target = "modifiedAt")
    PostDto.Response postToPostResponse(Post post);
    List<PostDto.Response> postsToPostResponses(List<Post> posts);
}
