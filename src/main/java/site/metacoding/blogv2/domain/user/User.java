package site.metacoding.blogv2.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.blogv2.domain.post.Post;


@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략
    private Integer id;

    @Column(unique = true, nullable = false, length = 12) // 기본이 255Byte
    private String username;

    @Column(nullable = false, length = 12)
    private String password;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false, length = 300)
    private String addr; // API 주소 라이브러리 사용하기

    @Column(nullable = true, length = 300)
    private String profileImg;
    // 이미지 업로드, 이미지 파일을 전송 받아서 해당 파일을 서버에 두고 해당 경로를 DB에 저장한다.
    
    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

}
