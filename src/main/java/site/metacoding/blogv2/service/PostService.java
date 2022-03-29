package site.metacoding.blogv2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.post.Post;
import site.metacoding.blogv2.domain.post.PostRepository;

// 웹브라우저(컨트롤러 호출) - 컨트롤러(요청 받고 뷰 또는 JOSN 리턴) -
// 서비스(트랜잭션 관리) - 레포지토리(DB를 연결하는 쿼리 저장소) -
// 영속성 컨텍스트(오브젝트를 영속화 시킴) - 디비

@RequiredArgsConstructor
@Service // 기능을 넣는다.
public class PostService {
    
    private final PostRepository postRepository;

    @Transactional
    public void 글쓰기(Post post){
        postRepository.save(post); // 글 써서 save
    }
}
