package site.metacoding.blogv2.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import site.metacoding.blogv2.config.filter.MyFilter1;
import site.metacoding.blogv2.config.filter.MyFilter2;

// Controller, RestController, Repository, Service, Component, Configuration(config필터)

// class Dog {  내가 만든 게 아니면 어떻게 띄워야 할까?
//      내가 짠 게 아니면 직접 new 해주어야 한다.

// }

@Configuration // IoC 컨테이너에 띄우기
public class FilterConfig { // filter 설정 파일

    // @Bean  강제로 메서드 호출
    // public Dog a(){
    //     return new Dog();  메모리에 띄움
    // }

    @Bean // IoC 컨테이너 필터의 설정파일을 등록한다.
    public FilterRegistrationBean<?> filter1() { // IoC컨테이너에 띄워야 함
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        // 해당 객체에 필터 등록하기, IoC컨테이너에 뜨진 않았음, 메모리에는 떴음

        bean.addUrlPatterns("/*"); // /다음의 모두 설정, context path로 설정
        bean.setOrder(1); // 낮은 번호의 필터가 가장 먼저 실행된다.

        return bean;
    }

    // chain 타고 DS로 간다.

    @Bean // IoC 컨테이너 필터의 설정파일을 등록한다.
    public FilterRegistrationBean<?> filter2() { // IoC컨테이너에 띄워야 함
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        // 해당 객체에 필터 등록하기, IoC컨테이너에 뜨진 않았음, 메모리에는 떴음

        bean.addUrlPatterns("/*"); // /다음의 모두 설정, context path로 설정
        bean.setOrder(2); // 낮은 번호의 필터가 가장 먼저 실행된다.

        return bean;
    }
    
}
