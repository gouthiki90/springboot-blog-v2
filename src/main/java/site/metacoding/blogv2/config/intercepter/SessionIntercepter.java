package site.metacoding.blogv2.config.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import site.metacoding.blogv2.domain.user.User;

public class SessionIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    // 컨트롤러 진입 직전에 실행된다.
            throws Exception {
        System.out.println("preHandle 호출됨");

        HttpSession session = request.getSession();

        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            throw new RuntimeException("인증되지 않았습니다."); // 에러 핸들러에 맡겨버리기. false로 하면 아무것도 하지 못하기 때문이다.
        } else {
            return true; // boolean 타입
        }
    }

}
