package site.metacoding.blogv2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.post.Post;
import site.metacoding.blogv2.domain.post.PostRepository;
import site.metacoding.blogv2.service.PostService;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    // s 때문에 자동으로 인터셉터 가동
    @GetMapping("/s/post/writeForm")
    public String writeForm() {
        return "post/writeForm";
    }

    @GetMapping("/post/{id}")
    public Post detail(@PathVariable Integer id) {
        Post postEntity = postRepository.findById(id).get();
        return null;

    }

    @GetMapping({ "/", "/post" }) // post 전부를 달라는 것
    public String home() {
        return "post/list";
    }
}
