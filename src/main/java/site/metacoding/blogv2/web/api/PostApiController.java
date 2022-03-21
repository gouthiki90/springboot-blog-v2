package site.metacoding.blogv2.web.api;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.service.PostService;

@RequiredArgsConstructor
@RestController
public class PostApiController { // 데이터만 리턴해주는 컨트롤러 분리
    
    private final PostService postService; // 의존성 주입
}
