package site.metacoding.blogv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 날짜 주시를 위한 JPA 활성화, 내부에서 시간 자동으로 넣어주는 것
@SpringBootApplication
public class BlogV2Application {

	public static void main(String[] args) {
		SpringApplication.run(BlogV2Application.class, args);
	}

}
