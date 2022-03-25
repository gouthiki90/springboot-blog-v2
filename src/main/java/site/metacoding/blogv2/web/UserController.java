package site.metacoding.blogv2.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {
    
    private final UserService userService;
    

    

    // 웹브라우저 회원가입 페이지 O, 앱은 X swing으로 줘야 함
    @GetMapping("/join") // 페이지를 달라
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/login") // 페이지를 달라
    public String loginForm(){

        return "user/loginForm";
    }
}
