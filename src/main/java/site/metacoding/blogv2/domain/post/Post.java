package site.metacoding.blogv2.domain.post;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.blogv2.domain.comment.Comment;
import site.metacoding.blogv2.domain.user.User;

/**
 * GET /post/1 상세보기, 인증 필요 없음
 * USER 1, POST 1, COMMENT N List<Comment>
 */


@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 300)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ColumnDefault("0") // 기본값 넣기
    @Column(nullable = false)
    private Integer pagecount; // 조회수

    // 컬럼으로 넣지 않음을 명시, 양방향 맵핑
    @OneToMany(mappedBy = "post") // 연관관계의 주인의 변수명을 넣는다.
    private List<Comment> comments; // FK가 아님

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @CreatedDate
    private LocalDateTime creaDate;

    @LastModifiedDate
    private LocalDateTime updateDate;
}
