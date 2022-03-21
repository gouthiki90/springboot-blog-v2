package site.metacoding.blogv2.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

// interface하는 거 잊지 말기
public interface PostRepository extends JpaRepository<Post, Integer> {
    
}
