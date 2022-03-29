package site.metacoding.blogv2.web.api.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.blogv2.domain.post.Post;
import site.metacoding.blogv2.domain.user.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WriteDto {
    
    private String title;
    private String content;

    // save할 때 User 오브젝트로 변경할 때 필요
    public Post toEntity(User princiapl){

        Post post = new Post(); // Post 객체 생성
        post.setTitle(title); // 글을 쓸 때 사용자에게 받은 제목과 내용 넣기
        post.setContent(content);
        post.setUser(princiapl); // 세션에 유저 받아서 글 작성자 데이터 사용
        post.setPagecount(0); // 여기에서 count 초기값 넣기
        
        return post;
    }
}
