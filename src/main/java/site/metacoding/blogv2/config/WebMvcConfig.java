package site.metacoding.blogv2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import site.metacoding.blogv2.config.intercepter.SessionIntercepter;

// @Configuration
public class WebMvcConfig implements WebMvcConfigurer { // default 인터페이스
    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 인터셉터 메모리에 띄우기
        registry.addInterceptor(new SessionIntercepter())
                .addPathPatterns("/s/**"); // 전략적으로 인증이 필요한 것만 가능함
                // 어떤 것은 별 하나, 어떤 것은 별 두개 *, **

    }

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) { // 인터셉터 메모리에 띄우기
    //     registry.addInterceptor(new SessionIntercepter())
    //             .addPathPatterns("/s/*") // 전략적으로 인증이 필요한 것만 가능함
    //             .addPathPatterns("/user/*")
    //             .excludePathPatterns("/s/post/*"); // 제외할 일 없도록 주소를 잘 만들어야...
    // }
}
