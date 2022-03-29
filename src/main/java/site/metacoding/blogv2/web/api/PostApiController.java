package site.metacoding.blogv2.web.api;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.post.Post;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.service.PostService;
import site.metacoding.blogv2.web.api.dto.ResponseDto;
import site.metacoding.blogv2.web.api.dto.post.WriteDto;

@RequiredArgsConstructor
@RestController
public class PostApiController { // 데이터만 리턴해주는 컨트롤러 분리

    private final PostService postService; // 의존성 주입
    private final HttpSession session;

    @PostMapping("/s/post")
    public ResponseDto<?> write(@RequestBody WriteDto writeDto) {

        User princiapl = (User) session.getAttribute("princiapl"); // 세션값 얻어서
        Post post = writeDto.toEntity(princiapl); // 여기에 넣기
        
        postService.글쓰기(post); // 글쓰기 서비스 진행

        return new ResponseDto<>(1, "성공", null);
    }

}
