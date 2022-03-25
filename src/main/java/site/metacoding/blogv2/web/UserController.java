package site.metacoding.blogv2.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    // 앱은 컨트롤러 요청 안 하지만 웹은 해야됨.
    @GetMapping("/s/user/{id}")
    public String userInfo(@PathVariable Integer id, Model model) {
        
        User userEntity = userService.회원정보(id);
        model.addAttribute("user", userEntity);

        return "user/updateForm";
    }

    // @GetMapping("/logout")
    // public String logout(){

    // session.invalidate(); 세션 무효화 세션 아이디 영역의 데이터를 모두 삭제

    // return "redirect:/"; 메인 페이지 재사용
    // }

    // 웹브라우저 회원가입 페이지 O, 앱은 X swing으로 줘야 함
    @GetMapping("/join") // 페이지를 달라
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/login") // 페이지를 달라
    public String loginForm() {

        return "user/loginForm";
    }

}
